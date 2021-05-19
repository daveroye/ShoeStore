package com.example.android.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.android.shoestore.databinding.FragmentShoedetailBinding

class ShoeDetailFragment : Fragment() {

    private val viewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                                savedInstanceState: Bundle? ): View {

        val binding: FragmentShoedetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoedetail, container, false)
        // needed for view model data binding to layout
        binding.shoesViewModel = viewModel
        // attach shoe data model as well to the layout data binding
        binding.aShoe = viewModel.aShoe
        // make data binding lifecycle aware
        binding.lifecycleOwner = this

        viewModel.showToast.observe(viewLifecycleOwner, { showToast ->
            if (showToast) {
                Toast.makeText(
                    requireContext(),
                    "Please fill in all fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        viewModel.shoeComplete.observe(viewLifecycleOwner, { isComplete ->
            if (isComplete) {
                // return to previous view
                requireView().findNavController().popBackStack()
                viewModel.clearShoe()
            }
        })
        return binding.root
    }
}