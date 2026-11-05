package com.example.pb05_adhammaula

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: CourseUiState,
    onSearchQueryChange: (String) -> Unit,
    onFilterChange: (String) -> Unit,
    onResetClick: () -> Unit,
    onCourseClick: (Int) -> Unit,
    onAddClick: () -> Unit
) {
    val filteredCourses = uiState.courses.filter { course ->
        val matchesQuery = course.code.contains(uiState.searchQuery, ignoreCase = true) ||
                course.name.contains(uiState.searchQuery, ignoreCase = true)
        val matchesFilter = when (uiState.selectedClassFilter) {
            "IF24A" -> course.code.equals("IF24A", ignoreCase = true)
            "IF24H" -> course.code.equals("IF24H", ignoreCase = true)
            else -> true
        }
        matchesQuery && matchesFilter
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Katalog Mata Kuliah") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Tambah")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text("Cari kode/nama matkul...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (uiState.searchQuery.isNotEmpty()) {
                        IconButton(onClick = { onSearchQueryChange("") }) {
                            Icon(Icons.Default.Clear, contentDescription = "Clear")
                        }
                    }
                },
                singleLine = true
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = uiState.selectedClassFilter == "ALL",
                    onClick = { onFilterChange("ALL") },
                    label = { Text("Semua") }
                )
                FilterChip(
                    selected = uiState.selectedClassFilter == "IF24A",
                    onClick = { onFilterChange("IF24A") },
                    label = { Text("IF24A") }
                )
                FilterChip(
                    selected = uiState.selectedClassFilter == "IF24H",
                    onClick = { onFilterChange("IF24H") },
                    label = { Text("IF24H") }
                )
                TextButton(onClick = onResetClick) {
                    Text("Reset")
                }
            }

            // Handling Non-Happy Path (Empty State)
            if (filteredCourses.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Mata kuliah tidak ditemukan",
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = onResetClick) {
                            Text("Reset Filter")
                        }
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredCourses, key = { it.id }) { course ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onCourseClick(course.id) },
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(course.code, color = MaterialTheme.colorScheme.primary)
                                    Text("${course.sks} SKS")
                                }
                                Spacer(Modifier.height(4.dp))
                                Text(course.name, style = MaterialTheme.typography.titleMedium)
                            }
                        }
                    }
                }
            }
        }
    }
}