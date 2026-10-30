package com.example.pb05_adhammaula

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    course: Course?,
    onBackClick: () -> Unit,
    onEditClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(course?.name ?: "Detail Matkul") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (course != null) {
                        IconButton(onClick = { onEditClick(course.id) }) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)
        ) {
            if (course == null) { // Handling Invalid ID
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Data Mata Kuliah Tidak Ditemukan!", color = MaterialTheme.colorScheme.error)
                    Spacer(Modifier.height(16.dp))
                    Button(onClick = onBackClick) { Text("Kembali ke Home") }
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Kode: ${course.code}", color = MaterialTheme.colorScheme.primary)
                    Text(course.name, style = MaterialTheme.typography.headlineSmall)
                    Text("Bobot: ${course.sks} SKS")
                    HorizontalDivider()
                    Text("Deskripsi:", style = MaterialTheme.typography.titleSmall)
                    Text(course.description)
                }
            }
        }
    }
}