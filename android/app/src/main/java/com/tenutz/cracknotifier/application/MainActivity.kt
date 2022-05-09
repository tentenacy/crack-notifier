package com.tenutz.cracknotifier.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.tenutz.cracknotifier.R
import com.tenutz.cracknotifier.sharedpref.Token
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Token.accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyXzAwMDAwMDAwMDEiLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjUyMDk1NDgwLCJleHAiOjE2NTIwOTkwODB9._3xmi-m7zTectLAHZkNFoQpsq7nAyqbMt-KOJgzE1ts"
    }
}