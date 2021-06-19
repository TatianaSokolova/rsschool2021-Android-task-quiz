package com.rsschool.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.rsschool.quiz.databinding.FragmentQuestion5Binding
import com.rsschool.quiz.databinding.FragmentQuizBinding


class Question5Fragment : Fragment() {


    private var _binding: FragmentQuestion5Binding? = null
    private val binding get() = _binding!!

    private var mCurrentPosition: Int = 5
    private var mQuestionsList: ArrayList<Question>? = null
    private var mAnswers: HashMap<Int, String>? = null
    private var mPositions: HashMap<Int, Int>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        context?.theme?.applyStyle(R.style.Theme_Quiz_Fifth, true)
        activity?.window?.statusBarColor = resources.getColor(R.color.green_status_bar)
        _binding = FragmentQuestion5Binding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mQuestionsList = Constants.getQuestions()
        mAnswers = Constants.answers
        mPositions = Constants.checkedPositions

        val question = mQuestionsList!![mCurrentPosition - 1]

        binding.toolbar.title = question.title
        binding.question.text = question.question

        if (!mPositions!!.containsKey(5)) {
            binding.radioGroup.clearCheck()
        } else {
            when (mPositions?.getValue(5)) {
                binding.optionOne.id -> binding.optionOne.isChecked = true
                binding.optionTwo.id -> binding.optionTwo.isChecked = true
                binding.optionThree.id -> binding.optionThree.isChecked = true
                binding.optionFour.id -> binding.optionFour.isChecked = true
                binding.optionFive.id -> binding.optionFive.isChecked = true
            }
            binding.nextButton.isEnabled = true
            binding.nextButton.setOnClickListener {
                Navigation.findNavController(view).navigate(Question5FragmentDirections.actionQuestion5FragmentToResultFragment())
            }
        }

        binding.optionOne.text = question.item1
        binding.optionTwo.text = question.item2
        binding.optionThree.text = question.item3
        binding.optionFour.text = question.item4
        binding.optionFive.text = question.item5

        binding.nextButton.text = "submit"

        if (binding.radioGroup.checkedRadioButtonId == -1) {
            binding.nextButton.isEnabled = false
        }

        val actionForward = Question5FragmentDirections.actionQuestion5FragmentToResultFragment()

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            binding.nextButton.isEnabled = true
            when (checkedId) {
                R.id.option_one -> mAnswers?.put(5, question.item1)
                R.id.option_two -> mAnswers?.put(5, question.item2)
                R.id.option_three -> mAnswers?.put(5, question.item3)
                R.id.option_four -> mAnswers?.put(5, question.item4)
                R.id.option_five -> mAnswers?.put(5, question.item5)
            }
            binding.nextButton.setOnClickListener {
                Navigation.findNavController(view).navigate(actionForward)
            }
        }

        binding.previousButton.setOnClickListener {
            val selectedRadioButtonId = binding.radioGroup.checkedRadioButtonId
            mPositions?.put(5, selectedRadioButtonId)
            when (mPositions!!.getValue(5)) {
                R.id.option_one -> mAnswers?.put(5, question.item1)
                R.id.option_two -> mAnswers?.put(5, question.item2)
                R.id.option_three -> mAnswers?.put(5, question.item3)
                R.id.option_four -> mAnswers?.put(5, question.item4)
                R.id.option_five -> mAnswers?.put(5, question.item5)
            }
            Navigation.findNavController(view).navigate(R.id.action_question5Fragment_to_question4Fragment)
        }

        binding.toolbar.setNavigationOnClickListener {
            val selectedRadioButtonId = binding.radioGroup.checkedRadioButtonId
            mPositions?.put(5, selectedRadioButtonId)
            when (mPositions!!.getValue(5)) {
                R.id.option_one -> mAnswers?.put(5, question.item1)
                R.id.option_two -> mAnswers?.put(5, question.item2)
                R.id.option_three -> mAnswers?.put(5, question.item3)
                R.id.option_four -> mAnswers?.put(5, question.item4)
                R.id.option_five -> mAnswers?.put(5, question.item5)
            }
            Navigation.findNavController(view).navigate(R.id.action_question5Fragment_to_question4Fragment)
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}