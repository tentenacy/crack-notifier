package com.tenutz.cracknotifier.ui.root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tenutz.cracknotifier.R
import com.tenutz.cracknotifier.databinding.FragmentRootBinding
import com.tenutz.cracknotifier.ui.root.cracks.CracksFragment
import com.tenutz.cracknotifier.ui.root.robotcurrentsituation.RobotCurrentSituationFragment
import com.tenutz.cracknotifier.ui.root.works.WorksFragment
import com.tenutz.cracknotifier.util.mainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootFragment: Fragment() {

    private var _binding: FragmentRootBinding? = null
    val binding: FragmentRootBinding get() = _binding!!

    private lateinit var cracksFragment: CracksFragment
    private lateinit var worksFragment: WorksFragment
    private lateinit var robotCurrentSituationFragment: RobotCurrentSituationFragment

    private val fragments: Array<Fragment>
        get() = arrayOf(
            cracksFragment,
            worksFragment,
            robotCurrentSituationFragment,
        )

    private fun initFragments() {

        cracksFragment = CracksFragment()
        worksFragment = WorksFragment()
        robotCurrentSituationFragment = RobotCurrentSituationFragment()

        childFragmentManager.beginTransaction().apply {
            add(
                R.id.frame_root,
                cracksFragment,
                "cracksFragment"
            )
            add(
                R.id.frame_root,
                worksFragment,
                "worksFragment"
            )
            add(
                R.id.frame_root,
                robotCurrentSituationFragment,
                "robotCurrentSituationFragment"
            )
        }.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFragments()
        selectFragment(cracksFragment)
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

        setOtherListeners()
    }

    private fun setOtherListeners() {
        binding.bnvRoot.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_cracks -> selectFragment(cracksFragment)
                R.id.menu_works -> selectFragment(worksFragment)
                R.id.menu_robotcs -> selectFragment(robotCurrentSituationFragment)
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