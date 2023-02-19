package com.tugceozcakir.sql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    try{
        val myDatabase = openOrCreateDatabase("Musicians", MODE_PRIVATE, null)
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY, name VARCHAR, age INT)")
        myDatabase.execSQL("INSERT INTO musicians(name, age) VALUES('Cookie', 0)")

        var cursor = myDatabase.rawQuery("SELECT * FROM musicians", null)
        //cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE  name LIKE '%e'", null)
        myDatabase.execSQL("UPDATE musicians SET age = 1 WHERE name = 'Pant'")
        myDatabase.execSQL("UPDATE musicians SET age = 0 WHERE name = 'Cookie'")
        myDatabase.execSQL("DELETE FROM musicians WHERE name = 'Tugce'")

        var nameIndex = cursor.getColumnIndex("name")
        var ageIndex = cursor.getColumnIndex("age")

        while(cursor.moveToNext()){
            println("Name: " + cursor.getString(nameIndex))
            println("Age: " + cursor.getInt(ageIndex))
        }
        cursor.close()

    }catch (e:Exception){
        e.printStackTrace()
    }
    }
}