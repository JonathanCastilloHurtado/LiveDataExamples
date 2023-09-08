package com.example.livedataexample.timmer

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimmerViewModel: ViewModel() {
    private val _seconds = MutableLiveData<Int>()
    private val _finished = MutableLiveData<Boolean>()

    // getter method for seconds var
    fun seconds():LiveData<Int>{
        return _seconds
    }

    // getter method for finished var
    fun finished(): LiveData<Boolean> {
        return _finished
    }


    // Counter method that uses CountDownTimer()
    fun startCounter(){

        // you can change the millisInFuture value
        object : CountDownTimer(5000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                val time = millisUntilFinished / 1000

                // setting the count value
                _seconds?.value = time.toInt()
            }

            override fun onFinish() {
                // if millisInFuture completed
                // it set the value true
                _finished?.value = true
            }
        }.start()
    }
}