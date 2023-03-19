package com.example.examen02sclc


import com.google.firebase.firestore.Query
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    var query:Query?=null
    val arreglo: ArrayList<Bodega> = arrayListOf()
    var bodegaSeleccionado= Bodega("","","","")

    var id=""
    var nombre=""
    var totalProductos=""
    var telefono=""
    var gerente=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView=findViewById<ListView>(R.id.lista_Bodegas)
        val adaptador: ArrayAdapter<Bodega> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()
        consultarConOrderBy(adaptador)

        val btn_Crear=findViewById<Button>(R.id.btn_Crear)
        btn_Crear.setOnClickListener {
            irActividad(InsertBodega::class.java)
        }

        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "Manten presionado en algún item para ver sus opciones", Toast.LENGTH_SHORT).show()

        }

        // Agrega un listener de clics largos al ListView para mostrar el menú contextual
        listView.setOnItemLongClickListener { parent, view, position, id ->
            val popupMenu = PopupMenu(this, view)
            popupMenu.menuInflater.inflate(R.menu.menu_bodega, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.editar_Bodega -> {

                        var itemselect= listView.getItemAtPosition(position)

                        var objetoSeleccionado= itemselect as Bodega
                        bodegaSeleccionado=objetoSeleccionado
                        nombre= bodegaSeleccionado.nombre.toString()
                        totalProductos= bodegaSeleccionado.totalProductos.toString()
                        telefono= bodegaSeleccionado.telefono.toString()
                        gerente= bodegaSeleccionado.gerente.toString()

                        parametrosBodegaSeleccionado(UpdateBodega::class.java)
                        true
                    }
                    R.id.eliminar_Bodega-> {

                        val db = FirebaseFirestore.getInstance()
                        val usuariosRef = db.collection("bodega")

                        var itemselect= listView.getItemAtPosition(position)
                        var objetoSeleccionado= itemselect as Bodega
                        bodegaSeleccionado=objetoSeleccionado

                        usuariosRef.whereEqualTo("nombre", bodegaSeleccionado.nombre.toString())
                            .get()
                            .addOnSuccessListener { querySnapshot ->
                                if (!querySnapshot.isEmpty) {
                                    val documentId = querySnapshot.documents[0].id

                                    //Eliminar
                                    val builder= AlertDialog.Builder(this)
                                    builder.setMessage("Estas seguro que desea eliminar la bodega?")
                                    builder.setCancelable(true)
                                    builder.setPositiveButton("Si"){ dialog,_->

                                    // Eliminacion definitiva
                                        val documentReference = db.collection("bodega").document(documentId)
                                        documentReference.delete()
                                            .addOnSuccessListener {
                                                Toast.makeText(this, "Bodega Eliminada", Toast.LENGTH_SHORT).show()
                                                consultarConOrderBy(adaptador)
                                            }
                                            .addOnFailureListener { e ->
                                                Toast.makeText(this, "Error al eliminar la bodega"+e, Toast.LENGTH_SHORT).show()
                                            }

                                    }
                                    builder.setNegativeButton("No"){ dialog,_->
                                        dialog.dismiss()
                                    }
                                    val alert=builder.create()
                                    alert.show()
                                }
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Error al obtener el documento", Toast.LENGTH_SHORT).show()
                            }
                        true
                    }
                    R.id.ver_Productos -> {


                        // obtener idDocumento del producto


                        val db = FirebaseFirestore.getInstance()
                        val usuariosRef = db.collection("bodega")

                        usuariosRef.whereEqualTo("nombre", bodegaSeleccionado.nombre.toString())
                            .get()
                            .addOnSuccessListener { querySnapshot ->
                                if (!querySnapshot.isEmpty) {
                                    val documentId = querySnapshot.documents[0].id
                                    val documentReference = db.collection("bodega").document(documentId)

                                    documentReference.get()
                                        .addOnSuccessListener { documentSnapshot ->
                                            if (documentSnapshot.exists()) {
                                                val nombreBodegaSelecionadas = documentSnapshot.getString("nombre")

                                                val intenProductosDeBodega=Intent(this,ProductoView::class.java)
                                                intenProductosDeBodega.putExtra("id",documentId.toString())
                                                intenProductosDeBodega.putExtra("nombre_bodega",nombreBodegaSelecionadas.toString())
                                                startActivity(intenProductosDeBodega)
                                            }
                                        }

                                }
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Error al obtener el documento", Toast.LENGTH_SHORT).show()
                            }





                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
            true
        }




    }// end onCreate



    fun parametrosBodegaSeleccionado(
        clase: Class<*>
    ){
        val intentBodegaSeleccionado=Intent(this, clase)
        intentBodegaSeleccionado.putExtra("nombre", nombre )
        intentBodegaSeleccionado.putExtra("totalProductos", totalProductos)
        intentBodegaSeleccionado.putExtra("telefono", telefono )
        intentBodegaSeleccionado.putExtra("gerente", gerente)

        startActivity(intentBodegaSeleccionado)
    }





    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)

    }


    fun consultarConOrderBy(adaptador: ArrayAdapter<Bodega>){
        val db=Firebase.firestore
        val bodegasRefUnico=db
            .collection("bodega")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        bodegasRefUnico
            .orderBy("nombre",Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                for(bodega in it){
                    bodega.id
                    anadirArregloBodega(arreglo,bodega,adaptador)
                }
            }
    }

    fun anadirArregloBodega(
        arregloNuevo:ArrayList<Bodega>,
        bodega: QueryDocumentSnapshot,
        adaptador:ArrayAdapter<Bodega>
    ){


        bodega.id
        val nuevaBodega=Bodega(
            bodega.data.get("nombre") as String?,
            bodega.data.get("totalProductos") as String?,
            bodega.data.get("telefono") as String?,
            bodega.data.get("gerente") as String
        )

        arregloNuevo.add(nuevaBodega)
        adaptador.notifyDataSetChanged()
    }

    fun limpiarArreglo(){arreglo.clear()}


}