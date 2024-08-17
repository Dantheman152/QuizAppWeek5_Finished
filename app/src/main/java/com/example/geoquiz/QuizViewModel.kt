package com.example.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"
const val IS_A_CHEATER = "IS_A_CHEATER"
const val QUESTION = "QUESTION"
const val QUESTIONLIST = "QUESTIONLIST"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private var questionBank = mutableListOf (
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
        Question(R.string.question_patagonia, false),
        Question(R.string.question_annapurna, false),
        Question(R.string.question_everest, false),
        Question(R.string.question_belize, false),
        Question(R.string.question_venezuela, false),
        Question(R.string.question_portMoresby, true),
        Question(R.string.question_kiritimati, true),
        Question(R.string.question_moldova, true)
    )
        get() = savedStateHandle.get(QUESTIONLIST) ?: field
        set(value) = savedStateHandle.set(QUESTIONLIST, value)

    private var currentIndex
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    var currentQuestionCheated: Boolean
        get() = savedStateHandle.get(IS_A_CHEATER) ?: questionBank[currentIndex].cheated
        set(value) = savedStateHandle.set(IS_A_CHEATER, value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        if (currentIndex == 0) {
            currentIndex += (questionBank.size - 1)
        } else {
            currentIndex = (currentIndex - 1) % questionBank.size
        }
    }

    fun setCheatToTrue() {
        questionBank[currentIndex].cheated = true
        currentQuestionCheated = true
        questionBank = questionBank
    }
}