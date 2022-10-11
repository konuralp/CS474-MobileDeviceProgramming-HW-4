package com.example.homework4

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val users: ArrayList<User> = arrayListOf(
        User("John", "Doe", "joe", "123456"),
        User("Jane", "Doe", "jane", "password"),
        User("John", "Smith", "john", "qwerty"),
        User("Jane", "Smith", "jane", "000000"),
        User("Murat Konuralp", "Senoglu", "murat.senoglu@miu.edu", "111111")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        signInButton.setOnClickListener {
            val username = editTextEmail.text.toString()
            if(username.isEmpty()){
                editTextEmail.error = "Email is Required"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }
            val password = editTextPassword.text.toString()
            if(password.isEmpty()){
                editTextPassword.error = "Password is Required"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }
            val user = users.find { it.username == username && it.password == password }
            if (user != null) {
                Toast.makeText(this, "Welcome ${user.firstName} ${user.lastName}", Toast.LENGTH_LONG).show()
                val shoppingIntent = Intent(this, ShoppingActivity::class.java)
                shoppingIntent.putExtra("user", user)
                startActivity(shoppingIntent)
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

        val resultContracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val user = data?.getSerializableExtra("createdUser") as User?
                if (user != null) {
                    users.add(user)
                    Toast.makeText(this, "User ${user.firstName} ${user.lastName} has been registered", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Registration cancelled", Toast.LENGTH_SHORT).show()
            }
        }

        registerBtn.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            resultContracts.launch(registerIntent)
        }

        textView5.setOnClickListener{
            val username = editTextEmail.text.toString()
            val user = users.find { it.username == username }
            if (user != null) {
                val mailIntent = Intent(Intent.ACTION_SENDTO)
                mailIntent.data = Uri.parse("mailto:")
                mailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(user.username))
                mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Forgot Password")
                mailIntent.putExtra(Intent.EXTRA_TEXT, "Your password is ${user.password}")
                startActivity(mailIntent)
            }else{
                Toast.makeText(this, "No such user with email found", Toast.LENGTH_SHORT).show()
            }
        }
    }



}