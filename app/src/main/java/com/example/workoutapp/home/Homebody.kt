package com.example.workoutapp.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.workoutapp.R
import com.example.workoutapp.components.CustomTopAppBar


@Composable
fun HomeBody(navController: NavController) {
    Scaffold(
        topBar = { CustomTopAppBar() }, // custom TopAppBar anzeigen.
        bottomBar = { // untere Menü bar.
            BottomNavigation(
                modifier = Modifier.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                elevation = 5.dp,
                backgroundColor = MaterialTheme.colors.surface,
            ) {
                BottomNavigationItem(
                    selected = true,
                    onClick = {}, // hier sollte man auf eine Art Profil kommen aber es gab keine Zeit mehr das zu implementieren.
                    icon = { Icon(Icons.Filled.Person, contentDescription = null, modifier = Modifier.size(30.dp)) }
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = {}, // Dieser Button wird erst wichtig wenn wir im workout screen sind. Siehe das Composable dafür.
                    icon = { Icon(Icons.Filled.Menu, contentDescription = null, modifier = Modifier.size(30.dp)) }
                )
            }
        }
    ) { paddingValues ->  // lambda (no-name) Funktion, die dafür sorgt dass das padding ausgeführt wird und geordnet ist.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 18.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            // Workout-Card mit Start-Button
            Box(
                modifier = Modifier
                    .fillMaxWidth() // Box füllt die gesamte Breite
                    .clip(RoundedCornerShape(10.dp)) // Ecken der Box abgerundet mit 10dp Radius
                    .background(colorResource(id = R.color.light_purple))
            ) {
                Column(
                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 22.dp
                    )
                ) { // Innenabstand rundherum
                    Row(
                        modifier = Modifier.fillMaxWidth(), // Zeile füllt Breite aus
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.home_lose_belly_fat_title), // Titeltext aus den Ressourcen (Tutorial)
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            lineHeight = 32.sp
                        )
                        Button( //Schwierigkeitsgrad Button
                            shape = RoundedCornerShape(20.dp),
                            contentPadding = PaddingValues(
                                horizontal = 12.dp,
                                vertical = 6.dp
                            ),
                            onClick = {}, //Man sollte auswählen können wie schwer das Workout ist, ist aber nicht implementiert.
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = colorResource(id = R.color.purple_200) // lila Hintergrundfarbe
                            )
                        ) {
                            Text(
                                stringResource(R.string.home_level_middle),
                                color = Color.White,
                                fontSize = 18.sp
                            )

                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Image(
                        painter = painterResource(id = R.drawable.woman),
                        contentDescription = stringResource(R.string.content_desc_workout_image),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .border(
                                width = 2.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(10.dp)
                            ), // schwarzer Rahmen mit 2dp
                        contentScale = ContentScale.Fit // Bild proportional skalieren und zentrieren.
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row( // Dauer und Start Button
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Filled.Schedule,
                            contentDescription = null
                        ) // Uhr-Icon ohne Beschreibung
                        Text(
                            stringResource(R.string.home_duration_5_minutes),
                            fontSize = 18.sp, // Schriftgröße 18sp
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f)) // nimmt verbleibenden Platz ein und schiebt Button nach rechts.
                        TextButton(onClick = { navController.navigate("workout") }) { // Startet das Workout.
                            Row(
                                verticalAlignment = Alignment.CenterVertically, // Elemente vertikal zentrieren.
                                horizontalArrangement = Arrangement.spacedBy(5.dp) // Abstand zwischen Text und Icon.
                            ) {
                                Text(
                                    stringResource(R.string.home_start_button),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
            //Kalorien Defizit Anzeige (Leere UI aus den Tutorial).
            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(colorResource(id = R.color.orange))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween // Elemente mit Abstand
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator( //zeigt einen runden Fortschrittsbalken an. Hier wird der Fortschritt explizit mit progress
                            progress = 0.56f, // Fortschritt 56%
                            modifier = Modifier.size(75.dp), // Größe 75x75dp
                            color = Color.Black, // schwarze Farbe
                            strokeWidth = 8.dp // Strichbreite 8dp
                        )
                        Text(
                            "56%", // Text über dem Kreis
                            color = Color.Black, // schwarz
                            fontWeight = FontWeight.Bold, // fett
                            fontSize = 25.sp // Schriftgröße 25sp
                        )
                    }
                    Column(modifier = Modifier.padding(start = 16.dp)) { // Spalte mit Abstand links
                        Text(
                            stringResource(R.string.home_progress_great), // Text "Fortschritt großartig"
                            color = Color.Black, // schwarz
                            fontWeight = FontWeight.Bold, // fett
                            fontSize = 20.sp // Größe 20sp
                        )
                        Text(
                            stringResource(R.string.home_progress_calorie_info), // Kalorieninfo Text
                            color = Color.Gray, // grau
                            fontSize = 16.sp, // Schriftgröße 16sp
                            lineHeight = 18.sp // Zeilenhöhe 18sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp)) // Abstand 16dp nach unten
        }
    }
}