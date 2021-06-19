package com.rsschool.quiz

data class Question (
    val id: Int,
    val title: String,
    val question: String,
    val item1: String,
    val item2: String,
    val item3: String,
    val item4: String,
    val item5: String,
    val correctAnswer: String
        )