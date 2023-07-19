package com.example.a160420124_marcelladivaviorent_healthcareumc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.a160420124_marcelladivaviorent_healthcareumc.R
import com.example.a160420124_marcelladivaviorent_healthcareumc.databinding.FragmentLoginBinding
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.User
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.LoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class LoginFragment : Fragment(), LoginLayoutInterface, SignUpLayoutInterface {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var dataBinding: FragmentLoginBinding

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
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        observeViewModel(view)
        dataBinding.loginInterface = this
    }

    private fun observeViewModel(view: View) {
        loginViewModel.userLD.observe(viewLifecycleOwner) {
            if (it != null) {
                loginViewModel.sessionLogin(it.username, it.uuid)
                val action = LoginFragmentDirections.actionArticleFragment()
                Navigation.findNavController(view).navigate(action)
            } else {
                Toast.makeText(view.context, "Username or password is incorrect", Toast.LENGTH_SHORT).show()
            }
        }

        if (loginViewModel.isLogin()) {
            val action = LoginFragmentDirections.actionArticleFragment()
            Navigation.findNavController(view).navigate(action)
            loginViewModel.getUserFromSharedPref()
        }
    }

    override fun login(v: View) {
        val username = dataBinding.editUsername.text.toString()
        val password = dataBinding.editPassword.text.toString()

        loginViewModel.login(username, password)
    }

    override fun daftar(v: View) {
        val action = LoginFragmentDirections.actionSignUpFragment()
        Navigation.findNavController(v).navigate(action)
    }

}