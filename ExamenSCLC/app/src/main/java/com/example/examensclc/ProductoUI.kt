package com.example.examensclc

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.*
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

private val CODIGO_PERMISO_ALMACENAMIENTO = 3

class ProductoUI : AppCompatActivity() {
    //variables globales
    private lateinit var recyclerView: RecyclerView
    private var adapter:ProductoAdapter?=null
    val rutaSDCard="/storage/emulated/0/Download/producto.txt"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto_ui)
        //Botones
        val btn_agregar=findViewById<Button>(R.id.btn_agregar)
        val btn_ver=findViewById<Button>(R.id.btn_ver)
        val btn_actulizar=findViewById<Button>(R.id.btn_Actualizar)
        //campos de variable
        val nombreProducto=findViewById<EditText>(R.id.nombreProducto)
        val marcaProducto=findViewById<EditText>(R.id.marcaProducto)
        val precioProducto=findViewById<EditText>(R.id.precioProducto)
        val disponibleProducto=findViewById<EditText>(R.id.disponibilidadProducto)
        //recyclerView - lista de productos
        recyclerView =findViewById(R.id.lista_productos)

        //Acciones de los botones
        btn_agregar.setOnClickListener {
            //Permisos para el almacenamiento en la SDCard del telefono
            verificarYPedirPermisosDeAlmacenamiento()
            //creamos una nueva id para el producto
            var totalProductosExistentes=0
            totalProductosExistentes=totalproductos()
            totalProductosExistentes += 1
            val nuevoProducto = totalProductosExistentes.toString()+"|"+
                    nombreProducto.text+"|"+
                    marcaProducto.text+"|"+
                    precioProducto.text+"|"+
                    disponibleProducto.text+"\n"
            //llamada a la funcion agregar un nuevo producto
                agregarProducto(nuevoProducto)
        }// fin de Listener de agregar


        // mostrar la lista de productos
        btn_ver.setOnClickListener{
            getProductos()

        }
        initRecyclerView()

        btn_actulizar.setOnClickListener{
        var nombre:String=""
            nombre=nombreProducto.text.toString()
            eliminarProductoF(nombre)
            //Actualizar
            var totalProductosExistentes=0
            totalProductosExistentes=totalproductos()
            totalProductosExistentes += 1
            val nuevoProducto = totalProductosExistentes.toString()+"|"+
                    nombreProducto.text+"|"+
                    marcaProducto.text+"|"+
                    precioProducto.text+"|"+
                    disponibleProducto.text+"\n"
            //llamada a la funcion agregar un nuevo producto
            agregarProductoF(nuevoProducto)
            getProductos()
        }// fin del accion listener del boton actualizar


        adapter?.setOnClickDeleteItem {
            deleteProducto(it.id)
        }
        adapter?.setOnClickItem {
            nombreProducto.setText(it.nombre)
            marcaProducto.setText(it.marca)
            precioProducto.setText(it.precio.toString())
            disponibleProducto.setText(it.disponible.toString())
        }

    }// end onCreate
    @RequiresApi(Build.VERSION_CODES.O)
    private fun deleteProducto(id:Int){
        val builder=AlertDialog.Builder(this)
        builder.setMessage("Estas seguro que desea eliminar producto?")
        builder.setCancelable(true)
        builder.setPositiveButton("Si"){ dialog,_->
            //eliminar producto metodo
            eliminarProducto(id.toString())
            dialog.dismiss()
            getProductos()
        }
        builder.setNegativeButton("No"){ dialog,_->
            dialog.dismiss()
        }
        val alert=builder.create()
        alert.show()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun agregarProducto(nuevoProducto:String){
        try {
            val nombreArchivo = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + "producto.txt"
            val path = Paths.get(rutaSDCard)
            val archivoProducto = File(nombreArchivo)
            if (!archivoProducto.exists()) {
                archivoProducto.createNewFile()
            }
            Files.write(path,nuevoProducto.toByteArray(),StandardOpenOption.APPEND)
            Toast.makeText(this, "Producto guardando en $nombreArchivo", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(this, "Error al guardar el producto"+e,Toast.LENGTH_SHORT).show()
        }
        finish()
    }// fin del metodo agregar nuevo producto


    @RequiresApi(Build.VERSION_CODES.O)
    fun agregarProductoF(nuevoProducto:String){
        try {
            val nombreArchivo = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + "producto.txt"
            val path = Paths.get(rutaSDCard)
            val archivoProducto = File(nombreArchivo)
            if (!archivoProducto.exists()) {
                archivoProducto.createNewFile()
            }
            Files.write(path,nuevoProducto.toByteArray(),StandardOpenOption.APPEND)
            Toast.makeText(this, "Producto Actualizado", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(this, "Error al guardar el producto"+e,Toast.LENGTH_SHORT).show()
        }
        finish()
    }// fin del metodo agregar nuevo producto


    @RequiresApi(Build.VERSION_CODES.O)
    fun totalproductos(): Int {
        var path = Paths.get(rutaSDCard)
        var numProductos = 0
        Files.lines(path, Charsets.UTF_8).forEach {
            numProductos ++
        }
        return numProductos
    }// fin del metodo total de productos





    @RequiresApi(Build.VERSION_CODES.O)
    private fun eliminarProducto(id:String){
        //linea para borrar
        var productosrestantes = ""
        //Leer archivo
        var path = Paths.get(rutaSDCard)
        Files.lines(path, Charsets.UTF_8).forEach { var idProducto = it.split("|")
            if (idProducto[0] == id) {
                Toast.makeText(this, "Producto eliminado", Toast.LENGTH_SHORT).show()
            } else { productosrestantes =productosrestantes+ it + "\n" }
        }
        File(rutaSDCard).printWriter().use {
                out
            ->
            out.print(productosrestantes)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun eliminarProductoF(nombre:String){
        //linea para borrar
        var productosrestantes = ""
        //Leer archivo
        var path = Paths.get(rutaSDCard)
        Files.lines(path, Charsets.UTF_8).forEach { var idProducto = it.split("|")
            if (idProducto[1] == nombre) {
                //Toast.makeText(this, "Producto eliminado", Toast.LENGTH_SHORT).show()
            } else { productosrestantes =productosrestantes+ it + "\n" }
        }
        File(rutaSDCard).printWriter().use {
                out
            ->
            out.print(productosrestantes)
        }
    }




    @RequiresApi(Build.VERSION_CODES.O)

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isEmpty()) {
            return
        }

        if (requestCode == CODIGO_PERMISO_ALMACENAMIENTO) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Aquí se ha concedido el permiso
            } else {
                Toast.makeText(this, "Permiso de almacenamiento requerido", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    private fun verificarYPedirPermisosDeAlmacenamiento() {
        val estadoDePermiso =
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
            // Aquí se ha concedido el permiso
        } else {
            // Si no, pedimos permisos. Ahora mira onRequestPermissionsResult
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                CODIGO_PERMISO_ALMACENAMIENTO
            )
        }
    }



    private fun getProductos(){

        val stdList=getAllProductos()
        Log.e("piuuuu","${stdList.size}")
        adapter?.addItems(stdList)

    }

    fun getAllProductos():ArrayList<ProductoModelo>{

        //lectura de datos
        var datosProducto=""
        val file = File(rutaSDCard)
        val stdList: ArrayList<ProductoModelo> = ArrayList()
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
                val std= ProductoModelo(id = "${datesParadise[0]}".toInt(),
                    nombre =  "${datesParadise[1]}",
                    marca =  "${datesParadise[2]}",
                    precio =  "${datesParadise[3]}".toDouble(),
                    disponible = false
                )
                stdList.add(std)
            }else
            {
                Toast.makeText(this, "No existen registro de productos",Toast.LENGTH_SHORT).show()

            }

        // Agregar datos
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return stdList
    }
    private fun initRecyclerView(){
        recyclerView.layoutManager=LinearLayoutManager(this)
        adapter=ProductoAdapter()
        recyclerView.adapter=adapter
    }






}