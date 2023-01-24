package com.example.examensclc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BodegaAdapter:RecyclerView.Adapter<BodegaAdapter.bodegaVerHolder>()  {
    private var stdList: ArrayList<BodegaModelo> = ArrayList()
    private var onClickDelete:((BodegaModelo)->Unit)?=null
    private var onClickItem:((BodegaModelo)->Unit)?=null

    fun addItems(items:ArrayList<BodegaModelo>){
        this.stdList=items
        notifyDataSetChanged()
    }
    fun setOnClickDeleteItem(callback:(BodegaModelo)->Unit){
        this.onClickDelete=callback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= bodegaVerHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_std,parent,false)
    )

    override fun onBindViewHolder(holder: bodegaVerHolder, position: Int) {
        val std=stdList[position]
        holder.enlazarVista(std)
        holder.itemView.setOnClickListener{onClickItem?.invoke(std)}
        holder.btnDelete.setOnClickListener{onClickDelete?.invoke(std)}
    }

    fun setOnClickItem(callback:(BodegaModelo)->Unit){
        this.onClickItem=callback
    }
    override fun getItemCount(): Int {
        return stdList.size
    }

    class bodegaVerHolder( var view: View):RecyclerView.ViewHolder(view){
        private var id_bodega=view.findViewById<TextView>(R.id.List_id)
        private var ciudad_bodega=view.findViewById<TextView>(R.id.List_nombre)
        private var cantidad_producto=view.findViewById<TextView>(R.id.List_marca)
        private var telefono_producto=view.findViewById<TextView>(R.id.List_precio)
        private var gerente_producto=view.findViewById<TextView>(R.id.List_disponible)
        var btnDelete=view.findViewById<Button>(R.id.List_bnt_eliminar)

        fun enlazarVista(std:BodegaModelo){
            id_bodega.text=std.id.toString()
            ciudad_bodega.text=std.ciudad
            cantidad_producto.text=std.cantidad.toString()
            telefono_producto.text=std.telefono.toString()
            gerente_producto.text=std.gerente
        }
    }


}