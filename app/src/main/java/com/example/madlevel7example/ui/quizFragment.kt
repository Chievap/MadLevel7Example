package com.example.madlevel7example.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.madlevel7example.R
import com.example.madlevel7example.databinding.FragmentQuizBinding
import com.example.madlevel7example.vm.QuizViewModel
import java.util.Observer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding
    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeQuiz()
    }

    private fun observeQuiz() {
        viewModel.quiz.observe(viewLifecycleOwner, {
            val quiz = it
            binding.tvQuizQuestion.text = quiz.question

            binding.btConfirmAnswer.setOnClickListener {
                if (viewModel.isAnswerCorrect(binding.etQuizAnswer.text.toString())) {
                    Toast.makeText(context, "Your answer is correct!", Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(
                        context,
                        "Your answer is incorrect! The correct answer was: ${quiz.answer}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }
}