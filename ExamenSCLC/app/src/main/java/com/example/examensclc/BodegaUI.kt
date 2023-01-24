package com.example.examensclc

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

class BodegaUI : AppCompatActivity() {
    //variables globales
    private lateinit var recyclerView: RecyclerView
    private var adapter:BodegaAdapter?=null
    val rutaSDCard="/storage/emulated/0/Download/bodega.txt"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bodega_ui)

        //Botones para UI BODEGA
        val btn_agregar=findViewById<Button>(R.id.btn_agregar)
        val btn_ver=findViewById<Button>(R.id.btn_ver)
        val btn_actulizar=findViewById<Button>(R.id.btn_Actualizar)
        //campos de variable
        val ciudadBodega=findViewById<EditText>(R.id.ciudadBodega)
        val totalProductosenBodega=findViewById<EditText>(R.id.totalProductos)
        val telefono=findViewById<EditText>(R.id.telefono)
        val gerente=findViewById<EditText>(R.id.gerente)
        //recyclerView - lista de productos
        recyclerView =findViewById(R.id.lista_bodegas)

        //Acciones de los botones
        btn_agregar.setOnClickListener {
            //creamos una nueva id para el producto
            var totalProductosExistentes=0
            totalProductosExistentes=totalbodegas()
            totalProductosExistentes += 1
            val nuevaBodega = totalProductosExistentes.toString()+"|"+
                    ciudadBodega.text+"|"+
                    totalProductosenBodega.text+"|"+
                    telefono.text+"|"+
                    gerente.text+"\n"
            //llamada a la funcion agregar un nuevo producto
            agregarBodega(nuevaBodega)
        }// fin de Listener de agregar

        // mostrar la lista de productos
        btn_ver.setOnClickListener{
            getBodegas()

        }
        initRecyclerView()



        btn_actulizar.setOnClickListener{
            var ciudad:String=""
            ciudad=ciudadBodega.text.toString()
            eliminarBodegaF(ciudad)
            //Actualizar
            var totalProductosExistentes=0
            totalProductosExistentes=totalbodegas()
            totalProductosExistentes += 1
            val nuevaBodega = totalProductosExistentes.toString()+"|"+
                    ciudadBodega.text+"|"+
                    totalProductosenBodega.text+"|"+
                    telefono.text+"|"+
                    gerente.text+"\n"
            //llamada a la funcion agregar un nuevo producto
            agregarBodegaF(nuevaBodega)
            getBodegas()
        }// fin del accion listener del boton actualizar



        adapter?.setOnClickDeleteItem {
            deleteBodega(it.id)
        }
        adapter?.setOnClickItem {
            ciudadBodega.setText(it.ciudad)
            totalProductosenBodega.setText(it.cantidad)
            telefono.setText(it.telefono.toString())
            gerente.setText(it.gerente)
        }

    }// fin de onCreate bodega


    @RequiresApi(Build.VERSION_CODES.O)
    private fun deleteBodega(id:Int){
        val builder= AlertDialog.Builder(this)
        builder.setMessage("Estas seguro que desea eliminar producto?")
        builder.setCancelable(true)
        builder.setPositiveButton("Si"){ dialog,_->
            //eliminar bodega
            eliminarBodega(id.toString())
            dialog.dismiss()
            getBodegas()
        }
        builder.setNegativeButton("No"){ dialog,_->
            dialog.dismiss()
        }
        val alert=builder.create()
        alert.show()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun agregarBodega(nuevoBodega:String){
        try {
            val nombreArchivo = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + "bodega.txt"
            val path = Paths.get(rutaSDCard)
            val archivoBodega = File(nombreArchivo)
            if (!archivoBodega.exists()) {
                archivoBodega.createNewFile()
            }
            Files.write(path,nuevoBodega.toByteArray(), StandardOpenOption.APPEND)
            Toast.makeText(this, "Bodega guardando en $nombreArchivo", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(this, "Error al guardar la bodega"+e, Toast.LENGTH_SHORT).show()
        }
        finish()
    }// fin del metodo agregar nuevo producto


    @RequiresApi(Build.VERSION_CODES.O)
    fun agregarBodegaF(nuevoBodega:String){
        try {
            val nombreArchivo = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + "producto.txt"
            val path = Paths.get(rutaSDCard)
            val archivoBodega = File(nombreArchivo)
            if (!archivoBodega.exists()) {
                archivoBodega.createNewFile()
            }
            Files.write(path,nuevoBodega.toByteArray(), StandardOpenOption.APPEND)
            Toast.makeText(this, "Bodega Actualizada", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(this, "Error al guardar la bodega"+e, Toast.LENGTH_SHORT).show()
        }
        finish()
    }// fin del metodo agregar nuevo producto


    @RequiresApi(Build.VERSION_CODES.O)
    fun totalbodegas(): Int {
        var path = Paths.get(rutaSDCard)
        var numbodegas = 0
        Files.lines(path, Charsets.UTF_8).forEach {
            numbodegas ++
        }
        return numbodegas
    }// fin del metodo total de productos


    @RequiresApi(Build.VERSION_CODES.O)
    private fun eliminarBodega(id:String){
        //linea para borrar
        var bodegasrestantes = ""
        //Leer archivo
        var path = Paths.get(rutaSDCard)
        Files.lines(path, Charsets.UTF_8).forEach { var idBodega = it.split("|")
            if (idBodega[0] == id) {
                Toast.makeText(this, "Bodega eliminada", Toast.LENGTH_SHORT).show()
            } else { bodegasrestantes =bodegasrestantes+ it + "\n" }
        }
        File(rutaSDCard).printWriter().use {
                out
            ->
            out.print(bodegasrestantes)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun eliminarBodegaF(ciudad:String){
        //linea para borrar
        var bodegasrestantes = ""
        //Leer archivo
        var path = Paths.get(rutaSDCard)
        Files.lines(path, Charsets.UTF_8).forEach { var ciudadBodega = it.split("|")
            if (ciudadBodega[1] == ciudad) {
            } else { bodegasrestantes =bodegasrestantes+ it + "\n" }
        }
        File(rutaSDCard).printWriter().use {
                out
            ->
            out.print(bodegasrestantes)
        }
    }




    @RequiresApi(Build.VERSION_CODES.O)






    private fun getBodegas(){

        val stdList=getAllBodegas()
        Log.e("piuuuu","${stdList.size}")
        adapter?.addItems(stdList)

    }

    fun getAllBodegas():ArrayList<BodegaModelo>{

        //lectura de datos
        var datosBodegas=""
        val file = File(rutaSDCard)
        val stdList: ArrayList<BodegaModelo> = ArrayList()
        val vector:  ArrayList<String> = ArrayList()
        val vectorDatosParseados:  ArrayList<String> = ArrayList()
        try {
            BufferedReader(FileReader(file)).use { br ->
                var line: String?
                while (br.readLine().also { line = it } != null) {
                    vector.add(line.toString())
                }
            }
            if(vector.isNotEmpty()){
                val datesParadise= vector[0].split("|")
                val std= BodegaModelo(id = "${datesParadise[0]}".toInt(),
                    ciudad =  "${datesParadise[1]}",
                    cantidad = "${datesParadise[2]}".toInt(),
                    telefono =  "${datesParadise[3]}".toInt(),
                    gerente = "${datesParadise[4]}"
                )
                stdList.add(std)
            }else
            {
                Toast.makeText(this, "No existen registro de Bodegas", Toast.LENGTH_SHORT).show()

            }

            // Agregar datos
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return stdList
    }
    private fun initRecyclerView(){
        recyclerView.layoutManager= LinearLayoutManager(this)
        adapter=BodegaAdapter()
        recyclerView.adapter=adapter
    }

}