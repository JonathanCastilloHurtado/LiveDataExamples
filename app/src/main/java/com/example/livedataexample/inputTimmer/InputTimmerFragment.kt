package com.example.livedataexample.inputTimmer

 import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
 import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.livedataexample.R
 import com.example.livedataexample.databinding.FragmentInputTimmerBinding
 import com.example.livedataexample.databinding.FragmentTimmerFregmentBinding
 import com.example.livedataexample.timmer.TimmerFregment


class InputTimmerFragment : Fragment(), View.OnClickListener{
    // The type of binding class will change from fragment to fragment
    private var _binding: FragmentInputTimmerBinding? = null

    val binding get() = _binding!! // Helper Property

    lateinit var mTimeInputLayout: EditText
    lateinit var mButton: Button

    // Crear view model.
    val viewModel = InputTimmerViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // As the binding class will change, binding inflate method will also change from fragment to fragment
        _binding = FragmentInputTimmerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mTimeInputLayout=  view.findViewById(R.id.mTimeInputLayout)
        mButton=  view.findViewById(R.id.mButton)



        // Actualizar password por cada edición
        mTimeInputLayout.addTextChangedListener(object : TextWatcher {
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
                if (password!!.length < 4)
                {
                    mTimeInputLayout.setError("Son mínimo 4 caracteres")
                }
                else{
                    mTimeInputLayout.setError(null)
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
       // makeText(activity,"Fragment switching",LENGTH_LONG).show()
         //child fragment
        val bundle = Bundle()

        val myMessage =  viewModel.getPassword()!!.value

        bundle.putString("message", myMessage)
        // Create new fragment and transaction
         val newFragment: Fragment = TimmerFregment()
        newFragment.setArguments(bundle)
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