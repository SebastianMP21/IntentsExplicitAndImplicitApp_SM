package com.marquez.intentsexplicitandimplicitapp_sm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.marquez.intentsexplicitandimplicitapp_sm.databinding.ActivityEditBinding
import com.marquez.intentsexplicitandimplicitapp_sm.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*

const val PARAMETER_EXTRA_NAME = "nombre"
const val PARAMETER_EXTRA_GMAIL = "correo"
const val PARAMETER_EXTRA_OFFICE = "oficina"
const val PARAMETER_EXTRA_PHONE = "telefono"


class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val extras = this.intent.extras

        if (extras != null) {
            if (extras.get(PARAMETER_EXTRA_NAME) != null) {
                binding.edtName.setText(extras.getString(PARAMETER_EXTRA_NAME))
            }

            if (extras.get(PARAMETER_EXTRA_GMAIL) != null) {
                binding.edtEmail.setText(extras.getString(PARAMETER_EXTRA_GMAIL))
            }

            if (extras.get(PARAMETER_EXTRA_OFFICE) != null) {
                binding.edtOffice.setText(extras.getString(PARAMETER_EXTRA_OFFICE))
            }

            if (extras.get(PARAMETER_EXTRA_PHONE) != null) {
                binding.edtPhone.setText(extras.getString(PARAMETER_EXTRA_PHONE))
            }
        }
    }

    fun GuardarDatos(view: View) {
        val nombre =binding.edtName.text.toString()
        val correo = binding.edtEmail.text.toString()
        val oficina = binding.edtOffice.text.toString()
        val telefono = binding.edtPhone.text.toString()
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("nombre", nombre)
        intent.putExtra("correo", correo)
        intent.putExtra("oficina", oficina)
        intent.putExtra("telefono", telefono)
        setResult(RESULT_OK,intent)
        finish()
    }

}