package com.example.android.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.shoestore.databinding.FragmentShoedetailBinding

class ShoeDetailFragment : Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                                savedInstanceState: Bundle? ): View {

        val binding: FragmentShoedetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoedetail, container, false)

        binding.saveShoeInfo.setOnClickListener {
            // TODO
        }

        binding.cancelSave.setOnClickListener {
            // TODO
        }

        return binding.root
    }
}