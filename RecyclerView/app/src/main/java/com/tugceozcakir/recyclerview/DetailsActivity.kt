package com.tugceozcakir.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tugceozcakir.recyclerview.databinding.ActivityDetailsBinding
import kotlinx.android.synthetic.main.activity_details.view.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent = intent
        val selectedLandmark = intent.getSerializableExtra("landmark") as Landmark
        binding.landmarkName.text = selectedLandmark.name
        binding.landmarkName.text = selectedLandmark.country
        binding.imageView2.setImageResource(selectedLandmark.image)

       /*
        //Singleton
        if(Singleton.chosenLandmark != null) {
            binding.imageView2.setImageResource(Singleton.chosenLandmark!!.image)
            binding.landmarkName.text = Singleton.chosenLandmark!!.name
            binding.landmarkCountry.text = Singleton.chosenLandmark!!.country
        }
        */
}
}