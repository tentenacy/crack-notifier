package com.tenutz.cracknotifier.di.module

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.orhanobut.logger.Logger
import com.tenutz.cracknotifier.R
import com.tenutz.cracknotifier.application.MainActivity
import com.tenutz.cracknotifier.databinding.BottomsheetFilterCracksBinding
import com.tenutz.cracknotifier.ui.common.BottomSheetFilterDialogFragment
import com.tenutz.cracknotifier.util.start
import com.tenutz.cracknotifier.util.toDateFormat
import com.tenutz.cracknotifier.util.tomorrow
import com.tenutz.cracknotifier.util.view.CustomDivider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import java.util.*

@InstallIn(FragmentComponent::class)
@Module
class ViewModule {

    @FragmentScoped
    @Provides
    fun provideBottomSheetFilterCracks(): BottomSheetFilterDialogFragment {
        return BottomSheetFilterDialogFragment()
    }

    @FragmentScoped
    @Provides
    fun provideCustomDivider(fragment: Fragment): CustomDivider {
        return CustomDivider(2.5f, 1f, ContextCompat.getColor(fragment.requireContext(), R.color.divider))
    }
}