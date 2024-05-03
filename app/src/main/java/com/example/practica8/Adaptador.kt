package com.example.practica8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador (private var mainActivity: MainActivity)
    : RecyclerView.Adapter<Adaptador.ViewHolderContactos>(){

    //Parte interna
    class ViewHolderContactos(item: View): RecyclerView.ViewHolder(item){
        var txtNombre : TextView = item.findViewById(R.id.txtNom)
        var txtTelefono : TextView = item.findViewById(R.id.txtTel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderContactos {
        var layoutItem = LayoutInflater.from(parent.context).inflate(R.layout.contacto_item, parent, false)
        return ViewHolderContactos(layoutItem)
    }

    override fun getItemCount(): Int  = ControlArchivos.listaContactos.size

    override fun onBindViewHolder(holder: ViewHolderContactos, position: Int) {
        val contacto = ControlArchivos.listaContactos[position]
        holder.txtNombre.text = contacto.nombre
        holder.txtTelefono.text = contacto.telefono
        holder.itemView.setOnClickListener{
            mainActivity.clickItem(position)
        }
    }
}