package com.example.swipequiz

data class Quiz (val question: String, val answer: Boolean) {
    companion object {
        val QUESTIONS = arrayListOf(
            "1 +1 = 18",
            "moeder begint met m",
            "ik ben 20",
            "nederland is een super groot land"
        )
        val ANSWERS = arrayListOf(
            false,
            true,
            true,
            false
        )
    }
}