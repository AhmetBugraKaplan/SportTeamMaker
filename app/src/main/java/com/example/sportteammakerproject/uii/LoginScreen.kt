package com.example.sportteammakerproject.uii

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sportteammakerproject.R
import com.example.sportteammakerproject.viewModel.AuthState
import com.example.sportteammakerproject.viewModel.AuthViewModel


@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate("ScoringScreen")
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message,
                Toast.LENGTH_SHORT
            ).show()

            else-> Unit
        }


    }


    val image = painterResource(id = R.drawable.ic_launcher_background)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 38.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = image,
                contentDescription = "Login main image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth(0.6f)
            )
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Color(0xFFF7F7F7))
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = "Giriş Yap",
                style = TextStyle(fontWeight = FontWeight.Bold, letterSpacing = 1.sp),
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.padding(12.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("E-posta") },
                modifier = Modifier.fillMaxWidth(0.85f)
            )

            Spacer(modifier = Modifier.padding(8.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Şifre") },
                modifier = Modifier.fillMaxWidth(0.85f)
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Button(
                onClick = { authViewModel.login(email, password) },
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(50.dp)
            ) {
                Text(text = "Giriş Yap", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = "Kayıt Ol",
                color = Color.Gray,
                modifier = Modifier
                    .clickable { navController.navigate("SignUpScreen") }
                    .padding(top = 8.dp)
            )
        }
    }
}

