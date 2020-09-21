package com.example.platziconf.network

import java.lang.Exception

interface Callback<T>{//la funcion llamada que recibe los datos pero puede retornar resultado excepcion
    fun onSucces(result: T?)

    fun onFailed(exception: Exception)

}






















