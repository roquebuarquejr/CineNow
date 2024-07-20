package com.devspacecinenow.detail.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.devspacecinenow.common.data.remote.model.MovieDto
import com.devspacecinenow.detail.presentation.MovieDetailViewModel
import com.devspacecinenow.ui.theme.CineNowTheme

@Composable
fun MovieDetailScreen(
    movieId: String,
    navHostController: NavHostController,
    detailViewModel: MovieDetailViewModel,
) {
    val movieDto by detailViewModel.uiMovie.collectAsState()
    detailViewModel.fetchMovieDetail(movieId)

    movieDto?.let {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    detailViewModel.cleanMovieId()
                    navHostController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = it.title
                )
            }
            MovieDetailContent(it)
        }
    }
}

@Composable
private fun MovieDetailContent(movie: MovieDto) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .height(200.dp)
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            model = movie.posterFullPath,
            contentDescription = "${movie.title} Poster image"
        )

        Text(
            modifier = Modifier.padding(16.dp),
            text = movie.overview,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieDetailPreview() {
    CineNowTheme {
        val movie = MovieDto(
            id = 9,
            title = "Title",
            postPath = "sadasdsad",
            overview = "Long overview movie Long overview movie" +
                    " Long overview movie Long overview movie " +
                    "Long overview movie Long overview movie"
        )
        MovieDetailContent(movie = movie)
    }
}