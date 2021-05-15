package com.example.android.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.shoestore.databinding.FragmentInstructionsBinding
import com.example.android.shoestore.databinding.FragmentShoelistBinding

private lateinit var viewModel: ShoeListViewModel

class ShoeListFragment : Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                                savedInstanceState: Bundle? ): View {

        val binding: FragmentShoelistBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoelist, container, false)

        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
        binding.shoeListViewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { newShoes ->
            // TODO: process the new shoe(s) added to the list
        })

        return binding.root
    }
}