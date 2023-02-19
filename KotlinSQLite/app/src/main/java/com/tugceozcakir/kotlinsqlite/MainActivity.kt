package com.tugceozcakir.kotlinsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try{
            val myDatabase = this.openOrCreateDatabase("Musicians", MODE_PRIVATE, null)
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY, name VARCHAR, age INT)")
            myDatabase.execSQL("INSERT INTO musicians(name, age) VALUES('Hicran',22)")

            //myDatabase.execSQL("UPDATE musicians SET age = 23 WHERE name='Tugce'")
            myDatabase.execSQL("UPDATE musicians SET name = 'Anil Caliskan' WHERE name='Anil'")
            myDatabase.execSQL("UPDATE musicians SET age=0 WHERE name = 'Cookie'")
            myDatabase.execSQL("UPDATE musicians SET name = 'Pantheon' WHERE id = 14")
            val cursor = myDatabase.rawQuery("SELECT * FROM musicians", null)

           myDatabase.execSQL("DELETE FROM musicians WHERE name= 'Hicran'")

            val nameIndex = cursor.getColumnIndex("name")
            val ageIndex = cursor.getColumnIndex("age")
            val idIndex = cursor.getColumnIndex("id")

            while(cursor.moveToNext()){
                println("Name:" + cursor.getString(nameIndex))
                println("Age:" + cursor.getInt(ageIndex))
                print("Id:" + cursor.getInt(idIndex))
            }
            cursor.close()

        }catch(e: Exception){
            e.printStackTrace()
        }
    }
}