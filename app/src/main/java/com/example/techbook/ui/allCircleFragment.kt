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
import com.example.techbook.data.api.entity.CircleEntity
import com.example.techbook.data.api.service.CircleService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlin.concurrent.thread

class allCircleFragment : Fragment() {

    private val circleInterface by lazy { CircleService() }

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

        fetchItems()

    }

    private fun fetchItems() {

        circleInterface.circleAll().enqueue(object : Callback<List<CircleEntity>> {
            override fun onFailure(call: Call<List<CircleEntity>>?, t: Throwable?) {
                Log.d("fetchItems", "response fail")
                Log.d("fetchItems", "throwable :$t")
            }

            override fun onResponse(call: Call<List<CircleEntity>>?, response: Response<List<CircleEntity>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("fetchItems", "response success")
                        var items = mutableListOf<String>()

                        for (item in it) {
                            items.add(item.title)
                        }
                    }
                }
                Log.d("fetchItems", "response code:" + response.code())
                Log.d("fetchItems", "response errorBody:" + response.errorBody())
            }
        })
    }

}
