package com.qualitestudios.memorr.ui.importantmemories

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.qualitestudios.memorr.R
import com.qualitestudios.memorr.databinding.ImportantMemoriesFragmentBinding
import com.qualitestudios.memorr.ui.dashboard.DashboardFragmentDirections
import com.qualitestudios.memorr.ui.dashboard.memoriesrecyclerview.each_data_sealed
import com.qualitestudios.memorr.ui.importantmemories.timelinerecyclerview.TimelineItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImportantMemories : Fragment() ,TimelineItemAdapter.onItemClickListener{
private lateinit var binding:ImportantMemoriesFragmentBinding
    companion object {
        fun newInstance() = ImportantMemories()
    }

    private lateinit var viewModel: ImportantMemoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= ImportantMemoriesFragmentBinding.inflate(inflater,container,false)
        val mView=binding.root
        return mView
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ImportantMemoriesViewModel::class.java)

        viewModel.getAllMemories()
        binding.tlvRv.layoutManager=LinearLayoutManager(context)
        binding.tlvRv.adapter=TimelineItemAdapter(arrayListOf(each_data_sealed.each_data_sealed_value("","","")),this)
        viewModel.allmemories.observe(viewLifecycleOwner, Observer {

            binding.tlvRv.adapter=TimelineItemAdapter(it,this)

        })

    }

    override fun onItemClick(itemView: each_data_sealed.each_data_sealed_value) {
//


        val action=ImportantMemoriesDirections.actionNavigationMemoriesToCreateMemoryFragment(itemView,null)
        activity?.findNavController(R.id.navHostFragment)?.navigate(action)

    }

}