package com.example.examensclc

data class ProductoModelo (
    var id: Int ,
    var nombre:String="",
    var marca:String="",
    var precio: Double,
    var disponible:Boolean,
        ){
    override fun toString(): String {
        return super.toString()
    }
}