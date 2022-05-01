package com.tenutz.cracknotifier.ui.root.cracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.orhanobut.logger.Logger
import com.tenutz.cracknotifier.databinding.FragmentCracksBinding
import com.tenutz.cracknotifier.ui.common.BottomSheetFilterDialogFragment
import com.tenutz.cracknotifier.ui.root.RootFragment
import com.tenutz.cracknotifier.ui.root.RootFragmentDirections
import com.tenutz.cracknotifier.util.dummy.Dummies
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CracksFragment : Fragment() {

    private var _binding: FragmentCracksBinding? = null
    val binding: FragmentCracksBinding get() = _binding!!

    val viewModel: CracksViewModel by viewModels()

    @Inject
    lateinit var bottomSheetFilterDialogFragment: BottomSheetFilterDialogFragment

    private val adapter: TempCracksAdapter by lazy {
        TempCracksAdapter {
            (parentFragment as RootFragment).findNavController()
                .navigate(RootFragmentDirections.actionRootFragmentToCrackFragment(it))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.tempCracks()
    }

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

        initViews()
        observeData()
        setOnClickListeners()
    }

    private fun observeData() {
        viewModel.tempCracks.observe(viewLifecycleOwner) {
            adapter.items = it
        }
    }

    private fun initViews() {
        binding.recyclerCracks.adapter = adapter
    }

    private fun setOnClickListeners() {
        binding.btnCracksToSettings.setOnClickListener {
            (parentFragment as RootFragment).findNavController()
                .navigate(RootFragmentDirections.actionRootFragmentToSettingsFragment())
        }
        binding.imageCracksFilter.setOnClickListener {
            bottomSheetFilterDialogFragment.show(childFragmentManager, "bottomSheetFilterDialogFragment")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}