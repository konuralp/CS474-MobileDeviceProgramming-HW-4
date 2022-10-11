package com.example.homework4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        createAccountBtn.setOnClickListener {
            val firstName = editTextFirstName.text.toString()
            if(firstName.isEmpty()){
                editTextFirstName.error = "Firstname Required"
                editTextFirstName.requestFocus()
                return@setOnClickListener
            }
            val lastName = editTextLastName.text.toString()
            if(lastName.isEmpty()){
                editTextLastName.error = "Lastname Required"
                editTextLastName.requestFocus()
                return@setOnClickListener
            }
            val username = createEmailEditText.text.toString()
            if(username.isEmpty()){
                createEmailEditText.error = "Email Required"
                createEmailEditText.requestFocus()
                return@setOnClickListener
            }
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()){
                createEmailEditText.error = "Valid Email Required"
                createEmailEditText.requestFocus()
                return@setOnClickListener
            }
            val password = createPasswordEditText.text.toString()
            if(password.isEmpty()){
                createPasswordEditText.error = "Password Required"
                createPasswordEditText.requestFocus()
                return@setOnClickListener
            }
            val user = User(firstName, lastName, username, password)
            val rintent = intent
            rintent.putExtra("createdUser", user)
            setResult(RESULT_OK, rintent)
            finish()
        }

    }

    override fun onBackPressed() {
        setResult(RESULT_CANCELED)
        finish()
    }
}