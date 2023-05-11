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
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.DrugListViewModel

class ArticleListFragment : Fragment() {

    private lateinit var viewModel: ArticleListViewModel
    private val articleListAdapter = ArticleListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ArticleListViewModel::class.java)
        viewModel.refresh()

        val recViewArticle = view?.findViewById<RecyclerView>(R.id.recViewArticle)
        recViewArticle?.layoutManager = LinearLayoutManager(context)
        recViewArticle?.adapter = articleListAdapter

        observeViewModel()

        val refreshLayoutArticle = view?.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutArticle)
        val txtErrorArticle = view?.findViewById<TextView>(R.id.txtErrorArticle)
        val progressLoadArticle = view?.findViewById<ProgressBar>(R.id.progressLoadArticle)

        refreshLayoutArticle?.setOnRefreshListener {
            recViewArticle?.visibility = View.GONE
            txtErrorArticle?.visibility = View.GONE
            progressLoadArticle?.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutArticle?.isRefreshing = false
        }
    }

    fun observeViewModel(){

        viewModel.articleLD.observe(viewLifecycleOwner, Observer {
            articleListAdapter.updateArticleList(it)
        })

        viewModel.articleLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtErrorArticle = view?.findViewById<TextView>(R.id.txtErrorArticle)
            if(it == true) {
                txtErrorArticle?.visibility = View.VISIBLE
            } else {
                txtErrorArticle?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recViewArticle = view?.findViewById<RecyclerView>(R.id.recViewArticle)
            val progressLoadArticle = view?.findViewById<ProgressBar>(R.id.progressLoadArticle)
            if(it == true) {
                recViewArticle?.visibility = View.GONE
                progressLoadArticle?.visibility = View.VISIBLE
            } else {
                recViewArticle?.visibility = View.VISIBLE
                progressLoadArticle?.visibility = View.GONE
            }
        })
    }
}