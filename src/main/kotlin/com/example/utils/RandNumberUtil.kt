package com.example.utils

import kotlin.random.Random

fun getRandomNumber(): String {
    var random = ""
    for (i in 1..4) {
        random += Random.nextInt(0, 9).toString()
    }
    return random
}
