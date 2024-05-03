package com.example.practica8


import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class AgregarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun Guardar(v: View) {
        val nombre = findViewById<EditText>(R.id.txtNom)
        val telefono = findViewById<EditText>(R.id.txtTel)
        val nombreTexto = nombre.text.toString() // Obtener el texto del EditText nombre
        val telefonoTexto = telefono.text.toString() // Obtener el texto del EditText telefono

        val contacto = Contacto(nombreTexto, telefonoTexto, 0)
        ControlArchivos.listaContactos.add(contacto)


        val db = openOrCreateDatabase("MiBd", MODE_PRIVATE, null)
        val parametros = ContentValues()

        parametros.put("nombre", nombreTexto) // Usar el texto obtenido
        parametros.put("telefono", telefonoTexto) // Usar el texto obtenido

        db.insert("Contactos", null, parametros)
        db.close()

        finish()


    }


}