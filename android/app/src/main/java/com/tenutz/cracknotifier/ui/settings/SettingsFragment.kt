package com.tenutz.cracknotifier.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tenutz.cracknotifier.data.sharedpref.Settings
import com.tenutz.cracknotifier.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment: Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    val binding: FragmentSettingsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settings = Settings

        setOnClickListeners()
        setOtherListeners()
    }

    private fun setOtherListeners() {
        binding.switchSettingsPush.setOnCheckedChangeListener { buttonView, isChecked ->
            Settings.pushNotification = isChecked
        }
        binding.switchSettingsPushCracks.setOnCheckedChangeListener { buttonView, isChecked ->
            Settings.pushNotificationCrackRegistration = isChecked
        }
        binding.switchSettingsPushBattery.setOnCheckedChangeListener { buttonView, isChecked ->
            Settings.pushNotificationBattery = isChecked
        }
    }

    private fun setOnClickListeners() {
        binding.imageSettingsBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.linearSettingsContainerAccount.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToSettingsAccountFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}