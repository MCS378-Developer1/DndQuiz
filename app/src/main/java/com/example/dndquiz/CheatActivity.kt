package com.example.dndquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.dndquiz.databinding.ActivityCheatBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
// have a constant
const val EXTRA_ANSWER_SHOWN = "com.example.dndquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.example.dndquiz.answer_is_true"
class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding
    private var answerIsTrue = false;

    private val cheatViewModel: CheatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // activate the binding
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe the LiveData to update UI based on the state
        cheatViewModel.isAnswerShown.observe(this, Observer { isAnswerShown ->
            // Update UI based on the isAnswerShown value
            // For example, change the state of the cheat button
        })

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        binding.showAnswerButton.setOnClickListener {

            val answerText=when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(answerText)
            setAnswerShownResult(true)

            cheatViewModel.setAnswerShown(true)
        }
    }

    private fun setAnswerShownResult (isAnswerShown:Boolean){
        val data = Intent().apply{
            putExtra (EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK,data)
    }
    // set a cheating button listener

    companion object{
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent{
            return Intent (packageContext, CheatActivity::class.java).apply{
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}