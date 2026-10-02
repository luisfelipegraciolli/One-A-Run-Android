package com.shiwa.onearun.data.container

import com.shiwa.onearun.data.remote.MqttDataSource
import com.shiwa.onearun.data.repository.RaceRepository
import com.shiwa.onearun.data.repository.RaceRepositoryImpl

interface AppContainer {
    val raceRepository: RaceRepository
}

class DefaultAppContainer : AppContainer {
    override val raceRepository: RaceRepository by lazy {
        RaceRepositoryImpl(
            mqttDataSource = MqttDataSource(
                host = "test.mosquitto.org",
                port = 1883,
                clientIdPrefix = "corrida-android-"
            ),
        )
    }
}