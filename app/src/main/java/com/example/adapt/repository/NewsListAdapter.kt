package com.example.adapt.repository
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.adapt.databinding.ViewNewsListItemBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.adapt.model.Article
import com.example.adapt.viewmodel.NewsListViewHolder
import com.example.adapt.viewmodel.NewsListViewModel


class NewsListAdapter(private val newsListViewModel: NewsListViewModel) : RecyclerView.Adapter<NewsListViewHolder>(){
    var newsList: List<Article> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ViewNewsListItemBinding.inflate(inflater, parent, false)
        return NewsListViewHolder(dataBinding, newsListViewModel)
    }

    override fun getItemCount() = newsList.size

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.setup(newsList[position])
    }

    fun updateNewsList(repoList: List<Article>) {
        this.newsList = repoList
        notifyDataSetChanged()
    }
}
