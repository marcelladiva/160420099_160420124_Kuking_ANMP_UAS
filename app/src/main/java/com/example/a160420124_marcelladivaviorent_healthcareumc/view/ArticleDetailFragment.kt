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
import androidx.lifecycle.ViewModelProvider
import com.example.a160420124_marcelladivaviorent_healthcareumc.R
import com.example.a160420124_marcelladivaviorent_healthcareumc.util.loadImage
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.ArticleDetailViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import java.util.concurrent.TimeUnit

class ArticleDetailFragment : Fragment() {
    private lateinit var articleDetailViewModel: ArticleDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val idArticle = ArticleDetailFragmentArgs.fromBundle(requireArguments()).articleID
            articleDetailViewModel = ViewModelProvider(this).get(ArticleDetailViewModel::class.java)
            articleDetailViewModel.fetch(idArticle.toInt())
        }

        val textJudulArtikel = view.findViewById<TextView>(R.id.textJudulArtikel)
        val imageViewArtikel = view.findViewById<ImageView>(R.id.imageViewArtikel)
        val textDetailArtikel = view.findViewById<TextView>(R.id.textDetailArtikel)
        val progressLoadArtikel = view.findViewById<ProgressBar>(R.id.progressLoadArtikel)

        articleDetailViewModel.articleLD.observe(viewLifecycleOwner){articleDetail ->
            imageViewArtikel.loadImage(articleDetail.photoUrl,progressLoadArtikel)
            textJudulArtikel.setText(articleDetail.name.toString())
            textDetailArtikel.setText(articleDetail.detail.toString())
        }
    }
}