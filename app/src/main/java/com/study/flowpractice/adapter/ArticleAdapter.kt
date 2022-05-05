package com.study.flowpractice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.flowpractice.databinding.ItemArticleBinding
import com.study.flowpractice.databinding.ItemUserBinding
import com.study.flowpractice.db.User
import com.study.flowpractice.model.Article

class ArticleAdapter(private val context: Context):RecyclerView.Adapter<BindingViewHolder>() {

    private val data=ArrayList<Article>()

    fun setData(data:List<Article>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding =ItemArticleBinding.inflate(LayoutInflater.from(context),parent,false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val item=data.get(position)
        val binding = holder.binding as ItemArticleBinding
        binding.text.text=item.text

    }

    override fun getItemCount(): Int =data.size

}