package com.tenutz.cracknotifier.ui.signup.input2

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
import com.tenutz.cracknotifier.databinding.FragmentSignupInput2Binding
import com.tenutz.cracknotifier.ui.signup.ContainerSignupFragment
import com.tenutz.cracknotifier.util.mainActivity
import com.tenutz.cracknotifier.util.signupFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupInput2Fragment: Fragment() {

    private var _binding: FragmentSignupInput2Binding? = null
    val binding: FragmentSignupInput2Binding get() = _binding!!

    private lateinit var callback: OnBackPressedCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        }
        signupFragment().mainActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()

        callback.remove()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignupInput2Binding.inflate(inflater, container, false)

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
        signupFragment().binding.textContainersignupFtitle.text = "가입하기 (2 / 3)"
        signupFragment().binding.btnContainersignupNext.text = "다음"
    }

    private fun setOnClickListeners() {
        signupFragment().binding.btnContainersignupNext.setOnClickListener {
            findNavController().navigate(SignupInput2FragmentDirections.actionSignupInput2FragmentToSignupInput3Fragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}