package com.rsschool.quiz

object Constants {

    fun getQuestions(): ArrayList<Question> {
        val questionsList = arrayListOf<Question>()

        val question1 = Question (1, "Question 1", "Столица Великобритании?", "Лондон", "Париж",
            "Копенгаген", "Мадрид", "Стокгольм", "Лондон")
        val question2 = Question (2, "Question 2", "Столица Франции?", "Лондон", "Париж",
            "Копенгаген", "Мадрид", "Стокгольм", "Париж")
        val question3 = Question (3, "Question 3", "Столица Испании?", "Лондон", "Париж",
            "Копенгаген", "Мадрид", "Стокгольм", "Мадрид")
        val question4 = Question (4, "Question 4", "Столица Швеции?", "Лондон", "Париж",
            "Копенгаген", "Мадрид", "Стокгольм", "Стокгольм")
        val question5 = Question (5, "Question 5", "Столица Дании?", "Лондон", "Париж",
            "Копенгаген", "Мадрид", "Стокгольм", "Копенгаген")

        questionsList.add(question1)
        questionsList.add(question2)
        questionsList.add(question3)
        questionsList.add(question4)
        questionsList.add(question5)

        return questionsList

    }

    val answers = hashMapOf<Int, String>()
    val answersList = arrayListOf<String>()

    fun getResult(answers: HashMap<Int, String>): Int {
        val questions = getQuestions()
        var result = 0


        for ((key, value ) in answers) {
            if (key == 1) {
                if (value == questions[0].correctAnswer) {
                    result++
                    answersList.add(value)
                } else {
                    answersList.add(value)
                }
            }
            if (key == 2) {
                if (value == questions[1].correctAnswer) {
                    result++
                    answersList.add(value)
                } else {
                    answersList.add(value)
                }
            }
            if (key == 3) {
                if (value == questions[2].correctAnswer) {
                    result++
                    answersList.add(value)
                } else {
                    answersList.add(value)
                }
            }
            if (key == 4) {
                if (value == questions[3].correctAnswer) {
                    result++
                    answersList.add(value)
                } else {
                    answersList.add(value)
                }
            }
            if (key == 5) {
                if (value == questions[4].correctAnswer) {
                    result++
                    answersList.add(value)
                } else {
                    answersList.add(value)
                }
            }
        }
        return result

    }

    val checkedPositions = hashMapOf<Int, Int>()

}

