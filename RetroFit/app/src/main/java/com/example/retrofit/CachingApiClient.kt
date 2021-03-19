package com.example.retrofit

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object CachingApiClient {

    private const val TAG = "APIClient"
    private const val HEADER_CACHE_CONTROL = "Cache-Control"
    private const val HEADER_PRAGMA = "Pragma"
    private const val cacheSize = (5 * 1024 * 1024).toLong()
    private const val BASE_URL =
        "https://storage.googleapis.com/network-security-conf-codelab.appspot.com/"
    val getClient: CachingApiInterface
        /**
         * @return : Client
         */
        get() {

            return retrofit().create(CachingApiInterface::class.java)
        }

    /**
     * Prepare and Return Retrofit
     */

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     *  Prepare and return OkHTTPClient
     */
    private fun okHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .cache(cache())
            .addInterceptor(httpLoggingInterceptor())
            .addInterceptor(offlineInterceptor())
            .addNetworkInterceptor(networkInterceptor())
            .build()
    }

    /**
     * Interceptor will be called f network is avaailable or not
     */

    private fun offlineInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()

            // prevent caching when network is on. For that we use the "networkInterceptor"
            if (!CachingApplication.hasNetwork()) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()

                request = request.newBuilder()
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .removeHeader(HEADER_PRAGMA)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }


    private fun networkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())

            val cacheControl = CacheControl.Builder()
                .maxAge(5, TimeUnit.SECONDS)
                .build()

            response.newBuilder()
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .removeHeader(HEADER_PRAGMA)
                .build()
        }
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor { message -> Log.d(TAG, "log: http log: $message") }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    /**
     * Prepare and return cache
     */

    private fun cache(): Cache {
        return Cache(File(CachingApplication.instance?.cacheDir, "someIdentifier"), cacheSize)

    }
}