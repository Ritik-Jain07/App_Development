package com.example.finalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalapp.fragments.*
import com.example.finalapp.model.DataModel
import com.example.finalapp.model.NewsModel
import com.example.finalapp.retrofit.ApiClient
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var  drawer: DrawerLayout
    var dataList = ArrayList<NewsModel>()
    private val KEY = "163d19643270bc5175c96f6475eda9f8"
    private lateinit var customAdapter:CustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        val navigationView:NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle =  ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        drawer.addDrawerListener(toggle)

        toggle.syncState()

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, GeneralFragment()).commit()

//        setupUI()
//        getData()


    }

    override fun onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }


//    private fun getData(){
//
//        val call = ApiClient.getClient.getNews(KEY,"en","in")
//        call.enqueue(object :retrofit2.Callback<DataModel>{
//            override fun onFailure(call: Call<DataModel>, t: Throwable) {
//                Log.i("Failure","${call}")
//                Toast.makeText(this@MainActivity,
//                        "there is some error while getting data",
//                        Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
//                dataList.addAll(response.body()?.data?:ArrayList())
//                Log.i("MainActivity", "data is: ${dataList}\n\n")
//                recyclerView.adapter!!.notifyDataSetChanged()
//            }
//
//        })
//    }


//    private fun setupUI(){
//        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
//        recyclerView.layoutManager = linearLayoutManager
//
//        customAdapter = CustomAdapter(this, dataList)
//        recyclerView.adapter= this.customAdapter
//    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.getItemId()){
            (R.id.nav_general) -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, GeneralFragment()).commit()
            (R.id.nav_business) -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, BusinessFragment()).commit()
            (R.id.nav_health) -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HealthFragment()).commit()
            (R.id.nav_entertainment) -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, EntertainmentFragment()).commit()
            (R.id.nav_science) -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ScienceFragment()).commit()
            (R.id.nav_sports) -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SportsFragment()).commit()
            (R.id.nav_technology) -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, TechnologyFragment()).commit()
            (R.id.nav_saved) -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SavedFragment()).commit()
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

}