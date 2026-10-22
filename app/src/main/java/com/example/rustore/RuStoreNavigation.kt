package com.example.rustore

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rustore.domain.appdetails.AppDetails
import com.example.rustore.domain.appdetails.SimilarApp
import com.example.rustore.presentation.appdetails.AppDetailsScreen

private object RuStoreRoutes {
    const val APPS = "apps"
    const val DETAILS = "details"
    const val APP_ID = "appId"
    const val DETAILS_WITH_ID = "$DETAILS/{$APP_ID}"
}

@Composable
internal fun RuStoreNavigation(
    apps: List<AppDetails>,
    showcaseApps: List<AppDetails>
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RuStoreRoutes.APPS,
        modifier = Modifier
    ) {
        composable(RuStoreRoutes.APPS) {
            RuStoreScreen(
                apps = apps,
                onAppClick = { app ->
                    navController.navigate(
                        "${RuStoreRoutes.DETAILS}/${app.id}"
                    )
                }
            )
        }

        composable(
            route = RuStoreRoutes.DETAILS_WITH_ID,
            arguments = listOf(
                navArgument(RuStoreRoutes.APP_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val appId = backStackEntry.arguments
                ?.getString(RuStoreRoutes.APP_ID)

            val selectedApp = apps.firstOrNull { app ->
                app.id == appId
            }

            if (selectedApp == null) {
                LaunchedEffect(appId) {
                    navController.popBackStack()
                }
            } else {
                val similarApps = apps
                    .filterNot { app -> app.id == selectedApp.id }
                    .take(5)
                    .map { app ->
                        SimilarApp(
                            id = app.id,
                            name = app.name,
                            iconUrl = app.iconUrl
                        )
                    }

                AppDetailsScreen(
                    appDetails = selectedApp,
                    similarApps = similarApps,
                    showcaseApps = showcaseApps,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onShowcaseAppClick = {
                        navController.popBackStack(
                            route = RuStoreRoutes.APPS,
                            inclusive = false
                        )
                    }
                )
            }
        }
    }
}