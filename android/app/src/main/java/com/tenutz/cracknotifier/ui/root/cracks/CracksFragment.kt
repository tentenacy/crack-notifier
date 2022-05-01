package com.tenutz.cracknotifier.ui.root.cracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tenutz.cracknotifier.databinding.FragmentCracksBinding
import com.tenutz.cracknotifier.ui.root.RootFragment
import com.tenutz.cracknotifier.ui.root.RootFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CracksFragment : Fragment() {

    private var _binding: FragmentCracksBinding? = null
    val binding: FragmentCracksBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCracksBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        /*binding.btnCracksToCrack.setOnClickListener {
            (parentFragment as RootFragment).findNavController()
                .navigate(RootFragmentDirections.actionRootFragmentToCrackFragment())
        }*/
        binding.btnCracksToSettings.setOnClickListener {
            (parentFragment as RootFragment).findNavController()
                .navigate(RootFragmentDirections.actionRootFragmentToSettingsFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}