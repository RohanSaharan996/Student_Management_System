package com.example.myownapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class register : AppCompatActivity() {
    lateinit var sname :TextView
    lateinit var mail :TextView
    lateinit var pno :TextView
    lateinit var dob :TextView
    lateinit var pswd :TextView
    lateinit var reg :Button
    lateinit var l :Button
    lateinit var sharedPreferences: SharedPreferences
    val name = "namekey"
    val passw = "passkey"
    val myfile = "myprefile"



    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)
        sname = findViewById(R.id.sname)
        pswd = findViewById(R.id.pswd)
        reg = findViewById(R.id.reg)
        dob = findViewById(R.id.dob)
        sharedPreferences = getSharedPreferences(myfile, MODE_PRIVATE)

        reg.setOnClickListener(){
            val n = sname.text.toString()
            val p = pswd.text.toString()
            val editor = sharedPreferences.edit()
            editor.putString(name,n)
            editor.putString(passw,p)
            editor.apply()

            val ireg = Intent(this,login::class.java)
            startActivity(ireg)
        }

        dob.setOnClickListener(){
                val c = Calendar.getInstance()
            var mYear = c[Calendar.YEAR]
            var mMonth = c[Calendar.MONTH]
            var mYDay = c[Calendar.DAY_OF_MONTH]
            val datePickerDialog = DatePickerDialog(
                    this ,{ view,year, monthOfYear,dayOfMonth
                        ->
                        dob.setText(
                            if(monthOfYear < 9){
                                dayOfMonth.toString() + " _0" + (monthOfYear+1 ) + "_" + year}

                            else{
                                dayOfMonth.toString() + " _" + (monthOfYear+1 ) + "_" + year}
                        )
                    },
                    mYear,mMonth,mYDay
                )
                datePickerDialog.show()
            }
        }


    }
