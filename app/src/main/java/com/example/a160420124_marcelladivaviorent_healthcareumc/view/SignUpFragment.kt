package com.example.a160420124_marcelladivaviorent_healthcareumc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160420124_marcelladivaviorent_healthcareumc.R
import com.example.a160420124_marcelladivaviorent_healthcareumc.databinding.FragmentSignUpBinding
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.LoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class SignUpFragment : Fragment(), SignUpLayoutInterface {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var dataBinding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            it.findViewById<BottomNavigationView>(R.id.bottomNav).visibility = View.GONE
            it.findViewById<DrawerLayout>(R.id.drawerLayout).setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            it.findViewById<Toolbar>(androidx.appcompat.R.id.action_bar).navigationIcon = null
        }
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        observeViewModel(view)
        dataBinding.signupInterface = this
    }

    private fun observeViewModel(view: View) {
        loginViewModel.userLD.observe(viewLifecycleOwner) {
            if (it != null) {
                if (!loginViewModel.isLogin()) {
                    val action = SignUpFragmentDirections.actionSignUpToLoginFragment()
                    Navigation.findNavController(view).navigate(action)
                }
            } else {
                Toast.makeText(view.context, "Username or password must not be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun daftar(v: View) {
        val username = dataBinding.editUser.text.toString()
        val password = dataBinding.editPass.text.toString()

        loginViewModel.signup(username, password)
    }
}