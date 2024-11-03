package com.hizari.fakestore.ui.activity

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlin.random.Random

/**
 * Fake Store - com.hizari.fakestore.ui.activity
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val mutableCartCounts = MutableStateFlow(Random.nextInt(99))
    val cartCounts: StateFlow<Int> = mutableCartCounts

    private val mutableUserLoggedIn = MutableStateFlow(true)
    val userLoggedIn: StateFlow<Boolean> = mutableUserLoggedIn
}