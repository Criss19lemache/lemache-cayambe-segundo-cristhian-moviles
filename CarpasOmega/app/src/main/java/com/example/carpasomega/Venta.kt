package com.example.carpasomega

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Venta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venta)
        var nombreApellido=""
        val bundle=intent.extras
        nombreApellido=bundle?.getString("nombreApellido").toString()

        val txtNumProductos=findViewById<TextView>(R.id.txtNumProductos)

        val txtNumeroProdcutosNumero=txtNumProductos.text.toString().toInt()
        val txtTotalApagar=findViewById<TextView>(R.id.txtValorTotal)
        val txtTotalApagarNumero=txtTotalApagar.text.toString().toInt()

        val nombreUsuario=findViewById<TextView>(R.id.txtUsuarioLog)
        nombreUsuario.text=nombreApellido

        val btnMas=findViewById<Button>(R.id.btnMas)
            btnMas.setOnClickListener {

                txtNumProductos.text=(txtNumeroProdcutosNumero+1).toString()
                txtTotalApagar.text=(txtTotalApagarNumero*txtNumProductos.text.toString().toInt()).toString()
            }
        val btnMenos=findViewById<Button>(R.id.btnMenos)
            btnMenos.setOnClickListener {
                val txtNumProductos2=findViewById<TextView>(R.id.txtNumProductos)
                val txtNumeroProdcutosNumero2=txtNumProductos.text.toString().toInt()
                if (txtNumeroProdcutosNumero2>0){

                    txtNumProductos2.text=(txtNumeroProdcutosNumero2-1).toString()
                    txtTotalApagar.text=(txtTotalApagarNumero*txtNumProductos2.text.toString().toInt()).toString()
                }else{

                    txtNumProductos.text="0"
                }

            }


    }
}