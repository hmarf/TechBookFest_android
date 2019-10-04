package com.example.techbook.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.techbook.R
import com.example.techbook.adapter.CircleRecyclerViewAdapter
import kotlin.concurrent.thread

class webViewFragment : Fragment() {

    interface Listner{
        fun move_to_WebView(url: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        activity!!.setTitle("Web View")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        val url = bundle?.getString("URL")

        val myWebView = view.findViewById<WebView>(R.id.webview)
        myWebView.webViewClient = WebViewClient()
        myWebView.loadUrl(url)
        myWebView.settings.javaScriptEnabled = true

    }
}
