package com.rsschool.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.rsschool.quiz.databinding.FragmentQuizBinding
import com.rsschool.quiz.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    private var result: Int? = null
    private var mAnswers: HashMap<Int, String>? = null
    private var mPositions: HashMap<Int, Int>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context?.theme?.applyStyle(R.style.Theme_Quiz_First, true)
        activity?.window?.statusBarColor = resources.getColor(R.color.deep_orange_100_dark)
        _binding = FragmentResultBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAnswers = Constants.answers
        mPositions = Constants.checkedPositions

        result = Constants.getResult(mAnswers!!)

        binding.resultText.text = "Quiz is finished! Here is your result: $result out of 5"

        binding.anotherTryButton.setOnClickListener {
            mAnswers?.clear()
            mPositions?.clear()
            Constants.answersList.clear()
            Navigation.findNavController(view).navigate(R.id.action_resultFragment_to_questionFragment)
        }

        val textToShare = "Your result is $result out of 5 \n" +
                "Question 1: Столица Великобритании? \n Your answer: ${Constants.answersList[0]} \n" +
                "Question 2: Столица Франции? \n Your answer: ${Constants.answersList[1]} \n" +
                "Question 3: Столица Испании? \n Your answer: ${Constants.answersList[2]} \n" +
                "Question 4: Столица Швеции? \n Your answer: ${Constants.answersList[3]} \n" +
                "Question 5: Столица Дании? \n Your answer: ${Constants.answersList[4]}"

        binding.share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, textToShare)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        binding.exitButton.setOnClickListener {
            mAnswers?.clear()
            mPositions?.clear()
            Constants.answersList.clear()
            activity?.finish()
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}