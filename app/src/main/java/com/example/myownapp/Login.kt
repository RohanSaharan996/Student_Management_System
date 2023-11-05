package com.example.mydiary

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myownapp.MainActivity
import com.example.myownapp.R
import com.example.myownapp.register

class login : AppCompatActivity() {
    lateinit var uname :TextView
    lateinit var pwd : TextView
    lateinit var ln : Button
    lateinit var create : Button
    lateinit var sharedPreferences : SharedPreferences
    val name = "namekey"
    val passw = "passkey"
    val myfile = "myprefile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        uname = findViewById(R.id.usern)
        pwd = findViewById(R.id.pwd)
        ln = findViewById(R.id.login)
        create = findViewById(R.id.create)
        sharedPreferences = getSharedPreferences(myfile, MODE_PRIVATE)
        uname.text=sharedPreferences.getString(name,"")
        pwd.text = sharedPreferences.getString(passw,"")


        ln.setOnClickListener(){


          if((uname.text.toString().isEmpty()) && (pwd.text.toString().isEmpty())){
                Toast.makeText(this,"Create Account First",Toast.LENGTH_SHORT).show()

            }
            else {

              val n = uname.text.toString()
              val i = Intent(this, MainActivity::class.java)
              startActivity(i)
              Toast.makeText(this, "Welcome ${n}", Toast.LENGTH_LONG).show()

          }

        }
        create.setOnClickListener(){
            val ireg = Intent(this, register::class.java)
            startActivity(ireg)
            Toast.makeText(this,"Register your detail",Toast.LENGTH_SHORT).show()
        }


    }
}