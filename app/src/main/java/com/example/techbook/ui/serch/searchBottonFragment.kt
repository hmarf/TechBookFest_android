package com.example.techbook.ui.serch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.techbook.R
import com.example.techbook.ui.adapter.CircleAPIRecyclerViewAdapter
import com.example.techbook.data.api.entity.CircleEntityResult
import com.example.techbook.data.api.service.circleService
import com.example.techbook.viewmodel.CircleViewModel
import kotlin.concurrent.thread

class searchBottonFragment : Fragment() {

    private val circleInterface by lazy { circleService() }

    var keyword: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_botton, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        activity!!.setTitle("Search Book")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CircleAPIRecyclerViewAdapter(view!!.context)

        val userViewModel = ViewModelProviders.of(this).get(CircleViewModel::class.java)
        adapter.putView(userViewModel)

        val listView = view.findViewById<RecyclerView>(R.id.circleSearch)

        val decor = DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL)
        listView.addItemDecoration(decor)
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(view.context)

        val submitButton = view.findViewById<Button>(R.id.submitButton)
        val editKey = view.findViewById<EditText>(R.id.searchKey)

        submitButton.setOnClickListener {
            keyword = editKey.text.toString()
            thread {
                SearchBook(){
                        itemList -> adapter.setQiitaData(itemList.result)
                }
            }
        }
    }

    private fun SearchBook(callback: (CircleEntityResult) -> Unit) {

//        circleInterface.circleSearch(keyword).enqueue(object : Callback<CircleEntityResult> {
//            override fun onFailure(call: Call<CircleEntityResult>?, t: Throwable?) {
//                Log.d("fetchItems", "response fail")
//                Log.d("fetchItems", "throwable :$t")
//            }
//
//            override fun onResponse(call: Call<CircleEntityResult>?, response: Response<CircleEntityResult>) {
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        Log.d("fetchItems", "response success")
//                        response.body()?.let {
//                            callback(it)
//                        }
//                    }
//                }
//                Log.d("fetchItems", "response code:" + response.code())
//                Log.d("fetchItems", "response errorBody:" + response.errorBody())
//            }
//        })
    }

}
