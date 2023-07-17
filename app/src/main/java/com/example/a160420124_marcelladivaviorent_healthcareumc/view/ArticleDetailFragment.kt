package com.example.a160420124_marcelladivaviorent_healthcareumc.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a160420124_marcelladivaviorent_healthcareumc.R
import com.example.a160420124_marcelladivaviorent_healthcareumc.databinding.FragmentArticleDetailBinding
import com.example.a160420124_marcelladivaviorent_healthcareumc.databinding.FragmentDoctorPracticeScheduleBinding
import com.example.a160420124_marcelladivaviorent_healthcareumc.util.loadImage
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.ArticleDetailViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import java.util.concurrent.TimeUnit

class ArticleDetailFragment : Fragment() {
    private lateinit var articleDetailViewModel: ArticleDetailViewModel
    private lateinit var dataBinding: FragmentArticleDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentArticleDetailBinding>(inflater,R.layout.fragment_article_detail, container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val idArticle = ArticleDetailFragmentArgs.fromBundle(requireArguments()).articleID
            articleDetailViewModel = ViewModelProvider(this).get(ArticleDetailViewModel::class.java)
            articleDetailViewModel.fetch(idArticle.toInt())
        }
        observeViewModel()
    }
    fun observeViewModel() {
        articleDetailViewModel.articleLD.observe(viewLifecycleOwner, Observer {
            dataBinding.article = it
        })
    }
}