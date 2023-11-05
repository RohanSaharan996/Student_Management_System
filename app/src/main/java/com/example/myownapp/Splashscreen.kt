package com.example.myownapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        //here we run handle (handler class) : its help to run our threads parllel in asynchronous process.
        Handler().postDelayed(Runnable(){

                                        run() {
                                            val ilogin = Intent(this,login::class.java)
                                            startActivity(ilogin)
                                        }
        },2000)


    }
}