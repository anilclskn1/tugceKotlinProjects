package com.tugceozcakir.kotlinalertdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Toast.makeText(applicationContext, "Welcome", Toast.LENGTH_SHORT).show()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun save(view: View){
        val alert = android.app.AlertDialog.Builder(this)
        alert.setTitle("Save")
        alert.setPositiveButton("Yes"){dialog, which ->
            Toast.makeText(applicationContext, "Saved", Toast.LENGTH_SHORT).show()
        }
        alert.setNegativeButton("No"){dialog, which ->
            Toast.makeText(applicationContext, "Not Saved", Toast.LENGTH_SHORT).show()
        }
        alert.show()
    }
}