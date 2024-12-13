package com.example.secryptchat

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable fullscreen mode
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

        setContentView(R.layout.activity_login)

        // Initialize gesture detector
        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                if (e1 != null && e2 != null && e2.x - e1.x > 100 && Math.abs(velocityX) > 100) {
                    navigateBackToHome()
                    return true
                }
                return false
            }
        })

        // Bind views
        val usernameOrEmailField = findViewById<EditText>(R.id.usernameOrEmailField)
        val passwordField = findViewById<EditText>(R.id.passwordField)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val signupRedirectButton = findViewById<Button>(R.id.signupRedirectButton) // Sign Up Button

        // Handle login button click
        loginButton.setOnClickListener {
            val usernameOrEmail = usernameOrEmailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (usernameOrEmail.isEmpty() || password.isEmpty()) {
                showToast("All fields are required")
            } else {
                showToast("Login successful")
                // TODO: Authenticate user with backend
            }
        }

        // Handle Sign Up button click
        signupRedirectButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    // Handle touch events
    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    private fun navigateBackToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
