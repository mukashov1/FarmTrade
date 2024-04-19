package com.example.farmtrade.ui.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import java.util.Locale

@Composable
fun AddressScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center // Center the content horizontally
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "backArrow",
                    modifier = Modifier.size(30.dp)
                )
            }
            Text(
                text = "About Us",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 15.dp)
                    .fillMaxWidth()
            )
        }

        Box(modifier = Modifier.weight(1f)) {
            LocateUserLocationScreen()
        }
        
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Set the delivery address")
        }
    }
}


@Composable
fun LocateUserLocationScreen() {
    val context = LocalContext.current
    var mapProperties by remember { mutableStateOf(MapProperties()) }
    var cameraPositionState by remember {
        mutableStateOf(
            CameraPositionState(
                CameraPosition(
                    LatLng(43.238949, 76.889709), // Almaty's coordinates
                    1000f, // zoom level
                    0f, // tilt
                    0f  // bearing
                )
            )
        )
    }
    val addressState = remember { mutableStateOf("") }
    val markerPositionState = remember { mutableStateOf<LatLng?>(null) }


    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                moveToCurrentLocation(context, cameraPositionState)
            } else {
                // Handle permission denial
            }
        }
    )


    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }


    GoogleMap(
        modifier = Modifier.fillMaxWidth(),
        properties = mapProperties,
        cameraPositionState = cameraPositionState,
        onMapLoaded = {
            // Actions to do once map is loaded
        },
        onMapClick = { latLng ->
            markerPositionState.value = latLng
            getAddressFromLocation(context, latLng) { address ->
                addressState.value = address ?: ""
            }
        }
    ) {
        markerPositionState.value?.let { markerPosition ->
            Marker(
                state = MarkerState(position = markerPosition),
                title = "Selected Location",
                snippet = "This is the clicked point"
            )
        }
    }


    // Display the address to the user
    Text(
        text = addressState.value,
        modifier = Modifier.padding(16.dp)
    )
}


private fun getAddressFromLocation(
    context: Context,
    latLng: LatLng,
    onAddressReceived: (String?) -> Unit
) {
    val geocoder = Geocoder(context, Locale.getDefault())
    try {
        val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
        if (addresses != null) {
            if (addresses.isNotEmpty()) {
                val address = addresses[0]
                val addressText = address.getAddressLine(0)
                onAddressReceived(addressText)
            } else {
                onAddressReceived(null)
            }
        }
    } catch (e: Exception) {
        Log.e("Geocoder", "Error getting address: $e")
        onAddressReceived(null)
    }
}


private fun moveToCurrentLocation(context: Context, cameraPositionState: CameraPositionState) {
    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // Handle case where permissions are not granted
        return
    }
    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
        if (location != null) {
            val userLatLng = LatLng(location.latitude, location.longitude)
            // Set the camera position to the current location
            cameraPositionState.position = CameraPosition(userLatLng, 0f, 0f, 0f)


            // Print the current location coordinates
            Log.d(
                "CurrentLocation",
                "Latitude: ${location.latitude}, Longitude: ${location.longitude}"
            )
        }
    }.addOnFailureListener {
        // Handle the case where location is not available
        Log.e("LocationError", "Error getting location: $it")
    }
}
@Preview(showBackground = true)
@Composable
fun ChooseLocationScreenPreview() {
    AddressScreen(rememberNavController())
}






