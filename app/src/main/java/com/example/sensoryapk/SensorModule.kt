package com.example.sensoryapk

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorModule {

    @Provides
    @Named("proximity")
    fun provideProximitySensor (app: Application): MeasurableSensor {
        return ProximitySensor(app)
    }
    @Provides
    @Named("barometer")
    fun provideBarometerSensor (app: Application): MeasurableSensor {
        return BarometerSensor(app)
    }
    @Provides
    @Named("light")
    fun provideLightSensor(app: Application): MeasurableSensor {
        return LightSensor(app)
    }
}




