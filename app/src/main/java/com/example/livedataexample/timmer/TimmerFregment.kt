package com.example.livedataexample.timmer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.livedataexample.R
import com.example.livedataexample.password.PasswordViewModel

class TimmerFregment : Fragment() {


    // Crear view model.
    val viewModel = TimmerViewModel()

    lateinit var btnCount: Button
    lateinit var timeLapse: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timmer_fregment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timeLapse=  view.findViewById(R.id.timeLapse)
        btnCount=  view.findViewById(R.id.btn_count)

        btnCount.setOnClickListener {    viewModel.startCounter()    }
        // observing the second value of our view model class
        viewModel.seconds().observe(this, Observer {

            // setting textview value
            timeLapse.text = it.toString()
        })

        viewModel.finished().observe(this, Observer {
            if(it){
                // if count time finished it set the value
                timeLapse.text = "Finished"
            }
        })

    }
}