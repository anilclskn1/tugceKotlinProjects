package com.tugceozcakir.artbookwithfragment.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tugceozcakir.artbookwithfragment.databinding.RecyclerRowBinding
import com.tugceozcakir.artbookwithfragment.model.Arts
import com.tugceozcakir.artbookwithfragment.view.ArtListFragmentDirections
import com.tugceozcakir.artbookwithfragment.view.DetailsFragment
import com.tugceozcakir.artbookwithfragment.view.DetailsFragmentDirections
/*
class ArtAdapter(val artList: ArrayList<Arts>): RecyclerView.Adapter<ArtAdapter.ArtHolder>() {
    class ArtHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArtHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtHolder, position: Int) {
        holder.binding.recyclerViewTextView.text = artList.get(position).artName
        holder.itemView.setOnClickListener(){
            val action = ArtListFragmentDirections.actionArtListToDetailFragment(artList.get(position).id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return artList.size
    }
}

 */