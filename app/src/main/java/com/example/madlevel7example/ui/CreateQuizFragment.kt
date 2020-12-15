package com.example.madlevel7example.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.madlevel7example.R
import com.example.madlevel7example.databinding.FragmentCreateQuizBinding
import com.example.madlevel7example.vm.QuizViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CreateQuizFragment : Fragment() {
    private lateinit var binding: FragmentCreateQuizBinding
    private val viewModel: QuizViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btCreateQuizConfirmAnswer.setOnClickListener {
            viewModel.createQuiz(binding.etCreateQuizQuestion.text.toString(), binding.etCreateQuizAnswer.text.toString())
        }

        observeQuizCreation()
    }

    private fun observeQuizCreation() {
        viewModel.createSuccess.observe(viewLifecycleOwner, {
            Toast.makeText(activity, R.string.successfully_created_quiz, Toast.LENGTH_LONG).show()
            findNavController().popBackStack()
        })

        viewModel.errorText.observe(viewLifecycleOwner,  {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }
}