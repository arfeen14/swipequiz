package com.example.swipequiz

data class Questions(
    var question: String,

    var answer: Boolean
) {
    companion object {

        val ALL_QUESTIONS = arrayOf(
            "2 plus 2 is 4",
            "first letter of kid is K",
            "2 plus 2 is 5",
            "first letter of me is T"
        )


        val FALSE_TRUE = arrayOf(
            true,
            true,
            false,
            false
        )


    }
}

