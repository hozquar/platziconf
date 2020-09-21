package com.example.platziconf.view.adapter

import android.telecom.Conference
import java.text.FieldPosition

interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}