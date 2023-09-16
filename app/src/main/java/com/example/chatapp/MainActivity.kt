package com.example.chatapp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var viewMode: ChatViewMode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val username : EditText = findViewById(R.id.loginUsername)
        val password : EditText=findViewById(R.id.loginPassword)
        val logToReg : Button = findViewById(R.id.LoginToRegisterButton)
        logToReg.setOnClickListener{
            val intent : Intent = Intent(this@MainActivity,RegisterActivity::class.java)
            startActivity(intent)
        }

        viewMode= ViewModelProvider(this).get(ChatViewMode::class.java)

        val logTores : Button = findViewById(R.id.LoginToForgotButton)
        logTores.setOnClickListener{
            val intent1 : Intent = Intent(this@MainActivity,ForgotPasswordActivity::class.java)
            startActivity(intent1)
        }



        val register : Button = findViewById(R.id.loginButton)
        register.setOnClickListener{
            if(username.text.isNotEmpty()){
                if (password.text.isNotEmpty()){
                    MainScope().launch{
                        val result: User? = viewMode.logIn(username.text.toString(),password.text.toString())
                        if (result==null){
                            Toast.makeText(this@MainActivity,"Error in Signing in. Kindly check the credentials",Toast.LENGTH_SHORT).show()
                        }
                        else{
                            val intent : Intent=Intent(this@MainActivity,AllUsersActivity::class.java)
                            intent.putExtra("sender",result.username)
                            startActivity(intent)

                        }

                    }


                }
                else{

                    Toast.makeText(this@MainActivity,"Please Enter a valid Password",Toast.LENGTH_SHORT).show()

                }

            }
            else{
                Toast.makeText(this@MainActivity,"Please Enter a valid Username",Toast.LENGTH_SHORT).show()
            }


        }
    }





}