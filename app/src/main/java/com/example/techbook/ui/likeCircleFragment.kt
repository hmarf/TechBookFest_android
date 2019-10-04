package com.example.techbook.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.techbook.R
import com.example.techbook.adapter.CircleDBRecyclerViewAdapter
import com.example.techbook.adapter.CircleRecyclerViewAdapter
import com.example.techbook.data.db.entity.CircleDB
import com.example.techbook.viewmodel.CircleViewModel

class likeCircleFragment : Fragment() {

    private  lateinit var circleViewModel: CircleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_like_circle, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        activity!!.setTitle("Like Book")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CircleDBRecyclerViewAdapter(view.context)

        val listView = view.findViewById<RecyclerView>(R.id.likeRecyclerView)

        val decor = DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL)
        listView.addItemDecoration(decor)
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(view.context)

        this.circleViewModel = ViewModelProviders.of(this).get(CircleViewModel::class.java)
        this.circleViewModel.getLikeCircle().observe( this, Observer<List<CircleDB>>{
            data -> adapter.setData(data.toMutableList())
        })

    }
}
