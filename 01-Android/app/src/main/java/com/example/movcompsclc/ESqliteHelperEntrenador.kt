package com.example.movcompsclc

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador (
    contexto: Context?,
): SQLiteOpenHelper(
    contexto,
    "moviles",//nombre de nuestra BDD Sqlite(moviles.sqlite)
    null,
    1
){
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador=
            """
            CREATE TABLE ENTRENADOR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
            )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun crearEntrenador(
        nombre:String,
        descripcion:String
    ):Boolean{
        //this.readableDatabase Lectura
        //this.writableDatabase Escritura
        val basedatosEscritura=writableDatabase
        val valoresAGuardar=ContentValues()
        valoresAGuardar.put("nombre",nombre)
        valoresAGuardar.put("descripcion",descripcion)
        val resultadoGuardar=basedatosEscritura
            .insert(
                "ENTRENADOR",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt()==-1) false else true
    }

    fun eliminarEntrenadorFormulario(id:Int):Boolean{
        // val conexionEscritura=this.writableDatababase

        val conexionEscritura=writableDatabase
        //"Select * from entrenador where ID=?"
        // arrayOf(
        // id.toString()
        // )
        val resultadoEliminacion=conexionEscritura
            .delete(
                "ENTRENADOR",
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt()==-1) false else true
    }

    fun actuaizarEntrenadorFormulario( nombre: String, descripcion: String,idActualizar:Int
    ):Boolean{
        //this.readableDatabase Lectura
        //this.writetableDatabase Escritura
        val conexionEscritura=writableDatabase
        val valoresActualizar=ContentValues()
        valoresActualizar.put("nombre", nombre)
        valoresActualizar.put("descripcion", descripcion)

        val resultadoActualizacion=conexionEscritura.update(
            "ENTRENADOR",//nombre tabla
                valoresActualizar,// valores a actualizar
                "id?",// condicion
        arrayOf(
            idActualizar.toString()
        )// valores de la condicion
        )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt()==-1) false else true
    }

    fun consultarEntrenadorPorId(id:Int
    ):BEntrenador{
        // val baseDatosLectura = readableDatabase
        val baseDatosLectura=readableDatabase
        val scriptConsulta= "Select * from ENTRENADOR WHERE ID=?"
        val resultadoConsultaLectura=baseDatosLectura.rawQuery(
            scriptConsulta,
            arrayOf(
                id.toString()
            )
        )
        val existeUsuario=resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado=BEntrenador(0,"","")

        // LOGICA PARA OBTENER EL USUARIO
        do{
            val id = resultadoConsultaLectura.getInt(0)//columna indice 0->ID
            val nombre =resultadoConsultaLectura.getString(1)// Columna indice ->NOMBRE
            val descripcion=
                resultadoConsultaLectura.getString(2)// Columna indice 2->DESCRIPCION
            if(id!=null){
                usuarioEncontrado.id=id
                usuarioEncontrado.nombre=nombre
                usuarioEncontrado.descripcion=descripcion
            }
        }while(resultadoConsultaLectura.moveToNext())
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }



}


