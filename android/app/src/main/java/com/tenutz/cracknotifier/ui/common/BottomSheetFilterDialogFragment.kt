package com.tenutz.cracknotifier.ui.common

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tenutz.cracknotifier.R
import com.tenutz.cracknotifier.databinding.BottomsheetFilterCracksBinding
import com.tenutz.cracknotifier.ui.root.cracks.CracksViewModel
import com.tenutz.cracknotifier.util.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class BottomSheetFilterDialogFragment : BottomSheetDialogFragment() {

    private var _binding: BottomsheetFilterCracksBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CracksViewModel by viewModels({ requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = BottomsheetFilterCracksBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expandFullHeight()
        observeData()
        setOnClickListeners()
        setOtherListeners()
    }

    private fun setOtherListeners() {
        binding.radiogroupBsfiltercracksDuration.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radiobtn_bsfiltercracks_whole -> {
                    binding.btnBsfiltercracksStartdate.text = "-"
                    binding.btnBsfiltercracksEnddate.text = Date().toDateFormat()
                }
                R.id.radiobtn_bsfiltercracks_today -> {
                    binding.btnBsfiltercracksStartdate.text = Date().toDateFormat()
                    binding.btnBsfiltercracksEnddate.text = Date().toDateFormat()
                }
                R.id.radiobtn_bsfiltercracks_week -> {
                    binding.btnBsfiltercracksStartdate.text = Date().minusDay(7).toDateFormat()
                    binding.btnBsfiltercracksEnddate.text = Date().toDateFormat()
                }
                R.id.radiobtn_bsfiltercracks_month -> {
                    binding.btnBsfiltercracksStartdate.text = Date().minusMonth(1).toDateFormat()
                    binding.btnBsfiltercracksEnddate.text = Date().toDateFormat()
                }
                R.id.radiobtn_bsfiltercracks_year -> {
                    binding.btnBsfiltercracksStartdate.text = Date().minusYear(1).toDateFormat()
                    binding.btnBsfiltercracksEnddate.text = Date().toDateFormat()
                }
            }
        }
    }

    private fun setOnClickListeners() {
        binding.btnBsfiltercracksApply.setOnClickListener {
            applyFilter()
        }
        binding.imageBsfiltercracksClose.setOnClickListener {
            dismiss()
        }
        binding.textBsfiltercracksReset.setOnClickListener {
            resetFilter()
        }
        binding.btnBsfiltercracksStartdate.setOnClickListener {
            showDatePicker(it as Button)
        }
        binding.btnBsfiltercracksEnddate.setOnClickListener {
            showDatePicker(it as Button)
        }
    }

    private fun observeData() {
        viewModel.durationCheckedRadioId.observe(viewLifecycleOwner) {
            when (it) {
                R.id.radiobtn_bsfiltercracks_whole -> binding.radiobtnBsfiltercracksWhole.isChecked =
                    true
                R.id.radiobtn_bsfiltercracks_today -> binding.radiobtnBsfiltercracksToday.isChecked =
                    true
                R.id.radiobtn_bsfiltercracks_week -> binding.radiobtnBsfiltercracksWeek.isChecked =
                    true
                R.id.radiobtn_bsfiltercracks_month -> binding.radiobtnBsfiltercracksMonth.isChecked =
                    true
                R.id.radiobtn_bsfiltercracks_year -> binding.radiobtnBsfiltercracksYear.isChecked =
                    true
                else -> binding.radiogroupBsfiltercracksDuration.clearCheck()
            }
        }
        viewModel.sortCheckedRadioId.observe(viewLifecycleOwner) {
            when(it) {
                R.id.radiobtn_bsfiltercracks_sort_latest -> binding.radiobtnBsfiltercracksSortLatest.isChecked = true
                R.id.radiobtn_bsfiltercracks_sort_popularity -> binding.radiobtnBsfiltercracksSortPopularity.isChecked = true
            }
        }
        viewModel.dateFromText.observe(viewLifecycleOwner) {
            binding.btnBsfiltercracksStartdate.text = it ?: "-"
        }
        viewModel.dateToText.observe(viewLifecycleOwner) {
            binding.btnBsfiltercracksEnddate.text = it ?: Date().start().toDateFormat()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun expandFullHeight() {
        BottomSheetBehavior.from(dialog?.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!).state =
            BottomSheetBehavior.STATE_EXPANDED
    }

    private fun resetFilter() {

        binding.apply {
            binding.radiobtnBsfiltercracksWhole.isChecked = true
            binding.radiobtnBsfiltercracksSortLatest.isChecked = true
        }
    }

    private fun applyFilter() {

        binding.apply {
            viewModel.setDurationCheckedRadioId(radiogroupBsfiltercracksDuration.checkedRadioButtonId)
            viewModel.setSortCheckedRadioId(radiogroupBsfiltercracksSorting.checkedRadioButtonId)
            viewModel.setDateFromText(binding.btnBsfiltercracksStartdate.text.toString().takeIf { it != "-" })
            viewModel.setDateToText(binding.btnBsfiltercracksEnddate.text.toString())
            viewModel.setSortText(when(binding.radiogroupBsfiltercracksSorting.checkedRadioButtonId) {
                R.id.radiobtn_bsfiltercracks_sort_latest -> "createdAt,desc"
                //TODO: 서버에서 좋아요순 구현 후 작업
                R.id.radiobtn_bsfiltercracks_sort_popularity -> null
                else -> null
            })
            viewModel.cracks()
            dismiss()
        }
    }

    private fun showDatePicker(view: Button) {
        val cal = calendarFrom(view.text.toString())
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            R.style.DialogTheme,
            { _, year, month, dayOfMonth ->
                binding.radiogroupBsfiltercracksDuration.clearCheck()

                when (view.id) {
                    R.id.btn_bsfiltercracks_startdate -> {

                        val startDateText = "$year-${
                            month.plus(1).toZeroZeroFormat()
                        }-${dayOfMonth.toZeroZeroFormat()}"

                        view.text = startDateText

                        if (!dateFrom(startDateText).before(dateFrom(binding.btnBsfiltercracksEnddate.text.toString()))) {
                            binding.btnBsfiltercracksEnddate.text = startDateText
                        }
                    }
                    R.id.btn_bsfiltercracks_enddate -> {

                        val endDateText = "$year-${
                            month.plus(1).toZeroZeroFormat()
                        }-${dayOfMonth.toZeroZeroFormat()}"

                        view.text = endDateText

                        if (binding.btnBsfiltercracksStartdate.text.toString() != "-" && !dateFrom(binding.btnBsfiltercracksStartdate.text.toString()).before(
                                dateFrom(endDateText)
                            )
                        ) {
                            binding.btnBsfiltercracksStartdate.text = endDateText
                        }
                    }
                }
            },
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
        )
        // 날짜 선택 제한
        limitDateSelection(datePickerDialog)
    }

    private fun limitDateSelection(dialog: DatePickerDialog) {
        dialog.apply {

            val now = Calendar.getInstance()

            datePicker.maxDate = now.timeInMillis

            now.add(Calendar.YEAR, -2)
            datePicker.minDate = now.timeInMillis
        }.show()
    }
}