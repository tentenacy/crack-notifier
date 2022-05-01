package com.tenutz.cracknotifier.ui.emaillogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tenutz.cracknotifier.databinding.FragmentEmailLoginBinding
import com.tenutz.cracknotifier.util.mainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailLoginFragment: Fragment() {

    private var _binding: FragmentEmailLoginBinding? = null
    val binding: FragmentEmailLoginBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEmailLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.imageEmailloginBack.setOnClickListener {
            mainActivity().onBackPressed()
        }
        binding.btnEmaillogin.setOnClickListener {
            findNavController().navigate(EmailLoginFragmentDirections.actionEmailLoginFragmentToRootFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}