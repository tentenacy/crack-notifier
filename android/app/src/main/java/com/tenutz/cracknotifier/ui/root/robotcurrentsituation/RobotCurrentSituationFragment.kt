package com.tenutz.cracknotifier.ui.root.robotcurrentsituation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tenutz.cracknotifier.databinding.FragmentRobotcsBinding
import com.tenutz.cracknotifier.ui.root.RootFragment
import com.tenutz.cracknotifier.ui.root.RootFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RobotCurrentSituationFragment: Fragment() {

    private var _binding: FragmentRobotcsBinding? = null
    val binding: FragmentRobotcsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRobotcsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btnRobotcsToSettings.setOnClickListener {
            (parentFragment as RootFragment).findNavController()
                .navigate(RootFragmentDirections.actionRootFragmentToSettingsFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
