  <img src="./img/java.png" align="left" width="22%">

# Práctica 3 Contenedores y excepciones

## Repositorio PAI-practica3
Los objetivos de esta práctica son:
* Familiarizarse con los contenedores mas habituales en Java, as ´ ´ı como con
iteradores y excepciones.

* Fijar de forma consistente y definitiva el convenio respecto a la forma de codificacion´
que ha de usarse para los programas que se desarrollan en el ambito de la ´
asignatura.


* author  Carlos Troyano Carmona
* version 1.1
* since   2015-27-16
* [Página del repositorio](http://ctc87.github.io/PAI-practica3/doc/index.html)

### Apartado1
Es un paquete que contiene dos clases.

#### Serpientes:

* Serpientes es una clase que representa a una serpiente.

* [Cabeceras  documentadas](https://github.com/ctc87/PAI-practica3/blob/gh-pages/src/Apartado1/Serpientes.java)

#### App:

* App es la clase que ejecuta el programa principal de la clase Serpientes del paquete Apartado1.  La implementación es con un contenedor de tipo ArrayList y recorrido con un for.

* [Cabeceras  documentadas](https://github.com/ctc87/PAI-practica3/blob/gh-pages/src/Apartado1/App.java)

### Apartado2
  Es un paquete que contiene una clase.

#### App:

* App es la clase que ejecuta el programa principal de la clase Serpientes del paquete Apartado1.  La implementación es con un contenedor de tipo ArrayList y un iterador de tipo Iterator para recorrerlo.

* [Cabeceras  documentadas](https://github.com/ctc87/PAI-practica3/blob/gh-pages/src/Apartado2/App.java)

### Apartado3
  Es un paquete que contiene una clase.

#### App:

* App es la clase que ejecuta el programa principal de la clase Serpientes del paquete Apartado1. Tomaa la clase Serpiente y la pone en un Mapa, asociando el nombre de la Serpiente como una String (la clave) con cada Serpiente (el valor) que se introduzca en la tabla. Conseguir un iterador para el keySet() y utilizarlo para recorrer el Mapa, buscando la Serpiente para cada clave, imprimiendo la clave y ordenando a la Serpiente reptar().

* [Cabeceras  documentadas](https://github.com/ctc87/PAI-practica3/blob/gh-pages/src/Apartado3/App.java)

### Apartado4
  Es un paquete que contiene dos clases.

  * El Paquete trata básicamente del manejo de excepciones mediante bloques try catch o con la palabra reservada throw.

#### NumOcurrencias:
* NumOcurrencias es una clase que cuenta el número de ocurrencias de cada palabra dentro de un fichero de texto. El nombre del fichero de texto deberá ser pasado como parámetro en la línea de comandos.

* Las palabras estarán delimitadas por espacios, signos de puntuación ( ´ , ; : . ?), comillas " y paréntesis. Las palabras se contabilizarán sin atender a si están escritas en mayúsculas o minúsculas (la palabra Bueno se considerara igual que buENo).

* No se contabilizará una palabra si su primer carácter no es una letra. Muestre la salida en orden alfabético de palabras con cada palabra precedida por su contador de ocurrencias.

* [Cabeceras  documentadas](https://github.com/ctc87/PAI-practica3/blob/gh-pages/src/Apartado4/NumOcurrencias.java)

#### App:

* App es la clase que ejecuta el programa principal de la clase NumOcurrencias.java del paquete Apartado4.

* Controla que se reciban parámetros de entrada, crea un objeto de la clase NumOcurrencias con el primer argumento pasado a la función main. Por último muestra el resultado de ejecutar NumOcurrencias sobre el archivo llamando al método apropiado.

* [Cabeceras  documentadas](https://github.com/ctc87/PAI-practica3/blob/gh-pages/src/Apartado4/App.java)

### Apartado4
  Es un paquete que contiene cinco clases.

#### Log1:
* Log1 es la clase que ejecuta un programa principal el cual calcula el logaritmo de un número pasado por parámetro.

* Controla las excepciones:

  1. IllegalArgumentException -> Si se le pasa más de un parámetro.
  2. ArrayIndexOutOfBoundsException -> Si no se le pasan parámetros.
  3. NumberFormatException -> Si se le pasa algo que no sea un número por parámetro.

* [Cabeceras  documentadas](https://github.com/ctc87/PAI-practica3/blob/gh-pages/src/Apartado5/Log1.java)

#### Log2:
* Log2 es la clase que ejecuta un programa principal el cual calcula el logaritmo de un número pasado por parámetro. Este es calculado con el método constructor que lanza una excepción si es negativo la cual se gestiona desde el programa principal.

* Controla las excepciones:

  1. IllegalArgumentException -> Si se le pasa más de un parámetro.
  2. ArrayIndexOutOfBoundsException -> Si no se le pasan parámetros.
  3. NumberFormatException -> Si se le pasa algo que no sea un número por parámetro.
  4. ArithmeticException -> Si el número pasado como parámetro es negativo.

* [Cabeceras  documentadas](https://github.com/ctc87/PAI-practica3/blob/gh-pages/src/Apartado5/Log2.java)

#### Log3:
* Log3 es la clase que ejecuta un programa principal el cual calcula el logaritmo de un número pasado por parámetro. Este es calculado con el método constructor que lanza una excepción si es negativo la cual se gestiona desde el programa principal.

* Controla las excepciones

  1. IllegalArgumentException -> Si se le pasa más de un parámetro.
  2. ArrayIndexOutOfBoundsException -> Si no se le pasan parámetros.
  3. NumberFormatException -> Si se le pasa algo que no sea un número por parámetro.
  4. WrongParameterException -> Si el número pasado como parámetro es negativo.

* [Cabeceras  documentadas](https://github.com/ctc87/PAI-practica3/blob/gh-pages/src/Apartado5/Log3.java)

#### Log4:
* Log4 es la clase que ejecuta un programa principal el cual calcula el logaritmo de un número pasado por parámetro. Este es calculado con el método constructor que lanza una excepción si es negativo la cual se gestiona desde el programa principal.

* Controla las excepciones

  1. IllegalArgumentException -> Si se le pasa más de un parámetro.
  2. ArrayIndexOutOfBoundsException -> Si no se le pasan parámetros.
  3. NumberFormatException -> Si se le pasa algo que no sea un número por parámetro.
  4. WrongParameterException -> Si el número pasado como parámetro es negativo.

* [Cabeceras  documentadas](https://github.com/ctc87/PAI-practica3/blob/gh-pages/src/Apartado5/Log4.java)

#### WrongParameterException:
* WrongParameterException es la clase que crea una excepción de tipo checked hija de la clase Exception.

* Informa sobre un Error en los parámetros, bastante genéricos.

* Admite otra excepción con y un mensaje como constructor.

* [Cabeceras  documentadas](https://github.com/ctc87/PAI-practica3/blob/gh-pages/src/Apartado5/WrongParameterException.java)
