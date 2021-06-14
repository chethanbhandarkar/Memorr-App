package com.qualitestudios.memorr.ui.creatememories

import android.app.DatePickerDialog

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle

import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI

import com.qualitestudios.memorr.R


import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data_sealed
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.create_memory_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
@AndroidEntryPoint
class CreateMemoryFragment : Fragment() {
    private var year=0;
    private var month=0;
    private var monthString:String="0"
private var monthInName:String=""
    private var day=0;
    private var daystring="0"
    private var selectedDate:String="0"
    private var calenderMonthString:String="0"
    private var hour=0;
    private var minute=0;
    private var second=0;
    private var date:String="2020"
    private var finalDate:String=""
    private var singleMemory:each_data_sealed.each_data_sealed_value=each_data_sealed.each_data_sealed_value(selectedDate,"","")

   private val args: CreateMemoryFragmentArgs by navArgs()
    companion object {
        fun newInstance() = CreateMemoryFragment()
    }

    private lateinit var viewModel: CreateMemoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_memory_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setHasOptionsMenu(true)



        viewModel = ViewModelProvider(this).get(CreateMemoryViewModel::class.java)
//get calendar
        var cal = Calendar.getInstance()

//setting default value as todays date
// this default value sd be one passed from previous
        this.year = cal.get(Calendar.YEAR)
        this.month = cal.get(Calendar.MONTH)
        this.monthString=this.month.toString()
        this.day = cal.get(Calendar.DAY_OF_MONTH)
        this.daystring=this.day.toString()


        when(this.day)
        {
            1->this.daystring="01"
            2->this.daystring="02"
            3->this.daystring="03"
            4->this.daystring="04"
            5->this.daystring="05"
            6->this.daystring="06"
            7->this.daystring="07"
            8->this.daystring="08"
            9->this.daystring="09"

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

        this.hour = cal.get(Calendar.HOUR_OF_DAY)
        this.minute = cal.get(Calendar.MINUTE)
        this.second=cal.get(Calendar.SECOND)
        this.second=cal.get(Calendar.DAY_OF_MONTH)
        this.date=this.year.toString()+"/"+this.monthString+"/"+this.daystring
        this.selectedDate=this.date

        //setting text
        this.monthInName=cal.getDisplayName(Calendar.MONTH,Calendar.LONG,Locale.getDefault())
        //setting tv value default as todays date
        tv_dateselect.text=this.monthInName+" "+this.daystring+" "+this.year.toString()

       //args check if datae is passed
        if(args.memoryData==null)  //when plus btn  clicked
        {
            //selectedDateq is in 2020/01/01
                //this.date is in 2020/01/01
                    //selectedDatq is todays date/or selected depdns
            this.date=args.selectedDateq!!
            this.selectedDate=this.date
            tv_dateselect.text=args.selectedDateq


        }
        else{
            et_title.setText(args.memoryData!!.title.toString())
            et_message.setText(args.memoryData!!.message.toString())
this.date=args.memoryData!!.date.substring(0,10)

            this.selectedDate=this.date
            Log.d("value isss",this.date)
            tv_dateselect.text=this.date.toString()
            tv_dateselect.setTextIsSelectable(false)


        }


        //when datechangeris clicked
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            this.year=year
            this.month=monthOfYear
            this.day=dayOfMonth
            this.daystring=this.day.toString()
            this.monthString=this.month.toString()

            when(this.day)
            {
                1->this.daystring="01"
                2->this.daystring="02"
                3->this.daystring="03"
                4->this.daystring="04"
                5->this.daystring="05"
                6->this.daystring="06"
                7->this.daystring="07"
                8->this.daystring="08"
                9->this.daystring="09"

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
            this.hour=cal.get(Calendar.HOUR_OF_DAY)
            this.minute=cal.get(Calendar.MINUTE)
            this.second=cal.get(Calendar.SECOND)

            this.date=this.year.toString()+"/"+this.monthString+"/"+this.daystring
            this.selectedDate=this.date
          //  val myFormat = "dd.MM.yyyy" // mention the format you need
          //  val sdf = SimpleDateFormat(myFormat, Locale.US)


            this.monthInName=cal.getDisplayName(Calendar.MONTH,Calendar.LONG,Locale.getDefault())
            //setting tv value default as todays date
            tv_dateselect.text=this.monthInName+" "+this.daystring+" "+this.year.toString()



            }



        tv_dateselect.setOnClickListener {

            context?.let { it1 ->
                DatePickerDialog(it1,dateSetListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()

            }
        }


//

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.savedeletemenu,menu)


    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menubtn_save -> {
                Log.d("menu", "clicked")
//                CoroutineScope(Dispatchers.Main).launch {
//                    Log.d("menu", singleMemory?.title.toString())
//                    singleMemory?.let { insertSingleMemory(it) }
//                }
                val cal=Calendar.getInstance()

                this.finalDate=this.selectedDate+"/"+cal.get(Calendar.HOUR_OF_DAY).toString()+"/"+cal.get(Calendar.MINUTE).toString()+"/"+cal.get(Calendar.SECOND).toString()
                if(args.memoryData!=null)
                {
                    this.finalDate= args.memoryData!!.date
                    Log.d("args", args.memoryData!!.date.toString())
                }

                Log.d("todaysdate",this.finalDate)
                val titleText:String=et_title.text!!.toString()
                val messageText:String=et_message.text.toString()
                this.singleMemory=each_data_sealed.each_data_sealed_value(this.finalDate,titleText,messageText)


                insertSingleMemory(this.singleMemory)

                findNavController().navigateUp()
                true
            }


            R.id.menubtn_delete -> {
             //   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    deleteSingleMemory(args.memoryData!!)

                findNavController().navigateUp()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }



    fun insertSingleMemory(singleMemory:each_data_sealed.each_data_sealed_value)
    {
        viewModel.insertMemory(singleMemory)
    }
    fun deleteSingleMemory(deleteMemory:each_data_sealed.each_data_sealed_value)
    {
        viewModel.deleteMemory(deleteMemory)
    }

}