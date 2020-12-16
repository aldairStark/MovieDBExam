package com.example.viewModel.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewModel.R
import com.example.viewModel.common.MyApp
import com.example.viewmodel.ui.dashboard.DasboardAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {


    private lateinit var adapter: DasboardAdapter

       private val viewModel by lazy {
           ViewModelProvider(this).get(DashboardViewModel::class.java)
       }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

        }
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_dashboard,container, false)
        observer()
        adapter= DasboardAdapter(MyApp.instance)
        recyclerView?.layoutManager=LinearLayoutManager(MyApp.instance)
        recyclerView?.adapter=adapter



        return view

    }
    fun observer(){
        viewModel.fetchUserData().observe(viewLifecycleOwner, Observer {
            adapter.setListUser(it)
            adapter.notifyDataSetChanged()
        })

    }
}