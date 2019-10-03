package com.example.techbook.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.techbook.R
import com.example.techbook.adapter.CircleRecyclerViewAdapter
import com.example.techbook.data.api.service.CircleRepository
import kotlin.concurrent.thread

class allCircleFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_circle, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        activity!!.setTitle("All Book")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CircleRecyclerViewAdapter(view!!.context)

        val listView = view.findViewById<RecyclerView>(R.id.circleAll)

        val decor = DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL)
        listView.addItemDecoration(decor)
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(view.context)
        Log.v("aaaa","bbbb")
        val itemRepository = CircleRepository()

        thread {
            itemRepository.getAllCircle() { itemList ->
                adapter.setQiitaData(itemList)
            }
        }
    }
}
