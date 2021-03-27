package com.qersh.swimmig_schole_api

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.qersh.swimmig_schole_api.fragments.fragment_profile
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        readinfo()


        new_user_but.setOnClickListener {

            saveinfo()

        }
    }
    //***********************************************
    private fun saveinfo() {
        if (username_edt.text.isEmpty()) {
            username_edt.error = "ادخل اسم المتسخدم"
        }
        if (passwrd_edt.text.isEmpty()) {
            passwrd_edt.error = "ادخل الباسورد"
        } else{


            val sp = getSharedPreferences("sp", Context.MODE_PRIVATE)
            val editSP = sp.edit()
            editSP.putString("username", username_edt.text.toString())
            editSP.putString("password", passwrd_edt.text.toString())
            editSP.apply()
            Toast.makeText(this, "تم تسجيل البيانات", Toast.LENGTH_LONG).show()

            val infodata_shift: Intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(infodata_shift)
            finish()
        }
    }

    //*************************************************
    private fun readinfo() {
        val sp = getSharedPreferences("sp", Context.MODE_PRIVATE)
        val username_log = sp.getString("username", "")
        val password_log = sp.getString("password", "")

        username_edt.setText(username_log)
        passwrd_edt.setText(password_log)
        if (username_edt.text.isNotEmpty()) {
            Toast.makeText(this, "أهلا  " + username_edt.text, Toast.LENGTH_LONG).show()

            val intent: Intent = Intent(applicationContext, MainActivity::class.java)
            val username_pass = username_edt.text.toString()
            intent.putExtra("username", username_pass)
            startActivity(intent)
            finish()
        }


        // ***********************************************

    }
}