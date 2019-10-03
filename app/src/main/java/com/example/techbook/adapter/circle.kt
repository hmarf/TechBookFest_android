package com.example.techbook.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.techbook.R
import com.example.techbook.data.api.entity.CircleEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.circle.view.*

class CircleRecyclerViewAdapter (private val context: Context):
    RecyclerView.Adapter<CircleRecyclerViewAdapter.QiitaViewHolder>() {

    private var mContext: Context? = null
    var mCircleData: MutableList<CircleEntity> = mutableListOf()

    init {
        mContext = context
    }

    override fun onBindViewHolder(holder: QiitaViewHolder, position: Int) {
        val imagepath = mCircleData[position].circleImage
        Picasso.get()
            .load(imagepath)
            .resize(100, 100)
            .centerCrop()
            .into(holder.circleImage)

        holder.circleName.text = mCircleData[position].circle
        holder.bookTitle.text = mCircleData[position].title
        holder.bookContent.text = mCircleData[position].content
    }

    class QiitaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val circleImage = view.findViewById<ImageView>(R.id.circleImage)
        val circleName = view.findViewById<TextView>(R.id.circleName)
        val bookTitle = view.findViewById<TextView>(R.id.bookTitle)
        val bookContent = view.findViewById<TextView>(R.id.bookContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QiitaViewHolder =
        QiitaViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.circle,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = mCircleData.size

    fun setQiitaData(user: List<CircleEntity>){
        Log.v("aaaa","ok")
        this.mCircleData = user.toMutableList()
        notifyDataSetChanged()
    }

}


