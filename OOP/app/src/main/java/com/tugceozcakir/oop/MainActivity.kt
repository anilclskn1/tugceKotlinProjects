package com.tugceozcakir.oop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("----Classes----")
        val firstUser = User("Tugce", 23)
        val secondUser = User("Anil", 40)

        println("----Encapsulation----")
        val yasemin = Artist("Yasemin", 42, "Firefighter")
        println(yasemin.name)
        //yasemin.name = "Ayse"

        println("----Inheritance----")
        val elif = SpecialArtist("Elif", 19, "Painter")
        elif.drawAPicture()

        println("----Polymorphism----")
        //Static Polymorphism
        val process = Process()
        println(process.multiply())
        println(process.multiply(2, 3))
        println(process.multiply(2, 3 , 4))

        //Dinamic Polymorphism
        val cat = Animal()
        cat.makeNoise()

        val bunny = Dog()
        bunny.makeNoise()

        println("----Abstraction & Interface----")
        firstUser.human()

        var guitar = Guitar()
        guitar.brand = "Guitar brand"
        guitar.electro = true

        guitar.information()
        println(guitar.room)

        println("----Lambda Expressions----")

    }
}