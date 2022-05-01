package com.tenutz.cracknotifier.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.tenutz.cracknotifier.R
import com.tenutz.cracknotifier.databinding.FragmentContainerSignupBinding
import com.tenutz.cracknotifier.util.mainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContainerSignupFragment: Fragment() {

    private var _binding: FragmentContainerSignupBinding? = null
    val binding: FragmentContainerSignupBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentContainerSignupBinding.inflate(inflater, container, false)

        initNavHostFragment()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageContainersignupBack.setOnClickListener {
            mainActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun initNavHostFragment() {
        childFragmentManager.beginTransaction()
            .replace(
                R.id.container_signup,
                NavHostFragment.create(R.navigation.navigation_signup)
            )
            .commit()
    }
}