package com.shiwa.onearun.data.repository

import com.shiwa.onearun.data.remote.MqttConnectionState
import com.shiwa.onearun.data.remote.MqttDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface RaceRepository {
    val connectionState: StateFlow<MqttConnectionState>
    val incomingMessages: Flow<String>

    suspend fun connectToBroker()
    suspend fun subscribeToRaceTopic(topic: String = DEFAULT_RACE_TOPIC)
    suspend fun publishRaceMessage(message: String, topic: String = DEFAULT_RACE_TOPIC)
    suspend fun disconnect()

    companion object {
        const val DEFAULT_RACE_TOPIC = "corrida-app/test/hello"
    }
}

class RaceRepositoryImpl(
    private val mqttDataSource: MqttDataSource = MqttDataSource()
) : RaceRepository {

    override val connectionState: StateFlow<MqttConnectionState> = mqttDataSource.connectionState
    override val incomingMessages: Flow<String> = mqttDataSource.incomingMessages

    override suspend fun connectToBroker() {
        mqttDataSource.connect()
    }

    override suspend fun subscribeToRaceTopic(topic: String) {
        mqttDataSource.subscribe(topic)
    }

    override suspend fun publishRaceMessage(message: String, topic: String) {
        mqttDataSource.publish(topic, message)
    }

    override suspend fun disconnect() {
        mqttDataSource.disconnect()
    }
}
