package com.tenutz.cracknotifier.ui.crack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tenutz.cracknotifier.databinding.FragmentCrackBinding
import com.tenutz.cracknotifier.util.mainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CrackFragment: Fragment() {

    private var _binding: FragmentCrackBinding? = null
    val binding: FragmentCrackBinding get() = _binding!!

    val viewModel: CrackViewModel by viewModels()

    val args: CrackFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCrackBinding.inflate(inflater, container, false)

        mainActivity().setSupportActionBar(binding.toolbarCrack)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeData()
        setOnClickListeners()
    }

    private fun observeData() {
        viewModel.tempCrack.observe(viewLifecycleOwner) {
            Glide.with(binding.root)
                .asBitmap()
                .load(it.imageUrl)
                .into(binding.imageCrack)
            binding.crack = it
        }
    }

    private fun setOnClickListeners() {
        binding.btnCrackRefresh.setOnClickListener {
            viewModel.tempCrack(args.crack.id)
        }
        binding.imageCrackBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initViews() {
        binding.imageCrack.setImageBitmap(args.imageBitmap)
        binding.crack = args.crack
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}