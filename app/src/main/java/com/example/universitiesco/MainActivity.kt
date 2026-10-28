package com.example.universitiesco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.universitiesco.ViewModels.UniversityViewModel
import com.example.universitiesco.data.University
import com.example.universitiesco.ui.theme.UniversitiesCOTheme
import java.util.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UniversitiesCOTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    val viewModel: RandomViewModel = viewModel()

    NavHost(navController = navController, startDestination = "SearchScreen") {
        composable("SearchScreen") { SearchScreen(navController, viewModel) }
        composable("UniversityDetailScreen") { UniversityDetailScreen(navController, viewModel) }
    }
}

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: RandomViewModel,
    modifier: Modifier = Modifier
){
    val universityViewModel: UniversityViewModel = viewModel()
    val university by universityViewModel.university.collectAsState()
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(18.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = {name = it},
            placeholder = { Text(text = "University") }
        )
        Button(
            onClick = {
                universityViewModel.loadUniversity(name)
            },
            modifier.fillMaxWidth()
        ) {
            Text(text = "Search")
        }
        LazyColumn(

        ) {
            items(items = university) {
                    university ->
                Column(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable(onClick = {
                            viewModel.name = university.name
                            viewModel.country = university.country
                            viewModel.code = university.alpha_two_code
                            viewModel.state = university.stateProvince ?: "Unknown"
                            viewModel.page = university.web_pages.toString()

                            navController.navigate("UniversityDetailScreen")
                        })
                ) {
                    Text(
                        text = university.name,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = university.country
                    )
                }

                HorizontalDivider()
            }
        }
    }
}

@Composable
fun UniversityDetailScreen(
    navController: NavController,
    viewModel: RandomViewModel,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        item{
            Text(
                text = viewModel.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Country: ${viewModel.country}"
            )
            Text(
                text = "State/Province:${viewModel.state}"
            )
            Text(
                text = "Website: ${viewModel.country}"
            )
            Button(
                onClick = { navController.navigateUp()}
            ) {
                Text(
                    text = "Back to search"
                )
            }
        }
    }
}

