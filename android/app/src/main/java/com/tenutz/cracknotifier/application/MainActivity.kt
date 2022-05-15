package com.tenutz.cracknotifier.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.tenutz.cracknotifier.R
import com.tenutz.cracknotifier.util.navigateToLogin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.viewEvent.observe(this) {
            it?.getContentIfNotHandled()?.let {
                when(it.first) {
                    MainViewModel.EVENT_LOGOUT -> {
                        navigateToLogin()
                        Toast.makeText(this, "로그인 정보가 유효하지 않아 로그아웃되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}