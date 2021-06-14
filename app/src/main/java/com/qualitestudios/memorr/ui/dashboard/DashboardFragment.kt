package com.qualitestudios.memorr.ui.dashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.qualitestudios.memorr.R


import com.qualitestudios.memorr.databinding.DashboardFragmentBinding

import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.RecyclerViewAdapter
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data_sealed
import com.qualitestudios.memorr.ui.importantmemories.ImportantMemoriesDirections
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.android.synthetic.main.dashboard_fragment.*

import java.util.*


@AndroidEntryPoint
class DashboardFragment : Fragment(),RecyclerViewAdapter.onItemClickListener {


    private var year=0;
    private var month=0;
    private var day=0;
    private var dayString:String="0"
    private var monthString:String="0"
    private var calenderDayString:String="0"
    private var calenderMonthString:String="0"
    private var hour=0;
    private var minute=0;
    private var second=0;
    private var selectedDate:String="0"
    private var date:String="2020"

    private lateinit var binding:DashboardFragmentBinding
    private lateinit var viewModel: DashboardViewModel
    private  var layoutislinear:Boolean=true




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DashboardFragmentBinding.inflate(inflater,container,false)
        val view=binding.root
        return view
    }





 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     var cal = Calendar.getInstance()

//setting default value as todays date
     this.year = cal.get(Calendar.YEAR)
     this.month = cal.get(Calendar.MONTH)
     this.calenderMonthString=this.month.toString()
     this.monthString=this.month.toString()
     this.day = cal.get(Calendar.DAY_OF_MONTH)
     this.dayString=this.day.toString()
     //changing date in case its 1 2 3 to 01 02 03
     when(this.day)
     {
         1->this.dayString="01"
         2->this.dayString="02"
         3->this.dayString="03"
         4->this.dayString="04"
         5->this.dayString="05"
         6->this.dayString="06"
         7->this.dayString="07"
         8->this.dayString="08"
         9->this.dayString="09"


     }
     when(this.month)
     {
         0->this.monthString="01"
         1->this.monthString="02"
         2->this.monthString="03"
         3->this.monthString="04"
         4->this.monthString="05"
         5->this.monthString="06"
         6->this.monthString="07"
         7->this.monthString="08"
         8->this.monthString="09"
         9->this.monthString="10"
         10->this.monthString="11"
         11->this.monthString="12"



     }

     //time
     this.hour = cal.get(Calendar.HOUR_OF_DAY)
     this.minute = cal.get(Calendar.MINUTE)
     this.second=cal.get(Calendar.SECOND)


     this.date=this.year.toString()+"/"+this.monthString+"/"+this.dayString
     this.selectedDate=this.date






















     binding.mainRecyclerView.layoutManager=LinearLayoutManager(context)
     viewModel  = ViewModelProvider(this).get(DashboardViewModel::class.java)

     Log.d("value",this.arguments.toString())





//PART 2 get todays recyclerview by default when launched
     viewModel.getMemoriesForDates(this.selectedDate)

     this.calenderDayString=this.dayString


     //observe the recyclerview
     viewModel.memoryListForDate.observe(viewLifecycleOwner, Observer {
         binding.mainRecyclerView.adapter=RecyclerViewAdapter(it,this)

     })


















//PART3 Listen for cal date change and get list of memories for that day
     mainCalender.setOnDateChangeListener { view, year, month, dayOfMonth ->
         this.calenderDayString=dayOfMonth.toString()
         this.calenderMonthString=(month+1).toString()
         when(dayOfMonth)
         {
             1->this.calenderDayString="01"
             2->this.calenderDayString="02"
             3->this.calenderDayString="03"
             4->this.calenderDayString="04"
             5->this.calenderDayString="05"
             6->this.calenderDayString="06"
             7->this.calenderDayString="07"
             8->this.calenderDayString="08"
             9->this.calenderDayString="09"


         }
         when(month)
         {
             0->this.calenderMonthString="01"
             1->this.calenderMonthString="02"
             2->this.calenderMonthString="03"
             3->this.calenderMonthString="04"
             4->this.calenderMonthString="05"
             5->this.calenderMonthString="06"
             6->this.calenderMonthString="07"
             7->this.calenderMonthString="08"
             8->this.calenderMonthString="09"
             9->this.calenderMonthString="10"

             10->this.calenderMonthString="11"
             11->this.calenderMonthString="12"



         }
         this.selectedDate=year.toString()+"/"+this.calenderMonthString+"/"+this.calenderDayString
         Log.d("currentdate",selectedDate)
         viewModel.getMemoriesForDates(this.selectedDate)

     }

//at this place
   //  this.selected date will be todays date or selected date in format 2020/01/01
     //PART1 CREATING A NEW MEMORY - NAVIGATE TO CREATE MEMORY FRAGMENT
     btn_creatememory.setOnClickListener {
         val action=DashboardFragmentDirections.actionNavigationDashboardToCreateMemoryFragment(null,this.selectedDate)
         view.findNavController().navigate(action)
     }


//PART4 List layout change
     binding.changegridbutton.setOnClickListener {
         if(layoutislinear) {
            binding.changegridbutton.setBackgroundResource(R.drawable.ic_baseline_menu_24)
             binding.mainRecyclerView.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
             layoutislinear=false
         }
         else
         {          binding.changegridbutton.setBackgroundResource(R.drawable.ic_grid2)
             binding.mainRecyclerView.layoutManager=LinearLayoutManager(context)
             layoutislinear=true
         }

     }


//PART5 Observe for no of memories found
     viewModel.noofmemoriesfound.observe(viewLifecycleOwner,{

         binding.tvNoofmemories.text="Found "+it.toString()+" Memories"
     })



    }





    override fun onItemClick(eachMemory:each_data_sealed.each_data_sealed_value) {
       Log.d("itemclicked",eachMemory.date.toString()+eachMemory.title.toString())
       val action=DashboardFragmentDirections.actionNavigationDashboardToCreateMemoryFragment(eachMemory,null)

        activity?.findNavController(R.id.navHostFragment)?.navigate(action)



    }


}