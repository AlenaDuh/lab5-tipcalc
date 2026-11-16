package com.example.tipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalc.ui.theme.TipCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalcTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DemoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DemoScreen(modifier: Modifier = Modifier) {
    // Состояния
    var orderSum by remember { mutableStateOf("") }
    var dishesCount by remember { mutableStateOf("") }
    var tipPercent by remember { mutableStateOf(10f) } // от 0 до 25
    val discountOptions = listOf(3, 5, 7, 10)
    var selectedDiscount by remember { mutableIntStateOf(3) }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Поле 1: Сумма заказа
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Сумма заказа: ", fontSize = 16.sp)
            OutlinedTextField(
                value = orderSum,
                onValueChange = { orderSum = it },
                modifier = Modifier.weight(1f),
                label = { Text("₽") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        // Поле 2: Количество блюд
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Количество блюд: ", fontSize = 16.sp)
            OutlinedTextField(
                value = dishesCount,
                onValueChange = { dishesCount = it },
                modifier = Modifier.weight(1f),
                label = { Text("шт") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        // Слайдер чаевых
        Text("Чаевые:", fontSize = 16.sp)
        Slider(
            value = tipPercent,
            onValueChange = { tipPercent = it },
            valueRange = 0f..25f,
            steps = 24 // Делает шаг 1%
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("0")
            Text("25")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Скидка:", fontSize = 16.sp)
            discountOptions.forEach { option ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    RadioButton(
                        selected = (selectedDiscount == option),
                        onClick = null // Кнопка неактивна для пользователя!
                    )
                    Text("$option%")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DemoScreenPreview() {
    TipCalcTheme {
        DemoScreen()
    }
}