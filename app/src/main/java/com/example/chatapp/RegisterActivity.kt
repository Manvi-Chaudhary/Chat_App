package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    lateinit var viewMode: ChatViewMode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_page)

        val username : EditText = findViewById(R.id.regUsername)
        val password : EditText =findViewById(R.id.regPassword)
        val email : EditText = findViewById(R.id.regEmail)
        val regToLog : Button = findViewById(R.id.RegToLoginButton)
        regToLog.setOnClickListener{
            val intent : Intent = Intent(this@RegisterActivity,MainActivity::class.java)
            startActivity(intent)
        }

        viewMode=ViewModelProvider(this).get(ChatViewMode::class.java)

        val register : Button = findViewById(R.id.regButton)
        register.setOnClickListener{
            if(username.text.isNotEmpty()){
                if (password.text.isNotEmpty()){
                    if(email.text.isNotEmpty()){

                        MainScope().launch {
                            val res : Boolean = viewMode.isUserPresent(username.text.toString())
                            Log.d("regusterAnother",res.toString())
                            if (res){
                                Toast.makeText(this@RegisterActivity,"Username already exists",Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Log.d("reguster",User(username=username.text.toString(),password=password.text.toString(), email = email.text.toString(), userId = 0).toString())
                                viewMode.register(User(username=username.text.toString(),password=password.text.toString(), email = email.text.toString(), userId = 0))
                                Toast.makeText(this@RegisterActivity,"Registered Successfully. You can Login now",Toast.LENGTH_SHORT).show()

                            }
                        }

                    }
                    else{

                        Toast.makeText(this,"Please Enter a valid Email",Toast.LENGTH_SHORT).show()

                    }

                }
                else{

                    Toast.makeText(this,"Please Enter a valid Password",Toast.LENGTH_SHORT).show()

                }

            }
            else{

                Toast.makeText(this,"Please Enter a valid Username", Toast.LENGTH_SHORT).show()

            }


        }


    }
}