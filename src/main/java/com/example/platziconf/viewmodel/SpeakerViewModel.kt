package com.example.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.platziconf.model.Speaker
import com.example.platziconf.network.Callback
import com.example.platziconf.network.FirestoreService
import java.lang.Exception
//esta funcion sirve igual que la deScheduleViewModel pero con Speakers
class SpeakerViewModel {
    val firestoreService = FirestoreService()
    var listSchedule: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getSpeakerFirebase()
    }

    fun getSpeakerFirebase(){
        firestoreService.getSpeakers(object: Callback<List<Speaker>> {
            override fun onSucces(result: List<Speaker>?) {
                listSchedule.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun processFinished(){
        isLoading.value=true
    }
}