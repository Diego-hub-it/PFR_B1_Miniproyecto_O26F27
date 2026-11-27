
import scala.util.{Try, Success, Failure}

object Main {

  // =====================================================
  // 1. FUNCION DE ORDEN SUPERIOR
  // =====================================================

  def procesarMensaje(
                       mensaje: String,
                       clave: Int,
                       funcionCifrado: (Char, Int) => Char
                     ): Try[String] = {

    Try {
      // Validacion del mensaje
      require(mensaje != null, "El mensaje no puede ser nulo.")
      require(mensaje.nonEmpty, "El mensaje no puede estar vacio.")

      // Validacion de la funcion
      require(
        funcionCifrado != null,
        "La funcion de cifrado no puede ser nula."
      )

      // Aplicacion de la transformacion mediante map
      mensaje.map(caracter => funcionCifrado(caracter, clave))
    }
  }


  // =====================================================
  // 2. CIFRADO CESAR
  // =====================================================

  def cifradoCesar(caracter: Char, clave: Int): Char = {

    require(
      clave >= -25 && clave <= 25,
      "La clave Cesar debe estar entre -25 y 25."
    )

    require(
      caracter >= 'A' && caracter <= 'Z',
      s"Caracter no soportado por Cesar: '$caracter'."
    )

    val posicion = caracter - 'A'

    val nuevaPosicion = Math.floorMod(posicion + clave, 26)

    (nuevaPosicion + 'A').toChar
  }


  // =====================================================
  // 3. CIFRADO XOR
  // =====================================================

  def cifradoXOR(caracter: Char, clave: Int): Char = {

    require(
      clave >= 0 && clave <= 127,
      "La clave XOR debe estar entre 0 y 127."
    )

    require(
      caracter >= 32 && caracter <= 126,
      s"Caracter ASCII no soportado: '$caracter'."
    )

    // Operacion XOR a nivel de bits
    val resultado = (caracter.toInt ^ clave).toChar

    require(
      resultado >= 32 && resultado <= 126,
      "El resultado XOR genera un caracter no soportado."
    )

    resultado
  }


  // =====================================================
  // 4. FUNCION PARA MOSTRAR RESULTADOS
  // =====================================================

  def mostrarResultado(descripcion: String, resultado: Try[String]): Unit = {

    resultado match {

      case Success(mensaje) =>
        println(s"$descripcion: $mensaje")

      case Failure(error) =>
        println(s"$descripcion: ERROR - ${error.getMessage}")
    }
  }


  // =====================================================
  // 5. PRUEBAS DEL CIFRADO CESAR
  // =====================================================

  def probarCesar(): Unit = {

    println("\n========== CIFRADO CESAR ==========")

    val mensaje = "FUNCIONAL"
    val clave = 3

    // Cifrar
    val cifrado = procesarMensaje(mensaje, clave, cifradoCesar)

    mostrarResultado("Mensaje original", Success(mensaje))
    mostrarResultado("Mensaje cifrado", cifrado)

    // Descifrar utilizando la clave negativa
    cifrado.foreach { resultado =>

      val descifrado = procesarMensaje(
        resultado,
        -clave,
        cifradoCesar
      )

      mostrarResultado("Mensaje descifrado", descifrado)
    }
  }


  // =====================================================
  // 6. PRUEBAS DEL CIFRADO XOR
  // =====================================================

  def probarXOR(): Unit = {

    println("\n========== CIFRADO XOR ==========")

    val mensaje = "scala"
    val clave = 5

    // Cifrar
    val cifrado = procesarMensaje(mensaje, clave, cifradoXOR)

    mostrarResultado("Mensaje original", Success(mensaje))
    mostrarResultado("Mensaje cifrado", cifrado)

    // Descifrar aplicando nuevamente XOR con la misma clave
    cifrado.foreach { resultado =>

      val descifrado = procesarMensaje(
        resultado,
        clave,
        cifradoXOR
      )

      mostrarResultado("Mensaje descifrado", descifrado)
    }
  }


  // =====================================================
  // 7. PRUEBAS DE MANEJO DE ERRORES
  // =====================================================

  def probarErrores(): Unit = {

    println("\n========== PRUEBAS DE ERRORES ==========")

    // Mensaje vacio
    mostrarResultado(
      "Mensaje vacio",
      procesarMensaje("", 3, cifradoCesar)
    )

    // Mensaje nulo
    mostrarResultado(
      "Mensaje nulo",
      procesarMensaje(null, 3, cifradoCesar)
    )

    // Clave Cesar excesivamente grande
    mostrarResultado(
      "Clave Cesar invalida",
      procesarMensaje("FUNCIONAL", 1000, cifradoCesar)
    )

    // Caracter especial no soportado por Cesar
    mostrarResultado(
      "Caracter especial en Cesar",
      procesarMensaje("HOLA!", 3, cifradoCesar)
    )

    // Clave XOR negativa
    mostrarResultado(
      "Clave XOR invalida",
      procesarMensaje("scala", -5, cifradoXOR)
    )

    // Clave XOR excesivamente grande
    mostrarResultado(
      "Clave XOR excesiva",
      procesarMensaje("scala", 200, cifradoXOR)
    )

    // Caracter ASCII no soportado
    mostrarResultado(
      "Caracter no ASCII",
      procesarMensaje("Holañ", 5, cifradoXOR)
    )
  }


  // =====================================================
  // 8. PROGRAMA PRINCIPAL
  // =====================================================

  def main(args: Array[String]): Unit = {

    probarCesar()

    probarXOR()

    probarErrores()

  }
}


