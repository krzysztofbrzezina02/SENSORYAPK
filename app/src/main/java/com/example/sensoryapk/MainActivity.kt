package com.example.sensoryapk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sensoryapk.ui.theme.SENSORYAPKTheme
import com.example.sensoryapk.ui.theme.lightgreen
import com.example.sensoryapk.ui.theme.orange
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SENSORYAPKTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {
                    
                    composable("home"){HomeScreen(navController = navController)}
                    composable("Luxomierz"){val viewModel = hiltViewModel<MainViewModel>()
                        Lux(navController = navController,viewModel)}
                    composable("Zbliżeniowy"){val viewModel1 = hiltViewModel<MainViewModel1>()
                        Zbliżeniowy(viewModel1)}
                    composable("Barometr"){val viewModel2 = hiltViewModel<MainViewModel2>()
                        BarometerSensor(viewModel2)
                    }


                }
        }
    }}
    @Composable
    fun HomeScreen(navController: NavHostController) {
        val modifier = Modifier
        Column(
            modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Text(text = "Wybierz czujnik", fontSize = 26.sp, color = Color.Blue)
            Spacer(modifier = modifier.height(65.dp))


            Button(onClick = {

                navController.navigate("Luxomierz")

            }) {
                Text(text = "Luxomierz", fontSize = 10.sp)

            }
            Button(onClick = {

                navController.navigate("Zbliżeniowy")

            }) {
                Text(text = "Zbliżeniowy", fontSize = 10.sp)

            }
            Button(onClick = {

                navController.navigate("Barometr")

            }) {
                Text(text = "Barometr", fontSize = 10.sp)

            }


        }
    }
    @Composable
    fun Lux(navController: NavHostController, viewModel: MainViewModel = viewModel()) {

        val isDark = viewModel.isDark
        val isDark1 = viewModel.isDark1
        val isBrightly = viewModel.isBrightly



        Box(
            
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (isDark) Color.DarkGray else if (isDark1) Color.Gray else if (isBrightly) Color.LightGray else Color.White


                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (isDark) {
                    "jest ciemno"
                } else if (isDark1) {
                    "jest ciemniej"
                } else if (isBrightly) {
                    "jest jasniej"
                } else {
                    "jest jasno"

                },
                color = if (isDark) Color.White else if (isDark1) Color.White else if (isBrightly) Color.White else Color.DarkGray
            )
           
        }
        Text(
            text = "Wartość czujnika światła: ${viewModel.liczba} lux",
            modifier = Modifier.padding(16.dp),
            color =( if (isDark|| isDark1 ) Color.Green else Color.Black

        ))
    }
    }

    @Composable

    fun Zbliżeniowy(viewModel1: MainViewModel1 = viewModel()) {
        val daleko = viewModel1.daleko
        val dalej = viewModel1.dalej
        val blizej = viewModel1.blizej




        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (daleko) Color.Red else if (dalej) orange else if (blizej) lightgreen else Color.Green


                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (daleko) {
                    "jesteś daleko"
                } else if (dalej) {
                    "jesteś dalej"
                } else if (blizej) {
                    "jesteś bliżej"
                } else {
                    "jesteś blisko"

                },
                color = Color.White
            )
        }
        Text(
            text = "Wartość czujnika zbliżeniowego: ${viewModel1.wartosc} cm",
            modifier = Modifier.padding(16.dp)
        )
    }

@Composable

fun BarometerSensor(viewModel2: MainViewModel2 = viewModel()) {
    val bardzo_wysokie = viewModel2.bardzo_wysokie
    val wysokie = viewModel2.wysokie
    val niskie = viewModel2.niskie




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (bardzo_wysokie) Color.Red else if (wysokie) orange else if (niskie) lightgreen else Color.Green


            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (bardzo_wysokie) {
                "jesteś bardzo wysokie"
            } else if (wysokie) {
                "jesteś wysokie"
            } else if (niskie) {
                "jest niskie"
            } else {
                "jest bardzo niskie"

            },
            color = Color.White
        )
    }
    Text(
        text = "Wartość czujnika barometrycznego: ${viewModel2.wartosc2} hPa",
        modifier = Modifier.padding(16.dp)
    )
}

