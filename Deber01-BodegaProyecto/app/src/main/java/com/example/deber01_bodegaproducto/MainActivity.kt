package com.example.deber01_bodegaproducto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {


    var bodegas= ArrayList<Bodega>()
    var idItemSeleccionado=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BaseDeDatos.tablaBodega= BSqliteHelper(this)

        val btn_Crear=findViewById<Button>(R.id.btn_Crear)
        btn_Crear.setOnClickListener {
           irActividad(InsertBodega::class.java)
        }
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)

    }


    override fun onResume() {
        super.onResume()

        bodegas = BaseDeDatos.tablaBodega!!.listarBodegas()
        val listViewBodegas=findViewById<ListView>(R.id.lista_Bodegas)
        val adaptador=AdapterB(this,bodegas)
        listViewBodegas.adapter=adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listViewBodegas)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        view: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ){
        super.onCreateContextMenu(menu, view, menuInfo)
        //LLenamos las opciones del menu
        val inflater=menuInflater
        inflater.inflate(R.menu.menu_bodega, menu)
        //Obtener el id del ArrayListSeleccionada
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado=id
    }

    var bodegaSeleccionado= Bodega(0,"",0,"","",)

    var id=""
    var nombre=""
    var totalProductos=""
    var telefono=""
    var gerente=""
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.editar_Bodega->{


                val listView_bodega=findViewById<ListView>(R.id.lista_Bodegas)
                var itemselect= listView_bodega.getItemAtPosition(idItemSeleccionado)
                var objetoSeleccionado= itemselect as Bodega

                bodegaSeleccionado=objetoSeleccionado

                id=bodegaSeleccionado.id.toString()
                nombre= bodegaSeleccionado.nombre.toString()
                totalProductos= bodegaSeleccionado.totalProductos.toString()
                telefono= bodegaSeleccionado.telefono.toString()
                gerente= bodegaSeleccionado.gerente.toString()


                parametrosPaisSeleccionado(UpdateBodega::class.java)

                return true
            }
            R.id.eliminar_Bodega->{

                val listView_bodega=findViewById<ListView>(R.id.lista_Bodegas)
                var itemselect= listView_bodega.getItemAtPosition(idItemSeleccionado)
                var objetoSeleccionado= itemselect as Bodega
                bodegaSeleccionado=objetoSeleccionado
                val builder= AlertDialog.Builder(this)
                builder.setMessage("Estas seguro que desea eliminar la bodega?")
                builder.setCancelable(true)
                builder.setPositiveButton("Si"){ dialog,_->
                    BaseDeDatos.tablaBodega!!.eliminarBodega(objetoSeleccionado.id)
                    onResume()
                }
                builder.setNegativeButton("No"){ dialog,_->
                    dialog.dismiss()
                }
                val alert=builder.create()
                alert.show()
                return true
            }
            R.id.ver_Productos->{


                val listView_bodega=findViewById<ListView>(R.id.lista_Bodegas)
                var itemselect= listView_bodega.getItemAtPosition(idItemSeleccionado)
                var objetoSeleccionado= itemselect as Bodega
                bodegaSeleccionado=objetoSeleccionado
                id=bodegaSeleccionado.id.toString()
                nombre= bodegaSeleccionado.nombre.toString()
                parametroID(ProductoView::class.java)


                return true
            }
            else->super.onContextItemSelected(item)

        }
    }

    fun parametrosPaisSeleccionado(
        clase: Class<*>
    ){
        val intentBodegaSeleccionado=Intent(this, clase)
        intentBodegaSeleccionado.putExtra("id", id)
        intentBodegaSeleccionado.putExtra("nombre", nombre )
        intentBodegaSeleccionado.putExtra("totalProductos", totalProductos)
        intentBodegaSeleccionado.putExtra("telefono", telefono )
        intentBodegaSeleccionado.putExtra("gerente", gerente)

        startActivity(intentBodegaSeleccionado)
    }

    fun parametroID(
        clase: Class<*>
    ) {
        val intenProductosDeBodega=Intent(this,clase)
        intenProductosDeBodega.putExtra("id",id)
        intenProductosDeBodega.putExtra("nombre",nombre)
        startActivity(intenProductosDeBodega)
    }



}