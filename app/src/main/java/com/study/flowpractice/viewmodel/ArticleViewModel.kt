package com.study.flowpractice.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.study.flowpractice.model.Article
import com.study.flowpractice.net.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ArticleViewModel(app: Application) : AndroidViewModel(app) {

    val articles=MutableLiveData<List<Article>>()

    fun searchArticles(key: String) {
        viewModelScope.launch {
            flow {
                var list = RetrofitClient.articleApi.searchArticles(key)
                /*
                var list= mutableListOf<Article>()
                list.add(Article(1,"特斯拉[TSLA]美股实时行情 - 百度股市通"))
                list.add(Article(2,"Tesla(汽车厂商) - 百度百科"))
                list.add(Article(3,"【特斯拉】TESLA 报价_特斯拉汽车报价_图片_汽车之家"))
                list.add(Article(4,"Electric Cars, Solar & Clean Energy | Tesla"))
                */
                emit(list)
            }.flowOn(Dispatchers.IO)
                .catch { e -> e.printStackTrace() }
                .collect {
                    articles.setValue(it)
                }
        }
    }
}