# PFR_B1_Miniproyecto_O26F27

# 1. Implementación de funciones

En esta primera actividad se desarrolló una función de orden superior denominada `procesarMensaje`, cuya finalidad es recibir un mensaje, una clave numérica y una función de cifrado como parámetros.

La característica principal de esta función es que permite recibir diferentes algoritmos de transformación sin necesidad de modificar su implementación interna. De esta manera, puede utilizarse tanto para el cifrado César como para el cifrado XOR.

### 1.1. Función de orden superior: procesarMensaje

La función recibe los siguientes parámetros:

- **Mensaje (String):** contiene el texto que se desea cifrar o descifrar.
- **Clave (Int):** representa el valor numérico utilizado por el algoritmo de cifrado.
- **Función de cifrado ((Char, Int) => Char):** recibe un carácter y una clave, y devuelve el carácter transformado.

**Código de implementación:**

<img width="742" height="460" alt="image" src="https://github.com/user-attachments/assets/76495b4a-f3f3-4441-ab08-37374c17fe25" />

### 1.2. Transformación mediante map

Para realizar la transformación del mensaje se utilizó la función `map`, que permite recorrer cada carácter de la cadena y aplicar la función de cifrado correspondiente.

Mediante este enfoque funcional se genera una nueva cadena con los caracteres transformados, evitando utilizar estructuras iterativas tradicionales como los bucles `for`.

**Código de implementación:**

<img width="622" height="106" alt="image" src="https://github.com/user-attachments/assets/b3f1c881-6e9f-4c89-a6fd-c6c126ef862a" />


### 1.3. Implementación de los algoritmos de cifrado

Se desarrollaron dos funciones independientes:

**Cifrado César:** transforma cada letra mediante el desplazamiento de su posición dentro del alfabeto, utilizando la clave proporcionada. Se emplea la operación módulo 26 para mantener el desplazamiento dentro de las 26 letras del alfabeto.

**Cifrado XOR:** transforma cada carácter mediante una operación lógica XOR entre su código numérico y la clave. Para ello se utiliza el operador `^` de Scala.

**Código de implementación:**

<img width="712" height="632" alt="image" src="https://github.com/user-attachments/assets/556657ba-1d7d-4b5f-b1b3-8fd78d08bf04" />

<img width="702" height="735" alt="image" src="https://github.com/user-attachments/assets/f87cb3e4-64c1-482d-840e-fb701996c1c1" />

---

# 2. Pruebas de cifrado y descifrado

En esta segunda actividad se realizaron las pruebas solicitadas en el enunciado del miniproyecto, utilizando ambos algoritmos para comprobar que los mensajes pueden cifrarse y posteriormente recuperarse mediante el procedimiento de descifrado correspondiente.

## 2.1. Caso César (Desplazamiento)

Para esta prueba se utilizó la palabra **FUNCIONAL** con una clave de desplazamiento de `k = 3`.

El procedimiento consiste en convertir cada letra en su posición correspondiente dentro del alfabeto, sumar la clave y obtener la nueva posición mediante el módulo 26.

**Datos de entrada:**

- Mensaje original: `FUNCIONAL`
- Clave: `3`
- Algoritmo: Cifrado César.

**Resultado del cifrado:**

El mensaje original `FUNCIONAL` se transforma en `IXQFLRQDO`.

**Resultado del descifrado:**

Para recuperar el mensaje original se vuelve a procesar el texto cifrado utilizando la clave negativa `k = -3`. De esta manera, se invierte el desplazamiento realizado durante el cifrado y se obtiene nuevamente `FUNCIONAL`.

**Código de implementación:**

<img width="732" height="815" alt="image" src="https://github.com/user-attachments/assets/0491cb15-2889-45f5-87f2-e8021adfe11a" />

**Captura de ejecución:**

<img width="420" height="122" alt="image" src="https://github.com/user-attachments/assets/55d34925-a558-4a22-b39b-86e9a2fb5981" />

## 2.2. Caso XOR (Lógico)

Para esta prueba se utilizó la palabra **scala** con una clave numérica de `k = 5`.

El algoritmo realiza una operación XOR entre el código numérico de cada carácter y la clave proporcionada, utilizando el operador `^`.

Por ejemplo, para el primer carácter:

- Carácter original: `s`
- Código ASCII: `115`
- Clave: `5`
- Operación: `115 ^ 5`
- Resultado decimal: `118`
- Carácter transformado: `v`

**Datos de entrada:**

- Mensaje original: `scala`
- Clave: `5`
- Algoritmo: Cifrado XOR.

**Resultado del cifrado:**

El mensaje original `scala` se transforma en `vfdid`.

**Resultado del descifrado:**

Para recuperar el mensaje original se vuelve a aplicar la misma función XOR utilizando la misma clave `k = 5`.

Esto es posible porque XOR posee la propiedad de ser su propia operación inversa. Por lo tanto, al aplicar nuevamente la operación con la misma clave se recupera el mensaje original `scala`.

**Código de implementación:**

<img width="717" height="820" alt="image" src="https://github.com/user-attachments/assets/47f07398-3b77-414c-a341-a8e3666fa041" />

**Captura de ejecución:**

[Aquí insertar la captura de pantalla que demuestra el cifrado y descifrado XOR].

---

# 3. Manejo de errores

En esta tercera actividad se implementaron mecanismos de validación y manejo de excepciones para garantizar que el programa pueda identificar entradas incorrectas y controlar los errores durante la ejecución.

Para ello se utilizó `Try`, junto con las estructuras `Success` y `Failure` de Scala. Esto permite capturar las excepciones producidas durante el procesamiento del mensaje y mostrar información sobre el error sin interrumpir completamente la ejecución del programa.

### 3.1. Validación de mensajes

Se incorporaron comprobaciones para evitar el procesamiento de cadenas que no cumplen las condiciones establecidas.

Se realizaron las siguientes pruebas:

- **Mensaje vacío:** se envía una cadena sin contenido para comprobar que el programa detecte esta condición.
- **Mensaje nulo:** se proporciona un valor `null` para verificar que sea rechazado correctamente.

**Código de implementación:**

[Aquí colocar el fragmento de código correspondiente a las validaciones].

### 3.2. Validación de claves

Se implementaron restricciones para controlar que las claves numéricas se encuentren dentro de los rangos permitidos por cada algoritmo.

Las pruebas realizadas fueron:

- **Clave César excesivamente grande:** se utiliza una clave de `1000` para comprobar que el programa rechace valores fuera del rango establecido.
- **Clave XOR negativa:** se utiliza una clave de `-5` para comprobar que se detecten valores inválidos.
- **Clave XOR excesiva:** se utiliza una clave de `200` para verificar que se controle una clave fuera del rango permitido.

**Código de implementación:**

[Aquí colocar el fragmento de código de validación de claves].

### 3.3. Validación de caracteres no soportados

También se realizaron pruebas utilizando caracteres que no cumplen las condiciones admitidas por los algoritmos.

- **Cifrado César:** se utiliza el mensaje `HOLA!` para comprobar que el programa identifique el carácter especial `!`, ya que el algoritmo implementado admite únicamente letras mayúsculas de la A a la Z.
- **Cifrado XOR:** se utiliza el mensaje `Holañ` para comprobar que el programa detecte caracteres fuera del rango ASCII imprimible establecido.

**Código de implementación:**

[Aquí colocar los fragmentos de código de validación de caracteres].

### 3.4. Resultados de las pruebas de errores

Las pruebas permitieron comprobar que el programa identifica las entradas inválidas y captura las excepciones correspondientes mediante `Try`, mostrando mensajes descriptivos para cada situación.

**Captura de ejecución:**

[Aquí insertar la captura de pantalla de la consola mostrando todas las pruebas de errores].

---

## Conclusiones

- La implementación permitió aplicar el concepto de funciones de orden superior, demostrando que una misma función puede recibir diferentes algoritmos de transformación como parámetros.
- El uso de `map` permitió realizar las operaciones de cifrado sobre cada carácter del mensaje siguiendo el paradigma de programación funcional.
- Se comprobó el funcionamiento de los algoritmos César y XOR mediante sus respectivos procesos de cifrado y descifrado, recuperando correctamente los mensajes originales.
- Finalmente, mediante `Try`, `Success` y `Failure` se implementó un mecanismo de manejo de excepciones que permite identificar mensajes inválidos, claves fuera de rango y caracteres no soportados.
