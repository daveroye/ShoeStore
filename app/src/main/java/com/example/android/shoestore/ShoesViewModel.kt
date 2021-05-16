package com.example.android.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.shoestore.models.Shoe
import timber.log.Timber

class ShoesViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList : LiveData<List<Shoe>>
        get() = _shoeList

    private val aShoeList = ArrayList<Shoe>()

    fun addShoeToList(newShoe : Shoe) {
        // add new shoe to the list
        aShoeList.add(newShoe)
        // update list to Live Data observers
        _shoeList.value = aShoeList
    }
}