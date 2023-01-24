package com.example.movcompsclc

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts


class MainActivity : AppCompatActivity() {

    val contenidoIntentExplicito=
         registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
             if(result.resultCode== Activity.RESULT_OK){
                 if (result.data!=null){
                     val data=result.data
                     Log.i("intent-epn","${data?.getStringExtra("nombreModificado")}")
                 }
             }
         }


    val contenidoIntentImplicito=
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            if(result.resultCode== Activity.RESULT_OK){
                if (result.data!=null){
                    if (result.data!!.data!=null){
                        val uri: Uri =result.data!!.data!!
                        val cursor = contentResolver.query(
                            uri,
                            null,
                            null,
                            null,
                            null,
                            null
                        )
                        cursor?.moveToFirst()
                        val indiceTelefono=cursor?.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val telefono = cursor?.getString(
                            indiceTelefono!!
                        )
                        cursor?.close()
                        Log.i("intent-epn","Telefono${telefono}")
                    }
                }
            }
        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener {
                irActividad(ACicloVida::class.java)
            }

        val botonListView = findViewById<Button>(R.id.btn_list_view)
        botonListView
            .setOnClickListener {
                irActividad(BlistView::class.java)
            }


        val botonIntentImplicito =findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito
            .setOnClickListener{
                val intentConRespuesta=Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                contenidoIntentImplicito.launch(intentConRespuesta)
            }

        val botonIntent= findViewById<Button>(R.id.btn_ir_intent_explicito)
        botonIntent
            .setOnClickListener{
                abrirActividadConParametros(CIntentExplicitoParametros::class.java)
            }

        val botonBDD = findViewById<Button>(R.id.btn_crud)
        botonBDD
            .setOnClickListener{
                irActividad(ECrudEntrenador::class.java)
            }
    }



    fun abrirActividadConParametros(
        clase:Class<*>,
    ){
        val intentExplicito=Intent(this,clase)
        // Enviar parametros (solamente variables primitivas)
        intentExplicito.putExtra("nombre","Cristhian")
        intentExplicito.putExtra("apellido","Lemache")
        intentExplicito.putExtra("edad",32)
        intentExplicito.putExtra("entrenador",
            BEntrenador(
                1,
                "ash",
                "pueblo paleta"
            )
        )
        contenidoIntentExplicito.launch(intentExplicito)
    }


    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}