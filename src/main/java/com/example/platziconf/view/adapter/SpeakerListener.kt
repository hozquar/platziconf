package com.example.platziconf.view.adapter
import com.example.platziconf.model.Speaker


interface SpeakerListener {
    fun onSpeakerClicked(speaker: Speaker,position: Int)
}