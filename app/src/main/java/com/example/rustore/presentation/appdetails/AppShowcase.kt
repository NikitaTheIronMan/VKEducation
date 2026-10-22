package com.example.rustore.presentation.appdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rustore.AppItem
import com.example.rustore.domain.appdetails.AppDetails

private const val PAGE_COUNT = 3
private const val APPS_PER_PAGE = 3

@Composable
fun AppShowcase(
    apps: List<AppDetails>,
    onAppClick: (AppDetails) -> Unit,
    modifier: Modifier = Modifier.Companion
) {
    Column(modifier = modifier) {
        Text(
            text = "Витрина",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        HorizontalPager(
            state = rememberPagerState(
                pageCount = { PAGE_COUNT }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 12.dp
        ) { page ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repeat(APPS_PER_PAGE) { index ->
                    val appIndex = page * APPS_PER_PAGE + index
                    val app = apps[appIndex]

                    AppItem(
                        app = app,
                        onClick = {
                            onAppClick(app)
                        }
                    )
                }
            }
        }
    }
}