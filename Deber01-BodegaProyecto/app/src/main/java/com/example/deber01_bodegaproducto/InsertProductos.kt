package com.example.deber01_bodegaproducto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class InsertProductos: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_productos)
        var id=""
        val bundle=intent.extras
        id= bundle?.getString("id").toString()

        val nombre = findViewById<EditText>(R.id.txtnombre)
        val marca = findViewById<EditText>(R.id.txtMarca)
        val precio = findViewById<EditText>(R.id.txtPrecio)
        val stock = findViewById<EditText>(R.id.txtStock)

        val btnCrear = findViewById<Button>(R.id.btn_ingresarProducto)
        btnCrear
            .setOnClickListener {
                BaseDeDatos.tablaBodega!!.ingresarProducto(
                    nombre.text.toString(),
                    marca.text.toString(),
                    precio.text.toString(),
                    stock.text.toString(),
                    1
                )
                Toast.makeText(this, "Se ingresó un producto correctamente ", Toast.LENGTH_SHORT)
                    .show()
                //Limpiar Texto
                val nombre = findViewById<EditText>(R.id.txtnombre)
                val marca = findViewById<EditText>(R.id.txtMarca)
                val precio = findViewById<EditText>(R.id.txtPrecio)
                val stock = findViewById<EditText>(R.id.txtStock)
                nombre.setText("")
                marca.setText("")
                precio.setText("")
                stock.setText("")

            }
    }
}