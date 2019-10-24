package com.example.techbook.ui.adapter

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
import com.example.techbook.ui.MainActivity
import com.example.techbook.viewmodel.CircleViewModel
import com.squareup.picasso.Picasso

class CircleAPIRecyclerViewAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<CircleAPIRecyclerViewAdapter.QiitaViewHolder>() {

    interface Listener {
        fun onClickImage(circle: CircleEntity)
    }

    private lateinit var circleViewModel: CircleViewModel
    var mCircleData: MutableList<CircleEntity> = mutableListOf()

    //adapterにviewModelを持たせない方針なら不要
    fun putView(aa: CircleViewModel) {
        circleViewModel = aa
    }

    override fun onBindViewHolder(holder: QiitaViewHolder, position: Int) {
        val circle = mCircleData[position]
        val imagepath = circle.circleImage
        Picasso.get()
            .load(imagepath)
            .resize(70, 100)
            .centerCrop()
            .into(holder.circleImage)

        // holder.circleName.text = mCircleData[position].circle
        holder.bookTitle.text = circle.title
        holder.bookContent.text = circle.content
        holder.linearlayoutSingle.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, MainActivity::class.java)
            val url = circle.circleURL.replace("\n", "")
            intent.putExtra("action", "webview")
            intent.putExtra("URL", url)
            context.startActivity(intent)
        }
        holder.circleImage.setOnClickListener {
            listener.onClickImage(circle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QiitaViewHolder =
        QiitaViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.circle,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = mCircleData.size


    fun setQiitaData(user: List<CircleEntity>) {
        Log.v("aaaa", "ok")
        this.mCircleData = user.toMutableList()
        notifyDataSetChanged()
    }

    class QiitaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val linearlayoutSingle = view.findViewById<LinearLayout>(R.id.linerlayoutSingle)
        val circleImage = view.findViewById<ImageView>(R.id.circleImage)
        // val circleName = view.findViewById<TextView>(R.id.circleName)
        val bookTitle = view.findViewById<TextView>(R.id.bookTitle)
        val bookContent = view.findViewById<TextView>(R.id.bookContent)
    }
}
