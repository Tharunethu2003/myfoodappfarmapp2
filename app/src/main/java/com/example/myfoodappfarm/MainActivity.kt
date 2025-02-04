package com.example.myfoodappfarm

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.myfoodappfarm.ui.navigation.AppNavigation

class MainActivity : ComponentActivity(), SensorEventListener, LocationListener {

    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback  // Store the callback instance
    private lateinit var sensorManager: SensorManager
    private var lightSensor: Sensor? = null
    private lateinit var locationManager: LocationManager
    private val targetLatitude = 7.281262454609818
    private val targetLongitude = 80.62030080803211
    private val proximityThreshold = 100f // Proximity threshold in meters (100 meters)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Ensures full-screen experience

        // Initialize ConnectivityManager to listen for network changes
        connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        // Initialize SensorManager to access sensors
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Get the ambient light sensor (if available)
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        // Initialize LocationManager
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // Initial network check when the app starts
        if (!isNetworkAvailable()) {
            Toast.makeText(this, "You are offline. Please check your internet connection.", Toast.LENGTH_LONG).show()
        }

        // Register the network callback to listen for network changes
        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                runOnUiThread {
                    Toast.makeText(applicationContext, "Network is available", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                runOnUiThread {
                    Toast.makeText(applicationContext, "Network is lost. You are offline.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCapabilitiesChanged(network: Network, capabilities: NetworkCapabilities) {
                super.onCapabilitiesChanged(network, capabilities)
                if (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Network capabilities changed.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // Register the network callback
        connectivityManager.registerDefaultNetworkCallback(networkCallback)

        // Start location updates
        try {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0L,
                0f,
                this
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }

        setContent {
            val navController = rememberNavController()

            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                AppNavigation(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding) // Apply padding to respect system UI
                )
            }
        }
    }

    // Check network availability
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    // Register the light sensor listener when the activity resumes
    override fun onResume() {
        super.onResume()
        lightSensor?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    // Unregister the light sensor listener when the activity pauses
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    // Handle the sensor data (light level) and adjust screen brightness
    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null && event.sensor.type == Sensor.TYPE_LIGHT) {
            val lightLevel = event.values[0]

            // Adjust the screen brightness based on the light level
            // Here, we're using the value of ambient light to set screen brightness.
            val brightness = (lightLevel / 1000f).coerceIn(0.1f, 1.0f) // Normalize the brightness value

            // Set the brightness of the screen
            try {
                Settings.System.putInt(
                    contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS,
                    (brightness * 255).toInt() // Convert to a range of 0 to 255
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // This is required by the SensorEventListener, but we don't need to implement it here
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not needed for this example
    }

    // LocationListener method to check proximity
    override fun onLocationChanged(location: Location) {
        val userLatitude = location.latitude
        val userLongitude = location.longitude

        // Calculate the distance between the user's location and the target farm location
        val targetLocation = Location("target")
        targetLocation.latitude = targetLatitude
        targetLocation.longitude = targetLongitude

        val distance = location.distanceTo(targetLocation)

        // If the user is within the proximity threshold (100 meters), show a prompt
        if (distance <= proximityThreshold) {
            Toast.makeText(this, "You are very close to one of our farms. Want to check it out?", Toast.LENGTH_LONG).show()
            // You can add an Intent to open the map here (for example)
            // For now, you can open a map link in the browser
            val mapUri = "https://maps.app.goo.gl/uvjVoNKPBJiQpyDB8"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUri))
            startActivity(intent)
        }
    }

    // onDestroy() to clean up resources
    override fun onDestroy() {
        super.onDestroy()
        // Unregister the network callback to stop monitoring network changes
        connectivityManager.unregisterNetworkCallback(networkCallback) // Unregister with the stored callback
        // Remove location updates
        locationManager.removeUpdates(this)
    }
}
