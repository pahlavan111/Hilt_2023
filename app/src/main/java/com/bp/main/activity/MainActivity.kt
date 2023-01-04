package com.bp.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bp.main.api.RemoteErrorEmitter
import com.bp.hilt.R
import com.bp.main.api.ErrorType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , RemoteErrorEmitter {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onError(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onError(errorType: ErrorType) {
        TODO("Not yet implemented")
    }
}