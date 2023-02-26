package com.example.videosfacebook

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),VideoFacebookAdapter.onTextInteractionsClickListener {
    var publicaciones= listOf(
        VideoFacebook("Javier Cruz","5 de marzo de 2022","Hola:)","25",
        "16 Comentarios","https://www.ritmourbano.com.mx/wp-content/uploads/2017/01/ZPU-Promo-2016-Light-1-620x350.jpg",
            "https://www.ritmourbano.com.mx/wp-content/uploads/2017/01/ZPU-Promo-2016-Light-7-620x413.jpg")
        ,
        VideoFacebook("Karla Ruiz","10 de abril de 2021","Gracias los quiero","532",
            "140 Comentarios","https://media1.popsugar-assets.com/files/thumbor/ba6DLGNmUTug9xJUvKYvlPdfwZQ/9x192:2244x2427/fit-in/500x500/filters:format_auto-!!-:strip_icc-!!-/2020/02/11/900/n/1922398/112085525e431065dba032.37867622_/i/Beyonc%C3%A9-Knowles.jpg",
            "https://media.vogue.mx/photos/5c06fdc37ce411f152ae901f/2:3/w_1600,c_limit/beyonce_8511.jpg")
        ,
        VideoFacebook("Falcon","19 de agosto de 2023","noooooooo","185",
            "10 Comentarios"
            ,"https://srv.latostadora.com/designall.dll/alcon--i:141385121191714138523;d:1211917;w:520;b:F3E8CA;m:1.jpg",
            "https://www.tuexpertoapps.com/wp-content/uploads/2022/01/8.jpg.webp")
        ,
        VideoFacebook("MF Doom","19 de mayo de 2010","Modern Day Mugging!!!","852",
            "590 Comentarios"
            ,"https://i0.wp.com/www.michigandaily.com/wp-content/uploads/2021/10/madison-grosvenor-mf-doom-arts.png?resize=1200%2C800&ssl=1",
            "https://images.squarespace-cdn.com/content/v1/5a5653aeb7411ce23017e460/664b484b-3c9e-4923-b3bf-c0bafabb6344/Did+Ever+Tell+You+podcast+artwork.jpg?format=750w")
        ,
        VideoFacebook("Javier Cruz","5 de marzo de 2022","Hola:)","25",
            "16 Comentarios","https://www.ritmourbano.com.mx/wp-content/uploads/2017/01/ZPU-Promo-2016-Light-1-620x350.jpg",
            "https://www.ritmourbano.com.mx/wp-content/uploads/2017/01/ZPU-Promo-2016-Light-7-620x413.jpg")
        ,
        VideoFacebook("Karla Ruiz","10 de abril de 2021","Gracias los quiero","532",
            "140 Comentarios","https://media1.popsugar-assets.com/files/thumbor/ba6DLGNmUTug9xJUvKYvlPdfwZQ/9x192:2244x2427/fit-in/500x500/filters:format_auto-!!-:strip_icc-!!-/2020/02/11/900/n/1922398/112085525e431065dba032.37867622_/i/Beyonc%C3%A9-Knowles.jpg",
            "https://media.vogue.mx/photos/5c06fdc37ce411f152ae901f/2:3/w_1600,c_limit/beyonce_8511.jpg")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()


    }

    fun initRecycler(){
        val listVide =findViewById<RecyclerView>(R.id.listVideosFacebook)
        listVide.layoutManager=LinearLayoutManager(this)
        val adapter = VideoFacebookAdapter(this,publicaciones,this)
        listVide.adapter=adapter
    }

    override fun onItemClickk(numeroInteraciones: String) {
        val intent = Intent(this,Interacciones::class.java)
        intent.putExtra("txtNumInteracciones",numeroInteraciones)
        startActivity(intent)
    }


}