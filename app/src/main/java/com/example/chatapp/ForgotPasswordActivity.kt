package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ForgotPasswordActivity : AppCompatActivity() {

    lateinit var viewMode: ChatViewMode
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password)

        val username : EditText = findViewById(R.id.ResetUsername)
        val newpassword : EditText =findViewById(R.id.ResetPassword)
        val resToLogin : Button = findViewById(R.id.ForgotToLoginButton)
        resToLogin.setOnClickListener{
            val intent : Intent = Intent(this@ForgotPasswordActivity,MainActivity::class.java)
            startActivity(intent)
        }

        viewMode= ViewModelProvider(this).get(ChatViewMode::class.java)
        val resetpassword : Button = findViewById(R.id.resetPassButton)
        resetpassword.setOnClickListener{
            if(username.text.isNotEmpty()){
                if (newpassword.text.isNotEmpty()){

                    MainScope().launch{

                        if (viewMode.isUserPresent(username.text.toString())){
                            viewMode.resetPass(username.text.toString(),newpassword.text.toString())
                            Toast.makeText(this@ForgotPasswordActivity,"Password reset successfull. You can Login now", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(this@ForgotPasswordActivity,"User not Found", Toast.LENGTH_SHORT).show()
                        }
                    }


                }
                else{

                    Toast.makeText(this@ForgotPasswordActivity,"Please Enter a valid Password", Toast.LENGTH_SHORT).show()

                }

            }
            else{
                Toast.makeText(this@ForgotPasswordActivity,"Please Enter a valid Username", Toast.LENGTH_SHORT).show()
            }
        }
    }
}