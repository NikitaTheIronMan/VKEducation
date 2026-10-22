package com.example.rustore.presentation.appdetails

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.rustore.domain.appdetails.AppDetails
import com.example.rustore.domain.appdetails.SimilarApp

@Composable
fun AppDetailsScreen(
    appDetails: AppDetails,
    similarApps: List<SimilarApp>,
    showcaseApps: List<AppDetails>,
    onBackClick: () -> Unit,
    onShowcaseAppClick: (AppDetails) -> Unit,
) {
    val descriptionCollapsed = rememberSaveable(appDetails.id) {
        mutableStateOf(false)
    }

    val content = AppDetailsState.Content(
        appDetails = appDetails,
        similarApps = similarApps,
        descriptionCollapsed = descriptionCollapsed.value,
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { contentPadding ->
        AppDetailsContent(
            content = content,
            onBackClick = onBackClick,
            onShareClick = {},
            onInstallClick = {},
            onReadMoreClick = {
                descriptionCollapsed.value = true
            },
            onDeveloperClick = {},
            onSimilarAppDownloadClick = {},
            showcaseApps = showcaseApps,
            onShowcaseAppClick = onShowcaseAppClick,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .safeDrawingPadding(),
        )
    }
}