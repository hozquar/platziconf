package com.example.platziconf.view.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.platziconf.R
import com.example.platziconf.model.Speaker
import kotlinx.android.synthetic.main.fragment_speakers_detail_dialog.*


class SpeakersDetailDialogFragment : DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarSpeakerDetailDialog.navigationIcon = ContextCompat.getDrawable(view.context,R.drawable.ic_close)
        toolbarSpeakerDetailDialog.setTitleTextColor(Color.WHITE)
        toolbarSpeakerDetailDialog.setNavigationOnClickListener{
            dismiss()
        }
        val speaker = arguments?.getSerializable("speakers") as Speaker
        toolbarSpeakerDetailDialog.title = speaker.name

        ivDetailDialogSpeakerUrlTwitter.setOnClickListener{
            val url = speaker.twitter
            val uri= Uri.parse("https://twitter.com/"+url)
            val launhBrowser = Intent(Intent.ACTION_VIEW, uri)
            startActivity(launhBrowser)

        }
        Glide.with(ivDetailDialogSpeakerPhoto.context) //indicamos el contexto de la imagen
            .load(speaker.image) //la carga del url de la imagen
            .apply(RequestOptions.circleCropTransform()) //transforma la imagen cortandola en un circulo
            .into(ivDetailDialogSpeakerPhoto) //va a colocar la imagen ahi

        tvDetailSpeakerName.text = speaker.name
        tvDetailSpeakerJobtittle.text = speaker.jobtitle
        tvDetailSpeakerJob.text = speaker.workplace
        tvDetailSpeakerAbstract.text = speaker.biography
    }

    override fun onStart() {
        super.onStart()//aqui colocamos los margenes de la pantalla
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }

}