package com.example.lectureexamples.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.lectureexamples.R
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.models.getMovies
import coil.imageLoader


@Composable
fun HomeScreen(navController: NavController) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            MyList(navController)
        }
    }
}



@Composable
fun MyList(navController: NavController = rememberNavController(),
           movies: List<Movie> = getMovies()){
    var expanded by remember {
        mutableStateOf(false)
    }
    Column(Modifier.fillMaxWidth()) {
        TopAppBar (
            title = {Text (text = "Movies")},
            actions = {
                 Icon(
                     modifier = Modifier
                         .clickable { expanded = !expanded }
                         .size(25.dp),
                     contentDescription = "More",
                     imageVector = Icons.Default.MoreVert
                     )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {expanded = false}){
                    DropdownMenuItem({ navController.navigate("favourites") })
                    {
                        Icon(
                            modifier = Modifier,
                            contentDescription = "Favourites",
                            imageVector = Icons.Default.Favorite
                        )
                        Spacer(Modifier.size(10.dp))
                        Text(text = "Favourites")
                    }
                }
            })
    }
    LazyColumn{
        items(movies) {movie ->
            MovieRow(
                movie = movie,
            )  { movieId ->
                navController.navigate("detail/${movieId}")
            }
        }
    }
}

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {
    var visible by remember { mutableStateOf(false)}
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = movie.images.first(),
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        tint = MaterialTheme.colors.secondary,
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites"
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var iconArrow by remember {
                    mutableStateOf(Icons.Default.KeyboardArrowUp)
                }
                Text(
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (iconArrow == Icons.Default.KeyboardArrowUp) 1 else Int.MAX_VALUE,
                    text = movie.title ,
                    style = MaterialTheme.typography.h6
                )
                IconToggleButton(checked = visible, onCheckedChange = {visible = it }) {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                if (iconArrow == Icons.Default.KeyboardArrowUp) {
                                    iconArrow = Icons.Default.KeyboardArrowDown
                                } else {
                                    iconArrow = Icons.Default.KeyboardArrowUp
                                }
                                visible = !visible
                            }
                            .size(32.dp),
                        imageVector = iconArrow,
                        contentDescription = "Show details",
                        tint = Color.Black,
                    )
                }
            }
            AnimatedVisibility(visible = visible) {
                MovieDetails(movie)
            }
        }
    }
}


@Composable
fun MovieDetails (movie: Movie){
    Column {
        Text (text = "Release Year: ${movie.year}\nActors: ${movie.actors} \nPlot: ${movie.plot} " )
    }
}




/*
@Preview
@Composable
fun Greeting() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember {
            mutableStateOf("")
        }

        Text(text = "Hello ${name}!")

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it},
            label = { Text("Name")}
        )


        /*
        // step 2 - add a mutableStateOf to fire the event for recomposition

       var name = mutableStateOf("")   // use a state holder to register changes
        // var name  by mutableStateOf("")
        Text(text = "Hello ${name.value}!")   // get value of state holder object

        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },    // change its value accordingly
            label = { Text("Name")}
        )
        */



        /*
        // step 3 - use remember
        var name by remember {         // use remember to skip overwriting after first composition
            mutableStateOf("")
        }

        Text(text = "Hello ${name}!")

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name")}
        )

         */
    }
}*/