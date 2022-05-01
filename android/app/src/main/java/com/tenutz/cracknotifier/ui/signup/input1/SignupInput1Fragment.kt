package com.tenutz.cracknotifier.ui.signup.input1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.orhanobut.logger.Logger
import com.tenutz.cracknotifier.databinding.FragmentSignupInput1Binding
import com.tenutz.cracknotifier.ui.signup.ContainerSignupFragment
import com.tenutz.cracknotifier.util.mainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupInput1Fragment: Fragment() {

    private var _binding: FragmentSignupInput1Binding? = null
    val binding: FragmentSignupInput1Binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignupInput1Binding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        (parentFragment?.parentFragment as ContainerSignupFragment).binding.btnContainersignupNext.setOnClickListener {
            findNavController().navigate(SignupInput1FragmentDirections.actionSignupInput1FragmentToSignupInput2Fragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}