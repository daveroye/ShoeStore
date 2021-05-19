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

    private val _showToast = MutableLiveData<Boolean>()
    val showToast : LiveData<Boolean>
        get() = _showToast

    private val _shoeComplete = MutableLiveData<Boolean>()
    val shoeComplete : LiveData<Boolean>
        get() = _shoeComplete

    private val aShoeList = ArrayList<Shoe>()

    lateinit var aShoe : Shoe

    init {
        clearShoe()
    }

    fun addShoeToList() {
        Timber.i("adding a shoe name: ${aShoe.name} co: ${aShoe.company} size: ${aShoe.size} des: ${aShoe.description}")
        if (aShoe.name.isBlank() || aShoe.size <= 0.0 ||
            aShoe.company.isBlank() || aShoe.description.isBlank() ) {
            // indicate to observers that shoe has blank fields
            _showToast.value = true
        } else {
            // add new shoe to the list
            aShoeList.add(aShoe)
            // update list to Live Data observers
            _shoeList.value = aShoeList
            // indicate to observers that shoe was used to update list
            _shoeComplete.value = true
        }
    }

    fun cancelShoe() {
        _shoeComplete.value = true
    }

    fun clearShoe() {
        aShoe = Shoe("", 0.0, "", "")
        _showToast.value = false
        _shoeComplete.value = false
    }
}