package com.tenutz.cracknotifier.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tenutz.cracknotifier.databinding.FragmentSettingsAccountBinding
import com.tenutz.cracknotifier.util.mainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsAccountFragment : Fragment() {

    private var _binding: FragmentSettingsAccountBinding? = null
    val binding: FragmentSettingsAccountBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingsAccountBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.linearSettingsaccountContainerAccount.setOnClickListener {
            mainActivity().viewModel.logout()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}