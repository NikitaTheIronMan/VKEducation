
package com.example.rustore

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rustore.ui.theme.RuStoreTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RuStoreTheme {
                MainScreen(
                    onOpenSecondActivity = { text ->
                        val intent = Intent(
                            this@MainActivity,
                            SecondActivity::class.java
                        ).apply {
                            putExtra(SecondActivity.EXTRA_TEXT, text)
                        }

                        startActivity(intent)
                    },
                    onEmptyText = {
                        Toast.makeText(
                            this@MainActivity,
                            "Введите текст",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    onOpenSecondActivity: (String) -> Unit,
    onEmptyText: () -> Unit
) {
    var inputText by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Введите данные")
                },
                minLines = 2
            )

            Button(
                onClick = {
                    val text = inputText.trim()

                    if (text.isBlank()) {
                        onEmptyText()
                    } else {
                        onOpenSecondActivity(text)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Открыть вторую Activity")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    RuStoreTheme {
        MainScreen(
            onOpenSecondActivity = {},
            onEmptyText = {}
        )
    }
}