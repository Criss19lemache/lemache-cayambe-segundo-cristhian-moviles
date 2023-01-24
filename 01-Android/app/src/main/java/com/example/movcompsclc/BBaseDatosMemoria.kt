package com.example.movcompsclc

class BBaseDatosMemoria {

    companion object{
        val arregloBEntrenador= arrayListOf<BEntrenador>()

        init {

            arregloBEntrenador
                .add(
                    BEntrenador(1,"Andrian","a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2,"Vicente","b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3,"Carolina","c@c.com")
                )
        }
    }
}