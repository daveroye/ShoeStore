package com.example.android.shoestore

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.android.shoestore.databinding.FragmentShoelistBinding
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private val viewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                                savedInstanceState: Bundle? ): View {

        val binding: FragmentShoelistBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoelist, container, false)

        viewModel.shoeList.observe(viewLifecycleOwner, { newShoes ->
            if (newShoes.isNotEmpty()) {
                Timber.i("new shoes!! $newShoes")
                newShoes.forEach {
                    val newShoeTextView = TextView(requireContext())
                    newShoeTextView.text = "${it}"//shoeString
                    binding.shoeList.addView(newShoeTextView)
                }
            }
        })

        binding.shoeDetailButton.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        // register for options menu in this view
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // find the nav controller for this view
        val navController = requireView().findNavController()

        // added to remove current view from back stack that seems to be left
        // when you navigate to destination given by menu ID
        navController.popBackStack()

        // menuId was set to navigation destination
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }
}