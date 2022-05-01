package com.tenutz.cracknotifier.ui.signup.input3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tenutz.cracknotifier.databinding.FragmentSignupInput1Binding
import com.tenutz.cracknotifier.databinding.FragmentSignupInput3Binding
import com.tenutz.cracknotifier.ui.signup.ContainerSignupFragment
import com.tenutz.cracknotifier.ui.signup.ContainerSignupFragmentDirections
import com.tenutz.cracknotifier.util.mainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupInput3Fragment: Fragment() {

    private var _binding: FragmentSignupInput3Binding? = null
    val binding: FragmentSignupInput3Binding get() = _binding!!

    private lateinit var callback: OnBackPressedCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        }
        (parentFragment?.parentFragment as ContainerSignupFragment).mainActivity().onBackPressedDispatcher.addCallback(this, callback)
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

        _binding = FragmentSignupInput3Binding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (parentFragment?.parentFragment as ContainerSignupFragment).binding.btnContainersignupNext.setOnClickListener {
            (parentFragment?.parentFragment as ContainerSignupFragment).findNavController().navigate(ContainerSignupFragmentDirections.actionSignupFragmentToRootFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}