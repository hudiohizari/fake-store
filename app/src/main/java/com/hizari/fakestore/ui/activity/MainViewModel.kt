package com.hizari.fakestore.ui.activity

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Fake Store - com.hizari.fakestore.ui.activity
 *
 * Created by hudiohizari on 03/11/24.
 * https://github.com/hudiohizari
 *
 */

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val mutableUserLoggedIn = MutableStateFlow(true)
    val userLoggedIn: StateFlow<Boolean> = mutableUserLoggedIn
}