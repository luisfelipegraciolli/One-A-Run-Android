package com.shiwa.onearun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import com.shiwa.onearun.ui.theme.OneARunTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private const val BROKER_HOST = "test.mosquitto.org"
private const val CLIENT_ID_PREFIX = "corrida-android-"
private const val TEST_TOPIC = "corrida-app/test/hello"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            OneARunTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val client = connectToHiveMq()
            
            client.publishWith()
                .topic(TEST_TOPIC)
                .qos(MqttQos.AT_LEAST_ONCE)
                .payload("Hello from Android!".toByteArray())
                .send()
        }
    }

    private fun connectToHiveMq(): Mqtt5AsyncClient {

        val clientId =
            CLIENT_ID_PREFIX + System.currentTimeMillis()

        val client = Mqtt5Client.builder()
            .identifier(clientId)
            .serverHost(BROKER_HOST)
            .serverPort(1883)
            .automaticReconnectWithDefaultConfig()
            .buildAsync()

        println("Connecting to MQTT...")

        client.connectWith()
            .cleanStart(true)
            .send()
            .join()

        println("MQTT connected!")

        return client
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OneARunTheme {
        Greeting("Android")
    }
}