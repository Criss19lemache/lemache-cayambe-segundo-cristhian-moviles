import java.util.*;
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    println("Hello world!")

    // Tipos de variables
    // inmutables (no re asignar)
    val inmutable: String = "Cristhian"
    //inmutable="Lemache"

    //Mutables (re asignar) =
    var mutable:String="Criss"
    mutable="Lemachee"

    // val > vars

    // duck typing
    val ejemploVariable= "Ejemplo"
    ejemploVariable.trim()
    val edadEjemplo: Int = 12

    // varibles primitivas
    val nombreEstudiante:String ="Cristhian Lemache"
    val sueldo: Double =1.2
    val estadoCivil: Char ='S'
    val mayorEdad:Boolean = true

    //clases
    val fechaNacimiento: Date =Date()
    if(true){

    }else{

    }

    //switch no existe
    val estadoCivilWhen='S'
    when(estadoCivilWhen){
        ('S')->{
            println("soltero")
        }
        'C'-> println("Casado")
        else -> println("Desconocido")
    }

    val coqueto =if(estadoCivilWhen=='S')"Si" else "No"

    val sumaUno=Suma(1,2)
    val sumaDos=Suma(1,null)
    val sumaTres=Suma(null,2)
    val sumaCuatro=Suma(null,null)

    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()

    println(Suma.historialSumas)

     // arreglos
    // tipos de arreglos
    //Arreglo estatico
    val arreagloEstatico:Array<Int> = arrayOf(1,2,3)
    println(arreagloEstatico)

    // arreglo dinamico
    val arregloDinamico: ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)




    //operadores -- Sirven para los arreglos estaticos y dinamicos
    // for each -- Unit
    // Iterar un arreglo
    val respuestaForEach:Unit=arregloDinamico
        .forEach{
            valorActual:Int->
            println("Valor actual:${valorActual}")
        }
    arregloDinamico
        .forEachIndexed{indice:Int,valorActual:Int->
            println("Valor ${valorActual} Indice: ${indice}")
        }
        println(respuestaForEach)


        //Map -muta el arreglo (cambia el arreglo)
    // 1) Enviamos el nuevo valor de la iteracion
    // 2) Nos devuelve un nuevo arreglo con los valores modificadores

    val respuestaMap: List<Double> = arregloDinamico
        .map{valorActual:Int->
            return@map valorActual.toDouble()+100.00
        }
        println(respuestaMap)

    val respuestaMapDos=arregloDinamico.map{it+15}

    //  .map{ valorActual:Int ->
    //   return@map ValorActual +15}



    //Filter -- filtrar el arreglo
    // 1) Devolver una expresion (TRUE O FALSE)
    // 2) Nuevo arreglo filtrado

    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual:Int->
            val mayoresACinco: Boolean =valorActual > 5
            return@filter mayoresACinco
        }

    val respuestaFilterDos = arregloDinamico.filter { it<=5 }
    println(respuestaFilter)
    println(respuestaFilterDos)


    //  OR AND
    // OR-- ANY (Alguno cumple?)
    // AND -- ALL(Todos cumplen?)

    val respuestaAny:Boolean= arregloDinamico
        .any{ valorActual : Int->
            return@any (valorActual>5)
        }

    println(respuestaAny)//true

    val respuestaAll:Boolean= arregloDinamico
        .all{ valorActual : Int->
            return@all (valorActual>5)
        }
    println(respuestaAll) //false



    //  REDUCE
    // valor acumulacion =0 (Siempre 0 en lenguaje de Kotlin)
    // [1,2,3,4,5] -- -- sumeme todos los valores del arreglo
    // valorIteracion1= valorEmpieza +1 =0 + 1 =1 Iteracion 1
    // valorIteracion2= valorIteracion1 +2 =1 + 2 =3 Iteracion 2
    // valorIteracion3= valorIteracion2 +3 =3 + 3 =6 Iteracion 3
    // valorIteracion4= valorIteracion3 +4 =6 + 4 =10 Iteracion 4
    // valorIteracion5= valorIteracion4 +5 =10 + 5 =15 Iteracion 6


    val respuestaReduce:Int = arregloDinamico
        .reduce{// acumulado =0 -- siempre empieza en 0
            acumulado: Int, valorActual:Int->
            return@reduce (acumulado + valorActual)// logica negocio
        }

        println(respuestaReduce)


    fun imprimirNombre(nombre:String): Unit{
        println("Nombre :${nombre}")

    }
    fun calcularSueldo(
        sueldo:Double,
        tasa:Double=12.00,
        bonoEspecial:Double?=null
    ): Double{
        //String -> String?
        //Int->Int
        // Date ->Date
        if(bonoEspecial!=null){
            return sueldo*tasa*bonoEspecial
        }


        return sueldo*tasa
    }



}// fin class main




    abstract class  NumerosJava{
        protected val numeroUno: Int
        private  val numeroDos: Int

        constructor(uno:Int, dos:Int){
            this.numeroUno=uno;
            this.numeroDos=dos;
            println("Iniciando")
        }
    }

    abstract class Numeros(// constructor primario
        //uno:Int, Parametro
        // public var uno: Int, // propiedad de la clase publica
        protected val numeroUno:Int, // Propiedad
        protected  val numeroDos:Int // Propiedad
    ){

        init {// Bloque codigo constructor primario
            // this.NumeroDos // "this" opcional
            // this.numeroUno
            numeroUno
            numeroDos
            println("Iniciando")
        }
    }

    class Suma( // constructor Primario
        uno:Int, // Parametro
        dos: Int // Parametro
    ):Numeros ( // Heredamo de la clase Numeros
        // super constructor numeros
        uno,
        dos,
    ){
        init { // Bloque constructor
            this.numeroUno
            this.numeroDos
        }
        constructor(// Segundo Constructor
            uno:Int?,
            dos:Int
        ): this(
            if(uno==null) 0 else uno, dos
        ){}
        constructor(// Tercer Constructor
            uno:Int,
            dos:Int?
        ): this(
            uno,
            if(dos==null) 0 else dos
        )
        constructor(// Cuarto Constructor
            uno:Int?,
            dos:Int?
        ): this(
            if(uno==null) 0 else uno,
            if(dos==null) 0 else dos,
        ){}

        fun sumar(): Int {
            val total= numeroUno+numeroDos
            agregarHistorial(total)
            return total
        }

        companion object {
            val pi=3.14// suma.pi -- 3.14
            val historialSumas= arrayListOf<Int>()
            fun  agregarHistorial(valorNuevaSuma:Int){
                historialSumas.add(valorNuevaSuma)
            }
        }
    }




