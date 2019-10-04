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
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.techbook.R
import com.example.techbook.adapter.CircleRecyclerViewAdapter
import com.example.techbook.data.api.entity.CircleEntity
import com.example.techbook.data.api.entity.CircleEntityResult
import com.example.techbook.data.api.service.CircleService
import com.example.techbook.viewmodel.CircleViewModel
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

        val userViewModel = ViewModelProviders.of(this).get(CircleViewModel::class.java)
        adapter.putView(userViewModel)

        val listView = view.findViewById<RecyclerView>(R.id.circleAll)

        val decor = DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL)
        listView.addItemDecoration(decor)
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(view.context)

        thread {
            fetchItems(){
                itemList -> adapter.setQiitaData(itemList.result)
            }
        }

    }

    private fun fetchItems(callback: (CircleEntityResult) -> Unit) {

        circleInterface.circleAll().enqueue(object : Callback<CircleEntityResult> {
            override fun onFailure(call: Call<CircleEntityResult>?, t: Throwable?) {
                Log.d("fetchItems", "response fail")
                Log.d("fetchItems", "throwable :$t")
            }

            override fun onResponse(call: Call<CircleEntityResult>?, response: Response<CircleEntityResult>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("fetchItems", "response success")
                        response.body()?.let {
                            callback(it)
                        }
                        var items = mutableListOf<String>()
                        /*itemRepository.getAllCircle() { itemList ->
                            adapter.setQiitaData(itemList)
                        }*/
                        /*for (item in it) {
                            items.add(item.title)
                        }*/
                    }
                }
                Log.d("fetchItems", "response code:" + response.code())
                Log.d("fetchItems", "response errorBody:" + response.errorBody())
            }
        })
    }

}
