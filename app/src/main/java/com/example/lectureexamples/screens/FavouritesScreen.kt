package com.example.lectureexamples.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.models.getMovieTitles
import com.example.lectureexamples.models.getMovies

@Composable
fun FavouritesScreen(navController: NavController) {
        val movieTitles = getMovieTitles()
val movieList = getMovies()
        Column() {
                TopAppBar(
                ) {
                        Column() {
                                Text(text = "Your Favourite Movies",
                                        style = MaterialTheme.typography.h5)
                        }
                }
                Column() {
                        Spacer(modifier = Modifier.padding(10.dp))
                }
                Column() {
                        Card() {
                             Text(text = movieTitles.toString())
                        }
                }

        }


}
