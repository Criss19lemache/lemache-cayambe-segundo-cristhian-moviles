package com.example.deber01_bodegaproducto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AdapterB (context: Context, bodegas: ArrayList<Bodega>): BaseAdapter() {

        val dBodegas=bodegas
        val cont=context

        inner class ViewHolder(){
            var nombreBodega: TextView?=null
            var telefono:TextView?=null
        }

        override fun getView(position: Int, row: View?, parent: ViewGroup?): View? {

            var columnaver=row
            var columnaver2=row

            var viewHolder: ViewHolder

            if(columnaver==null){
                viewHolder=ViewHolder()
                val inflater= LayoutInflater.from(cont)
                columnaver=inflater.inflate(R.layout.card_items,parent,false)

                viewHolder.nombreBodega=columnaver.findViewById(R.id.List_nombre) as TextView
                viewHolder.telefono=columnaver.findViewById(R.id.List_marca_telefonoB)
                columnaver.tag=viewHolder
            }else{
                viewHolder=columnaver.tag as ViewHolder
            }
            viewHolder.nombreBodega!!.setText("Nombre Bodega: "+dBodegas.get(position).nombre)
            viewHolder.telefono!!.setText("Contacto: "+dBodegas.get(position).telefono)
            return columnaver


        }
        override fun getCount(): Int {
            return dBodegas.size
        }

        override fun getItem(i: Int): Any {
            return dBodegas[i]
        }

        override fun getItemId(j: Int): Long {
            return j.toLong()
        }


    }
