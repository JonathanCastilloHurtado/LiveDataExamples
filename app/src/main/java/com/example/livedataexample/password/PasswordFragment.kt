package com.example.livedataexample.password

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.livedataexample.R
import com.example.livedataexample.timmer.TimmerFregment
import com.example.livedataexample.databinding.FragmentPasswordBinding

class PasswordFragment : Fragment(), BehaviorPassword {
    // The type of binding class will change from fragment to fragment
    private var _binding: FragmentPasswordBinding? = null

    val binding get() = _binding!! // Helper Property

    lateinit var mPasswordInputLayout: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // As the binding class will change, binding inflate method will also change from fragment to fragment
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPasswordInputLayout=  view.findViewById(R.id.mPasswordInputLayout)

        // Crear view model.
        val viewModel = PasswordViewModel(this)

        // Actualizar password por cada edición
        mPasswordInputLayout.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }
            override fun afterTextChanged(editable: Editable) {
                //editable.toString() = complain String
                makeText(activity,editable.toString(),LENGTH_LONG).show()
                viewModel.getPassword()!!.value = editable.toString()

                // model.getPassword()!!.setValue(editable.toString());
            }
        })

        // Observar pasando el par owner-observer
        viewModel.getPassword()?.observe(activity!!, object : Observer<String?> {

            override fun onChanged(password: String?) {
                mPasswordInputLayout.setError(
                    if (password!!.length < 6) "Son mínimo 6 caracteres" else null
                )
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun launchNextScreen() {

        val ft: FragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        ft.replace(R.id.main_container, TimmerFregment())
        ft.commit()
    }

}