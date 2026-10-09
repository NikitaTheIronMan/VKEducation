
package com.example.rustore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rustore.ui.theme.RuStoreTheme

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val receivedText = intent.getStringExtra(EXTRA_TEXT)

        setContent {
            RuStoreTheme {
                SecondScreen(
                    text = receivedText
                        ?.takeIf { it.isNotBlank() }
                        ?: "Текст не передан",
                    onBack = { finish() }
                )
            }
        }
    }

    companion object {
        const val EXTRA_TEXT = "com.example.rustore.EXTRA_TEXT"
    }
}

@Composable
private fun SecondScreen(
    text: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Вторая Activity",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )

        Button(onClick = onBack) {
            Text("Назад")
        }
    }
}
