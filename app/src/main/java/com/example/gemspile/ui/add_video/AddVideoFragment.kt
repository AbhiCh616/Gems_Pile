package com.example.gemspile.ui.add_video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.gemspile.R
import com.example.gemspile.databinding.AddVideoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.urlTextField.let { editTextField ->
            editTextField.setText(viewModel.urlTextField)
            editTextField.doAfterTextChanged { editable ->
                editable?.let {
                    viewModel.urlTextField = editable.toString()
                }
            }
        }

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.save_btn -> {
                    viewModel.onSaveClick()
                    findNavController().popBackStack()
                    true
                }
                else -> false
            }
        }
    }
}
