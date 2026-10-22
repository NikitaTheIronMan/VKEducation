package com.example.rustore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.rustore.domain.appdetails.AppDetails
import com.example.rustore.domain.appdetails.Category
import com.example.rustore.ui.theme.RuStoreTheme

private val AccentColor = Color(0xFF1769E0)
private val BackgroundColor = Color(0xFFF5F7FA)

private val apps = listOf(
    AppDetails(
        id = "telegram",
        name = "Telegram",
        developer = "Telegram",
        category = Category.SOCIAL,
        ageRating = 16,
        size = 50f,
        iconUrl = "",
        screenshotUrlList = null,
        description = "Сообщения, звонки и каналы"
    ),
    AppDetails(
        id = "vk",
        name = "ВКонтакте",
        developer = "VK",
        category = Category.SOCIAL,
        ageRating = 12,
        size = 100f,
        iconUrl = "",
        screenshotUrlList = null,
        description = "Музыка, видео и общение с друзьями"
    ),
    AppDetails(
        id = "quicknote",
        name = "QuickNote",
        developer = "QuickNote Team",
        category = Category.PRODUCTIVITY,
        ageRating = 0,
        size = 15f,
        iconUrl = "",
        screenshotUrlList = null,
        description = "Быстрые заметки и списки дел"
    ),
    AppDetails(
        id = "calmalist",
        name = "Calmalist",
        developer = "Calmalist Team",
        category = Category.PRODUCTIVITY,
        ageRating = 0,
        size = 20f,
        iconUrl = "",
        screenshotUrlList = null,
        description = "Планирование задач и полезных привычек"
    ),
    AppDetails(
        id = "fitriver",
        name = "FitRiver",
        developer = "FitRiver Team",
        category = Category.SPORTS,
        ageRating = 0,
        size = 35f,
        iconUrl = "",
        screenshotUrlList = null,
        description = "Тренировки и отслеживание активности"
    ),
    AppDetails(
        id = "shoply",
        name = "Shoply",
        developer = "Shoply Team",
        category = Category.SHOPPING,
        ageRating = 0,
        size = 25f,
        iconUrl = "",
        screenshotUrlList = null,
        description = "Покупки и выгодные предложения"
    ),
    AppDetails(
        id = "zenradio",
        name = "ZenRadio",
        developer = "ZenRadio Team",
        category = Category.MUSIC,
        ageRating = 0,
        size = 30f,
        iconUrl = "",
        screenshotUrlList = null,
        description = "Музыка и аудио для отдыха"
    )
)

/*
 * Витрина содержит ровно девять приложений:
 * семь существующих и два дополнительных.
 */
private val showcaseApps = apps + listOf(
    AppDetails(
        id = "pixtrix",
        name = "Pixtrix",
        developer = "Pixtrix Team",
        category = Category.PHOTOGRAPHY,
        ageRating = 0,
        size = 18f,
        iconUrl = "",
        screenshotUrlList = null,
        description = "Редактирование фотографий"
    ),
    AppDetails(
        id = "tinychef",
        name = "TinyChef",
        developer = "TinyChef Team",
        category = Category.FOOD,
        ageRating = 0,
        size = 22f,
        iconUrl = "",
        screenshotUrlList = null,
        description = "Идеи рецептов на каждый день"
    )
)

class RuStoreActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RuStoreTheme {
                RuStoreNavigation(
                    apps = apps,
                    showcaseApps = showcaseApps
                )
            }
        }
    }
}

@Composable
internal fun RuStoreScreen(
    apps: List<AppDetails>,
    onAppClick: (AppDetails) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = BackgroundColor,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            RuStoreToolbar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Популярные приложения",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 20.dp,
                    bottom = 12.dp
                )
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 4.dp,
                    bottom = 24.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = apps,
                    key = { app -> app.id }
                ) { app ->
                    AppItem(
                        app = app,
                        onClick = { onAppClick(app) }
                    )
                }
            }
        }
    }
}

@Composable
private fun RuStoreToolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AccentColor)
            .padding(
                top = WindowInsets.statusBars
                    .asPaddingValues()
                    .calculateTopPadding()
            )
            .height(64.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "R",
                color = AccentColor,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp
            )
        }

        Spacer(modifier = Modifier.size(12.dp))

        Text(
            text = "RuStore",
            color = Color.White,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
internal fun AppItem(
    app: AppDetails,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon(
                app = app,
                modifier = Modifier.size(60.dp)
            )

            Spacer(modifier = Modifier.size(14.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = app.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = app.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF606976)
                )

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(0xFFEDF3FF)
                ) {
                    Text(
                        text = app.category.displayName(),
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 4.dp
                        ),
                        color = AccentColor,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Composable
internal fun AppIcon(
    app: AppDetails,
    modifier: Modifier = Modifier
) {
    val iconColor = when (app.category) {
        Category.SOCIAL -> Color(0xFFDCE8FF)
        Category.PRODUCTIVITY -> Color(0xFFFFE5D6)
        Category.SPORTS -> Color(0xFFDDF5E4)
        Category.SHOPPING -> Color(0xFFF0DFFF)
        Category.MUSIC -> Color(0xFFFFEBC2)
        else -> Color(0xFFE5EAF2)
    }

    val symbol = when (app.id) {
        "telegram" -> "✈"
        "vk" -> "VK"
        "quicknote" -> "N"
        "calmalist" -> "C"
        "fitriver" -> "F"
        "shoply" -> "S"
        "zenradio" -> "♫"
        else -> app.name.take(1)
    }

    Box(
        modifier = modifier.background(
            color = iconColor,
            shape = RoundedCornerShape(14.dp)
        ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol,
            color = AccentColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

private fun Category.displayName(): String = when (this) {
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RuStoreScreenPreview() {
    RuStoreTheme {
        RuStoreScreen(
            apps = apps,
            onAppClick = {}
        )
    }
}