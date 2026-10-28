package com.example.universitiesco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.universitiesco.ViewModels.UniversityViewModel
import com.example.universitiesco.data.University
import com.example.universitiesco.ui.theme.UniversitiesCOTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UniversitiesCOTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainSreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainSreen(modifier: Modifier = Modifier){
    val viewModel: UniversityViewModel = viewModel()
    val university by viewModel.university.collectAsState()
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
                viewModel.loadUniversity(name)
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
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "${university.name}",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${university.country}"
                    )
                }

                HorizontalDivider()
            }
        }
    }
}

