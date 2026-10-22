package com.example.rustore.presentation.appdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rustore.domain.appdetails.AppDetails

@Composable
fun AppDetailsContent(
    content: AppDetailsState.Content,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onInstallClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onDeveloperClick: () -> Unit,
    onSimilarAppDownloadClick: (com.example.rustore.domain.appdetails.SimilarApp) -> Unit,
    showcaseApps: List<AppDetails>,
    onShowcaseAppClick: (AppDetails) -> Unit,
    modifier: Modifier = Modifier,
) {
    val appDetails = content.appDetails
    val screenshots = appDetails.screenshotUrlList.orEmpty()

    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        Toolbar(
            onBackClick = onBackClick,
            onShareClick = onShareClick,
        )

        Spacer(Modifier.height(8.dp))

        AppDetailsHeader(
            appDetails = appDetails,
            modifier = Modifier.padding(horizontal = 16.dp),
        )

        Spacer(Modifier.height(16.dp))

        InstallButton(
            onClick = onInstallClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )

        if (content.similarApps.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))

            SimilarApps(
                apps = content.similarApps,
                onDownloadClick = onSimilarAppDownloadClick,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }

        if (screenshots.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))

            ScreenshotsList(
                screenshotUrlList = screenshots,
                contentPadding = PaddingValues(horizontal = 16.dp),
            )
        }

        Spacer(Modifier.height(12.dp))

        AppDescription(
            description = appDetails.description,
            collapsed = content.descriptionCollapsed,
            onReadMoreClick = onReadMoreClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )

        Spacer(Modifier.height(12.dp))

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.outlineVariant,
        )

        Spacer(Modifier.height(12.dp))

        Developer(
            name = appDetails.developer,
            onClick = onDeveloperClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
        )

        Spacer(Modifier.height(24.dp))

        AppShowcase(
            apps = showcaseApps,
            onAppClick = onShowcaseAppClick,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(24.dp))
    }
}