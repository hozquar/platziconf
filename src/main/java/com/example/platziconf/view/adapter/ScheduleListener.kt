package com.example.platziconf.view.adapter
import com.example.platziconf.model.Conference

import java.text.FieldPosition

interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}