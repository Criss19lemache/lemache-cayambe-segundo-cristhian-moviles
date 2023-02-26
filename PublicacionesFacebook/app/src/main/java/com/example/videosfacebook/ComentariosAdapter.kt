package com.example.videosfacebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ComentariosAdapter(val comentarioFacebook: List<ComentariosFacebook>):
    RecyclerView.Adapter<ComentariosAdapter.ComentarioHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentarioHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return ComentarioHolder(layoutInflater.inflate(R.layout.comentarios_facebook,parent,false))
    }

    override fun getItemCount(): Int {
        return comentarioFacebook.size
    }

    override fun onBindViewHolder(holder: ComentarioHolder, position: Int) {
        holder.render(comentarioFacebook[position])
    }

    inner  class ComentarioHolder(val view: View):RecyclerView.ViewHolder(view) {

        private var imagenPerfil=view.findViewById<CircleImageView>(R.id.profile_image)
        private var nombrePerfil=view.findViewById<TextView>(R.id.txtNombrePerfil)
        private var comentario=view.findViewById<TextView>(R.id.txtComentario)
        private var horaPublicacion=view.findViewById<TextView>(R.id.txtHora)
        private var numInteraccionesComentarios=view.findViewById<TextView>(R.id.txtNumInteracionesComentarios)

        fun render(comentarioFacebook: ComentariosFacebook){

            //Definimos nuevos parametros para la publicacion
            Picasso.get().load(comentarioFacebook.imagenPerfil).into(imagenPerfil)
            nombrePerfil.text=comentarioFacebook.nombrePerfil
            comentario.text=comentarioFacebook.comentario
            horaPublicacion.text=comentarioFacebook.hora
            numInteraccionesComentarios.text=comentarioFacebook.numeroInteracionesComentarios

        }


    }
}