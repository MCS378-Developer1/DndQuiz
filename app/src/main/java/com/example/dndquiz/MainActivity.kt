package com.example.dndquiz

import Question
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dndquiz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

// add a log variable for debugging
private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {
    // create two button objects ready to go
    private lateinit var binding: ActivityMainBinding

    //private lateinit var trueButton: Button
    //private lateinit var falseButton: Button
    // question bank
    private val quizViewModel: QuizViewModel by viewModels()


    // create cheatLauncher
    //random comment

    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // do whatever it takes to handle the result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // before a process is created
        // put the tag in your log
        Log.d(TAG, "onCreate(Bundle?) is called, a process is created")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // once your app is launched, you need to
        // you need to wire those two buttons
        // trueButton = findViewById(R.id.true_button)
        // falseButton = findViewById(R.id.false_button)

        // log the process
        Log.d(TAG, "We got a QuizViewModel: QuizViewModel")
        binding.trueButton.setOnClickListener { view: View ->
            // do something when you click the true button
            // popping out a text that says something
            //Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG).show()
            checkAnswer(true)
            //disableAnswerButtons()
        }

        binding.falseButton.setOnClickListener { view: View ->
            // do something when you click on the false button
            //Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_LONG).show()
            checkAnswer(false)
            //disableAnswerButtons()
        }

        // once you click the next button, text is going to rotate
        binding.nextButton.setOnClickListener { view: View ->

            quizViewModel.moveToNext()
            //val questionTextResId = questionBank[currentIndex].textResId
            //binding.questionTextView.setText(questionTextResId)
            updateQuestion()
        }
        binding.prevButton.setOnClickListener { view: View ->

            quizViewModel.moveToPrev()
            //val questionTextResId = questionBank[currentIndex].textResId
            //binding.questionTextView.setText(questionTextResId)
            updateQuestion()
        }
        Log.d(TAG, "We got a CheatViewModel: CheatViewModel")
        binding.cheatButton.setOnClickListener {
            // if you click this, start cheatActivity
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            //startActivity(intent)
            cheatLauncher.launch(intent)
            quizViewModel.cheatedStatus[quizViewModel.currentIndex] = true

        }
        updateQuestion()
        // we are going to create the rotating text
        //val questionTextResId = questionBank[currentIndex].textResId
        //binding.questionTextView.setText(questionTextResId)

    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.questionBank[quizViewModel.currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)

        // Display true/false buttons based on whether the question has been answered
        val questionAnswered = quizViewModel.questionAnswered[quizViewModel.currentIndex]

        //if (questionAnswered) {
           // disableAnswerButtons()

        //} else {
         //   enableAnswerButtons()

    //}
    }

    // I need a function that checks my answer
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        quizViewModel.answeredQuestions++
        quizViewModel.questionAnswered[quizViewModel.currentIndex] = true
        // check if my answer is going to be the correct answer ot not
        if (quizViewModel.cheatedStatus[quizViewModel.currentIndex]) {
            Toast.makeText(this, R.string.cheater_toast, Toast.LENGTH_LONG).show()


        } else if (userAnswer == correctAnswer) {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_LONG).show()


        } else {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG).show()
        }

       // val snackView = findViewById<View>(android.R.id.content)
       // Snackbar.make(snackView, R.string.percentage_toast, Snackbar.LENGTH_LONG).show()

        if (quizViewModel.answeredQuestions == quizViewModel.questionBank.size) {
            displayPercentageToast()

        }



        // onstart in my log
        fun onStart() {
            super.onStart()
            Log.d(TAG, "OnStart() is called")
        }

        fun onResume() {
            super.onResume()
            Log.d(TAG, "OnResume() is called")
        }

        fun OnPause() {
            super.onPause()
            Log.d(TAG, "OnPause() is called ")
        }

        fun onStop() {
            super.onStop()
            Log.d(TAG, "OnStop() is called")
        }

        fun onDestroy() {
            super.onDestroy()
            Log.d(TAG, "OnDestroyed() is called")
        }
    }

    private fun displayPercentageToast() {
        val percentage =
            (quizViewModel.answeredCorrect.toDouble() / quizViewModel.answeredQuestions.toDouble()) * 100
        val percentageString = getString(R.string.percentage_toast, percentage)
        Toast.makeText(this, percentageString, Toast.LENGTH_SHORT).show()
    }
    private fun disableAnswerButtons() {
        binding.trueButton.isEnabled = false
        binding.falseButton.isEnabled = false
    }

    private fun enableAnswerButtons() {
        binding.trueButton.isEnabled = true
        binding.falseButton.isEnabled = true
    }

}