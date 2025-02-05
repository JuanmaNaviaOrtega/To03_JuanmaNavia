#EJERCICIO1

- Esta Aplicación permite al usuario ingresar una URL en un cuadro de texto.
- Descargar imagenes(jpg o png) y archivos (html y txt) de forma asíncrona usando AsyncTask.
- El contenido descargado se almacena en la memoria del dispositivo.
- En la misma ventana se mostrará el contenido del fichero  la imagen descargada.
- Mostrará un mensaje Toast indicando si la descarga ha sido exitosa o si ha habido algun error
- Es muy importante agregar los permisos de internet en el androidManifest.xml.

- Este ejercicio consta de varias clases:
MainActivity-> Desde esta clase manejamos la interaccion del usuario y mostraremos los resultados.
DowloadFileTask-> Esta clase se encarga de descargar el archivo/imagen en segundo plano usando AsyncTask
NetworkUtils-> Esta clase maneja la conexión a internet y la descarga del archivo
FileUtils-> Esta clase permite guardar y leer archivos.

- Este ejercicio consta de un layout activity_main.xml que contiene:
- Un EditText-> para que el usuario pueda ingresar la URL 
- Un Button -> para que cuando el usuario lo pulse se inicie la descarga
- Una ImageView-> para mostrar la imagen cuando sea descargada
- Un TextView-> Para mosrtar el contenido de los archivos cuando sean descargado.


#EJERCICIO2  
- Esta aplicación  es un conversor de moneda que descargará la tasa de cambio Dólar → Euro desde un archivo alojado en mi pagina web GitHub y permite realizar conversiones en ambas direcciones.  
- Cuando se inicie la aplicacion se descargara el fichero cambio,txt y obtendra el valor del cambio y lo utilizará para realizar la conversión, la descarga se realiza usando OkHttp.
- En un Toast se mostrara si la descarga y el cambio de valor de la conversion se ha realizado correctamente, tambien muestra si ha habido algun error en la comunicación con el servidor.

Este Ejercicio consta de:
- Una clase MainActivity2->
Que contiene las funcion necesaria para descargar la tasa del cambio a traves de la URL, tambien para manejar la interacción del usuario y mostrar resultados
- Un layout activity_main2 que contiene:
Un EditText: para ingresar la cantidad de €/$ de la que se hará la conversión
Dos RadioButton: para indiciar si la conversion se hará de euros -> dolares o de dolares -> euros
Un Button: para que se realice la conversion dependiendo del radioButton que se haya escogido
Un Textview: Para mostrar el resultado de la conversion.
- Un archivo cambio.txt-> En este archivo se especifica el cambio del valor para la conversion



