package com.tugceozcakir.foodbookapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tugceozcakir.foodbookapp.R
import com.tugceozcakir.foodbookapp.databinding.FoodRecyclerRowBinding
import com.tugceozcakir.foodbookapp.model.Food
import com.tugceozcakir.foodbookapp.util.MakePlaceHolder
import com.tugceozcakir.foodbookapp.util.downloadImage
import com.tugceozcakir.foodbookapp.view.FoodListFragmentDirections

class FoodRecyclerAdapter (val foodList: ArrayList<Food>): RecyclerView.Adapter<FoodRecyclerAdapter.FoodViewHolder>(), FoodClickListener {
    class FoodViewHolder(var view: FoodRecyclerRowBinding): RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.food_recycler_row, parent, false)
        val view = DataBindingUtil.inflate<FoodRecyclerRowBinding>(inflater,R.layout.food_recycler_row,parent,false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder,position: Int) {
        holder.view.food = foodList.get(position)
        holder.view.listener = this
        /*
        holder.itemView.findViewById<TextView>(R.id.foodNameText).text = foodList.get(position).name
        holder.itemView.findViewById<TextView>(R.id.foodCaloriesText).text = foodList.get(position).calories

        holder.itemView.setOnClickListener {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(foodList.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.itemView.findViewById<ImageView>(R.id.foodImageView)
    .downloadImage(foodList.get(position).image, MakePlaceHolder(holder.itemView.context))
 */
    }
    override fun getItemCount(): Int {
        return foodList.size
    }

    fun foodListLoading(newFoodList: List<Food>){
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()
    }

    override fun clickedFood(view: View) {
        val uuid = view.findViewById<TextView>(R.id.foodUuid).text.toString().toIntOrNull()
        uuid?.let {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(it)
            Navigation.findNavController(view).navigate(action)
        }
    }
}