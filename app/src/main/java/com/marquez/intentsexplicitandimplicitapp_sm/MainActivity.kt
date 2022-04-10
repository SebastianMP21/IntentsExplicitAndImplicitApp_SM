package com.marquez.intentsexplicitandimplicitapp_sm

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*


const val PARAMETER_EXTRA_NAME2 = "nombre"
const val PARAMETER_EXTRA_GMAIL2 = "correo"
const val PARAMETER_EXTRA_OFFICE2 = "oficina"
const val PARAMETER_EXTRA_PHONE2 = "telefono"
const val ACTIVITY_A_REQUEST = 991
const val ACTIVITY_B_REQUEST = 992



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun SendExplicity(view: android.view.View) {
        val nombre = tvNameInput.text.toString()
        val correo = tvEmailInput.text.toString()
        val oficina = tvOfficeInput.text.toString()
        val telefono = tvPhoneInput.text.toString()
        validateInputFields(nombre, correo, oficina, telefono)
        DetailActivity(nombre, correo, oficina, telefono)
    }


    private fun DetailActivity(nombre: String, correo: String, oficina: String, telefono: String) {
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("nombre", nombre)
        intent.putExtra("correo", correo)
        intent.putExtra("oficina", oficina)
        intent.putExtra("telefono", telefono)
        startActivityForResult(intent, ACTIVITY_B_REQUEST)
    }

    private fun validateInputFields(
        nombre: String,
        correo: String,
        oficina: String,
        telefono: String
    ) {
        if (nombre.isBlank() && correo.isBlank() && oficina.isBlank() && telefono.isBlank()) return
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "requestCode:$requestCode")
        Log.d(TAG, "resultCode:$resultCode")
        Log.d(TAG, "data:" + android.R.attr.data)

            when (requestCode) {
                ACTIVITY_A_REQUEST -> Log.d(TAG, "Regresamos del Activity A")
                ACTIVITY_B_REQUEST -> {
                    Log.d(TAG, "Regresando a la vista principal")
                    if (resultCode === RESULT_OK) {
                        val valor: String = data?.extras?.getString("valor").toString()
                        Toast.makeText(this, "valor: $valor", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "valor: $valor")
                    }
                    val extras = data?.extras
                    if (extras != null) {
                        if (extras.get(PARAMETER_EXTRA_NAME2) != null) {
                            tvNameInput.setText(extras.getString(PARAMETER_EXTRA_NAME2))
                        }

                        if (extras.get(PARAMETER_EXTRA_GMAIL2) != null) {
                            tvEmailInput.setText(extras.getString(PARAMETER_EXTRA_GMAIL2))
                        }

                        if (extras.get(PARAMETER_EXTRA_OFFICE2) != null) {
                            tvOfficeInput.setText(extras.getString(PARAMETER_EXTRA_OFFICE2))
                        }

                        if (extras.get(PARAMETER_EXTRA_PHONE2) != null) {
                            tvPhoneInput.setText(extras.getString(PARAMETER_EXTRA_PHONE2))
                        }
                    }
                }
            }
        }
    fun openWhatsApp(view: android.view.View) {
        try {
            val text = "This is a test" // Replace with your message.
            val telefono = tvPhoneInput.text.toString()
            "xxxxxxxxxx" // Replace with mobile phone number without +Sign or leading zeros, but with country code
            //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$telefono&text=$text")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}
