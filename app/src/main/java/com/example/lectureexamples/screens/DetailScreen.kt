package com.example.lectureexamples.screens

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.lectureexamples.R
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.models.getMovies
import coil.imageLoader

@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
    val movie: Movie? = getMovies().find{it.id == movieId}
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
        ) {
                    TopAppBar {
                        Icon(
                            modifier = Modifier
                                .size(25.dp)
                                // if clicked, it should go back
                                .clickable(onClick = { navController.popBackStack() })
                            ,
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Arror",
                        )
                        if (movie != null) {
                                    Text(
                                        text = movie.title,
                                        style = MaterialTheme.typography.h4,
                                        textAlign = TextAlign.Center
                                    )
                                }

                }
            if (movie != null) {
                MovieRow(movie = movie)
            }
            else Text(text = "You haven't selected a movie")
            Column(modifier = Modifier.padding(16.dp)) {
                    if (movie != null) {
                        Text(text = "Movie Images from: ${movie.title}",
                        style = MaterialTheme.typography.h5)
                        Spacer(modifier = Modifier.size(10.dp))
                }
                }
                Column() {
                        Row(){
                            if (movie != null) {
                                ImageRow(movie = movie)
                            }

                    }

                }









            }




        }
        }

@Composable
fun ImageRow (movie : Movie) {
    LazyRow(){
        if (movie != null) {
            items(movie.images){ movieImage ->
                Card(
                    modifier = Modifier.size(200.dp)
                ){
                    AsyncImage(model = movieImage,
                        contentDescription = "Movie Image",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}
