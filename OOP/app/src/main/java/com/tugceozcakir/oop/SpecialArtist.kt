package com.tugceozcakir.oop

class SpecialArtist(name: String,age: Int,job: String) : Artist(name,age,job) {
    fun drawAPicture(){
        println("The picture is being drawn.")
    }
}