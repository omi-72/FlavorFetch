package com.example.foodapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapp.activities.MainActivity
import com.example.foodapp.adapter.CategoriesAdapter
import com.example.foodapp.databinding.FragmentCatagoryBinding
import com.example.foodapp.viewmodel.HomeViewModel


class CatagoryFragment : Fragment() {

    private lateinit var binding: FragmentCatagoryBinding
    private lateinit var categoriesAdapter : CategoriesAdapter

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCatagoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        observerCategories()
    }

    private fun observerCategories() {
        viewModel.observerCategoriesLiveData().observe(viewLifecycleOwner, Observer { categories ->
            categoriesAdapter.setCategoryList(categories)
        })
    }

    private fun prepareRecyclerView() {
       categoriesAdapter = CategoriesAdapter()
        binding.rcvCategories.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL,false)
            adapter = categoriesAdapter
        }
    }


}