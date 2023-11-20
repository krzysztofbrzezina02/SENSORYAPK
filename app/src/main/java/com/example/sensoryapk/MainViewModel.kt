package com.example.sensoryapk

import android.app.Application
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainViewModel @Inject constructor(
    @Named("light") private val lightSensor: MeasurableSensor,

): ViewModel() {


    var isDark by mutableStateOf(false)
    var isDark1 by mutableStateOf(false)
    var isBrightly by mutableStateOf(false)
    var liczba by mutableStateOf(0f)


    init {
        lightSensor.startListening()
        lightSensor.setOnSensorValuesChangedListener { values ->
            val lux = values[0]
            this.liczba = lux
            isDark = lux < 200f
            isDark1 = lux > 200f && lux < 600f
            isBrightly = lux > 600f && lux < 1100f

        }
    }
}
    @HiltViewModel
    class MainViewModel1 @Inject constructor(
        @Named("proximity")private val proximitySensor: MeasurableSensor,
    ) : ViewModel() {


        var daleko by mutableStateOf(false)
        var dalej by mutableStateOf(false)
        var blizej by mutableStateOf(false)
        var wartosc by mutableStateOf(0f)


        init {


            proximitySensor.startListening()
            proximitySensor.setOnSensorValuesChangedListener { values ->
                val wartosc = values[0]
                this.wartosc = wartosc
                daleko = wartosc > 8f
                dalej = wartosc > 5f && wartosc < 8f
                blizej = wartosc > 3f && wartosc < 5f

            }
        }
    }
@HiltViewModel
class MainViewModel2 @Inject constructor(
    @Named("barometer")private val BarometerSensor: MeasurableSensor,
) : ViewModel() {


    var bardzo_wysokie by mutableStateOf(false)
    var  wysokie by mutableStateOf(false)
    var niskie by mutableStateOf(false)
    var wartosc2 by mutableStateOf(0f)


    init {


        BarometerSensor.startListening()
        BarometerSensor.setOnSensorValuesChangedListener { values ->
            val wartosci = values[0]
            this.wartosc2 = wartosci
            bardzo_wysokie = wartosci > 1000f
            wysokie = wartosci > 700f && wartosci < 1000f
            niskie = wartosci > 300f && wartosci < 700f

        }
    }
}
