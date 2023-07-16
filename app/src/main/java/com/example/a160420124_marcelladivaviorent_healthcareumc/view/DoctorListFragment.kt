package com.example.a160420124_marcelladivaviorent_healthcareumc.view

import android.os.Bundle
import android.util.Log
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.a160420124_marcelladivaviorent_healthcareumc.R
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.DoctorListViewModel

class DoctorListFragment : Fragment() {

    private lateinit var viewModel: DoctorListViewModel
    private val doctorListAdapter = DoctorListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DoctorListViewModel::class.java)
        viewModel.refresh()

        val recViewDoctor = view?.findViewById<RecyclerView>(R.id.recViewDoctor)
        recViewDoctor?.layoutManager = LinearLayoutManager(context)
        recViewDoctor?.adapter = doctorListAdapter

        observeViewModel()

//        val refreshLayoutDoctor = view?.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutDoctor)
//        val txtErrorDoctor = view?.findViewById<TextView>(R.id.txtErrorDoctor)
//        val progressLoadDoctor = view?.findViewById<ProgressBar>(R.id.progressLoadDoctor)
//
//        refreshLayoutDoctor?.setOnRefreshListener {
//            recViewDoctor?.visibility = View.GONE
//            txtErrorDoctor?.visibility = View.GONE
//            progressLoadDoctor?.visibility = View.VISIBLE
//            viewModel.refresh()
//            refreshLayoutDoctor?.isRefreshing = false
//        }
    }

    fun observeViewModel(){

        viewModel.doctorLD.observe(viewLifecycleOwner, Observer {
            doctorListAdapter.updateDoctorList(it)
        })

        viewModel.doctorLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtErrorDoctor = view?.findViewById<TextView>(R.id.txtErrorDoctor)
            if(it == null) {
                txtErrorDoctor?.visibility = View.VISIBLE
            } else {
                txtErrorDoctor?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recViewDoctor = view?.findViewById<RecyclerView>(R.id.recViewDoctor)
            val progressLoadDoctor = view?.findViewById<ProgressBar>(R.id.progressLoadDoctor)
            if(it == null) {
                recViewDoctor?.visibility = View.GONE
                progressLoadDoctor?.visibility = View.VISIBLE
            } else {
                recViewDoctor?.visibility = View.VISIBLE
                progressLoadDoctor?.visibility = View.GONE
            }
        })
    }
}