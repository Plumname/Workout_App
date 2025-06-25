package com.example.workoutapp.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.Schedule
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
import com.example.workoutapp.R
import com.example.workoutapp.components.CustomTopAppBar

@Composable
fun HomeBody() {
    val workOutCategories = listOf(
        R.string.workout_category_full_body,
        R.string.workout_category_cardio,
        R.string.workout_category_cross_fit,
        R.string.workout_category_cyclist,
        R.string.workout_category_glutes,
        R.string.workout_category_power
    )

    Scaffold(
        topBar = { CustomTopAppBar() },
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                elevation = 5.dp,
                backgroundColor = MaterialTheme.colors.surface,
            ) {
                BottomNavigationItem(
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = stringResource(R.string.content_desc_person),
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = stringResource(R.string.content_desc_menu),
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            Icons.Filled.Tune,
                            contentDescription = stringResource(R.string.content_desc_tune),
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            Icons.Filled.RadioButtonChecked,
                            contentDescription = stringResource(R.string.content_desc_radio_button),
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 18.dp)
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Spacer(modifier = Modifier.height(30.dp))

            // Workout Goal Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(colorResource(id = R.color.light_purple))
            ) {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 22.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.home_loose_belly_fat_title),
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            lineHeight = 32.sp
                        )
                        Button(
                            shape = RoundedCornerShape(20.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = colorResource(id = R.color.purple_200)
                            )
                        ) {
                            Text(stringResource(R.string.home_level_middle), color = Color.White, fontSize = 18.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(30.dp))
                            .background(MaterialTheme.colors.surface),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.woman),
                            contentDescription = stringResource(R.string.content_desc_workout_image),
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Fit
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Filled.Schedule,
                            contentDescription = stringResource(R.string.content_desc_timer),
                            tint = MaterialTheme.colors.onSurface
                        )
                        Text(
                            stringResource(R.string.home_duration_5_minutes),
                            fontSize = 18.sp,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(onClick = {}) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Text(
                                    stringResource(R.string.home_start_button),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.onSurface
                                )
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = stringResource(R.string.content_desc_start_arrow),
                                    tint = MaterialTheme.colors.onSurface
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Progress Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(colorResource(id = R.color.orange))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            progress = 0.56f,
                            modifier = Modifier.size(75.dp),
                            color = Color.Black,
                            strokeWidth = 8.dp
                        )
                        Text(
                            "56%",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                    }
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        Text(
                            stringResource(R.string.home_progress_great),
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text(
                            stringResource(R.string.home_progress_calorie_info),
                            color = Color.Gray,
                            fontSize = 16.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
