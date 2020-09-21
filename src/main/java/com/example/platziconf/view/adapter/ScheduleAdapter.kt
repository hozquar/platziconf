package com.example.platziconf.view.adapter

import com.example.platziconf.model.Conference
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.platziconf.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter(val scheduleListener: ScheduleListener):RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

///estructura de todo reciclerview

    var listConference= ArrayList<Conference>() //lista donde se colocan graficamente los elementos

    //metodo o diseño que se va a utilizar en la lista: archivo que se va a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule,parent,false))


    //cuantos elementos se tienen
    override fun getItemCount()=listConference.size


    // los datos que se vallan a cargarg
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val conference = listConference[position] as Conference //toma ahora las conferencias y las busca segun la posicion
        holder.tvConferenceName.text = conference.title //llama al textview le pone el tex para modificar el text y le coloca el nombre del titulo de la conferencia
        holder.tvConferenceSpeaker.text=conference.speaker
        holder.tvConferenceTag.text = conference.tag

        val simpleDataFormat = SimpleDateFormat("HH:mm")
        val simpleDateFormatAMPM = SimpleDateFormat("a")

        val cal = Calendar.getInstance()
        cal.time = conference.datetime
        val hourFormat = simpleDataFormat.format(conference.datetime)

        holder.tvConferenceHour.text = hourFormat
        holder.tvConferenceAMPM.text = simpleDateFormatAMPM.format(conference.datetime).toUpperCase()

        holder.itemView.setOnClickListener {
            scheduleListener.onConferenceClicked(conference,position)
        }
    }


    fun update(data : List<Conference>){
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    // como enlazar los elementos visuales, llama los id de item_shcedule del xml
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvConferenceName = itemView.findViewById<TextView>(R.id.tvItemScheduleconferenceName)
        val tvConferenceSpeaker = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceSpeaker)
        val tvConferenceTag = itemView.findViewById<TextView>(R.id.tvItemScheduletag)
        val tvConferenceHour = itemView.findViewById<TextView>(R.id.tvItemScheduleHour)
        val tvConferenceAMPM = itemView.findViewById<TextView>(R.id.tvItemScheduleHourAMPM)
    }
}