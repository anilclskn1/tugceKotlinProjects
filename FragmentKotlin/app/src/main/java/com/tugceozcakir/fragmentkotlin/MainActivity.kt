package com.tugceozcakir.fragmentkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun firstFragment(view:View){
        val fragmentManager = supportFragmentManager
        val fragmentTrancastion =fragmentManager.beginTransaction()

        val firstFragment = Fragment()
        fragmentTrancastion.replace(R.id.frameLayout,firstFragment).commit()
    }
    fun secondFragment(view: View){
        val fragmentManager = supportFragmentManager
        val fragmentTrancastion = fragmentManager.beginTransaction()

        val secondFragment = Fragment2()
        fragmentTrancastion.replace(R.id.frameLayout, secondFragment).commit()

    }
}