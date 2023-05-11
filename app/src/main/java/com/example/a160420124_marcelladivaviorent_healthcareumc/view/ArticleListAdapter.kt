package com.example.a160420124_marcelladivaviorent_healthcareumc.view
//import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420124_marcelladivaviorent_healthcareumc.R
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.Article
import com.example.a160420124_marcelladivaviorent_healthcareumc.util.loadImage

class ArticleListAdapter(val articleList:ArrayList<Article>):RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>() {
    class ArticleViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.article_list_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articleList[position]
        val textArticleID = holder.view.findViewById<TextView>(R.id.textArticleID)
        val textArticleName = holder.view.findViewById<TextView>(R.id.textArticleName)
        val btnDetailArticle = holder.view.findViewById<Button>(R.id.btnDetailArticle)

        textArticleID.text = article.id
        textArticleName.text = article.name

        btnDetailArticle.setOnClickListener {
            val action = ArticleListFragmentDirections.actionArticleDetail(article.id.toString())
            Navigation.findNavController(it).navigate(action)
        }

        var imageViewArticle = holder.view.findViewById<ImageView>(R.id.imageViewArticle)
        var progressBarArticle = holder.view.findViewById<ProgressBar>(R.id.progressBarArticle)
        imageViewArticle.loadImage(article.photoUrl, progressBarArticle)
    }

    fun updateArticleList(newArticleList: ArrayList<Article>) {
        articleList.clear()
        articleList.addAll(newArticleList)
        notifyDataSetChanged()
    }
}