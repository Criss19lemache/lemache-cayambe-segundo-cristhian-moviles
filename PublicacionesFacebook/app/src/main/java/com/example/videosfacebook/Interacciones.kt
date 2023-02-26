package com.example.videosfacebook
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Interacciones : AppCompatActivity() {

    var comentarios= listOf(
        ComentariosFacebook("https://www.ritmourbano.com.mx/wp-content/uploads/2017/01/ZPU-Promo-2016-Light-1-620x350.jpg",
            "Javier Cruz","Buenas tardes","20 min","25")
    ,
        ComentariosFacebook("https://i0.wp.com/www.michigandaily.com/wp-content/uploads/2021/10/madison-grosvenor-mf-doom-arts.png?resize=1200%2C800&ssl=1",
            "MF DOOM","Saludos amigo,Suerte","8 h","95")
        ,
        ComentariosFacebook("https://media1.popsugar-assets.com/files/thumbor/ba6DLGNmUTug9xJUvKYvlPdfwZQ/9x192:2244x2427/fit-in/500x500/filters:format_auto-!!-:strip_icc-!!-/2020/02/11/900/n/1922398/112085525e431065dba032.37867622_/i/Beyonc%C3%A9-Knowles.jpg",
            "Melanie Meza","Buenas nochess tkm","10 h","53")
        ,
        ComentariosFacebook("https://media1.popsugar-assets.com/files/thumbor/ba6DLGNmUTug9xJUvKYvlPdfwZQ/9x192:2244x2427/fit-in/500x500/filters:format_auto-!!-:strip_icc-!!-/2020/02/11/900/n/1922398/112085525e431065dba032.37867622_/i/Beyonc%C3%A9-Knowles.jpg",
            "Mirian ZKR","Genial amigo sigue asi","16 h","46")
        ,
        ComentariosFacebook("https://www.ritmourbano.com.mx/wp-content/uploads/2017/01/ZPU-Promo-2016-Light-1-620x350.jpg",
            "Raul Ruiz","Sigue asi","19 h","43")
        ,
        ComentariosFacebook("https://www.ritmourbano.com.mx/wp-content/uploads/2017/01/ZPU-Promo-2016-Light-1-620x350.jpg",
            "Javier Cruz","Buenas tardes","20 h","19")
        ,
        ComentariosFacebook("https://i0.wp.com/www.michigandaily.com/wp-content/uploads/2021/10/madison-grosvenor-mf-doom-arts.png?resize=1200%2C800&ssl=1",
            "MF DOOM","Saludos amigo,Suerte","23 h","87")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interacciones)
        val txtInterations=findViewById<TextView>(R.id.txtNumLikesyMas)
        if (intent.extras!=null){
            txtInterations.text="David Cruz y "+intent.getStringExtra("txtNumInteracciones")+" personas mas >"
        }
         initRecycler()
    }


    fun initRecycler(){
        val listComentario =findViewById<RecyclerView>(R.id.listComentarios)
        listComentario.layoutManager= LinearLayoutManager(this)
        val adapter = ComentariosAdapter(comentarios)
        listComentario.adapter=adapter
    }
}