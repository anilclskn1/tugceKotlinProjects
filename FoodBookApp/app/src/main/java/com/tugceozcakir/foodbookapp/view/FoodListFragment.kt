package com.tugceozcakir.foodbookapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugceozcakir.foodbookapp.viewmodel.FoodListViewModel
import com.tugceozcakir.foodbookapp.adapter.FoodRecyclerAdapter
import com.tugceozcakir.foodbookapp.databinding.FragmentFoodListBinding


class FoodListFragment : Fragment() {
    private var _binding: FragmentFoodListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FoodListViewModel
    private val recyclerFoodAdapter = FoodRecyclerAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoodListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

        viewModel = ViewModelProvider(this)[FoodListViewModel::class.java]
        viewModel.refreshData()

        binding.foodListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.foodListRecyclerView.adapter = recyclerFoodAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.loadingBar.visibility = View.VISIBLE
            binding.errorText.visibility = View.GONE
            binding.foodListRecyclerView.visibility = View.GONE
            viewModel.refreshDataFromInternet()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.foods.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.foodListRecyclerView.visibility = View.VISIBLE
                recyclerFoodAdapter.foodListLoading(it)
            }
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it){
                    binding.errorText.visibility = View.VISIBLE
                    binding.foodListRecyclerView.visibility = View.GONE
                }else{
                    binding.errorText.visibility = View.GONE
                }
            }
        })
        viewModel.foodLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    binding.foodListRecyclerView.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                    binding.loadingBar.visibility = View.VISIBLE
                }else{
                    binding.loadingBar.visibility = View.GONE
                }
            }
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}