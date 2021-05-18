package com.example.android.shoestore

import android.os.Bundle
import android.view.*
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.android.shoestore.databinding.FragmentShoelistBinding

class ShoeListFragment : Fragment() {

    private val viewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                                savedInstanceState: Bundle? ): View {

        val binding: FragmentShoelistBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoelist, null, false)

        viewModel.shoeList.observe(viewLifecycleOwner, { newShoes ->
            if (newShoes.isNotEmpty()) {
                // loop through the list of shoes and add to the view
                newShoes.forEach {
                    val newShoeTextView = TextView(requireContext())
                    val shoeString = "name: ${it.name}\n size: ${it.size}\n Company: ${it.company}\n description: ${it.description}\n"
                    newShoeTextView.text = shoeString
                    newShoeTextView.layoutParams =
                        LinearLayout.LayoutParams( MATCH_PARENT, WRAP_CONTENT )
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