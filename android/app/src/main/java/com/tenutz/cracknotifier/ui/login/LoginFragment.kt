package com.tenutz.cracknotifier.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tenutz.cracknotifier.databinding.FragmentLoginBinding
import com.tenutz.cracknotifier.util.mainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: Fragment() {

    private var _binding: FragmentLoginBinding? = null
    val binding: FragmentLoginBinding get() = _binding!!

    val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btnLoginToEmail.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToEmailLoginFragment())
        }
        binding.btnLoginKakao.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRootFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}