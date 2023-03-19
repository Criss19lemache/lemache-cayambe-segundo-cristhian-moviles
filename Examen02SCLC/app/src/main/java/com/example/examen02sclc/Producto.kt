package com.example.examen02sclc

class Producto (
    var nombre:String?,
    var marca:String?,
    var precio: String?,
    var stock:String?,
    var nombre_bodega: String?
){
    override fun toString(): String {
        return "       Producto: ${nombre}  -  Precio: ${precio}"
    }
}