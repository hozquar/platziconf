package com.example.platziconf.view.ui.fragment

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.platziconf.R
import com.example.platziconf.model.Ubication
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class UbicationFragment : Fragment(), OnMapReadyCallback , GoogleMap.OnMarkerClickListener  {
    //se añade OnMapreadycalback para sincronizar mapa y manipularlo en cualquier momento
    // se añade OnMarkerListener para al dar cllick se valla a al frargmenubicationdetail
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment= childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        //chilfrgament es el hijo del que controla el map y se le da el identificador para hacerlo atravez de un fragmet
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {//aqui ya aparece el mapa en adelante lo modificamos
        val ubication = Ubication()
        val zoom= 16f
        val centerMap = LatLng(ubication.latitud,ubication.longitud)//variable de ubicacion en el mapa
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap,zoom))
        val centerMark = LatLng(ubication.latitud,ubication.longitud)
        val markerOptions = MarkerOptions()
        markerOptions.position(centerMark)
        markerOptions.title("Platzi Conf 2019")

        val bitmapDraw = context?.applicationContext?.let { ContextCompat.getDrawable(it, R.drawable.logo_platzi) } as BitmapDrawable
        //se añade la imagen marcador de el lugar del mapa
        val smallMarker = Bitmap.createScaledBitmap(bitmapDraw.bitmap, 150 , 150,false)
        //modifico el tamaño de la imagen
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
        //carga la imagen como icono
        googleMap?.addMarker(markerOptions) //coloca LA IMAGEN EN EL MAPA
        googleMap?.setOnMarkerClickListener(this)
       }

    override fun onMarkerClick(p0: Marker?): Boolean {
        findNavController().navigate(R.id.ubicationDetailFragmentDialog)
        return true
    }
}