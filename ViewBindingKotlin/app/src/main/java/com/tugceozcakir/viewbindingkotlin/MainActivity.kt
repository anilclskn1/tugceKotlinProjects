package com.tugceozcakir.viewbindingkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var name = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
   /* val i = 0
    fun ismiDegistir(view: View) {
        println("isim degistirildi")
        textView.text = "Degistirildi"
        text.text = "Anil Caliskan"
        Toast.makeText(this, text.text.toString(),Toast.LENGTH_SHORT).show()
        if()
    }*/
    fun makeSimpson (view: View){
       name = nameText.text.toString()
       val surname = surnameText.text.toString()
       var age = ageText.text.toString().toIntOrNull()
       if(age == null){
           age = 0
       }
       val job = jobText.text.toString()

       val simpson = Simpson(name,surname,age ,job)

       resultText.text = "Result = Name: ${simpson.name} Surname: ${simpson.surname}  Age: ${simpson.age}  Job: ${simpson.job}"
    }
    fun scopeExample(view: View){
        println (name)
    }
}