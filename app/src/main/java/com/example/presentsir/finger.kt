package com.example.presentsir

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.hardware.biometrics.BiometricPrompt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.util.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executor

class Finger : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finger)
        val registerText: Button = findViewById(R.id.register)
        registerText.setOnClickListener {
            val intent2 = Intent(this, Register::class.java)
            startActivity(intent2)
        }
        lateinit var executor:Executor
        lateinit  var biometricPrompt: androidx.biometric.BiometricPrompt
        lateinit var promptInfo:androidx.biometric.BiometricPrompt.PromptInfo
        val selectDate = findViewById<TextView>(R.id.date)
        val tv_Auth:TextView=findViewById(R.id.tvAuth)
        executor=ContextCompat.getMainExecutor(this)
        biometricPrompt=androidx.biometric.BiometricPrompt(this@Finger,object:androidx.biometric.BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                //error code
                tv_Auth.text="error:$errString"

            }

            override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                tv_Auth.text="Succeded"
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                tv_Auth.setText("Auntication Failed")
            }
        })
        promptInfo=androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("login using finger print/ face")
            .setNegativeButtonText("Cancel")
            .build()
        selectDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val calenPop =DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener{ view, Year, monthofyear, dayOfMonth ->
                    val months = monthofyear + 1
                    Toast.makeText(this,"$dayOfMonth-$months-$year",Toast.LENGTH_SHORT).show()
                },
                year,
                month,
                day
            )
         calenPop.datePicker.maxDate=c.timeInMillis
         calenPop.datePicker.minDate=c.timeInMillis

         calenPop.show()


            }
        val mark:Button=findViewById(R.id.attendence)
        mark.setOnClickListener{
            biometricPrompt.authenticate(promptInfo)

        }
        }
    }



