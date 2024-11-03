package com.hizari.fakestore.ui.activity

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.navigation.compose.rememberNavController
import com.hizari.common.extention.toast
import com.hizari.common.util.FSLog
import com.hizari.fakestore.R
import com.hizari.fakestore.navigation.root.RootNavigation
import com.hizari.fakestore.ui.theme.FakeStoreTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * Fake Store - com.hizari.fakestore.ui.activity
 *
 * Created by hudiohizari on 02/11/24.
 * https://github.com/hudiohizari
 *
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var backPressedOnce = false
    private val backPressHandler = Handler(Looper.getMainLooper())
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }

        enableEdgeToEdge()

        setContent {
            BackPressHandler()
            val handler = CoroutineExceptionHandler { _, throwable ->
                FSLog.e("There has been an issue: ", throwable)
            }

            val rootNavController = rememberNavController()

            val userResult by viewModel.userResult.collectAsState()

            CompositionLocalProvider(
                value = LocalFontFamilyResolver provides createFontFamilyResolver(
                    context = LocalContext.current,
                    coroutineContext = handler
                )
            ) {
                FakeStoreTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        if (userResult.loading().not()) {
                            RootNavigation(
                                modifier = Modifier.padding(paddingValues = innerPadding),
                                rootNavController = rootNavController,
                                userLoggedIn = userResult.success()
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        backPressHandler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    @Composable
    fun BackPressHandler() {
        BackHandler {
            if (backPressedOnce) finish() else {
                backPressedOnce = true
                this@MainActivity.toast(R.string.press_once_more_to_exit)

                backPressHandler.postDelayed({
                    backPressedOnce = false
                }, 1000)
            }
        }
    }
}