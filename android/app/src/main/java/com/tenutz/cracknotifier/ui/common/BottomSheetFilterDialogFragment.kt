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
        viewModel.termCheckedRadioId.observe(viewLifecycleOwner) {
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
                null -> binding.radiogroupBsfiltercracksDuration.clearCheck()
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

            btnBsfiltercracksStartdate.text = Date().start().toDateFormat()
            btnBsfiltercracksEnddate.text = Date().start().toDateFormat()

            radiogroupBsfiltercracksDuration.clearCheck()
            radiogroupBsfiltercracksSorting.clearCheck()

            radiobtnBsfiltercracksWhole.isChecked = true
            radiobtnBsfiltercracksSortLatest.isChecked = true

            viewModel.setTermCheckedRadioId(R.id.radiobtn_bsfiltercracks_whole)
        }
    }

    private fun applyFilter() {

        binding.apply {
            when (radiogroupBsfiltercracksDuration.checkedRadioButtonId) {
                radiobtnBsfiltercracksWhole.id -> {
                    viewModel.setDateFromText(null)
                    viewModel.setDateToText(Date().toDateFormat())
                    viewModel.setTermCheckedRadioId(R.id.radiobtn_bsfiltercracks_whole)
                }

                radiobtnBsfiltercracksToday.id -> {
                    viewModel.setDateFromText(Date().toDateFormat())
                    viewModel.setDateToText(Date().toDateFormat())
                    viewModel.setTermCheckedRadioId(R.id.radiobtn_bsfiltercracks_today)
                }
                radiobtnBsfiltercracksWeek.id -> {
                    viewModel.setDateFromText(Date().minusDay(7).toDateFormat())
                    viewModel.setDateToText(Date().toDateFormat())
                    viewModel.setTermCheckedRadioId(R.id.radiobtn_bsfiltercracks_week)
                }

                radiobtnBsfiltercracksMonth.id -> {
                    viewModel.setDateFromText(Date().minusMonth(1).toDateFormat())
                    viewModel.setDateToText(Date().toDateFormat())
                    viewModel.setTermCheckedRadioId(R.id.radiobtn_bsfiltercracks_month)
                }

                radiobtnBsfiltercracksYear.id -> {
                    viewModel.setDateFromText(Date().minusYear(1).toDateFormat())
                    viewModel.setDateToText(Date().toDateFormat())
                    viewModel.setTermCheckedRadioId(R.id.radiobtn_bsfiltercracks_year)
                }
                else -> {
                    viewModel.setDateFromText(if (binding.btnBsfiltercracksStartdate.text.toString() == "-") null else binding.btnBsfiltercracksStartdate.text.toString())
                    viewModel.setDateToText(binding.btnBsfiltercracksEnddate.text.toString())
                    viewModel.setTermCheckedRadioId(null)
                }
            }
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
                        viewModel.setDateFromText(startDateText)

                        if (!dateFrom(startDateText).before(dateFrom(binding.btnBsfiltercracksEnddate.text.toString()))) {
                            binding.btnBsfiltercracksEnddate.text = startDateText
                        }
                    }
                    R.id.btn_bsfiltercracks_enddate -> {

                        val endDateText = "$year-${
                            month.plus(1).toZeroZeroFormat()
                        }-${dayOfMonth.toZeroZeroFormat()}"

                        view.text = endDateText
                        viewModel.setDateToText(endDateText)

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