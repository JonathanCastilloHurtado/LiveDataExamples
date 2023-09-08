package com.example.livedataexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.livedataexample.password.PasswordFragment


class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.main_container, PasswordFragment())
        ft.commit()
    }

}