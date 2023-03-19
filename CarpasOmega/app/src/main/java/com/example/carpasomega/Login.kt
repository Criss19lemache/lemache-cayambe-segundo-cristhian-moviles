package com.example.carpasomega

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txtCrearCuenta=findViewById<TextView>(R.id.linkCrearEnlace)
            txtCrearCuenta.setOnClickListener {
                val intenMenuCarpas= Intent(this,RegistrarUsuario::class.java)
                startActivity(intenMenuCarpas)
            }

        val txtUsuarioLogin=findViewById<EditText>(R.id.txtUsuarioLogin)
        val txtPasswordLogin=findViewById<EditText>(R.id.txtPasswordLogin)
        val btnIngresar=findViewById<Button>(R.id.btnIngresar)
        btnIngresar.setOnClickListener {

            val db = Firebase.firestore
            val usuariosRef = db.collection("usuarios")

            usuariosRef.whereEqualTo("password", txtPasswordLogin.text.toString())
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val documentos = task.result
                        if (documentos.isEmpty) {
                            // No se encontraron documentos que coincidan con la búsqueda
                            Toast.makeText(this, "Contraseña incorrecta, ingrese nuevamente", Toast.LENGTH_SHORT).show()
                        } else {
                            val primerDocumento = documentos.documents[0]
                            // Obtener los datos del primer documento
                            val nombre = primerDocumento.getString("nombreApellido")

                            // Al menos un documento coincide con la búsqueda
                            Toast.makeText(this, "Bienvenido a Carpas Omega", Toast.LENGTH_SHORT).show()

                            val intenMenuCarpas= Intent(this,Menu::class.java)
                            intenMenuCarpas.putExtra("nombreApellido",nombre.toString())
                            startActivity(intenMenuCarpas)

                        }
                    } else {
                        // Error al realizar la búsqueda
                    }
                }


        }


    }
}