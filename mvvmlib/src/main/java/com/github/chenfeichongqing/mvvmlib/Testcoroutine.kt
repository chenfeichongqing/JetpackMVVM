package com.github.chenfeichongqing.mvvmlib

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    test1()
}


fun test1() {
    runBlocking {
        val job1 = launch {
            repeat(1000) { i ->
                println("test1 sleep" + i)
                delay(500)
            }
        }
        delay(1300)
        job1.cancelAndJoin()
    }
}

