package com.example.techbook.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.techbook.R
import com.example.techbook.data.api.entity.CircleEntity
import com.example.techbook.data.db.entity.CircleDB
import com.example.techbook.ui.MainActivity
import com.example.techbook.viewmodel.CircleViewModel
import com.squareup.picasso.Picasso

class CircleRecyclerViewAdapter (private val context: Context):
    RecyclerView.Adapter<CircleRecyclerViewAdapter.QiitaViewHolder>() {

    private var mContext: Context? = null
    private  lateinit var circleViewModel: CircleViewModel
    var mCircleData: MutableList<CircleEntity> = mutableListOf()

    init {
        mContext = context
    }

    fun putView(aa: CircleViewModel){
        circleViewModel = aa
    }

    override fun onBindViewHolder(holder: QiitaViewHolder, position: Int) {
        val imagepath = mCircleData[position].circleImage
        Picasso.get()
            .load(imagepath)
            .resize(70, 100)
            .centerCrop()
            .into(holder.circleImage)

        // holder.circleName.text = mCircleData[position].circle
        holder.bookTitle.text = mCircleData[position].title
        holder.bookContent.text = mCircleData[position].content
        holder.linearlayoutSingle.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, MainActivity::class.java)
            val url = mCircleData[position].circleURL.replace("\n","")
            intent.putExtra("action","webview")
            intent.putExtra("URL", url)
            context.startActivity(intent)
        }
        holder.likeButton.setOnClickListener {
            circleViewModel.insertData(convertData(mCircleData[position]))
        }
        holder.circleImage.setOnClickListener {
            circleViewModel.insertData(convertData(mCircleData[position]))
        }
    }

    class QiitaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val linearlayoutSingle = view.findViewById<LinearLayout>(R.id.linerlayoutSingle)
        val circleImage = view.findViewById<ImageView>(R.id.circleImage)
        // val circleName = view.findViewById<TextView>(R.id.circleName)
        val bookTitle = view.findViewById<TextView>(R.id.bookTitle)
        val bookContent = view.findViewById<TextView>(R.id.bookContent)
        val likeButton = view.findViewById<ImageView>(R.id.likeButton)
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

    fun convertData(data: CircleEntity):CircleDB{
        val returnData = CircleDB()
        Log.v("result",data.toString())
        returnData!!.uid = data.id
        returnData!!.circle = data.circle
        returnData!!.circleURL = data.circleURL
        returnData!!.circleImage = data.circleImage
        returnData!!.arr = data.arr
        returnData!!.genere = data.genere
        returnData!!.keyword = data.keyword
        returnData!!.title = data.title
        returnData!!.content = data.content
        return returnData
    }

}


