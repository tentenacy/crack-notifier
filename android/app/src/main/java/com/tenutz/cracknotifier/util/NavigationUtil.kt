package com.tenutz.cracknotifier.util

import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.tenutz.cracknotifier.R
import com.tenutz.cracknotifier.application.MainActivity

fun MainActivity.currentFragment(): Fragment? = supportFragmentManager.findFragmentById(R.id.container_main)

fun MainActivity.currentDestinationId(): Int? = currentFragment()?.let { NavHostFragment.findNavController(it).currentDestination?.id }

fun MainActivity.navigateToLogin() {
    if (currentDestinationId() != R.id.loginFragment) {
        val navController = findNavController(R.id.container_main)
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_main) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.navigation_main)
        graph.startDestination = R.id.loginFragment

        navController.graph = graph
    }
}