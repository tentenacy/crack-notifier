package com.tenutz.cracknotifier.ui.root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.tenutz.cracknotifier.R
import com.tenutz.cracknotifier.databinding.FragmentMyinfoBinding
import com.tenutz.cracknotifier.databinding.FragmentRootBinding
import com.tenutz.cracknotifier.ui.root.cracks.CracksFragment
import com.tenutz.cracknotifier.ui.root.myinfo.MyInfoFragment
import dagger.hilt.android.AndroidEntryPoint
import java.lang.RuntimeException

@AndroidEntryPoint
class RootFragment: Fragment() {

    private var _binding: FragmentRootBinding? = null
    val binding: FragmentRootBinding get() = _binding!!

    private lateinit var cracksFragment: CracksFragment
    private lateinit var myInfoFragment: MyInfoFragment

    private val fragments: Array<Fragment>
        get() = arrayOf(
            cracksFragment,
            myInfoFragment,
        )

    private fun initFragments() {

        cracksFragment = CracksFragment()
        myInfoFragment = MyInfoFragment()

        childFragmentManager.beginTransaction().apply {
            add(
                R.id.frame_root,
                cracksFragment,
                "cracksFragment"
            )
            add(
                R.id.frame_root,
                myInfoFragment,
                "myInfoFragment"
            )
        }.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFragments()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRootBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bnvRoot.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_cracks -> selectFragment(cracksFragment)
                R.id.menu_myinfo -> selectFragment(myInfoFragment)
            }

            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun selectFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction().apply {
            fragments.forEach {
                if(it == fragment) {
                    show(fragment)
                } else {
                    hide(it)
                }
            }
        }.commit()
    }
}