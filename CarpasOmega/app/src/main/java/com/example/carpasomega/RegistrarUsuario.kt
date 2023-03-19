package com.example.carpasomega

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistrarUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)

        val nombreApellido=findViewById<EditText>(R.id.txtUsuarioLogin)
        val telefono=findViewById<EditText>(R.id.txtTelefono)
        val direccion=findViewById<EditText>(R.id.txtDireccion)
        val correo=findViewById<EditText>(R.id.txtCorreo)
        val usuario=findViewById<EditText>(R.id.txtUsuario)
        val password=findViewById<EditText>(R.id.txtPassword)
        val btnRegistrarse=findViewById<Button>(R.id.btnRegistrarse)
        btnRegistrarse.setOnClickListener {

            val db = Firebase.firestore
            val usuarioFS = hashMapOf(
                "nombreApellido" to nombreApellido.text.toString(),
                "telefono" to telefono.text.toString(),
                "direccion" to direccion.text.toString(),
                "correo" to correo.text.toString(),
                "usuario" to usuario.text.toString(),
                "password" to password.text.toString()
            )

            db.collection("usuarios")
                .add(usuarioFS)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "Registro completado", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al registrarse", Toast.LENGTH_SHORT).show()
                }
            //Limpiar Texto
            nombreApellido.setText("")
            telefono.setText("")
            direccion.setText("")
            correo.setText("")
            usuario.setText("")
            password.setText("")

        }


    }
}