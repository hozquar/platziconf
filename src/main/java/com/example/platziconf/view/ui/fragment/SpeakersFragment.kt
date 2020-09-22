package com.example.platziconf.view.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.example.platziconf.R
import com.example.platziconf.model.Speaker
import com.example.platziconf.view.adapter.SpeakersAdapter
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
            LayoutManager = GridLayoutManager(view.context , 2)
            adapter = speakerAdapter
        }
        observerViewModel()
    }

    fun observerViewModel(){
        viewModel.listSpeaker.observe(this, Observer<List<Speaker>>{ speakers ->
            speakerAdapter.update(speakers)
        })
        viewModel.isLoading.observe(this,{
            if(it != null)
                rlBaseSpeakers.visibility = View.INVISIBLE
        })

    }


    override fun onSpeakerClicked(speaker: Speaker, position: Int) {
        val bundle = bundleOf("speakers" to speaker)
        findNavController().navigate(R.id.speakersDetailDialogFragment, bundle)
        TODO("Not yet implemented")
    }


}




