package com.example.deber01_bodegaproducto

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class BSqliteHelper (
        contexto:Context?,
        ):SQLiteOpenHelper(
        contexto,
        "bodegasProductos",
        null,
        1
        ) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptTablaBodega =
            """
               CREATE TABLE bodega(
               id INTEGER PRIMARY KEY AUTOINCREMENT,
               nombre VARCHAR(30),
               cantidadProductos INTEGER,
               telefono INTEGER,
               gerente VARCHAR(40)) 
            """.trimIndent()


        val scriptTablaProducto =
            """
               CREATE TABLE producto(
               id INTEGER PRIMARY KEY AUTOINCREMENT,
               nombre VARCHAR(40),
               marca VARCHAR(40),
               precio VARCHAR(40),
               stock VARCHAR(40),
               id_bodega INTEGER
               ) 
            """.trimIndent()

        db?.execSQL(scriptTablaBodega)
        db?.execSQL(scriptTablaProducto)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS producto")
        onCreate(db)

    }

    fun ingresarBodega(
        nombre: String,
        cantidadProductos: Int,
        telefono: String,
        gerente: String
    ):Boolean{

        val basedatosEscritura = writableDatabase
        val values=  ContentValues()
        values.put("nombre",nombre)
        values.put("cantidadProductos",cantidadProductos)
        values.put("telefono",telefono)
        values.put("gerente",gerente)

        val resultadoGuardar= basedatosEscritura
            .insert(
                "bodega",
                null,
                values
            )

        basedatosEscritura.close()
        return if(resultadoGuardar.toInt()==-1) false else true
    }

    fun ingresarProducto(
        nombre: String,
        marca: String,
        precio: String,
        stock: String,
        id_bodega: Int
    ):Boolean{

        val basedatosEscritura = writableDatabase
        val values=  ContentValues()
        values.put("nombre",nombre)
        values.put("marca",marca)
        values.put("precio",precio)
        values.put("stock",stock)
        values.put("id_bodega",id_bodega)
        val resultadoGuardar= basedatosEscritura
            .insert(
                "producto",
                null,
                values
            )

        basedatosEscritura.close()
        return if(resultadoGuardar.toInt()==-1) false else true
    }

    fun editarBodega(
        id: Int,
        nombre: String,
        cantidadProductos: Int,
        telefono: String,
        gerente: String
    ):Boolean{

        val basedatosEscritura = writableDatabase
        val values=  ContentValues()
        values.put("nombre",nombre)
        values.put("cantidadProductos",cantidadProductos)
        values.put("telefono",telefono)
        values.put("gerente",gerente)

        val resultadoActualizar= basedatosEscritura
            .update(
                "bodega",
                values,
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        basedatosEscritura.close()

        return if(resultadoActualizar.toInt()==-1) false else true

    }


    fun mostrarProductos(): ArrayList<Producto> {

        var productos = ArrayList<Producto>()
        val baseDatosLectura = readableDatabase
        var productosEncontrados = Producto(0, "", "", "", "",0)
        val scriptConsultarProducto = "Select * from producto"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarProducto,
            null
        )


        if(resultadoConsultaLectura!=null && resultadoConsultaLectura.count!=0){
            resultadoConsultaLectura.moveToFirst()
            do {


                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val marca = resultadoConsultaLectura.getString(2)
                val precio = resultadoConsultaLectura.getString(3)
                val stock = resultadoConsultaLectura.getString(4)





                if(nombre!=null){
                    productosEncontrados.id=id
                    productosEncontrados.nombre=nombre
                    productosEncontrados.marca=marca
                    productosEncontrados.precio=precio
                    productosEncontrados.stock=stock

                }
                productos.add(productosEncontrados)
                productosEncontrados = Producto(0, "", "", "", "",0)


            }while (resultadoConsultaLectura.moveToNext())
        }else{


            productos=ArrayList<Producto>()
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return productos


    }


    fun eliminarBodega(id: Int):Boolean{

        val basedatosEscritura=writableDatabase
        val resultadoEliminación=basedatosEscritura
            .delete(
                "bodega",
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        basedatosEscritura.close()
        return if(resultadoEliminación.toInt()==-1)false else true
    }

    fun listarBodegas(): ArrayList<Bodega> {

        var bodegas = ArrayList<Bodega>()
        val baseDatosLectura = readableDatabase
        var listBodegas= Bodega(0,"",0,"","")
        val consultaBodegas = "Select * from bodega"
        val resultadoConsultaLectura=baseDatosLectura.rawQuery(
            consultaBodegas,
            null
        )
        if(resultadoConsultaLectura!=null && resultadoConsultaLectura.count!=0){
            resultadoConsultaLectura.moveToFirst()
            do{
                val id=resultadoConsultaLectura.getInt(0)
                val nombre=resultadoConsultaLectura.getString(1)
                val totalProductos=resultadoConsultaLectura.getInt(2)
                val telefono=resultadoConsultaLectura.getString(3)
                val gerente=resultadoConsultaLectura.getString(4)

                if(id!=null){
                    listBodegas.id=id
                    listBodegas.nombre=nombre
                    listBodegas.totalProductos=totalProductos
                    listBodegas.telefono=telefono
                    listBodegas.gerente=gerente
                }
                bodegas.add(listBodegas)
                listBodegas=Bodega(0,"",0,"","")

            }while (resultadoConsultaLectura.moveToNext())
        }else{
            bodegas=ArrayList<Bodega>()
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return bodegas

    }


}