package com.example.carpasomega

class Producto (
    public var nombre: String?,
    public var tipo: String?,
    public var costo: String?,
    public var stock: String?){

    override fun toString(): String {
        return "       Producto: ${nombre}  -  Gerente: ${stock}"
    }
}