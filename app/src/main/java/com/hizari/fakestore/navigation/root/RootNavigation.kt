package com.hizari.fakestore.navigation.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.hizari.fakestore.navigation.main.MainNavigation
import com.hizari.fakestore.ui.component.navhost.SlideNavHost
import com.hizari.fakestore.ui.screen.auth.login.LoginScreen

/**
 * Fake Store - com.hizari.fakestore.navigation.root
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@Composable
fun RootNavigation(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController,
    userLoggedIn: Boolean,
) {
    SlideNavHost(
        modifier = modifier,
        navController = rootNavController,
        startDestination = if (userLoggedIn) MainNavigation else LoginScreen
    ) {

        composable<LoginScreen> {
            LoginScreen()
        }
        composable<MainNavigation> {
            MainNavigation()
        }
    }
}