package com.example.platziconf.view.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.platziconf.R
import com.example.platziconf.model.Conference
import com.example.platziconf.view.adapter.ScheduleAdapter
import com.example.platziconf.view.adapter.ScheduleListener
import com.example.platziconf.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_shcedule.*

class ShceduleFragment : Fragment(), ScheduleListener{//implementa con dos puntos":" y para adicionar concoma ","
    private lateinit var scheduleAdapter: ScheduleAdapter //importa el adaptador es el recyclerview
    private lateinit var viewModel: ScheduleViewModel //el lector de los datos donde estan los datos guardados, firebase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_shcedule, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)//con ViewModel le agrega la clase ScheduleViewModel
        viewModel.refresh() //la activa para buscar los datos

        scheduleAdapter = ScheduleAdapter(this) //trae el adaptador

        rv_Schcedule.apply {
            layoutManager = LinearLayoutManager(view.context , LinearLayoutManager.VERTICAL , false)
            adapter = scheduleAdapter //trae el recicler vier de fragmet_scheddule.xml
        }

        observerViewModel() //la mantiene obervada

    }

    fun observerViewModel(){ //para observarla
        viewModel.listSchedule.observe(this, Observer <List<Conference>>{ schedule->
            scheduleAdapter.update(schedule)
        })

        viewModel.isLoading.observe(this, Observer<Boolean>{
            if (it != null)
                rlBaseSchedule.visibility = View.INVISIBLE
        })

    }

    override fun onConferenceClicked(conference: Conference, position: Int) {
        val bundle = bundleOf("conference" to conference)

    }


}