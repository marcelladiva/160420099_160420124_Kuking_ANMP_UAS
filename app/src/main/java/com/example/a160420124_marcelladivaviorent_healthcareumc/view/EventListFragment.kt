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
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.ArticleListViewModel
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.EventListViewModel

class EventListFragment : Fragment() {

    private lateinit var viewModel: EventListViewModel
    private val eventListAdapter = EventListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(EventListViewModel::class.java)
        viewModel.refresh()

        val recView = view?.findViewById<RecyclerView>(R.id.recViewEvent)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = eventListAdapter


        observeViewModel()

        val refreshLayoutEvent = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutEvent)
        val txtErrorEvent = view.findViewById<TextView>(R.id.txtErrorEvent)
        val progressLoadEvent = view.findViewById<ProgressBar>(R.id.progressLoadEvent)

//        val txtError = view?.findViewById<TextView>(R.id.txtErrorEvent)
//        val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoadEvent)
//        val refreshLayout = view?.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutEvent)

        refreshLayoutEvent?.setOnRefreshListener {
            recView?.visibility = View.GONE
            txtErrorEvent?.visibility = View.GONE
            progressLoadEvent?.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutEvent?.isRefreshing = false
        }
    }

    fun observeViewModel(){
        viewModel.eventsLD.observe(viewLifecycleOwner, Observer {
            eventListAdapter.updateEventList(it)
        })

        viewModel.eventLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtErrorEvent = view?.findViewById<TextView>(R.id.txtErrorEvent)
            if(it == true) {
                txtErrorEvent?.visibility = View.VISIBLE
            } else {
                txtErrorEvent?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recViewEvent = view?.findViewById<RecyclerView>(R.id.recViewEvent)
            val progressLoadEvent = view?.findViewById<ProgressBar>(R.id.progressLoadEvent)
            if(it == true) {
                recViewEvent?.visibility = View.GONE
                progressLoadEvent?.visibility = View.VISIBLE
            } else {
                recViewEvent?.visibility = View.VISIBLE
                progressLoadEvent?.visibility = View.GONE
            }
        })
    }
}