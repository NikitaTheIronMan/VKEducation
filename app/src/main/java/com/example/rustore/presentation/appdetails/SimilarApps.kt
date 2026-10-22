package com.example.rustore.presentation.appdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.rustore.domain.appdetails.SimilarApp

@Composable
fun SimilarApps(
    apps: List<SimilarApp>,
    onDownloadClick: (SimilarApp) -> Unit,
    modifier: Modifier = Modifier.Companion
) {
    Column(modifier) {
        Text(
            text = "Вам может понравиться",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.size(4.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = apps,
                key = { app -> app.id }
            ) { app ->
                SimilarAppItem(
                    app = app,
                    onDownloadClick = {
                        onDownloadClick(app)
                    }
                )
            }
        }
    }
}

@Composable
private fun SimilarAppItem(
    app: SimilarApp,
    onDownloadClick: () -> Unit,
    modifier: Modifier = Modifier.Companion
) {
    Column(
        modifier = modifier.width(128.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(128.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFE5EAF2)),
            contentAlignment = Alignment.Center
        ) {
            if (app.iconUrl.isNotBlank()) {
                AsyncImage(
                    model = app.iconUrl,
                    contentDescription = app.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            } else {
                Text(
                    text = app.name.take(1),
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFF1769E0)
                )
            }
        }

        Text(
            text = app.name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Button(
            onClick = onDownloadClick,
            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Скачать")
        }
    }
}