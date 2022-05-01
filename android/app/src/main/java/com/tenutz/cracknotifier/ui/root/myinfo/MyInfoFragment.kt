package com.tenutz.cracknotifier.ui.root.myinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tenutz.cracknotifier.databinding.FragmentCracksBinding
import com.tenutz.cracknotifier.databinding.FragmentMyinfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyInfoFragment: Fragment() {

    private var _binding: FragmentMyinfoBinding? = null
    val binding: FragmentMyinfoBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMyinfoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
