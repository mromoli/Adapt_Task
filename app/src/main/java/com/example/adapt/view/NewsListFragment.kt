package com.example.adapt.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.Observer
import com.example.adapt.databinding.FragmentNewsListBinding
import com.example.adapt.repository.NewsListAdapter
import com.example.adapt.viewmodel.NewsListViewModel
import kotlinx.android.synthetic.main.fragment_news_list.*
import org.jetbrains.anko.longToast

class NewsListFragment : Fragment() {
    private lateinit var viewDataBinding: FragmentNewsListBinding
    private lateinit var adapter: NewsListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentNewsListBinding.inflate(inflater, container, false).apply {
            viewmodel =
                ViewModelProviders.of(this@NewsListFragment).get(NewsListViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel?.fetchNewsList()

        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.newsListLive?.observe(viewLifecycleOwner, Observer {
            adapter.updateNewsList(it)
        })

        viewDataBinding.viewmodel?.toastMessage?.observe(viewLifecycleOwner, Observer {
            activity?.longToast(it)
        })
    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = NewsListAdapter(viewDataBinding.viewmodel!!)
            val layoutManager = LinearLayoutManager(activity)
            news_list_rv.layoutManager = layoutManager
            news_list_rv.adapter = adapter
        }
    }
}