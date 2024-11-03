package com.hizari.fakestore.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hizari.fakestore.ui.component.navhost.SlideNavHost
import com.hizari.fakestore.ui.screen.main.cart.CartScreen
import com.hizari.fakestore.ui.screen.main.detail.ProductDetailScreen
import com.hizari.fakestore.ui.screen.main.home.HomeScreen
import kotlinx.serialization.Serializable

/**
 * Fake Store - com.hizari.fakestore.navigation.main
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */



@Serializable
data object MainNavigation

@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
    val mainNavController = rememberNavController()

    fun mainNavAction(action: MainNavAction) {
        when (action) {
            is MainNavAction.GoBack -> {
                action.result?.let { results ->
                    results.forEach { result ->
                        mainNavController.previousBackStackEntry?.savedStateHandle?.set(
                            key = result.key,
                            value = result.value
                        )
                    }
                }
                mainNavController.popBackStack()
            }

            is MainNavAction.GoToScreen -> {
                mainNavController.navigate(action.destination) {
                    restoreState = true
                    launchSingleTop = true
                }
            }
        }
    }

    SlideNavHost(
        modifier = modifier,
        navController = mainNavController,
        startDestination = HomeScreen,
    ) {
        composable<HomeScreen> {
            HomeScreen(mainNavAction = ::mainNavAction)
        }
        composable<CartScreen> {
            CartScreen(mainNavAction = ::mainNavAction)
        }
        composable<ProductDetailScreen> {
            ProductDetailScreen(mainNavAction = ::mainNavAction)
        }
    }
}