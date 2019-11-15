package com.example.adapt.repository

import com.example.adapt.model.News
import com.example.adapt.model.api.ApiClient
import com.example.adapt.utils.Utils.Companion.API_KEY
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class NewsRepository {

    fun getRepoList(onResult: (isSuccess: Boolean, response: News?) -> Unit) {

        ApiClient.instance.getRepo("us", API_KEY).enqueue(object : Callback<News> {

            override fun onResponse(call: Call<News>?, response: Response<News>?) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
            }


            override fun onFailure(call: Call<News>?, t: Throwable?) {
                onResult(false, null)
            }

        })
    }

    companion object {
        private var INSTANCE: NewsRepository? = null
        fun getInstance() = INSTANCE
            ?: NewsRepository().also {
                INSTANCE = it
            }
    }
}