package com.example.sportteammakerproject.uii

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.sportteammakerproject.data.model.Match
import com.example.sportteammakerproject.data.repository.FirestoreRepo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import android.util.Log
import com.example.sportteammakerproject.data.model.Player
import com.example.sportteammakerproject.data.repository.FirestoreRepo

@Composable
fun TestScreen() {

    val context = LocalContext.current

    val firestoreRepo = FirestoreRepo()
    val m = Match(1,1, 1, true, true, "Galatasaray", "Fenerbahçe", 1, 2)

    Scaffold { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                 
            }) {
                Text("Test Tıkla")
            }
        }
    }



}