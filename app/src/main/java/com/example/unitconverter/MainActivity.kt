package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterApp()
        }
    }
}

@Composable
fun UnitConverterApp() {
    var inputValue by remember { mutableStateOf("") }
    var fromUnit by remember { mutableStateOf("Centimeters") }
    var toUnit by remember { mutableStateOf("Meters") }
    val units = listOf("Centimeters", "Meters", "Inches", "Grams", "Kilograms", "Pounds", "Celsius", "Fahrenheit", "Kelvin")

    val convertedValue = inputValue.toDoubleOrNull()?.let { convertUnits(it, fromUnit, toUnit) } ?: 0.0
    var conversionHistory by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            label = { Text("Enter Value") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            UnitDropdownMenu("From", units, fromUnit) { fromUnit = it }
            UnitDropdownMenu("To", units, toUnit) { toUnit = it }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Converted Value: $convertedValue",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00796B)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val historyEntry = "$inputValue $fromUnit = $convertedValue $toUnit"
            conversionHistory = listOf(historyEntry) + conversionHistory.take(4) // Store last 5 entries
        }) {
            Text("Save Conversion")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Conversion History:", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        conversionHistory.forEach { historyEntry ->
            Text(historyEntry, fontSize = 14.sp)
        }
    }
}

@Composable
fun UnitDropdownMenu(label: String, options: List<String>, selectedOption: String, onSelectionChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.animateContentSize()) {
        Button(
            onClick = { expanded = !expanded },
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("$label: $selectedOption")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelectionChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
