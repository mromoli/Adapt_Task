package com.example.adapt.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.adapt.model.Article
import com.example.adapt.repository.NewsRepository

class NewsListViewModel : BaseViewModel(){

        val newsListLive = MutableLiveData<List<Article>>()

        fun fetchNewsList() {
            dataLoading.value = true
            NewsRepository.getInstance().getRepoList { isSuccess, response ->
                dataLoading.value = false
                if (isSuccess) {
                    newsListLive.value = response?.articles
                    empty.value = false
                } else {
                    empty.value = true
                }
            }
        }
}
