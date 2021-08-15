package com.metropolitan.treciprimer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.metropolitan.treciprimer.baza.Baza


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DrugiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DrugiFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_drugi, container, false)
        val btnLogin: Button = root.findViewById<Button>(R.id.btnLogin)
        val etUsername: EditText = root.findViewById<EditText>(R.id.etUsername)
        val etPassword: EditText = root.findViewById<EditText>(R.id.etPassword)
        var helper = Baza(requireContext())
        btnLogin.setOnClickListener {
            if (etUsername.text.toString() != "" && etPassword.text.toString() != "") {
                Log.d("ETUSERNAME", etUsername.text.toString())
                Log.d("ETPASSWORD", etPassword.text.toString())
                Log.d("Ovo", helper.getParticularUserData(etUsername.text.toString()).username)
                Log.d("Ovo2", helper.getParticularUserData(etUsername.text.toString()).password)
                //provera username i pw
                if ((etUsername.text.toString() == helper.getParticularUserData(etUsername.text.toString()).username) && (etPassword.text.toString() == helper.getParticularUserData(
                        etUsername.text.toString()
                    ).password)
                ) {
                    Toast.makeText(
                        activity,
                        "Uspesan login",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(requireContext(), Aktivnost2::class.java)
                    intent.putExtra("un", helper.getParticularUserData(etUsername.text.toString()).username)
                    intent.putExtra("pw", helper.getParticularUserData(etUsername.text.toString()).password)
                    startActivity(intent)
                    Log.d("Uspesan", "Uspesan")
                } else {
                    Toast.makeText(
                        activity,
                        "Proverite podatke opet",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(
                    activity,
                    "Niste uneli username i password",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DrugiFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DrugiFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}