package com.tenutz.cracknotifier.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.tenutz.cracknotifier.R
import com.tenutz.cracknotifier.databinding.FragmentContainerSignupBinding
import com.tenutz.cracknotifier.util.mainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContainerSignupFragment: Fragment() {

    private var _binding: FragmentContainerSignupBinding? = null
    val binding: FragmentContainerSignupBinding get() = _binding!!

    val viewModel: ContainerSignupViewModel by viewModels()

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
        viewModel.viewEvent.observe(viewLifecycleOwner) {
            it?.getContentIfNotHandled()?.let {
                when (it.first) {
                    ContainerSignupViewModel.EVENT_NAVIGATE_TO_ROOT -> {
                        findNavController()
                            .navigate(ContainerSignupFragmentDirections.actionSignupFragmentToRootFragment())
                    }
                }
            }
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