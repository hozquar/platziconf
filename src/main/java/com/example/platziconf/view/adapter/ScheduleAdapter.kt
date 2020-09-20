package com.example.platziconf.view.adapter

import android.telecom.Conference
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.platziconf.R

class ScheduleAdapter():RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    var listConference= ArrayList<Conference>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule,parent,false))

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount()=listConference.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        
    }

}