package com.example.workoutapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar() {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically, // Elemente vertikal zentrieren.
                modifier = Modifier.fillMaxWidth() // die ganze Breite ausnutzen.
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape), // runde Ecken.
                    contentAlignment = Alignment.Center // Inhalt in der Box zentrieren.
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.outline_person_24), // Icon für Profilbild laden.
                        contentDescription = "Profile picture",
                        contentScale = ContentScale.Fit // Bild proportional an die Box anpassen.
                    )
                }

                Text(
                    buildAnnotatedString {
                        append("Hello, ") // Standard-Text
                        withStyle(
                            style = SpanStyle(
                                color = colorResource(id = R.color.black),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        ) {
                            append("User") // Benutzername fetten Stil geben
                        }
                    },
                    modifier = Modifier.padding(start = 10.dp)
                )

                Spacer(modifier = Modifier.weight(1f))
                // nimmt den verbleibenden Platz ein und schiebt das folgende Element nach rechts

                BadgedBox( // Die Notifs Box. Sie macht nichts aber war im Tutorial und sieht gut aus. Zum Implmentieren gab es keine Zeit mehr.
                    badge = {
                        Badge(
                            containerColor = Color.Red // rote Hintergrundfarbe für Badge (Benachrichtigungspunkt).
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Notifications, // Benachrichtigungs-Icon.
                        contentDescription = "Notification",
                        tint = Color.Black
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White // Hintergrundfarbe der AppBar weiß setzen.
        )
    )
}

