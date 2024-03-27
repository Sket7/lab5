package com.example.lab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab5.ui.theme.Lab5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val first = "Введите сумму"
    val second = "Выберите срок"
    val buttonText = "Провести расчёт"
    val listRadio = listOf("3 месяцев (3%)", "6 месяцев (5%)", "12 месяцев (9%)")

    var number by remember {
        mutableDoubleStateOf(0.0)
    }
    var textF by remember {
        mutableStateOf("")
    }
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(listRadio[0]) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            Text(first, Modifier.fillMaxWidth(0.4f))
            TextField(
                value = textF,
                onValueChange = { text ->
                    textF = text.filter { it.isDigit() }
                },
                Modifier.fillMaxWidth(),
            )
        } // Ввод суммы
        Row(
            Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp, 0.dp, 10.dp),
            Arrangement.Start,
            Alignment.Top
        ) {
            Text(text = second)
            Column(Modifier.selectableGroup()) {
                listRadio.forEach { radioText ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            (radioText == selectedOption),
                            { onOptionSelected(radioText) })
                        Text(radioText)
                    }

                }
            }
        } // Выбор срока
        Button(
            onClick = {
                var gg = textF.toDouble()
                gg = when (selectedOption) {
                    "3 месяцев (3%)" -> gg + (gg * 0.03 * 0.25)
                    "6 месяцев (5%)" -> gg + (gg * 0.05 * 0.5)
                    "12 месяцев (9%)" -> gg + (gg * 0.09)
                    else -> 0.0
                }
                number = gg
            },
            Modifier.fillMaxWidth()
        ) {
            Text(buttonText)
        }
        Text(text = number.toString())

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab5Theme {
        Greeting()
    }
}