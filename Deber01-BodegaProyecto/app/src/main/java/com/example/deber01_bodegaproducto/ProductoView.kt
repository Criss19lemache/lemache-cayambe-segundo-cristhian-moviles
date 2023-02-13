package com.example.deber01_bodegaproducto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class ProductoView : AppCompatActivity() {


    var id=""
    var nombre=""
    var idItemSeleccionado =0
    var arreglosProductos= ArrayList<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto_view)


        val bundle=intent.extras
        id= bundle?.getString("id").toString()
        nombre=bundle?.getString("nombre").toString()


        val btn_Crear=findViewById<Button>(R.id.btn_Crear)
        btn_Crear.setOnClickListener {
            irActividad(InsertProductos::class.java)
        }
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)

    }

    override fun onResume() {
        super.onResume()

        val textTitulo=findViewById<TextView>(R.id.txtTitulo)
        textTitulo.setText("Productos de la bodega: ${nombre}")
        arreglosProductos = BaseDeDatos.tablaBodega!!.mostrarProductos()

        val listViewProductos=findViewById<ListView>(R.id.lista_Bodegas)

        val adaptador=AdapterP(this,arreglosProductos)
        listViewProductos.adapter=adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listViewProductos)

    }
}