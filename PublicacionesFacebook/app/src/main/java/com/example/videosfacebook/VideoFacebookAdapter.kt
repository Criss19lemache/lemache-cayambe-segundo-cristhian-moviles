package com.example.videosfacebook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class VideoFacebookAdapter(val context: Context,
                           val videoFacebook: List<VideoFacebook>,
                           val itemClickListener: onTextInteractionsClickListener):
    RecyclerView.Adapter<VideoFacebookAdapter.VideoHolder>() {

    interface onTextInteractionsClickListener{
        fun onItemClickk(numeroInteraciones: String)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return VideoHolder(layoutInflater.inflate(R.layout.publicaciones_facebook,parent,false))
    }

    override fun getItemCount(): Int {
        return videoFacebook.size
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.render(videoFacebook[position])

        //eventos
    }


   inner class VideoHolder(val view:View):RecyclerView.ViewHolder(view){
        private var imagenPerfil=view.findViewById<CircleImageView>(R.id.profile_image)
        private var nombrePerfil=view.findViewById<TextView>(R.id.txtNombrePerfil)
        private var fechaPublicacion=view.findViewById<TextView>(R.id.txtFechaPublicacion)
        private var encabezadoPublicacion=view.findViewById<TextView>(R.id.txtComentario)
        private var imagenPublicacion=view.findViewById<ImageView>(R.id.imagePublicacion)
        private var numInteracciones=view.findViewById<TextView>(R.id.txtInteracciones)
        private var numComentarios=view.findViewById<TextView>(R.id.txtComentarios)
        fun render(videoFacebook: VideoFacebook){

            itemView.findViewById<TextView>(R.id.txtComentarios).setOnClickListener { itemClickListener.onItemClickk(videoFacebook.numeroInteraciones) }


            //Definimos nuevos parametros para la publicacion
            Picasso.get().load(videoFacebook.imagenPerfil).into(imagenPerfil)
            nombrePerfil.text=videoFacebook.nombrePerfil
            fechaPublicacion.text=videoFacebook.fechaPublicacion
            encabezadoPublicacion.text=videoFacebook.encabezado
            Picasso.get().load(videoFacebook.imagenPublicacion).resize(600,620).into(imagenPublicacion)
            numInteracciones.text=videoFacebook.numeroInteraciones
            numComentarios.text=videoFacebook.numeroComentarios




                }


    }

}