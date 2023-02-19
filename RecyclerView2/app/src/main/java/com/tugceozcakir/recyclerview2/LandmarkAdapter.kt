package com.tugceozcakir.recyclerview2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tugceozcakir.recyclerview2.databinding.ResourceRowBinding

class LandmarkAdapter<T>(val landmarkList: ArrayList<Landmark>): RecyclerView.Adapter<LandmarkAdapter<Any?>.LandmarkHolder>() {

    class LandmarkHolder(val binding: ResourceRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkHolder {
        val binding = ResourceRowBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return LandmarkHolder(binding)
    }

    override fun onBindViewHolder(holder: LandmarkHolder, position: Int) {
        holder.binding.recyclerViewTextView.text = landmarkList.
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}