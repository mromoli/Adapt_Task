package com.example.adapt.viewmodel

import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adapt.R
import androidx.core.os.bundleOf
import com.example.adapt.BR
import com.example.adapt.model.Article
import kotlinx.android.synthetic.main.view_news_list_item.view.*


class NewsListViewHolder constructor(private val dataBinding: ViewDataBinding, private val newsListViewModel: NewsListViewModel)
    : RecyclerView.ViewHolder(dataBinding.root){

    val newsImage = itemView.newsImage
    fun setup(itemData: Article) {
        dataBinding.setVariable(BR.itemData, itemData)
        dataBinding.executePendingBindings()
    if(itemData.urlToImage != null) {
        Glide.with(itemView)
            .load(itemData.urlToImage)
            .into(newsImage)
    }else{
        Glide.with(itemView)
            .load(R.drawable.broken2)
            .into(newsImage)
    }
        itemView.setOnClickListener {
            val bundle = bundleOf ( "url" to itemData.url , "source" to itemData.source.name)
            itemView.findNavController().navigate(R.id.action_repoListFragment_to_repoDetailFragment, bundle)
        }
    }

}
