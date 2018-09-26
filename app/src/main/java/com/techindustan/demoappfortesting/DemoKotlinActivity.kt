package com.techindustan.demoappfortesting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class DemoKotlinActivity : AppCompatActivity() {


    val alList = intArrayOf(0, 1, 1)
    val alMixed = arrayOf(0, 1, 1, "hello", false)
    val alSecond = arrayListOf<String>()
    var alL = ArrayList<String>()

    var alS = mutableListOf<String>()
    var al = listOf<String>("abc", "dembbb")
    var a = 0
    var b = 20
    var c = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_kotlin)

        a = 40

        c = if (a > b) a else b
        Log.e("maxxxx", " Max Value sid :  $c")




        a = 2
        b = 9

        var max = if (a > b) {
            Log.e("aghshagdja", "jhdskajd")

            a

        }

        else if(b>c)

        {
            b
        }
        else {

            c

        }

        c = when (a) {
            1 -> {

                10
            }
            2 -> 50

            in 1..30 -> {
                40
            }

            else -> {
                30
            }
        }








        Log.e("maxxxx", " Max Value sid :  ${abc(1)}")

        for (index in alMixed.indices) {
            Log.e("dataa", alMixed[index].toString())
        }

        for ((index, value) in alMixed.withIndex()) {

        }


    }


    fun abc(x: Int) = when {

        x == 1 -> print("x is 1")
        else -> {
            print("x is 2")
        }
    }


}

class ABC {
    constructor(a: Int) {

    }

}

