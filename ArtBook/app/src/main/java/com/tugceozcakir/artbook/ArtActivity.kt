package com.tugceozcakir.artbook

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.tugceozcakir.artbook.databinding.ActivityArtBinding

class ArtActivity : AppCompatActivity() {

    lateinit var binding : ActivityArtBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityArtBinding.inflate(layoutInflater)

        val view = binding.root

        super.onCreate(savedInstanceState)
        setContentView(view)
    }

    fun saveButtonClicked(view: View) {

    }

    fun selectImage(view: View){

    }

}