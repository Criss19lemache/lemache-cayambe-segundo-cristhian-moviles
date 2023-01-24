package com.example.movcompsclc

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class BlistView : AppCompatActivity() {
    val arreglo:ArrayList<BEntrenador> = BBaseDatosMemoria.arregloBEntrenador
    var idItemSelecionado=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)


    val listView = findViewById<ListView>(R.id.lv_list_view)
    val adaptador=ArrayAdapter(
        this,
        android.R.layout.simple_expandable_list_item_1,
        arreglo
    )
        listView.adapter=adaptador
    adaptador.notifyDataSetChanged()



        val botonAndirListView=findViewById<Button>(
            R.id.btn_anadir_list_view)
        botonAndirListView
            .setOnClickListener{
                anadirEntrenador(adaptador)
            }

        registerForContextMenu(listView)

    }// on create end

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //llenamos las opciones del menu
        val inflater= menuInflater
        inflater.inflate(R.menu.menu,menu)
        //obtener el id del ArraylistSelecionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id=info.position
        idItemSelecionado=id

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                "${idItemSelecionado}"
                return true
            }
            R.id.mi_eliminar ->{
                abrirDialogo()
                "${idItemSelecionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{dialog,
                                            which->
                // Al aceptar eliminar el registro
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val opciones=resources.getStringArray(
            R.array.string_array_opciones_dialogo
        )

        val seleccionPrevia= booleanArrayOf(
            true,
            false,
            false
        )

        builder.setMultiChoiceItems(
            opciones,
            seleccionPrevia,
            {dialog,
            which,
            isChecked->
                "Dio clic en el item ${which}"
            }
        )
        val dialogo=builder.create()
        dialogo.show()

    }




    fun anadirEntrenador(adaptador:ArrayAdapter<BEntrenador>){
        arreglo.add(
            BEntrenador(
                1,"Ejemplo","a@a.com"
            )
        )
        adaptador.notifyDataSetChanged()
    }
}