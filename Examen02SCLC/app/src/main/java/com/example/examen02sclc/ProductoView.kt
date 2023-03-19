package com.example.examen02sclc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductoView : AppCompatActivity() {

    var idItemSeleccionado =0
    val arreglo: ArrayList<Producto> = arrayListOf()

    var productoSeleccionado= Producto("","","","","")

    var id=""
    var nombre=""
    var marca=""
    var precio=""
    var stock=""
    var nombre_bodega=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto_view)

        val bundle=intent.extras
        id= bundle?.getString("id").toString()
        nombre_bodega=bundle?.getString("nombre_bodega").toString()

        //damos nombre al titulo de la bodega
        val nombreBodega=findViewById<TextView>(R.id.txtTitulo)
            nombreBodega.text="Bodega: "+nombre_bodega


        val listView=findViewById<ListView>(R.id.lista_Bodegas)
        val adaptador: ArrayAdapter<Producto> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()
        consultarConOrderBy(adaptador,nombre_bodega)

        val btn_Crear=findViewById<Button>(R.id.btn_Crear)
        btn_Crear.setOnClickListener {

            val intenProductosInsert=Intent(this,InsertProducto::class.java)
            intenProductosInsert.putExtra("nombre_bodega",nombre_bodega)
            startActivity(intenProductosInsert)
        }

        //Opciones de documento
        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "Manten presionado en algún item para ver sus opciones", Toast.LENGTH_SHORT).show()

        }

        // Agrega un listener de clics largos al ListView para mostrar el menú contextual
        listView.setOnItemLongClickListener { parent, view, position, id ->
            val popupMenu = PopupMenu(this, view)
            popupMenu.menuInflater.inflate(R.menu.menu_producto, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.editar_producto -> {

                        var itemselect= listView.getItemAtPosition(position)

                        var objetoSeleccionado= itemselect as Producto
                        productoSeleccionado=objetoSeleccionado
                        nombre= productoSeleccionado.nombre.toString()
                        marca= productoSeleccionado.marca.toString()
                        precio= productoSeleccionado.precio.toString()
                        stock= productoSeleccionado.stock.toString()
                        nombre_bodega=productoSeleccionado.nombre_bodega.toString()

                        parametrosProductoSeleccionado(UpdateProducto::class.java)
                        true
                    }
                    R.id.eliminar_producto-> {

                        val db = FirebaseFirestore.getInstance()
                        val usuariosRef = db.collection("producto")

                        var itemselect= listView.getItemAtPosition(position)
                        var objetoSeleccionado= itemselect as Producto
                        productoSeleccionado=objetoSeleccionado

                        usuariosRef.whereEqualTo("nombre", productoSeleccionado.nombre.toString())
                            .get()
                            .addOnSuccessListener { querySnapshot ->
                                if (!querySnapshot.isEmpty) {
                                    val documentId = querySnapshot.documents[0].id

                                    //Eliminar
                                    val builder= AlertDialog.Builder(this)
                                    builder.setMessage("Estas seguro que desea eliminar el producto ?")
                                    builder.setCancelable(true)
                                    builder.setPositiveButton("Si"){ dialog,_->

                                        // Eliminacion definitiva
                                        val documentReference = db.collection("producto").document(documentId)
                                        documentReference.delete()
                                            .addOnSuccessListener {
                                                Toast.makeText(this, "Producto Eliminada", Toast.LENGTH_SHORT).show()
                                                consultarConOrderBy(adaptador,nombre_bodega)
                                            }
                                            .addOnFailureListener { e ->
                                                Toast.makeText(this, "Error al eliminar la producto"+e, Toast.LENGTH_SHORT).show()
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
                    else -> false
                }
            }
            popupMenu.show()
            true
        }


    }// end oncreate


    fun parametrosProductoSeleccionado(
        clase: Class<*>
    ){
        val intentBodegaSeleccionado=Intent(this, clase)
        intentBodegaSeleccionado.putExtra("nombre", nombre )
        intentBodegaSeleccionado.putExtra("marca", marca)
        intentBodegaSeleccionado.putExtra("precio", precio )
        intentBodegaSeleccionado.putExtra("stock", stock)
        intentBodegaSeleccionado.putExtra("nombre_bodega", nombre_bodega)
        startActivity(intentBodegaSeleccionado)
    }
    fun consultarConOrderBy(adaptador: ArrayAdapter<Producto>, nombreBodega: String){
        val db= Firebase.firestore
        val productossRefUnico=db
            .collection("producto")
        limpiarArreglo()

        adaptador.notifyDataSetChanged()
        productossRefUnico
            .orderBy("nombre", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                for(producto in it){
                    producto.id
                    anadirArregloProducto(arreglo,producto,adaptador,nombreBodega)

                }
            }

    }

    fun limpiarArreglo(){arreglo.clear()}


    fun anadirArregloProducto(
        arregloNuevo: ArrayList<Producto>, producto: QueryDocumentSnapshot,
        adaptador: ArrayAdapter<Producto>,
        nombreBodega: String
    ){

        producto.id
        val nuevaProducto=Producto(
            producto.data.get("nombre") as String,
            producto.data.get("marca") as String,
            producto.data.get("precio") as String,
            producto.data.get("stock") as String,
            producto.data.get("nombre_bodega") as String
        )
        if (nuevaProducto.nombre_bodega==nombreBodega){
            arregloNuevo.add(nuevaProducto)
            adaptador.notifyDataSetChanged()
        }


    }



}