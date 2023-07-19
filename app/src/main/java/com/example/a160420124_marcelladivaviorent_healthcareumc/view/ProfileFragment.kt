package com.example.a160420124_marcelladivaviorent_healthcareumc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420124_marcelladivaviorent_healthcareumc.R
import com.example.a160420124_marcelladivaviorent_healthcareumc.databinding.FragmentProfileBinding
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.DrugListViewModel
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.LoginViewModel

class ProfileFragment : Fragment(), ProfileLayoutInterface {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var dataBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.getUserFromSharedPref()
        observeViewModel(view)
        dataBinding.profileInterface = this
    }

    private fun observeViewModel(view: View) {
        loginViewModel.userLD.observe(viewLifecycleOwner) {
            if (it == null) {
                val action = ProfileFragmentDirections.actionProfileLoginFragment()
                Navigation.findNavController(view).navigate(action)
            } else {
                dataBinding.user = it
            }
        }
    }

    override fun ubah(v: View) {
        val newUsername = dataBinding.editUserProfile.text.toString()
        val newPassword = dataBinding.editPassProfile.text.toString()

        loginViewModel.ubah(newUsername, newPassword)
    }

    override fun logout(v: View) {
        loginViewModel.logout()
        loginViewModel.userLD.value = null
    }

    override fun history(v: View) {
        val action = ProfileFragmentDirections.actionHistoryfFragment()
        Navigation.findNavController(v).navigate(action)
    }
}