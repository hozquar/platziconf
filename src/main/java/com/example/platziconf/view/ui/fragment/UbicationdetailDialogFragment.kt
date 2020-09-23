package com.example.platziconf.view.ui.fragment


import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.platziconf.R
import com.example.platziconf.model.Ubication
import kotlinx.android.synthetic.main.fragment_ubicationdetail_dialog.*


class UbicationdetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreemDialogStyle)//sobrecarga onCreate para traer el estilo
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ubicationdetail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarUbication.navigationIcon = ContextCompat.getDrawable(view.context,R.drawable.ic_close)//se le coloca un icono
        toolbarUbication.setTitleTextColor(Color.WHITE)//se le cambia de color al titulo del toolbar
        toolbarUbication.setNavigationOnClickListener{//y al darle click este se apaga
            dismiss()
        }

        val ubication = Ubication()

        toolbarUbication.title = ubication.name
            tvDetailNombreUbicacion.text= ubication.name
        tvDireccionLugar.text=ubication.address
        tvTelefonoLugar.text = ubication.phone
        tvLinkLugar.text = ubication.website

        //dar opcion de click al linearlayuout del link del lugar
        lLLinkLugar.setOnClickListener{
            val intent= Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(ubication.website)
            startActivity(intent)
        }

        //dar opcion de llamar al linear layout del telefono
        lLTelefono.setOnClickListener {
            val intent =   Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${ubication.phone}")
            }
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)//da tamaño añ ubicationdetailfratgment


    }

}