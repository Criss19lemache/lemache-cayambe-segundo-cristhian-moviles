package com.example.examensclc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonBedegas = findViewById<Button>(R.id.btn_bodega)
        botonBedegas
            .setOnClickListener{
                irActividad(BodegaUI::class.java)

            }

        val botonProductos = findViewById<Button>(R.id.btn_producto)
        botonProductos
            .setOnClickListener{
                irActividad(ProductoUI::class.java)

            }

    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}