package com.example.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.platziconf.model.Conference
import com.example.platziconf.network.Callback
import com.example.platziconf.network.FirestoreService
import java.lang.Exception

//esta clase llama los datos y los guarda

class ScheduleViewModel:ViewModel() { //aqui se mantiene refresca los datos por fuera de la activity asi se cambie la pantalla de posicion
    val firestoreService = FirestoreService() //llama a la clase Firestore (objeto)
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData() //hace que los datos sean variables
    var isLoading = MutableLiveData<Boolean>()//lo hace para que la pantalla de carga termine cuando ya estan cargados

    fun refresh(){ //funcion que llama a la funcion que busca los fatos
        getScheduleFirebase()
    }

    fun getScheduleFirebase(){ //llama a la lista calendario guardada en el callback y guarda los datos aqui
        firestoreService.getSchedule(object: Callback<List<Conference>>{   //
            override fun onSucces(result: List<Conference>?) {
                listSchedule.postValue(result)
                //lisSchedulev guarda los datos de firebase, recibe el result de la lista ordenada
                processFinished()//termina la carga de la lista y guarda
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