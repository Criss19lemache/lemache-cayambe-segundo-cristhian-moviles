package com.example.examen02sclc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InsertBodega : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_bodega)

        val nombre = findViewById<EditText>(R.id.txtnombre)
        val totalProductos = findViewById<EditText>(R.id.txtMarca)
        val telefono = findViewById<EditText>(R.id.txtPrecio)
        val gerente = findViewById<EditText>(R.id.txtStock)
        val btnCrear=findViewById<Button>(R.id.btn_ingresarProducto)
        btnCrear
            .setOnClickListener {

                val db = Firebase.firestore
                val bodega = hashMapOf(
                    "nombre" to nombre.text.toString(),
                    "totalProductos" to totalProductos.text.toString(),
                    "telefono" to telefono.text.toString(),
                    "gerente" to gerente.text.toString()
                )

                db.collection("bodega")
                    .add(bodega)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this, "Bodega agregado con ID: ${documentReference.id}", Toast.LENGTH_SHORT).show()

                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error al agregar bodega: "+e, Toast.LENGTH_SHORT).show()
                    }
                //Limpiar Texto
                nombre.setText("")
                totalProductos.setText("")
                telefono.setText("")
                gerente.setText("")
            }


    }
}