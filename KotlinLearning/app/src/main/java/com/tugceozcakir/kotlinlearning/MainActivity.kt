package com.tugceozcakir.kotlinlearning

import android.annotation.SuppressLint
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var i = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        //Void/Unit
        mySum(20, 5)

        //Return
        val result = myMultiply(20, 4)
        textView.text = "Result: ${result}"
    }
    @SuppressLint("SetTextI18n")
    fun mySum (x:Int, y:Int){
        textView.text = "Result ${x+y}"
    }
    fun myMultiply (x:Int, y:Int) : Int {
        return x*y
    }
    fun helloKotlin (view : View) {
        i++
        if (i % 2 == 0)
        {
            textView.text = "Hello Kotlin"
        }else{
            textView.text = "adsdsad"
        }


    }

}



