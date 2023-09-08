package com.example.livedataexample.password

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class PasswordViewModel(val mView: BehaviorPassword) : ViewModel() {

    var btnText = ObservableField<String>()
    private var password: MutableLiveData<String>? = null

    fun getPassword(): MutableLiveData<String>? {
        if (password == null) {
            password = MutableLiveData()
        }
        return password
    }
    fun onButtonClick(){
        mView.launchNextScreen()
        btnText.set("Clicked")
    }
}