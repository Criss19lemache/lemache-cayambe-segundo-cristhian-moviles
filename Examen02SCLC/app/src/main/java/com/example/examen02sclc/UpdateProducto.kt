package com.example.examen02sclc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class UpdateProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_producto)
        val bundle=intent.extras
        val nombre = findViewById<EditText>(R.id.txtnombre)
        val marca = findViewById<EditText>(R.id.txtMarca)
        val precio = findViewById<EditText>(R.id.txtPrecio)
        val stock = findViewById<EditText>(R.id.txtStock)

        nombre.setText(bundle?.getString("nombre"))
        marca.setText(bundle?.getString("marca"))
        precio.setText(bundle?.getString("precio"))
        stock.setText(bundle?.getString("stock"))

        var nombreB=bundle?.getString("nombre")
        val nombre_bodega=bundle?.getString("nombre_bodega")
        val btnEditarProducto=findViewById<Button>(R.id.btn_ingresarProducto)
        btnEditarProducto.setOnClickListener {

            //Actualizar datos

            val db = FirebaseFirestore.getInstance()
            val usuariosRef = db.collection("producto")

            usuariosRef.whereEqualTo("nombre", nombreB)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val documentId = querySnapshot.documents[0].id

                        //actualizar
                        val data = HashMap<String, Any>()
                        data["nombre"] = nombre.text.toString()
                        data["marca"] = marca.text.toString()
                        data["precio"] = precio.text.toString()
                        data["stock"] = stock.text.toString()
                        data["nombre_bodega"] = nombre_bodega.toString()

                        usuariosRef.document(documentId).update(data)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Datos de la producto actualizados correctamente", Toast.LENGTH_SHORT).show()
                                val intenProductosInsert= Intent(this,ProductoView::class.java)
                                intenProductosInsert.putExtra("nombre_bodega",nombre_bodega)
                                startActivity(intenProductosInsert)
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Error a actualizar los datos", Toast.LENGTH_SHORT).show()
                            }


                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al obtener el documento", Toast.LENGTH_SHORT).show()
                }




        }


    }
}