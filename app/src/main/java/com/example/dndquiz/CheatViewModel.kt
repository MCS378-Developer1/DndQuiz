package com.example.dndquiz

import Question
import android.util.Log
//import quizViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CheatViewModel : ViewModel() {
    private val _isAnswerShown = MutableLiveData<Boolean>()
    val isAnswerShown: LiveData<Boolean>
        get() = _isAnswerShown

    init {
        // Initialize the LiveData with default value
        _isAnswerShown.value = false
    }

    fun setAnswerShown(isAnswerShown: Boolean) {
        _isAnswerShown.value = isAnswerShown
    }
}
