package com.study.flowpractice.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.study.flowpractice.R
import com.study.flowpractice.adapter.ArticleAdapter
import com.study.flowpractice.databinding.FragmentArticleBinding
import com.study.flowpractice.databinding.FragmentUserBinding
import com.study.flowpractice.viewmodel.ArticleViewModel
import com.study.flowpractice.viewmodel.UserViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import org.w3c.dom.Text


class ArticleFragment : Fragment() {

    private val viewModel by viewModels<ArticleViewModel>()
    private val mBinding: FragmentArticleBinding by lazy {
        FragmentArticleBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    private fun TextView.textWatcherFlow(): Flow<String> = callbackFlow {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                //offer(s.toString())
                trySend(s.toString())
            }
        }
        addTextChangedListener(textWatcher)
        awaitClose {
            removeTextChangedListener(textWatcher)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            mBinding.etKeywords.textWatcherFlow().collect {
                Log.d(this.javaClass.name, "collect keywords:$it")
                viewModel.searchArticles(it)

            }
        }
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            context?.let {context->
                Log.d(this.javaClass.name,"articles.observe articles:${articles}")
                val adapter = ArticleAdapter(context)
                mBinding.rvArticle.layoutManager= LinearLayoutManager(context)
                mBinding.rvArticle.adapter=adapter
                adapter.setData(articles)
            }
        }
    }

}