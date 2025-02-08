package com.example.unitconverter

fun convertUnits(value: Double, fromUnit: String, toUnit: String): Double {
    return when {
        // Length Conversions
        fromUnit == "Centimeters" && toUnit == "Meters" -> value / 100
        fromUnit == "Meters" && toUnit == "Centimeters" -> value * 100
        fromUnit == "Inches" && toUnit == "Centimeters" -> value * 2.54
        fromUnit == "Centimeters" && toUnit == "Inches" -> value / 2.54

        // Weight Conversions
        fromUnit == "Grams" && toUnit == "Kilograms" -> value / 1000
        fromUnit == "Kilograms" && toUnit == "Grams" -> value * 1000
        fromUnit == "Pounds" && toUnit == "Kilograms" -> value * 0.453592
        fromUnit == "Kilograms" && toUnit == "Pounds" -> value / 0.453592

        // Temperature Conversions
        fromUnit == "Celsius" && toUnit == "Fahrenheit" -> (value * 9 / 5) + 32
        fromUnit == "Fahrenheit" && toUnit == "Celsius" -> (value - 32) * 5 / 9
        fromUnit == "Celsius" && toUnit == "Kelvin" -> value + 273.15
        fromUnit == "Kelvin" && toUnit == "Celsius" -> value - 273.15

        else -> value
    }
}
