package com.example.adapt.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.adapt.R
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebResourceRequest
import androidx.appcompat.app.AppCompatActivity


class NewsDetailFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val source = arguments?.let { NewsDetailFragmentArgs.fromBundle(it).source}
        (activity as AppCompatActivity).supportActionBar?.title = source
        val url = arguments?.let { NewsDetailFragmentArgs.fromBundle(it).url }
        val myWebView: WebView = view.findViewById(R.id.myWebView)
        myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                view.loadUrl(request.url.toString())
                return false
            }
        }
        myWebView.loadUrl(url)
    }
}