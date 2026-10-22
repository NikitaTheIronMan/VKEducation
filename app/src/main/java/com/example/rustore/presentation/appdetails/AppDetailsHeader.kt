package com.example.rustore.presentation.appdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.rustore.AppIcon
import com.example.rustore.domain.appdetails.AppDetails
import com.example.rustore.domain.appdetails.Category
import kotlin.math.roundToInt

@Composable
fun AppDetailsHeader(
    appDetails: AppDetails,
    modifier: Modifier = Modifier.Companion
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(128.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            if (appDetails.iconUrl.isNotBlank()) {
                AsyncImage(
                    model = appDetails.iconUrl,
                    contentDescription = appDetails.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                AppIcon(
                    app = appDetails,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = getCategoryText(appDetails.category),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 12.sp
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = appDetails.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = appDetails.developer,
                fontSize = 12.sp
            )

            Spacer(Modifier.height(4.dp))

            Row {
                Column(
                    modifier = Modifier.width(IntrinsicSize.Max)
                ) {
                    Text(
                        text = "${appDetails.ageRating}+",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(text = "Возраст")
                }

                Spacer(Modifier.width(12.dp))

                Column {
                    Text(text = "${appDetails.size.roundToInt()} MB")

                    Spacer(Modifier.height(4.dp))

                    Text(text = "Размер")
                }
            }
        }
    }
}

private fun getCategoryText(category: Category): String = when (category) {
    Category.APP -> "Приложения"
    Category.GAME -> "Игры"
    Category.PRODUCTIVITY -> "Производительность"
    Category.SOCIAL -> "Социальные сети"
    Category.EDUCATION -> "Образование"
    Category.ENTERTAINMENT -> "Развлечения"
    Category.MUSIC -> "Музыка"
    Category.VIDEO -> "Видео"
    Category.PHOTOGRAPHY -> "Фотография"
    Category.HEALTH -> "Здоровье"
    Category.SPORTS -> "Спорт"
    Category.NEWS -> "Новости"
    Category.BOOKS -> "Книги"
    Category.BUSINESS -> "Бизнес"
    Category.FINANCE -> "Финансы"
    Category.TRAVEL -> "Путешествия"
    Category.MAPS -> "Карты"
    Category.FOOD -> "Еда"
    Category.SHOPPING -> "Покупки"
    Category.UTILITIES -> "Утилиты"
}