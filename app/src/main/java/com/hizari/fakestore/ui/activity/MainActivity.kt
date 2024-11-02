package com.hizari.fakestore.ui.activity

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hizari.common.extention.toast
import com.hizari.fakestore.R
import com.hizari.fakestore.ui.screen.auth.login.LoginScreen
import com.hizari.fakestore.ui.theme.FakeStoreTheme
import dagger.hilt.android.AndroidEntryPoint

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }

        enableEdgeToEdge()

        setContent {
            BackPressHandler()

            FakeStoreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
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