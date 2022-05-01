package com.tenutz.cracknotifier.ui.signup.input1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tenutz.cracknotifier.databinding.FragmentSignupInput1Binding
import com.tenutz.cracknotifier.util.signupFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupInput1Fragment : Fragment() {

    private var _binding: FragmentSignupInput1Binding? = null
    val binding: FragmentSignupInput1Binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignupInput1Binding.inflate(inflater, container, false)

        binding.vm = signupFragment().viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setOnClickListeners()
    }

    private fun initViews() {
        signupFragment().binding.textContainersignupFtitle.text = "가입하기 (1 / 3)"
        signupFragment().binding.btnContainersignupNext.text = "다음"
    }

    private fun setOnClickListeners() {
        signupFragment().binding.btnContainersignupNext.setOnClickListener {
            findNavController().navigate(SignupInput1FragmentDirections.actionSignupInput1FragmentToSignupInput2Fragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}