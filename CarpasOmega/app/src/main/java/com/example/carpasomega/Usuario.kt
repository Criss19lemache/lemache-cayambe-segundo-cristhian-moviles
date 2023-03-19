package com.example.carpasomega

class Usuario(
    public var nombreApellido: String?,
    public var telefono: String?,
    public var direcccion: String?,
    public var correo: String?,
    public var usuario: String?,
    public var password: String?

    ){

    override fun toString(): String {
        return "       Usuario: ${nombreApellido}  -  Gerente: ${usuario}"
    }
}
