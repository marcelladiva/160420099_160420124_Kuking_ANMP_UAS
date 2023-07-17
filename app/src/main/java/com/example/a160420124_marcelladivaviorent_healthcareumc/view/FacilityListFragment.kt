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

class FacilityListFragment : Fragment() {
    private lateinit var viewModel: FacilityListViewModel
    private val facilityListAdapter = FacilityListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_facility_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FacilityListViewModel::class.java)
        viewModel.refresh()

        val recViewFacility = view?.findViewById<RecyclerView>(R.id.recViewFacility)
        recViewFacility?.layoutManager = LinearLayoutManager(context)
        recViewFacility?.adapter = facilityListAdapter

        observeViewModel()
    }

    fun observeViewModel(){

        viewModel.facilityLD.observe(viewLifecycleOwner, Observer {
            facilityListAdapter.updateFacilityList(it)
        })

        viewModel.facilityLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtErrorFacility = view?.findViewById<TextView>(R.id.txtErrorFacility)
            if(it == null) {
                txtErrorFacility?.visibility = View.VISIBLE
            } else {
                txtErrorFacility?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recViewFacility = view?.findViewById<RecyclerView>(R.id.recViewFacility)
            val progressLoadFacility = view?.findViewById<ProgressBar>(R.id.progressLoadFacility)
            if(it == null) {
                recViewFacility?.visibility = View.GONE
                progressLoadFacility?.visibility = View.VISIBLE
            } else {
                recViewFacility?.visibility = View.VISIBLE
                progressLoadFacility?.visibility = View.GONE
            }
        })
    }
}