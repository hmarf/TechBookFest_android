package com.example.techbook.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.techbook.R
import com.example.techbook.adapter.CircleRecyclerViewAdapter
import com.example.techbook.data.api.service.CircleInterface
import com.example.techbook.data.api.service.circleService
import com.example.techbook.data.db.AppDatabase
import kotlinx.android.synthetic.main.fragment_all_circle.*
import java.lang.IllegalArgumentException

class AllCircleFragment : Fragment() {

    private val circleInterface by lazy { circleService() }
    private val appDatabase by lazy { AppDatabase.get(requireContext()) }
    private val viewModelFactory by lazy { Factory(circleInterface, appDatabase) }
    private lateinit var viewModel: AllCircleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AllCircleViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_circle, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        activity?.title = "All Book"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CircleRecyclerViewAdapter(requireContext())

//        adapter.putView(viewModel)

        val decor = DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL)
        circleList.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(view.context)
            addItemDecoration(decor)
        }

        viewModel.apply {
            fetchAllCircle()
            allCircle.observe(viewLifecycleOwner, Observer {
                adapter.setQiitaData(it)
            })
        }
    }

    class Factory(
        private val circleInterface: CircleInterface,
        private val appDatabase: AppDatabase
    ) : ViewModelProvider.Factory{
        @Suppress("UNCHECKED CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(AllCircleViewModel::class.java)){
                return AllCircleViewModel(circleInterface, appDatabase) as T
            }
            throw IllegalArgumentException("Unknown class name")
        }

    }
}
