package com.tugceozcakir.chatapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.tugceozcakir.chatapp.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding = _binding
    private lateinit var auth : FirebaseAuth
    lateinit var navController: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

    }

    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater,container,false)
        val view = binding?.root

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        navController = findNavController(view)

        super.onViewCreated(view, savedInstanceState)
        auth.createUserWithEmailAndPassword(binding?.emailText.toString(), binding?.passwordEditText.toString()).addOnSuccessListener {
            //kullanici olusturuldu.
            Toast.makeText(requireContext(),"Your account has been created.", Toast.LENGTH_SHORT).show()
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            findNavController().navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}