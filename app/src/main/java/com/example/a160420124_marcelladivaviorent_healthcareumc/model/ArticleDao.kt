package com.example.a160420124_marcelladivaviorent_healthcareumc.model

import androidx.room.*

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg article: Article)

    @Query("SELECT * FROM article")
    fun selectAllArticle(): List<Article>

    @Query("SELECT * FROM article WHERE uuid= :id")
    fun selectArticle(id:Int): Article

    @Delete
    fun deleteArticle(article: Article)
}