package com.example.livedataexample.password

 import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.livedataexample.R
import com.example.livedataexample.databinding.FragmentPasswordBinding
import com.example.livedataexample.timmer.TimmerFregment


class PasswordFragment : Fragment(), View.OnClickListener{
    // The type of binding class will change from fragment to fragment
    private var _binding: FragmentPasswordBinding? = null

    val binding get() = _binding!! // Helper Property

    lateinit var mPasswordInputLayout: EditText
    lateinit var mButton: Button

    // Crear view model.
    val viewModel = PasswordViewModel()

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
        mButton=  view.findViewById(R.id.mButton)



        // Actualizar password por cada edición
        mPasswordInputLayout.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }
            override fun afterTextChanged(editable: Editable) {
                //editable.toString() = complain String

                viewModel.getPassword()!!.value = editable.toString()

                // model.getPassword()!!.setValue(editable.toString());
            }
        })

        // Observar pasando el par owner-observer
        viewModel.getPassword()?.observe(activity!!, object : Observer<String?> {

            override fun onChanged(password: String?) {
                if (password!!.length < 6)
                {
                    mPasswordInputLayout.setError("Son mínimo 6 caracteres")
                }
                else{
                    mPasswordInputLayout.setError(null)
                    makeText(activity,"Field okey",LENGTH_LONG).show()
                }
            }
        })
        mButton.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun launchNextScreen() {
        makeText(activity,"Fragment switching",LENGTH_LONG).show()
        //child fragment
        //child fragment
        // Create new fragment and transaction
        // Create new fragment and transaction
        val newFragment: Fragment = TimmerFregment()
        val transaction = fragmentManager!!.beginTransaction()

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
        transaction.replace(R.id.main_container, newFragment)
        //Keep old fragments
       // transaction.addToBackStack(null)
        //AGREGAMOS LA CANTIDAD DE TIEMPO QUE USAREMOS PARA CONTAR!!!
// Commit the transaction
        transaction.commit()
    }

    override fun onClick(v: View?) {
         launchNextScreen()
    }

}