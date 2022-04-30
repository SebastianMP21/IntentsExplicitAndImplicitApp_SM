package com.marquez.intentsexplicitandimplicitapp_sm
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.Intent.ACTION_DIAL
import android.net.Uri
import android.net.Uri.fromParts
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.marquez.intentsexplicitandimplicitapp_sm.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


const val PARAMETER_EXTRA_NAME2 = "nombre"
const val PARAMETER_EXTRA_GMAIL2 = "correo"
const val PARAMETER_EXTRA_OFFICE2 = "oficina"
const val PARAMETER_EXTRA_PHONE2 = "telefono"
const val ACTIVITY_A_REQUEST = 991
const val ACTIVITY_B_REQUEST = 992

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //layoutInflater: Es un constructor de vistas
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)


    }
    //Primer binding
    fun SendExplicity(view: android.view.View) {
        val nombre = binding.tvNameInput.text.toString()
        val correo = binding.tvEmailInput.text.toString()
        val oficina = binding.tvOfficeInput.text.toString()
        val telefono = binding.tvPhoneInput.text.toString()
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
                            binding.tvNameInput.setText(extras.getString(PARAMETER_EXTRA_NAME2))
                        }

                        if (extras.get(PARAMETER_EXTRA_GMAIL2) != null) {
                            binding.tvEmailInput.setText(extras.getString(PARAMETER_EXTRA_GMAIL2))
                        }

                        if (extras.get(PARAMETER_EXTRA_OFFICE2) != null) {
                            binding.tvOfficeInput.setText(extras.getString(PARAMETER_EXTRA_OFFICE2))
                        }

                        if (extras.get(PARAMETER_EXTRA_PHONE2) != null) {
                            binding.tvPhoneInput.setText(extras.getString(PARAMETER_EXTRA_PHONE2))
                        }
                    }
                }
            }
        }

    //segundo binding
    fun SendMessage(view: android.view.View){
        val nombre = binding.tvNameInput.text.toString()
        val telefono = binding.tvPhoneInput.text.toString()
        val uri = Uri.parse("smsto: $telefono")
        val it = Intent(Intent.ACTION_SENDTO,uri)
        it.putExtra("sms_body", "Hola $nombre solo paso a decirte que no tengo nada que decir")
        startActivity(it)
    }
    //tercer binding
    fun CallPhone(view: View) {
        val phone = binding.tvPhoneInput.text.toString()
        val intent = Intent(ACTION_DIAL,Uri.fromParts("tel", phone, null))
        startActivity(intent)
    }


    //cuarto binding
    fun openWhatsApp(view: android.view.View) {
        try {
            val text = "This is a test" // Replace with your message.
            val telefono = binding.tvPhoneInput.text.toString()
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
