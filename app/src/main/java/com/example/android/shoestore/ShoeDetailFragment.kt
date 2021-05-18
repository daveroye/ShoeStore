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
import com.example.android.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    private val viewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                                savedInstanceState: Bundle? ): View {

        val binding: FragmentShoedetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoedetail, container, false)

        binding.saveShoeInfo.setOnClickListener {
            val shoeName = binding.shoeName.text.toString()
            val shoeSize = binding.shoeSize.text.toString()
            val shoeCompany = binding.shoeCompany.text.toString()
            val shoeDescription = binding.shoeDescription.text.toString()

            if (shoeName.isBlank() || shoeSize.isBlank() ||
                shoeCompany.isBlank() || shoeDescription.isBlank() ) {
                Toast.makeText( requireContext(),
                           "Please fill in all fields",
                                Toast.LENGTH_SHORT).show()
            } else {
                // create a new shoe from entered data
                val newShoe = Shoe( shoeName, shoeSize.toDouble(),
                                    shoeCompany, shoeDescription )
                // add the new show to the view model data object
                viewModel.addShoeToList(newShoe)
                // return to previous view
                requireView().findNavController().popBackStack()
            }
        }

        binding.cancelSave.setOnClickListener {
            // return to previous view without saving shoe data
            requireView().findNavController().popBackStack()
        }

        return binding.root
    }
}