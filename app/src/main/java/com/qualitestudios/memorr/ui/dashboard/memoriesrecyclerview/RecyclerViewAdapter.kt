package com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.qualitestudios.memorr.R
import kotlinx.android.synthetic.main.each_data.view.*

class RecyclerViewAdapter(
        private val dayMemoryList:List<each_data_sealed.each_data_sealed_value>,
        private val listener:onItemClickListener

):RecyclerView.Adapter<RecyclerViewAdapter.itemViewHolder> (){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
val itemView=LayoutInflater.from(parent.context).inflate(R.layout.each_data,parent,false)
        return itemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val currentItem=dayMemoryList[position]
        holder.holderdate.text=currentItem.title
        holder.holdermessage.text=currentItem.message
    }

    override fun getItemCount(): Int {
       return dayMemoryList.size
    }

    inner class itemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        var holderdate:TextView=itemView.tv_title
        var holdermessage:TextView=itemView.tv_message
init{
    itemView.setOnClickListener(this)

}

        override fun onClick(v: View?) {
            val position=adapterPosition
            if(position!=RecyclerView.NO_POSITION)
            {
                listener.onItemClick(dayMemoryList[adapterPosition])
            }


        }

    }


    interface  onItemClickListener {
        fun onItemClick(itemView: each_data_sealed.each_data_sealed_value)

    }

}