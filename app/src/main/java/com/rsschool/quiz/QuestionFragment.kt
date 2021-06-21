package com.rsschool.quiz

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.rsschool.quiz.databinding.FragmentQuizBinding


class QuestionFragment : Fragment() {

    private var TAG = "my logs"

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mAnswers: HashMap<Int, String>? = null
    private var mPositions: HashMap<Int, Int>? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context?.theme?.applyStyle(R.style.Theme_Quiz_First, true)
        activity?.window?.statusBarColor = resources.getColor(R.color.deep_orange_100_dark)
        _binding = FragmentQuizBinding.inflate(inflater, container, false)

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

        if (!mPositions!!.containsKey(1)) {
            binding.radioGroup.clearCheck()
        } else {
            when (mPositions?.getValue(1)) {
                binding.optionOne.id -> binding.optionOne.isChecked = true
                binding.optionTwo.id -> binding.optionTwo.isChecked = true
                binding.optionThree.id -> binding.optionThree.isChecked = true
                binding.optionFour.id -> binding.optionFour.isChecked = true
                binding.optionFive.id -> binding.optionFive.isChecked = true
            }
            binding.nextButton.isEnabled = true
            binding.nextButton.setOnClickListener {
                Navigation.findNavController(view).navigate(QuestionFragmentDirections.actionQuestionFragmentToQuestion2Fragment())
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
            val actionForward = QuestionFragmentDirections.actionQuestionFragmentToQuestion2Fragment()
                binding.nextButton.isEnabled = true
                binding.nextButton.setOnClickListener {
                    when (checkedId) {
                        R.id.option_one -> mAnswers?.put(1, question.item1)
                        R.id.option_two -> mAnswers?.put(1, question.item2)
                        R.id.option_three -> mAnswers?.put(1, question.item3)
                        R.id.option_four -> mAnswers?.put(1, question.item4)
                        R.id.option_five -> mAnswers?.put(1, question.item5)
                    }
                    mPositions?.put(1, checkedId)
                    Navigation.findNavController(view).navigate(actionForward)
                }
        }

        binding.previousButton.isEnabled = false

        requireActivity().onBackPressedDispatcher.addCallback(object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val myDialog = ExitDialogFragment()
                myDialog.show(childFragmentManager, "missiles")
            }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}