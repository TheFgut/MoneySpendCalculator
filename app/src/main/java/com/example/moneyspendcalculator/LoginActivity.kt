package com.example.moneyspendcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.moneyspendcalculator.autintification.Authentification
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {




    var mAuth:FirebaseAuth = FirebaseAuth.getInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var userNameField: EditText = findViewById<EditText>(R.id.usernameTextLogin)
        var passwordField: EditText = findViewById<EditText>(R.id.passwordTextLogin)

        var loginBut: Button = findViewById<Button>(R.id.loginBut)

        userNameField.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val updatedText = s.toString()
                login = updatedText
                loginCorrect = Authentification.emailCheck(updatedText)

            }
        })

        passwordField.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                val updatedText = s.toString()
                password = updatedText
                passwordCorrect = Authentification.passwordCheck(updatedText)
            }
        })
        loginBut.setOnClickListener{
            tryToLogin()
        }
        var toRegisterText: TextView = findViewById<TextView>(R.id.toRegisterTextBut)
        toRegisterText.setOnClickListener{
            finish()
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
    private var login:String  = ""
    private var loginCorrect: Boolean = false
    private var password:String  = ""
    private var passwordCorrect: Boolean = false
    fun tryToLogin() {
        if(!loginCorrect){
            Toast.makeText(
                applicationContext,
                "login structure is incorrect. Please write an Email",
                Toast.LENGTH_SHORT).show()
            return
        }
        if(!passwordCorrect){
            Toast.makeText(
                applicationContext,
                "Password is too short",
                Toast.LENGTH_SHORT).show()
            return
        }

        mAuth.signInWithEmailAndPassword(login, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val user = FirebaseAuth.getInstance().currentUser

                    finish()
                } else {
                    val exception = task.exception
                    Toast.makeText(
                        applicationContext,
                        "Something went wrong - " + exception,
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }

    }

}