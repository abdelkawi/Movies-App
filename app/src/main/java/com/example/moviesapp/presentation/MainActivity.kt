package com.example.moviesapp.presentation


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviesapp.common.startWork
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  private val mainViewModel: MainViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    startWork(this)
    setContent {
      ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val navController = rememberNavController()
        val (content, bottomBar) = createRefs()
        NavHost(
          navController = navController,
          startDestination = "Now Playing",
          modifier = Modifier
            .constrainAs(content) {
              top.linkTo(parent.top)
              start.linkTo(parent.start)
              end.linkTo(parent.end)
              bottom.linkTo(bottomBar.top)
              height = Dimension.fillToConstraints
            }
        ) {
          composable("Now Playing") {
            MoviesListView(mainViewModel, this@MainActivity, "now_playing", navController)
          }
          composable("Top Rated") {
            MoviesListView(mainViewModel, this@MainActivity, "top_rated", navController)
          }
          composable("Popular") {
            MoviesListView(mainViewModel, this@MainActivity, "popular", navController)
          }
          composable(
            "details/{movieId}"
          ) { navBackStack ->
            MovieDetails(mainViewModel, navBackStack.arguments?.getString("movieId") ?: "", navController)
          }
        }

        BottomNavigation(backgroundColor = Color(0xff1f212a),
          modifier = Modifier
            .constrainAs(bottomBar) {
              bottom.linkTo(parent.bottom)
              start.linkTo(parent.start)
              end.linkTo(parent.end)
            }
            .wrapContentHeight()) {
          val backStackEntry by navController.currentBackStackEntryAsState()
          val currentRoute = backStackEntry?.destination?.route
          val tabs = listOf("Now playing", "Top Rated", "Popular")
          tabs.forEach {
            val isSelected = currentRoute == it
            BottomNavigationItem(selected = isSelected, onClick = {
              navController.navigate(it) {
                popUpTo(navController.graph.findStartDestination().id) {
                  saveState = true
                }
                launchSingleTop = true
                restoreState = true
              }
            },
              icon = {
              },
              selectedContentColor = Color.Magenta,
              unselectedContentColor = Color.White,
              label = {
                Text(
                  text = it
                )
              })
          }
        }
      }
    }
  }
}
