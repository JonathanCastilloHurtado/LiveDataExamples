package com.example.livedataexample.password

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PasswordViewModel : ViewModel() {

     private var password: MutableLiveData<String>? = null

    fun getPassword(): MutableLiveData<String>? {
        if (password == null) {
            password = MutableLiveData()
        }
        return password
    }
}