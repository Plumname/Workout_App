package com.example.workoutapp.components

import android.R

data class Excercise(
    val name: String,
    val desc: String,
    val imageRes: Int, // sozusagen die ID für das entsprechende Bild.
    val timeActive: Int = 35, // Zeit der Übung in Sekunden.
    val timeBreak: Int = 5// Zeit der Pause in Sekunden.
)
