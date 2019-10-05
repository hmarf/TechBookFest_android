package com.example.techbook.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.techbook.R
import com.example.techbook.adapter.CircleDBRecyclerViewAdapter
import com.example.techbook.adapter.CircleRecyclerViewAdapter
import com.example.techbook.data.db.entity.CircleDB
import com.example.techbook.viewmodel.CircleViewModel

class likeCircleFragment : Fragment() {

    private  lateinit var circleViewModel: CircleViewModel
    private lateinit var mAdapter: CircleDBRecyclerViewAdapter

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

        val listView = view.findViewById<RecyclerView>(R.id.likeRecyclerView)
        mAdapter = CircleDBRecyclerViewAdapter(view.context)

        val decor = DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL)
        listView.addItemDecoration(decor)
        listView.adapter = mAdapter
        listView.layoutManager = LinearLayoutManager(view.context)

        val swipeToDismissTouchHelper =
            getSwipeForDeleteTouchHelper(mAdapter)
        swipeToDismissTouchHelper.attachToRecyclerView(listView)

        this.circleViewModel = ViewModelProviders.of(this).get(CircleViewModel::class.java)
        this.circleViewModel.getLikeCircle().observe( this, Observer<List<CircleDB>>{
            data -> mAdapter.setData(data.toMutableList())
        })
    }

    fun getSwipeForDeleteTouchHelper(adapter: RecyclerView.Adapter<*>) =
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.LEFT,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            //スワイプ時に実行
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val uid = mAdapter.mCircleData[position].uid
                mAdapter.mCircleData.removeAt(position)
                mAdapter.notifyItemRemoved(position)
                circleViewModel.deleteData(uid)
                adapter.notifyDataSetChanged()
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                val itemView = viewHolder.itemView
                val background = ColorDrawable()
                background.color = Color.parseColor("#f44336")
                if (dX < 0)
                    background.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                else
                    background.setBounds(
                        itemView.left,
                        itemView.top,
                        itemView.left + dX.toInt(),
                        itemView.bottom
                    )
                background.draw(c)
            }
        })
}
