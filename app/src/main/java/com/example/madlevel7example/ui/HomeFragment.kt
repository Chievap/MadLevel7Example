package com.example.madlevel7example.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.madlevel7example.R
import com.example.madlevel7example.databinding.FragmentHomeBinding
import com.example.madlevel7example.vm.QuizViewModel
import java.util.Observer

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getQuiz()

        binding.btCreateQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createQuizFragment)
        }

        viewModel.quiz.observe(viewLifecycleOwner, {
            //make button visible and clickable
            if (!it.answer.isBlank() || !it.answer.isBlank()) {
                binding.btStartQuiz.alpha = 1.0f
                binding.btStartQuiz.isClickable = true

                binding.btStartQuiz.setOnClickListener {
                    findNavController().navigate(R.id.action_homeFragment_to_quizFragment)
                }
            }
        })

    }
}