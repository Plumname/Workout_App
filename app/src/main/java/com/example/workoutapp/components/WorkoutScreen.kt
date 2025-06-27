package com.example.workoutapp.screens

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.workoutapp.R
import com.example.workoutapp.components.CustomTopAppBar
import com.example.workoutapp.viewmodel.WorkoutViewModel

@Composable
fun WorkoutScreen(navController: NavController) {
    val workoutViewModel: WorkoutViewModel = viewModel()
    val context = LocalContext.current
    val timeLeft by workoutViewModel.currentTime.observeAsState(0)
    val isResting by workoutViewModel.isResting.observeAsState(false)
    val exerciseIndex by workoutViewModel.currentExerciseIndex.observeAsState(0)
    val isWorkoutFinished by workoutViewModel.isWorkoutFinished.observeAsState(false)
    val exercises = workoutViewModel.getExercises()
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    LaunchedEffect(Unit) { // LaunchedEffect wird beim ersten Aufruf ausgeführt. Es ist eine Side-Effect API von JetPack Compose
        workoutViewModel.startWorkout() // startet das Workout.
    }

    LaunchedEffect(timeLeft) {
        if (timeLeft in 1..3) { //Checkt wieviele Sekunden von TimeLeft noch übrig sind. In der Spann von 3-1 spielt der Sound.
            if (mediaPlayer == null) { // Falls der MediaPlayer noch nicht initialisiert ist.
                mediaPlayer = MediaPlayer.create(context, R.raw.beep).apply { // MediaPlayer initialisieren.
                    setOnCompletionListener { // Listener, der ausgeführt wird, wenn der Sound fertig abgespielt ist.
                        it.release()  // gibt die Ressourcen des MediaPlayers frei.
                        mediaPlayer = null // setzt die MediaPlayer-Referenz zurück, damit sie neu erstellt werden kann.
                    }
                    setVolume(1.0f, 1.0f) // höchste Lautstärke, da ich den Sound kaum hören konnte.
                    start() // startet die Wiedergabe des Sounds.
                    Log.d("WorkoutScreen", "Playing beep sound") // Der Sound hat super lange nicht geklapp, Log Zeile damit ich sehen konnte ob es spielt.
                }
            }
        }
    }

    DisposableEffect(Unit) { // Terminiere den MediaPlayer, wenn das Composable zerstört wird.
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    Scaffold(
        topBar = { CustomTopAppBar() }, // custom TopAppBar anzeigen
        bottomBar = {
            BottomNavigation( // untere Menü bar.
                modifier = Modifier.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                elevation = 5.dp,
                backgroundColor = MaterialTheme.colors.surface,
            ) {
                BottomNavigationItem(
                    selected = true,
                    onClick = {}, // hier sollte man auf eine Art Profil kommen aber es gab keine Zeit mehr das zu implementieren.
                    icon = {
                        Icon(Icons.Filled.Person, contentDescription = null, modifier = Modifier.size(30.dp))
                    },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = {
                        navController.navigate("home") { // wenn man diesen Button drückt kommt man wieder auf den Homescreen.
                            popUpTo("workout") { inclusive = true } // der momentane workout screen verschwindet komplett. Er wird also wirklich terminiert.
                        }
                    },
                    icon = { Icon(Icons.Filled.Menu, contentDescription = null, modifier = Modifier.size(30.dp)) },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
                )
            }
        }
    ) { paddingValues -> // lambda (no-name) Funktion, die dafür sorgt dass das padding ausgeführt wird und geordnet ist.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Padding des Scaffold übernehmen
                .padding(horizontal = 18.dp) // zusätzlich horizontaler Padding
        ) {
            Spacer(modifier = Modifier.height(30.dp)) // Leerzeile nach der TopAppBar.

            Box(
                modifier = Modifier
                    .padding(top = 100.dp, bottom = 20.dp) // Abstand nach oben und unten
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)) // macht die Box rund
                    .background(colorResource(id = R.color.light_purple)) // Hintergrundfarbe der Box
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally, // Inhalt zentriert horizontal
                    verticalArrangement = Arrangement.Center, // Inhalt zentriert vertikal
                    modifier = Modifier
                        .padding(10.dp)
                        .padding(horizontal = 40.dp) // extra horizontaler Padding
                        .clip(RoundedCornerShape(10.dp)) // auch hier runde Ecken
                ) {
                    if (!isWorkoutFinished) {
                        val exercise = exercises.getOrNull(exerciseIndex) // momentane Excercise auswählen.
                        Text(
                            text = if (isResting) "Pause" else "${exercise?.name ?: "..."}", // zeigt Pause oder Übungsname
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            lineHeight = 32.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        if (isResting) {
                            Image( // das Bild für Pause anzeigen
                                painter = painterResource(id = R.drawable.pause),
                                contentDescription = stringResource(R.string.content_desc_workout_image), // vorgegeben vom Tutorial.
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .border(2.dp, Color.Black, RoundedCornerShape(10.dp)), // modifier Effekte.
                                contentScale = ContentScale.Fit // zentriert das Bild im Box.
                            )
                        } else if (exercise != null) { // war nötig damit exercise.imageRes sicher nicht null ist und ich es so schreiben kann.
                            Image( // das Bild für jede exercise. Ich hole es mir durch imageRes.
                                painter = painterResource(id = exercise.imageRes),
                                contentDescription = stringResource(R.string.content_desc_workout_image), // vorgegeben vom Tutorial.
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp)) // runde Ecken.
                                    .border(2.dp, Color.Black, RoundedCornerShape(10.dp)), // modifier Effekte.
                                contentScale = ContentScale.Fit // zentriert das Bild im Box.
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp)) // Leerzeile nach dem Bild.

                        // Zeit-Anzeige mit kreisförmigem Fortschrittsbalken, Hab es von dem UI Tutorial übernommen.
                        val totalTime = if (isResting) {
                            // falls gerade Pause ist: Zeit der Pause nehmen. Fallback auf 1 Sekunde, falls Daten fehlen
                            exercises.getOrNull(exerciseIndex)?.timeBreak ?: 1
                        } else {
                            // falls gerade Übung läuft: Zeit der Übung nehmen. Fallback auf 1 Sekunde, falls Daten fehlen
                            exercises.getOrNull(exerciseIndex)?.timeActive ?: 1
                        }

                        val progress = timeLeft.toFloat() / totalTime.toFloat() // berechne Fortschritt (0..1) aus verbleibender Zeit und Gesamtzeit, was einen prozent ert ergibt!

                        Box(
                            contentAlignment = Alignment.Center, // Inhalt zentriert in der Box anzeigen
                            modifier = Modifier.size(100.dp) // fixe Größe für die Box
                        ) {
                            CircularProgressIndicator(
                                progress = progress, // Fortschrittswert setzen.
                                modifier = Modifier.fillMaxSize(),
                                color = Color.Black,
                                strokeWidth = 8.dp
                            )

                            Text(
                                "$timeLeft s", // verbleibende Sekunden darunter anzeigen.
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp
                            )
                        }

                    } else {
                        Text(
                            "Workout abgeschlossen!", // Text bei abgeschlossenen Workout
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            lineHeight = 32.sp,
                            style = MaterialTheme.typography.h4
                        )
                    }
                }
            }
        }
    }
}
