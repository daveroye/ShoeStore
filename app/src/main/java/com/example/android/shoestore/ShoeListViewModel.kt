package com.example.android.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList : LiveData<MutableList<Shoe>> = _shoeList

    fun addShoeToList (newShoe : Shoe) {
        _shoeList.value?.add(newShoe)
        // to notify observer
        _shoeList.value = _shoeList.value
    }
}