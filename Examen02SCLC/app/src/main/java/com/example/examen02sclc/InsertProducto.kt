package com.example.examen02sclc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InsertProducto : AppCompatActivity() {
    var nombreBodega=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_producto)

        val bundle=intent.extras

        val nombre = findViewById<EditText>(R.id.txtnombre)
        val marca = findViewById<EditText>(R.id.txtMarca)
        val precio = findViewById<EditText>(R.id.txtPrecio)
        val stock = findViewById<EditText>(R.id.txtStock)
        nombreBodega=bundle?.getString("nombre_bodega").toString()

        val btnCrear = findViewById<Button>(R.id.btn_ingresarProducto)
        btnCrear
            .setOnClickListener {

                val db = Firebase.firestore
                val bodega = hashMapOf(
                    "nombre" to nombre.text.toString(),
                    "marca" to marca.text.toString(),
                    "precio" to precio.text.toString(),
                    "stock" to stock.text.toString(),
                    "nombre_bodega" to nombreBodega
                )

                db.collection("producto")
                    .add(bodega)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this, "Producto agregado con ID: ${documentReference.id}", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, ProductoView::class.java)
                        intent.putExtra("nombre_bodega", nombreBodega)
                        startActivity(intent)

                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error al agregar producto: "+e, Toast.LENGTH_SHORT).show()
                    }

                //Limpiar Texto
                nombre.setText("")
                marca.setText("")
                precio.setText("")
                stock.setText("")

            }

    }



}