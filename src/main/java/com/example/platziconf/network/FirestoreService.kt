package com.example.platziconf.network

import com.example.platziconf.model.Conference
import com.example.platziconf.model.Speaker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings


//llama a las bases de datos conference y speakers para ser leidas en FirestoreService
const val CONFERENCES_COLLECTION_NAME = "conferences"
const val SPEAKERS_COLLECTION_NAME = "speakers"

class FirestoreService {
    //crea variable firebaseFirestore para agregar la base de firebase y crear la instancia
val firebaseFirestore = FirebaseFirestore.getInstance()
    //la variable settings es para que la base se mantenga asi no halla coneccion
val settings= FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init { //iniciador de la base de datos para que siempre este disponible sin coneccion
        firebaseFirestore.firestoreSettings = settings
    }

    //esta funciion llama a la lista del Speakers_collection o la base de firesstore
    fun getSpeakers(callback: Callback<List<Speaker>>) {
        firebaseFirestore.collection(SPEAKERS_COLLECTION_NAME)
            .orderBy("category") //la organiza por categoria
            .get() //La  obtiene
            .addOnSuccessListener { result-> //la guarda como result ordenada y la envia a callback
                for (doc in result){
                    val list=result.toObjects(Speaker::class.java)
                    callback.onSucces(list)
                    break
                 }
            }

    }
    //lo mismo que la anterior funcion pero con speakers
    fun getSchedule(callback: Callback<List<Conference>>){
        firebaseFirestore.collection(CONFERENCES_COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result->
                for (doc in result){
                    val list=result.toObjects(Conference::class.java)
                    callback.onSucces(list)
                    break
                }
            }
    }

}