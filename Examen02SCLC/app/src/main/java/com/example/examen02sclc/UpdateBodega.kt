package com.example.examen02sclc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class UpdateBodega : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_bodega)

        val bundle=intent.extras

        val nombre = findViewById<EditText>(R.id.txtnombre)
        val totalProductos = findViewById<EditText>(R.id.txtMarca)
        val telefono = findViewById<EditText>(R.id.txtPrecio)
        val gerente = findViewById<EditText>(R.id.txtStock)

        val id=bundle?.getString("id")

        nombre.setText(bundle?.getString("nombre"))
        totalProductos.setText(bundle?.getString("totalProductos"))
        telefono.setText(bundle?.getString("telefono"))
        gerente.setText(bundle?.getString("gerente"))

        var nombreB=bundle?.getString("nombre")

        val btnEditarBodega=findViewById<Button>(R.id.btn_ingresarProducto)
        btnEditarBodega.setOnClickListener {

            //Actualizar datos


            val db = FirebaseFirestore.getInstance()
            val usuariosRef = db.collection("bodega")

            usuariosRef.whereEqualTo("nombre", nombreB)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val documentId = querySnapshot.documents[0].id

                        //actualizar
                        val data = HashMap<String, Any>()
                        data["nombre"] = nombre.text.toString()
                        data["totalProductos"] = totalProductos.text.toString()
                        data["telefono"] = telefono.text.toString()
                        data["gerente"] = gerente.text.toString()

                        usuariosRef.document(documentId).update(data)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Datos de la bodega actualizados correctamente", Toast.LENGTH_SHORT).show()

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