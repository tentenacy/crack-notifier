package com.tenutz.cracknotifier.ui.root.cracks

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.orhanobut.logger.Logger
import com.tenutz.cracknotifier.databinding.FragmentCracksBinding
import com.tenutz.cracknotifier.ui.common.BottomSheetFilterDialogFragment
import com.tenutz.cracknotifier.ui.root.RootFragment
import com.tenutz.cracknotifier.ui.root.RootFragmentDirections
import com.tenutz.cracknotifier.util.dummy.Dummies
import com.tenutz.cracknotifier.util.dummy.DummyCrackDetail
import com.tenutz.cracknotifier.util.mainActivity
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
            viewModel.tempCrack(it)
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

        mainActivity().setSupportActionBar(binding.toolbarCracks)

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
        viewModel.viewEvent.observe(viewLifecycleOwner) {
            it?.getContentIfNotHandled()?.let {
                when(it.first) {
                    CracksViewModel.EVENT_SEARCH -> {

                    }
                    CracksViewModel.EVENT_NAVIGATE_TO_CRACK -> {
                        val crack = it.second as DummyCrackDetail
                        Glide.with(binding.root)
                            .asBitmap()
                            .load(crack.imageUrl)
                            .into(object : CustomTarget<Bitmap>(){
                                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                                    (parentFragment as RootFragment).findNavController()
                                        .navigate(RootFragmentDirections.actionRootFragmentToCrackFragment(crack, resource))
                                }
                                override fun onLoadCleared(placeholder: Drawable?) {
                                    // this is called when imageView is cleared on lifecycle call or for
                                    // some other reason.
                                    // if you are referencing the bitmap somewhere else too other than this imageView
                                    // clear it here as you can no longer have the bitmap
                                }
                            })
                    }
                }
            }
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