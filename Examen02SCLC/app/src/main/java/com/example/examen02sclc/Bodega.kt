package com.example.examen02sclc

class Bodega(
    public var nombre: String?,
    public var totalProductos: String?,
    public var telefono: String?,
    public var gerente: String?){

    override fun toString(): String {
        return "       Bodega: ${nombre}  -  Gerente: ${gerente}"
    }
}
