package com.bp.main.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bp.hilt.R
import com.bp.main.api.ErrorType
import com.bp.main.api.RemoteErrorEmitter
import com.bp.main.api.RetrofitBuilder
import com.bp.main.api.apiCall
import com.bp.main.model.Post
import com.bp.main.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RemoteErrorEmitter {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webService = RetrofitBuilder.api

        findViewById<Button>(R.id.fire).setOnClickListener {
            var thePosts = apiCall<MutableList<Post>>(this) { webService.getPosts() }.observe(
                this,
                Observer {
                    if (it != null) {
                        for (item in it)
                            Log.d("HERE", item.title)
                    }
                }
            )
        }
    }

    override fun onError(msg: String) {
        toast(msg)
    }

    override fun onError(errorType: ErrorType) {

        when(errorType){
            ErrorType.TIMEOUT -> {
                toast("Time out")
            }
            ErrorType.NETWORK -> {
                toast("network error")
            }
            else -> {
                toast("unknown error")
            }
        }
    }
}