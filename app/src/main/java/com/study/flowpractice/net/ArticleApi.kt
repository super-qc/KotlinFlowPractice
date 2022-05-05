package com.study.flowpractice.net

import com.study.flowpractice.model.Article
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {

    @GET("article.php")
    suspend fun searchArticles(@Query("key") key:String):List<Article>
}