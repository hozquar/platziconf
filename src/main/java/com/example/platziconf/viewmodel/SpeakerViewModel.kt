package com.example.platziconf.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.platziconf.model.Speaker
import com.example.platziconf.network.Callback
import com.example.platziconf.network.FirestoreService

//esta funcion sirve igual que la deScheduleViewModel pero con Speakers
class SpeakerViewModel:ViewModel() {
    val firestoreService = FirestoreService()
    var listSpeaker: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getSpeakerFirebase()
    }

    fun getSpeakerFirebase(){
        firestoreService.getSpeakers(object: Callback<List<Speaker>> {
            override fun onSucces(result: List<Speaker>?) {
                listSpeaker.postValue(result)
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