package com.example.homework4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val rintent = intent
        val user = rintent.getSerializableExtra("user") as User?
        if(user != null) {
            welcomeText.text = "Welcome ${user.firstName} ${user.lastName}"
        }else{
            Toast.makeText(this,"Invalid user", Toast.LENGTH_SHORT).show()
        }

        cardElectronics.setOnClickListener {
            Toast.makeText(this,"You have chosen the Electronics category of shopping!", Toast.LENGTH_SHORT).show()
        }
        cardBeauty.setOnClickListener {
            Toast.makeText(this,"You have chosen the Beauty category of shopping!", Toast.LENGTH_SHORT).show()
        }
        cardClothings.setOnClickListener {
            Toast.makeText(this,"You have chosen the Clothings category of shopping!", Toast.LENGTH_SHORT).show()
        }
        cardFood.setOnClickListener {
            Toast.makeText(this,"You have chosen the Food category of shopping!", Toast.LENGTH_SHORT).show()
        }
    }
}