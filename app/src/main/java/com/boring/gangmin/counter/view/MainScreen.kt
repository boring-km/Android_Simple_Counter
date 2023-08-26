package com.boring.gangmin.counter.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boring.gangmin.counter.ui.CounterTheme
import com.boring.gangmin.counter.ui.deepGreen
import com.boring.gangmin.counter.ui.greenBackgroundColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: CounterViewModel = CounterViewModel(), onVibrate: () -> Unit = {}) {
    CounterTheme {
        Scaffold {
            val text = viewModel.text.collectAsState().value

            Column {
                BasicTextField(
                    value = text,
                    onValueChange = { str ->
                        viewModel.setValue(str)
                    },
                    textStyle = MaterialTheme.typography.titleLarge,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(greenBackgroundColor)
                )
                Row {
                    TextButton(onClick = {
                        viewModel.minus()
                    }) {
                        Text(
                            text = "-",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(horizontal = 20.dp)
                        )
                    }
                    TextButton(
                        onClick = {
                            viewModel.reset()
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                deepGreen
                            )
                    ) {
                        Text(
                            text = "RESET",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
                TextButton(
                    onClick = {
                        viewModel.add()
                        onVibrate()
                    }, modifier = Modifier
                        .background(Color.Black)
                        .fillMaxSize()
                ) {
                    Text(
                        text = "+",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}