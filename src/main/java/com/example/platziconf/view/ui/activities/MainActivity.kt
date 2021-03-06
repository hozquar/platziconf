package com.example.platziconf.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.platziconf.R
import com.google.firebase.firestore.FirebaseFirestore
import com.example.platziconf.model.Conference
import com.example.platziconf.model.Speaker
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBar(findViewById(R.id.tb_Main)) //cambia el toobal por el bonito de platzi
        configNav()
    }
    fun configNav(){
        NavigationUI.setupWithNavController(bnvMenu, Navigation.findNavController(this, R.id.fragContent))
        //con esta funcion da todo el programa a fragcontent que lleva todo enzima de el el cual esta en el main

    }
}