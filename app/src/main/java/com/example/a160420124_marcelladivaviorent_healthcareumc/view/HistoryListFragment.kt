package com.example.a160420124_marcelladivaviorent_healthcareumc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420124_marcelladivaviorent_healthcareumc.R
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.DoctorListViewModel
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.FacilityListViewModel
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.HistoryListViewModel
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.LoginViewModel

class HistoryListFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var viewModel: HistoryListViewModel
    private val historyListAdapter = HistoryListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel = ViewModelProvider(this).get(HistoryListViewModel::class.java)
        viewModel.refresh(loginViewModel.sharedPrefProvider.getUUID())

        val recViewHistory = view?.findViewById<RecyclerView>(R.id.recViewHistory)
        recViewHistory?.layoutManager = LinearLayoutManager(context)
        recViewHistory?.adapter = historyListAdapter

        observeViewModel()
    }

    fun observeViewModel(){

        viewModel.historyLD.observe(viewLifecycleOwner, Observer {
            historyListAdapter.updateHistoryList(it)
        })

        viewModel.historyLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtErrorHistory = view?.findViewById<TextView>(R.id.txtErrorHistory)
            if(it == null) {
                txtErrorHistory?.visibility = View.VISIBLE
            } else {
                txtErrorHistory?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recViewHistory = view?.findViewById<RecyclerView>(R.id.recViewHistory)
            val progressLoadHistory = view?.findViewById<ProgressBar>(R.id.progressLoadHistory)
            if(it == null) {
                recViewHistory?.visibility = View.GONE
                progressLoadHistory?.visibility = View.VISIBLE
            } else {
                recViewHistory?.visibility = View.VISIBLE
                progressLoadHistory?.visibility = View.GONE
            }
        })
    }
}