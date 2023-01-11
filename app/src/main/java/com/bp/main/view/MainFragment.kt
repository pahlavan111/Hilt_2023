package com.bp.main.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bp.hilt.R
import com.bp.main.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        textView = view.findViewById(R.id.text)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.getPhotos().observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    for (item in it) {
                        Log.d("HERE", item.title.toString())
                    }
                }
            }
        )

        textView.setOnClickListener{
            Log.d("HERE", "GET request sent")
            mainViewModel.getPhoto(8).observe(
                viewLifecycleOwner,
                Observer {
                    if (it != null) {
                        for (item in it) {
                            Log.d("HERE", item.title.toString())
                            if (item.id == 8) {
                                textView.text = item.title
                            }
                        }
                    }
                }
            )
        }
    }
}
