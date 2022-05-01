package com.tenutz.cracknotifier.di.module

import com.google.android.material.bottomsheet.BottomSheetDialog
import com.orhanobut.logger.Logger
import com.tenutz.cracknotifier.application.MainActivity
import com.tenutz.cracknotifier.databinding.BottomsheetFilterCracksBinding
import com.tenutz.cracknotifier.ui.common.BottomSheetFilterDialogFragment
import com.tenutz.cracknotifier.util.start
import com.tenutz.cracknotifier.util.toDateFormat
import com.tenutz.cracknotifier.util.tomorrow
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
}