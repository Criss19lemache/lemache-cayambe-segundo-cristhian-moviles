package com.example.deber01_bodegaproducto

class Producto (
    var id: Int ,
    var nombre:String,
    var marca:String,
    var precio: String,
    var stock:String,
    var id_bodega: Int
){
    override fun toString(): String {
        return super.toString()
    }
}