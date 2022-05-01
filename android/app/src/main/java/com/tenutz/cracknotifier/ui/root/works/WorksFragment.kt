package com.tenutz.cracknotifier.ui.root.works

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tenutz.cracknotifier.databinding.FragmentWorksBinding
import com.tenutz.cracknotifier.ui.root.RootFragment
import com.tenutz.cracknotifier.ui.root.RootFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorksFragment: Fragment() {

    private var _binding: FragmentWorksBinding? = null
    val binding: FragmentWorksBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWorksBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btnWorksToSettings.setOnClickListener {
            (parentFragment as RootFragment).findNavController()
                .navigate(RootFragmentDirections.actionRootFragmentToSettingsFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}