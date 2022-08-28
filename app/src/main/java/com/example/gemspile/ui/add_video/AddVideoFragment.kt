package com.example.gemspile.ui.add_video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gemspile.databinding.AddVideoBinding

class AddVideoFragment : Fragment() {
    private val viewModel: AddVideoViewModel by viewModels()
    private lateinit var binding: AddVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddVideoBinding.inflate(inflater, container, false)
        return binding.root
    }
}