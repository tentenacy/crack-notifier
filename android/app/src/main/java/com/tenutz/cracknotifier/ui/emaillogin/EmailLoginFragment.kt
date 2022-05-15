package com.tenutz.cracknotifier.ui.emaillogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tenutz.cracknotifier.data.api.dto.user.LoginRequest
import com.tenutz.cracknotifier.databinding.FragmentEmailLoginBinding
import com.tenutz.cracknotifier.util.mainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailLoginFragment: Fragment() {

    private var _binding: FragmentEmailLoginBinding? = null
    val binding: FragmentEmailLoginBinding get() = _binding!!

    val viewModel: EmailLoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEmailLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.imageEmailloginBack.setOnClickListener {
            mainActivity().onBackPressed()
        }
        binding.btnEmaillogin.setOnClickListener {
            if(binding.editEmailloginEmail.text.toString().trim().isBlank()) {
                Toast.makeText(mainActivity(), "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(!"""[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]""".toRegex()
                    .matches(binding.editEmailloginEmail.text.toString().trim())) {
                Toast.makeText(mainActivity(), "잘못된 형식의 이메일입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(binding.editEmailloginPwd.text.toString().trim().isBlank()) {
                Toast.makeText(mainActivity(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.login(LoginRequest(
                binding.editEmailloginEmail.text.toString(),
                binding.editEmailloginPwd.text.toString(),
            ))
        }
        binding.textEmailloginToSignup.setOnClickListener {
            findNavController().navigate(EmailLoginFragmentDirections.actionEmailLoginFragmentToContainerSignupFragment())
        }
        viewModel.viewEvent.observe(viewLifecycleOwner) {
            it?.getContentIfNotHandled()?.let {
                when(it.first) {
                    EmailLoginViewModel.EVENT_NAVIGATE_TO_ROOT -> {
                        findNavController().navigate(EmailLoginFragmentDirections.actionEmailLoginFragmentToRootFragment())
                    }
                    EmailLoginViewModel.EVENT_TOAST_LOGIN_FAILED -> {
                        Toast.makeText(mainActivity(), "존재하지 않는 계정이거나, 잘못된 비밀번호입니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}