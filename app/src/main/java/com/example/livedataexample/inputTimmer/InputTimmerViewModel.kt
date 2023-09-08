package com.example.livedataexample.inputTimmer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InputTimmerViewModel : ViewModel() {

     private var password: MutableLiveData<String>? = null

    fun getPassword(): MutableLiveData<String>? {
        if (password == null) {
            password = MutableLiveData()
        }
        return password
    }
}