package com.marquez.intentsexplicitandimplicitapp_sm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*

const val PARAMETER_EXTRA_NAME = "nombre"
const val PARAMETER_EXTRA_GMAIL = "correo"
const val PARAMETER_EXTRA_OFFICE = "oficina"
const val PARAMETER_EXTRA_PHONE = "telefono"


class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val extras = this.intent.extras

        if (extras != null) {
            if (extras.get(PARAMETER_EXTRA_NAME) != null) {
                edtName.setText(extras.getString(PARAMETER_EXTRA_NAME))
            }

            if (extras.get(PARAMETER_EXTRA_GMAIL) != null) {
                edtEmail.setText(extras.getString(PARAMETER_EXTRA_GMAIL))
            }

            if (extras.get(PARAMETER_EXTRA_OFFICE) != null) {
                edtOffice.setText(extras.getString(PARAMETER_EXTRA_OFFICE))
            }

            if (extras.get(PARAMETER_EXTRA_PHONE) != null) {
                edtPhone.setText(extras.getString(PARAMETER_EXTRA_PHONE))
            }
        }
    }

    fun GuardarDatos(view: View) {
        val nombre =edtName.text.toString()
        val correo = edtEmail.text.toString()
        val oficina = edtOffice.text.toString()
        val telefono = edtPhone.text.toString()
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("nombre", nombre)
        intent.putExtra("correo", correo)
        intent.putExtra("oficina", oficina)
        intent.putExtra("telefono", telefono)
        setResult(RESULT_OK,intent)
        finish()
    }

}