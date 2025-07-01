package com.example.sportteammakerproject.uii

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sportteammakerproject.Greeting
import com.example.sportteammakerproject.R
import com.example.sportteammakerproject.data.model.Match
import com.example.sportteammakerproject.data.repository.FirestoreRepo
import com.example.sportteammakerproject.ui.theme.SportTeamMakerProjectTheme
import com.example.sportteammakerproject.viewModel.AuthViewModel



val playerTextStyle = TextStyle(
    fontSize = 18.sp,
    fontWeight = FontWeight.Normal,
    color = Color.Black
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerScreen(navController: NavController, authViewModel: AuthViewModel) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Oyuncu Bilgileri", fontWeight = FontWeight.Bold) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = Color.Black
            )
        )


    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "Player Image",
                    Modifier.size(156.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Player Name", style = playerTextStyle)
                Spacer(Modifier.padding(5.dp))
                Text(text = "Player LastName", style = playerTextStyle)
            }

            Text(text = "Player Position :", style = playerTextStyle)
            Text(text = "Player Age :", style = playerTextStyle)
            Text(text = "Player Avarage Point :", style = playerTextStyle)

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Next Player",Modifier.size(33.dp))
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.ArrowForward, contentDescription = "Next Player",Modifier.size(33.dp))
                }

            }

        }


    }
}


