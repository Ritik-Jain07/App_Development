package com.example.Coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Run this main() to see the output
 */
fun main() {
    GlobalScope.launch { // launch a new coroutine in background and continue
        printFibonacciSeries(10) // print after delay
    }
    println("Fibonacci series of 5 is:") // main thread continues while coroutine is delayed
    Thread.sleep(10000) // block main thread for 10 seconds to keep JVM alive
}

suspend fun printFibonacciSeries(endRange: Int) {
        var t1 = 0; var t2 = 1; var nextTerm = 0;
        for (i in 1..endRange) {
            print(" $t1")
            nextTerm = t1 + t2;
            t1 = t2;
            t2 = nextTerm;
            delay(300)
        }
}