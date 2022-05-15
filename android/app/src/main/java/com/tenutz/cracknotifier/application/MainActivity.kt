package com.tenutz.cracknotifier.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.tenutz.cracknotifier.R
import com.tenutz.cracknotifier.databinding.ActivityMainBinding
import com.tenutz.cracknotifier.util.navigateToLogin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val viewModel: MainViewModel by viewModels()

    lateinit var navGraph: NavGraph
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_CrackNotifier)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_main) as NavHostFragment
        navController = navHostFragment.navController

        navGraph =
            navController.navInflater.inflate(R.navigation.navigation_main)

        viewModel.autoLogin()

        viewModel.viewEvent.observe(this) {
            it?.getContentIfNotHandled()?.let {
                when(it.first) {
                    MainViewModel.EVENT_LOGOUT -> {
                        navigateToLogin()
                        Toast.makeText(this, "로그인 정보가 유효하지 않아 로그아웃되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                    MainViewModel.EVENT_LEAVE -> {
                        navGraph.startDestination = R.id.rootFragment
                        navController.graph = navGraph

                        setContentView(binding.root)
                    }
                    MainViewModel.EVENT_REMAIN -> {
                        navGraph.startDestination = R.id.loginFragment
                        navController.graph = navGraph

                        setContentView(binding.root)
                    }
                }
            }
        }
    }
}