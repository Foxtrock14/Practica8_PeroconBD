package com.example.practica8

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    lateinit var rcv:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        rcv = findViewById(R.id.rvContactos)

    }//onCreate

    override fun onResume() {
        super.onResume()
        val control = ControlArchivos(this)
        val contenido = control.leerArchivo()
        //conversion de string a json
        val db = openOrCreateDatabase("MiBd", MODE_PRIVATE, null)
        db.execSQL("CREATE TABLE IF NOT EXISTS Contactos(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, telefono TEXT NOT NULL);")

        // Cargar los datos de la base de datos
        val cursor = db.rawQuery("SELECT * FROM Contactos", null)
        ControlArchivos.listaContactos.clear()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val nombre = cursor.getString(cursor.getColumnIndex("nombre"))
            val telefono = cursor.getString(cursor.getColumnIndex("telefono"))
            val contacto = Contacto(nombre, telefono, id)
            ControlArchivos.listaContactos.add(contacto)
        }
        cursor.close()
        db.close()

        rcv.adapter = Adaptador(this)
        rcv.layoutManager = LinearLayoutManager(this)
    }


    fun btnAgregar(v: View){
        val intent = Intent(this, AgregarActivity::class.java)
        startActivity(intent)
    }

    fun clickItem(position: Int){
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }
}