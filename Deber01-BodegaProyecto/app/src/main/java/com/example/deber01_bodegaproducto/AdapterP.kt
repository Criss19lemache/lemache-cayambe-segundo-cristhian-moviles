package com.example.deber01_bodegaproducto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AdapterP (
    context:Context,
    dProductos:ArrayList<Producto>
):BaseAdapter(){

    val datosProductos=dProductos
    val cont=context

    inner class ViewHolder(){
        var nombreProducto: TextView?=null
        var marca: TextView?=null
    }
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {
        var rowview=p1
        var viewHolder: ViewHolder

        if(rowview==null){
            viewHolder=ViewHolder()
            val inflater=LayoutInflater.from(cont)
            rowview=inflater.inflate(R.layout.card_items,p2,false)
            viewHolder.nombreProducto=rowview.findViewById(R.id.List_nombre) as TextView
            viewHolder.marca=rowview.findViewById(R.id.List_marca_telefonoB) as TextView
            rowview.tag=viewHolder
        }else{
            viewHolder=rowview.tag as ViewHolder
        }

        return rowview
    }
    override fun getCount(): Int {
        return datosProductos.size
    }

    override fun getItem(i: Int): Any {
        return datosProductos[i]
    }

    override fun getItemId(db: Int): Long {
        return db.toLong()
    }


}