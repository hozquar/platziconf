package com.example.platziconf.view.ui.fragment

import SpeakersAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.platziconf.R
import com.example.platziconf.model.Speaker
import com.example.platziconf.view.adapter.SpeakerListener
import com.example.platziconf.viewmodel.SpeakerViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*


class SpeakersFragment : Fragment(), SpeakerListener {
    private lateinit var speakerAdapter:SpeakersAdapter
    private lateinit var viewModel: SpeakerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProviders.of(this).get(SpeakerViewModel::class.java)
        viewModel.refresh()
        speakerAdapter = SpeakersAdapter(this)
        rv_Speakers.apply {
            RecyclerView.LayoutManager = LinearLayoutManager(view.context , LinearLayoutManager.VERTICAL,false)
            adapter = speakerAdapter
        }
        observerViewModel()
    }

    fun observerViewModel(){
        viewModel.listSchedule.observe(this,{speaker ->
            speakerAdapter.update(speaker)
        })
        viewModel.isLoading.observe(this,{
            if(it != null)
                rlBaseSpeakers.visibility = View.INVISIBLE
        })

    }


    override fun onSpeakerClicked(speaker: Speaker, position: Int) {
        val bundle = bundleOf("speaker" to conference)
        findNavController().navigate(R.id.speakersDetailDialogFragment, bundle)
        TODO("Not yet implemented")
    }


}



