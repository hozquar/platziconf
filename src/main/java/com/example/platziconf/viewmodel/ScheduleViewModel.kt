package com.example.platziconf.viewmodel

import android.telecom.Conference
import androidx.lifecycle.MutableLiveData
import com.example.platziconf.network.Callback
import com.example.platziconf.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel { //aqui se mantiene refresca los datos por fuera de la activity asi se cambie la pantalla de posicion
    val firestoreService = FirestoreService() //llama a la clase Firestore (objeto)
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData() //hace que los datos sean variables
    var isLoading = MutableLiveData<Boolean>()//lo hace para que la pantalla de carga termine cuando ya estan cargados

    fun refresh(){
        getScheduleFirebase()
    }

    fun getScheduleFirebase(){ //llama a la lista calendario guardada en el callback
        firestoreService.getSchedule(object: Callback<List<Conference>>{
            override fun onSucces(result: List<Conference>) {
                listSchedule.postValue(result) //si es valor es el adecuado la lista mutable recibe el resultado de la lista ordenada
                processFinished()//termina la carga de la lista
            }

            override fun onFailed(exception: Exception) {
                processFinished() //algo salio mal, termina la carga
            }
        })
    }

    fun processFinished(){
        isLoading.value=true //la carga termina en true
    }
}