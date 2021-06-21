package com.rsschool.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.rsschool.quiz.databinding.FragmentQuestion4Binding
import com.rsschool.quiz.databinding.FragmentQuestion5Binding

class Question4Fragment : Fragment() {

    private var _binding: FragmentQuestion4Binding? = null
    private val binding get() = _binding!!

    private var mCurrentPosition: Int = 4
    private var mQuestionsList: ArrayList<Question>? = null
    private var mAnswers: HashMap<Int, String>? = null
    private var mPositions: HashMap<Int, Int>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        context?.theme?.applyStyle(R.style.Theme_Quiz_Forth, true)
        activity?.window?.statusBarColor = resources.getColor(R.color.blue_status_bar)
        _binding = FragmentQuestion4Binding.inflate(inflater, container, false)

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

        if (!mPositions!!.containsKey(4)) {
            binding.radioGroup.clearCheck()
        } else {
            when (mPositions?.getValue(4)) {
                binding.optionOne.id -> binding.optionOne.isChecked = true
                binding.optionTwo.id -> binding.optionTwo.isChecked = true
                binding.optionThree.id -> binding.optionThree.isChecked = true
                binding.optionFour.id -> binding.optionFour.isChecked = true
                binding.optionFive.id -> binding.optionFive.isChecked = true
            }
            binding.nextButton.isEnabled = true
            binding.nextButton.setOnClickListener {
                Navigation.findNavController(view).navigate(Question4FragmentDirections.actionQuestion4FragmentToQuestion5Fragment())
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
            val actionForward = Question4FragmentDirections.actionQuestion4FragmentToQuestion5Fragment()
            binding.nextButton.isEnabled = true
            binding.nextButton.setOnClickListener {
                when (checkedId) {
                    R.id.option_one -> mAnswers?.put(4, question.item1)
                    R.id.option_two -> mAnswers?.put(4, question.item2)
                    R.id.option_three -> mAnswers?.put(4, question.item3)
                    R.id.option_four -> mAnswers?.put(4, question.item4)
                    R.id.option_five -> mAnswers?.put(4, question.item5)
                }
                mPositions?.put(4, checkedId)
                Navigation.findNavController(view).navigate(actionForward)
            }
        }

        binding.previousButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_question4Fragment_to_questionFragment3)
        }

        binding.toolbar.setNavigationOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_question4Fragment_to_questionFragment3)
        }

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