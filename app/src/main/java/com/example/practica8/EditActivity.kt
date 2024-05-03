package com.example.practica8

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity() {
    var position : Int = 0
    var id: Int = 0
    lateinit var txtNombre: EditText
    lateinit var txtTelefono: EditText

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        position = intent.getIntExtra("position", -1)
        Log.e("Contacto", "Se recibio un ${position}")
        txtNombre = findViewById(R.id.txtNom)
        txtTelefono = findViewById(R.id.txtTel)

        val contacto = ControlArchivos.listaContactos[position]
        txtNombre.setText(contacto.nombre)
        txtTelefono.setText(contacto.telefono)
        id = contacto.id
    }

    fun guardar(v:View){
        //val contact = Contacto(txtNombre.text.toString(), txtTelefono.text.toString(), )
        //ControlArchivos.listaContactos[position] = contact

        val db = openOrCreateDatabase("MiBd", MODE_PRIVATE, null)
        val parameters = ContentValues()
        parameters.put("nombre", txtNombre.text.toString())
        parameters.put("telefono", txtTelefono.text.toString())

        db.update("Contactos", parameters, "id=${id}", null)

        Toast.makeText(this, "Se modifico correctamente", Toast.LENGTH_LONG).show()
        db.close()
        finish()
    }
}