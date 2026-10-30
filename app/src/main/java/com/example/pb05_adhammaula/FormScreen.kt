package com.example.pb05_adhammaula

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    course: Course?,
    onSaveClick: (code: String, name: String, sks: Int, desc: String) -> Unit,
    onCancelClick: () -> Unit
) {
    var code by remember { mutableStateOf(course?.code ?: "") }
    var name by remember { mutableStateOf(course?.name ?: "") }
    var sksText by remember { mutableStateOf(course?.sks?.toString() ?: "3") }
    var description by remember { mutableStateOf(course?.description ?: "") }

    var isCodeError by remember { mutableStateOf(false) }
    var isNameError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (course != null) "Edit Mata Kuliah" else "Tambah Mata Kuliah") },
                navigationIcon = {
                    IconButton(onClick = onCancelClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Batal")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = code,
                onValueChange = {
                    code = it
                    isCodeError = false
                },
                label = { Text("Kode Kelas (cth: IF24A / IF24H)") },
                isError = isCodeError,
                supportingText = { if (isCodeError) Text("Kode kelas tidak boleh kosong!") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    isNameError = false
                },
                label = { Text("Nama Mata Kuliah") },
                isError = isNameError,
                supportingText = { if (isNameError) Text("Nama mata kuliah tidak boleh kosong!") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = sksText,
                onValueChange = { sksText = it },
                label = { Text("Jumlah SKS") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Deskripsi Mata Kuliah") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                maxLines = 4
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onCancelClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Batal")
                }

                Button(
                    onClick = {
                        isCodeError = code.isBlank()
                        isNameError = name.isBlank()

                        if (!isCodeError && !isNameError) {
                            val sksVal = sksText.toIntOrNull() ?: 3
                            onSaveClick(code, name, sksVal, description)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Simpan")
                }
            }
        }
    }
}