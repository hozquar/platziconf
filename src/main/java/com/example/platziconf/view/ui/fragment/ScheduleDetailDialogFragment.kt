package com.example.platziconf.view.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.platziconf.R
import com.example.platziconf.model.Conference
import kotlinx.android.synthetic.main.fragment_schelude_detail_dialog.*
import java.text.SimpleDateFormat


class ScheduleDetailDialogFragment : DialogFragment() {//se cambia ala herencia a DialogFrGMENT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreemDialogStyle)//sobrecarga onCreate para traer el estilo
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schelude_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)//SE TRAE EL TOOLBAR y lo modificamos
        tollbarConference.navigationIcon = ContextCompat.getDrawable(view.context,R.drawable.ic_close)//se le coloca un icono
        tollbarConference.setTitleTextColor(Color.WHITE)//se le cambia de color al titulo del toolbar
        tollbarConference.setNavigationOnClickListener{//y al darle click este se apaga
            dismiss()
        }
        //ahora vamos a traer el objetos que enviamos desde la pantalla de cronograma
        val conference = arguments?.getSerializable("conferences") as Conference
        /*Con la variable conference se traen los argumentos del calendario al que dimos click   */
        tollbarConference.title = conference.title
        tvScheduleTituloConferencia.text = conference.title
        val pattern = "dd/MM/yyyy hh:mm a"
        val simpleDF = SimpleDateFormat(pattern)
        val date = simpleDF.format(conference.datetime)
        tvDetailConferenceHour.text = date
        tvDetailConferenceSpeaker.text = conference.speaker
        tvDetailConferenceTag.text = conference.tag
        tvDetailConferenceDescription.text = conference.description
    }

    override fun onStart() {
        super.onStart()//aqui colocamos los margenes de la pantalla
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }

}