package com.espartanhack.pokedex20.core.domain.utils.extensions

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController

fun NavController.isNavigating() =
    currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

fun NavController.navigateBack() {
    if (isNavigating()) popBackStack()
}