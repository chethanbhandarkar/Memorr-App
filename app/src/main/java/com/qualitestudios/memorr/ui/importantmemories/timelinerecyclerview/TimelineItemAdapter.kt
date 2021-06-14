package com.qualitestudios.memorr.ui.importantmemories.timelinerecyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.qualitestudios.memorr.R
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.RecyclerViewAdapter
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data_sealed
import kotlinx.android.synthetic.main.each_timeline_data.view.*

class TimelineItemAdapter(private val memoryList:List<each_data_sealed.each_data_sealed_value>,private val listener: onItemClickListener): RecyclerView.Adapter<TimelineItemAdapter.titemViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): titemViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.each_timeline_data, parent, false)
        return titemViewHolder(layout)


    }

    override fun onBindViewHolder(holder: titemViewHolder, position: Int) {

        val currentItem = memoryList[position]
        Log.d("len", currentItem.date.length.toString())
        if(currentItem.date.length>12)
        { var tex:String=""
            when(currentItem.date.substring(5,7))
            { "01"->{tex="Jan"
            }
                "02"->{tex="Feb"
                }
                "03"->{tex="Mar"
                }
                "04"->{tex="Apr"
                }
                "05"->{tex="May"
                }
                "06"->{tex="Jun"
                }
                "07"->{tex="Jul"
                }
                "08"->{tex="Aug"
                }
                "09"->{tex="Sep"
                }
                "10"->{tex="Oct"
                }
                "11"->{tex="Nov"
                }
                "12"->{tex="Dec"
                }


            }
            holder.holderdate.text = tex+" "+currentItem.date.substring(8,10)+" "+currentItem.date.substring(0,4)

        }
        else {
            holder.holderdate.text = currentItem.date
        }
        holder.holdertitle.text = currentItem.title
        holder.holdermessage.text = currentItem.message

    }


    override fun getItemCount(): Int {
        return memoryList.size
    }

    inner class titemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {

        val holderdate =  itemView.tl_tv_date
        val holdertitle = itemView.tl_tv_title
        val holdermessage = itemView.tl_tv_message

        init {
            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(memoryList[adapterPosition])
            }

        }




    }


    interface  onItemClickListener
    {
        fun onItemClick(itemView: each_data_sealed.each_data_sealed_value)
    }

}