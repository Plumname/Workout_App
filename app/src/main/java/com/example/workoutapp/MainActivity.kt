package com.example.workoutapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.workoutapp.home.HomeBody

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutApp()
        }
    }
}

@Composable
fun WorkoutApp() {
    // You can customize the theme if needed
    MaterialTheme {
        Surface {
            HomeBody()
        }
    }
}