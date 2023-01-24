package com.example.examensclc

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductoAdapter:RecyclerView.Adapter<ProductoAdapter.productoVerHolder>() {
    private var stdList: ArrayList<ProductoModelo> = ArrayList()
    private var onClickDelete:((ProductoModelo)->Unit)?=null
    private var onClickItem:((ProductoModelo)->Unit)?=null

    fun addItems(items:ArrayList<ProductoModelo>){
        this.stdList=items
        notifyDataSetChanged()
    }
    fun setOnClickDeleteItem(callback:(ProductoModelo)->Unit){
        this.onClickDelete=callback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= productoVerHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_std,parent,false)
    )
    override fun onBindViewHolder(holder: productoVerHolder, position: Int) {
        val std=stdList[position]
        holder.enlazarVista(std)
        holder.itemView.setOnClickListener{onClickItem?.invoke(std)}
        holder.btnDelete.setOnClickListener{onClickDelete?.invoke(std)}
    }

    fun setOnClickItem(callback:(ProductoModelo)->Unit){
        this.onClickItem=callback
    }
    override fun getItemCount(): Int {
        return stdList.size
    }
    class productoVerHolder( var view: View):RecyclerView.ViewHolder(view){
        private var id_producto=view.findViewById<TextView>(R.id.List_id)
        private var nombre_producto=view.findViewById<TextView>(R.id.List_nombre)
        private var marca_producto=view.findViewById<TextView>(R.id.List_marca)
        private var precio_producto=view.findViewById<TextView>(R.id.List_precio)
        private var disponible_producto=view.findViewById<TextView>(R.id.List_disponible)
                var btnDelete=view.findViewById<Button>(R.id.List_bnt_eliminar)

        fun enlazarVista(std:ProductoModelo){
            id_producto.text=std.id.toString()
            nombre_producto.text=std.nombre
            marca_producto.text=std.marca
            precio_producto.text=std.precio.toString()
            disponible_producto.text=std.disponible.toString()
        }
    }
}