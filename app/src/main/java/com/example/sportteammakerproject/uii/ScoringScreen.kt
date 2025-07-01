package com.example.sportteammakerproject.uii

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.navigation.compose.rememberNavController

import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sportteammakerproject.R
import com.example.sportteammakerproject.viewModel.AuthState
import com.example.sportteammakerproject.viewModel.AuthViewModel

val scores = (1..10).toList()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoringScreen(navController: NavController, authViewModel: AuthViewModel) {

    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate("LoginScreen")
            else -> Unit
        }
    }


    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Scoring Page", fontWeight = FontWeight.Bold) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = Color.Black
            )
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Spacer(Modifier.padding(35.dp))

            Column(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "Player Image",
                    Modifier.size(206.dp)
                )

                Spacer(Modifier.padding(16.dp))
                Text(text = "Buğra Kaplan", fontSize = 26.sp)
                Spacer(Modifier.padding(16.dp))
                Text(text = "8.00", fontSize = 26.sp)

                Spacer(Modifier.padding(40.dp))

                ScoreSelector2() {

                }
                Spacer(Modifier.padding(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Next Player",
                            Modifier.size(33.dp)
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.ArrowForward,
                            contentDescription = "Next Player",
                            Modifier.size(33.dp)
                        )
                    }

                    Button(onClick = {
                        authViewModel.signout()
                    }) { Text("Çıkış Yap") }

                }

            }


        }
    }
}


@Composable
fun ScoreSelector2(onScoreSelected: (Int) -> Unit) {
    val scores = (1..10).toList()
    val chunkedScores = scores.chunked(5) //5erli iki alt liste: [1-5], [6-10]

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        chunkedScores.forEach { rowScores ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowScores.forEach { score ->
                    Button(
                        onClick = { onScoreSelected(score) },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .padding(horizontal = 2.dp)
                            .widthIn(min = 74.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$score",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }


                }

            }
        }
    }
}

//@SuppressLint("ViewModelConstructorInComposable")
//@Preview(showBackground = true)
//@Composable
//fun ScoringScreenPreview() {
//
//    ScoringScreen(navController = rememberNavController(), authViewModel = AuthViewModel())
//
//}