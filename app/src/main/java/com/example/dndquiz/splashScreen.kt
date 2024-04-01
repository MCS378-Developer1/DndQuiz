package com.example.dndquiz

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.ViewPropertyAnimator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
class splashScreen : AppCompatActivity() {

    private lateinit var  lottie:LottieAnimationView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        lottie=findViewById(R.id.lottie)
        animateLottie()
    }

    private fun animateLottie() {
        // Get the ViewPropertyAnimator for the LottieAnimationView
        val animator: ViewPropertyAnimator = lottie.animate()

        // Set the translationX to move the LottieAnimationView horizontally
        animator.translationX(2000f)

        // Set duration in milliseconds
        animator.setDuration(3000)

        // Set start delay in milliseconds
        animator.setStartDelay(2900)

        // Start the animation
        animator.start()
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Finish the SplashScreen activity to prevent it from being shown again when back button is pressed
        }, 4000) // Delay duration equals to animation duration plus start delay
    }
    }


