package com.tugceozcakir.foodbookapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tugceozcakir.foodbookapp.R
import com.tugceozcakir.foodbookapp.databinding.FragmentFoodDetailBinding
import com.tugceozcakir.foodbookapp.model.Food
import com.tugceozcakir.foodbookapp.util.MakePlaceHolder
import com.tugceozcakir.foodbookapp.util.downloadImage
import com.tugceozcakir.foodbookapp.viewmodel.FoodDetailViewModel

class FoodDetailFragment : Fragment() {
    private lateinit var dataBinding: FragmentFoodDetailBinding
    private lateinit var viewModel: FoodDetailViewModel
    private var foodId = 0

    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_detail, container, false)
        return dataBinding.root

    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

        arguments?.let {
            foodId = FoodDetailFragmentArgs.fromBundle(it).foodId
        }

        viewModel = ViewModelProvider(this)[FoodDetailViewModel::class.java]
        viewModel.getRoomData(foodId)

        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.foodLiveData.observe(viewLifecycleOwner,Observer {food ->
            food?.let {
                dataBinding.selectedFood = it
            }
            /*
                binding.foodName.text = it.name
                binding.foodCalories.text = it.calories
                binding.foodOil.text = it.oil
                binding.foodCarbohydrate.text = it.carbohydrate
                binding.foodProtein.text = it.protein
                context?.let {
                    binding.detailFoodImageView.downloadImage(food.image,MakePlaceHolder(it))
                }
             */
        })
    }

}