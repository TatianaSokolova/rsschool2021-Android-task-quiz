package com.rsschool.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.rsschool.quiz.databinding.FragmentQuestion2Binding
import com.rsschool.quiz.databinding.FragmentQuestion3Binding

class Question2Fragment : Fragment() {
   // val args: Question2FragmentArgs by navArgs()

    private var _binding: FragmentQuestion2Binding? = null
    private val binding get() = _binding!!

    private var mCurrentPosition: Int = 2
    private var mQuestionsList: ArrayList<Question>? = null
    private var mAnswers: HashMap<Int, String>? = null
    private var mPositions: HashMap<Int, Int>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context?.theme?.applyStyle(R.style.Theme_Quiz_Second, true)
        activity?.window?.statusBarColor = resources.getColor(R.color.yellow_100_dark)
        _binding = FragmentQuestion2Binding.inflate(inflater, container, false)

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

        //val prevId = args.previousChoice

        if (!mPositions!!.containsKey(2)) {
            binding.radioGroup.clearCheck()
        } else {
            when (mPositions?.getValue(2)) {
                binding.optionOne.id -> binding.optionOne.isChecked = true
                binding.optionTwo.id -> binding.optionTwo.isChecked = true
                binding.optionThree.id -> binding.optionThree.isChecked = true
                binding.optionFour.id -> binding.optionFour.isChecked = true
                binding.optionFive.id -> binding.optionFive.isChecked = true
            }
            binding.nextButton.isEnabled = true
            binding.nextButton.setOnClickListener {
                Navigation.findNavController(view).navigate(Question2FragmentDirections.actionQuestion2FragmentToQuestionFragment3())
            }
        }

        binding.optionOne.text = question.item1
        binding.optionTwo.text = question.item2
        binding.optionThree.text = question.item3
        binding.optionFour.text = question.item4
        binding.optionFive.text = question.item5



        if (binding.radioGroup.checkedRadioButtonId == -1) {
            binding.nextButton.isEnabled = false
        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            binding.nextButton.isEnabled = true

            val actionForward = Question2FragmentDirections.actionQuestion2FragmentToQuestionFragment3()
            binding.nextButton.setOnClickListener {
                when (checkedId) {
                    R.id.option_one -> mAnswers?.put(2, question.item1)
                    R.id.option_two -> mAnswers?.put(2, question.item2)
                    R.id.option_three -> mAnswers?.put(2, question.item3)
                    R.id.option_four -> mAnswers?.put(2, question.item4)
                    R.id.option_five -> mAnswers?.put(2, question.item5)
                }
                mPositions?.put(2, checkedId)
                Navigation.findNavController(view).navigate(actionForward)
            }
        }


        binding.previousButton.setOnClickListener {
            val actionBackward = Question2FragmentDirections.actionQuestion2FragmentToQuestionFragment()
            Navigation.findNavController(view).navigate(actionBackward)
        }

        binding.toolbar.setNavigationOnClickListener {
            val actionBackward = Question2FragmentDirections.actionQuestion2FragmentToQuestionFragment()
            Navigation.findNavController(view).navigate(actionBackward)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}