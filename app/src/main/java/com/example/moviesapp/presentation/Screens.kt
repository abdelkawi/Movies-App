package com.example.moviesapp.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.moviesapp.BuildConfig
import com.example.moviesapp.R.drawable
import com.example.moviesapp.common.Result
import com.example.moviesapp.common.Result.Error
import com.example.moviesapp.common.Result.Success
import com.example.moviesapp.common.isNetworkAvailable
import com.example.moviesapp.domain.HomeSection
import com.example.moviesapp.domain.Movie
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MovieDetails(mainViewModel: MainViewModel,movieId: String, navController: NavController) {
  ConstraintLayout(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .background(Color.Black)
  ) {
    val coroutineScope = rememberCoroutineScope()

    val movie = remember {
      mutableStateOf<Result<Movie>>(
        Success(
          Movie(
            0,
            "",
            "",
            "¬",
            "", emptyList()
          )
        )
      )
    }
    coroutineScope.launch {
      movie.value = mainViewModel.getMovieDetails(movieId.toInt())
    }
    when (movie.value) {
      is Error -> {

      }
      is Success -> {
        val data = (movie.value as Success).data
        val (title, poster, backImg, genres, description, releaseDate) = createRefs()
        Text(text = data.name, color = Color.White, fontSize = 18.sp, modifier = Modifier.constrainAs(title) {
          top.linkTo(backImg.top)
          start.linkTo(backImg.end, margin = 16.dp)
          bottom.linkTo(backImg.bottom)
        })
        Image(painter = painterResource(id = drawable.ic_back_arrow),
          contentDescription = null,
          modifier = Modifier
            .clickable { navController.navigateUp() }
            .width(20.dp)
            .height(20.dp)
            .constrainAs(backImg) {
              top.linkTo(parent.top, margin = 16.dp)
              start.linkTo(parent.start, margin = 16.dp)
            })
        AsyncImage(model = BuildConfig.IMAGE_BASE_URL + data.image,
          contentDescription = null,
          contentScale = ContentScale.FillWidth,
          modifier = Modifier
            .constrainAs(poster) {
              top.linkTo(backImg.bottom, margin = 16.dp)
              start.linkTo(backImg.start)
              end.linkTo(parent.end, margin = 16.dp)
              width = Dimension.fillToConstraints
            })
        LazyRow(modifier = Modifier.constrainAs(genres) {
          top.linkTo(poster.bottom, margin = 16.dp)
          start.linkTo(poster.start)
          end.linkTo(poster.end)
          width = Dimension.fillToConstraints
        }) {
          items(data.genres) { genre ->
            Text(
              text = genre.name ?: "", color = Color.White,
              modifier = Modifier
                .background(Color.Magenta, RoundedCornerShape(4.dp))
                .padding(4.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
          }
        }
        Text(
          text = data.releaseDate,
          color = Color.White,
          fontSize = 14.sp,
          modifier = Modifier.constrainAs(releaseDate) {
            top.linkTo(genres.bottom, margin = 16.dp)
            start.linkTo(genres.start)
            end.linkTo(poster.end)
            width = Dimension.fillToConstraints
          }
        )
        Text(
          text = data.description,
          color = Color.White,
          fontSize = 14.sp,
          modifier = Modifier.constrainAs(description) {
            top.linkTo(releaseDate.bottom, margin = 16.dp)
            start.linkTo(releaseDate.start)
            end.linkTo(poster.end)
            width = Dimension.fillToConstraints
          }
        )
      }
    }


  }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MoviesListView(mainViewModel: MainViewModel,context: Context,filter: String, navController: NavController) {
  val coroutineScope = rememberCoroutineScope()
  val movies = remember {
    mutableStateOf<Result<List<HomeSection>>>(Success(emptyList()))
  }

  coroutineScope.launch {
    if (isNetworkAvailable(context))
      movies.value = mainViewModel.getOnlineMovies(filter)
    else
      movies.value = mainViewModel.getOfflineMovies(filter)
  }

  when (movies.value) {
    is Error -> {
      ConstraintLayout(
        modifier = Modifier
          .fillMaxHeight()
          .fillMaxWidth()
      ) {
        val (errorTxt) = createRefs()
        Text(text = (movies.value as Error).error ?: "", color = Color.Black, fontSize = 18.sp, modifier = Modifier.constrainAs(errorTxt) {
          top.linkTo(parent.top)
          bottom.linkTo(parent.top)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
        })
      }
    }
    is Success -> Column(
      modifier = Modifier
        .verticalScroll(rememberScrollState())
        .fillMaxWidth()
        .background(Color.Black)
    ) {
      (movies.value as Success).data.forEach {
        if ((movies.value as Success).data.isNotEmpty()) {
          Column {
            Text(
              it.categoryName,
              modifier = Modifier.padding(8.dp),
              fontFamily = FontFamily(Typeface.DEFAULT),
              fontWeight = FontWeight.Bold,
              fontSize = 18.sp,
              color = Color.White
            )
            LazyRow {
              items(it.movies) { movie ->
                AsyncImage(
                  model = BuildConfig.IMAGE_BASE_URL + movie.image,
                  contentDescription = null,
                  modifier = Modifier
                    .height(250.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .clickable { navController.navigate("details/${movie.id}") }
                )
              }
            }
          }
        }
      }
    }
  }


}