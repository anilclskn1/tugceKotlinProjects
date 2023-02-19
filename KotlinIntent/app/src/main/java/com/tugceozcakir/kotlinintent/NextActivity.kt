package com.tugceozcakir.kotlinintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_next.*

class NextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        //getIntent
        val intent = getIntent()
       val username =  intent.getStringExtra("username")
       val name = intent.getStringExtra("name")

        userNameTextNextActivity.text = "Your Username: $username"
        nameTextNextActivity.text = "Your Name: $name"
    }

}