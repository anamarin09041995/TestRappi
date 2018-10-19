# Capas de la aplicacion 
## Modelo
### Persistencia
> En esta capa se maneja todo lo relacionado con la imformacion local de la aplicacion. Dicha informacion esta estructurada de la siguiente forma:

**Carpeta db:**
1. MovieDao.kt: Brinda el acceso e interaccion con la base de datos, en especifico con la entidad movie.
2. SerieDao.kt: Brinda el acceso e interaccion con la base de datos, en especifico con la entidad serie.
3. AppDatabase.kt: Genera las instancias de la base de datos a traves de las cuales se obtienen lso datos.

### Modelos
>Proveen la estructura de la informacion y las entidades de Room.

**Carpeta model:** 

1. Movie.kt: Provee la estructura de los objetos de tipo Movie.
2. Serie.kt: Provee la estructura de los objetos de tipo Serie.
3. Item.kt: Provee la estructura de los datos que tienen en comun los objetos de tipo Movie o Serie.

### Red
Implementa los clientes de retrofit para Movies y Series. Se estructura de la siguiente forma:

**Carpeta net**
1. MovieClient.kt: Provee los metodos para acceder a los servicios que se requieren de peliculas. 
2. SerieCliente.kt:Provee los metodos para acceder a los servicios que se requieren de series.
3. SearchClient.kt:Provee los metodos para busquedas, ya sea de peliculas o series.
4. Response.kt: Permite recibir de forma sencilla los objetos que se obtienen de las peticiones al servidor.

## Vista
Esta capa se encarga de exponer la informacion al usuario. Se encuentra estructurada de la siguiente forma:

### UI

**Carpeta main**
1. Contiene los fragments para pelicula y movie, y el activity main, el cual proporciona el menu principal y a su vez cambia el contenido con los fragments creados de acuerdo a la eleccion del usuario.

**Carpeta search**
1. SearchActivity.kt: Clase en la cual se realiza la busqueda de acuerdo a las solicitudes del usuario.

**Carpeta detail**
1. DetailActivity.kt: Expone mas informacion al usuario cuando este da clic sobre una pelicula o serie de la lista. 

**Carpeta adapter**
1. ItemAdapter.kt: Permite obtener los contenedores para exponer las listas que se despliegan, sean de peliculas o series. 

## ViewModel
### Logica de negocio
>En esta capa se encarga de toda la logica de la aplicacion que accede y provee los datos a las diferentes vistas. En ella se encuentran las clases:

1. SerieViewModel.kt: Accede y provee los datos al fragment de series.
2. MovieViewModel.kt: Accede y provee los datos al fragment de movies.
3. SearchViewModel.kt: Accede y provee los datos al activity de busqueda.

## Util
Este paquete provee tres clases:

1. Attr.kt: Provee un binding adapter para cargar las imagenes con la libreria utilizada, en este caso Fresco.
2. Ext.kt: Provee extensiones generales con el fin de optimizar codigo.
3. RxExts.kt: Provee extensiones de rx con el fin de optimizar codigo.

## Inyeccion de dependencias
>Se realizo mediante Koin. Provee todas las dependencias que se deben inyectar. A su vez, incluye las definiciones de los single, es decir, las instancias unicas que viven durante todo el ciclo de vida de la aplicacion. Se encuentra ubicada en la carpeta di bajo el nombre de AppModule.kt

## App.kt
Este archivo permite inicializar tanto la inyeccion de dependencias como el uso de Fresco. 

# Respuestas

**Principio de responsabilidad unica**
>Hace referencia a delimitar las responsabilidades de cada clase y objeto creado dentro de la aplicacion. Esto con el fin de disminuir la sensibilidad a posibles cambios que deban realizarse.

**Codigo limpio**
1. Autodocumentado, lo cual se refiere a que todo lo escrito (variables, clases, metodos, entre otros) debe dejar claro el proposito al cual apunta.
2. Uso del principio de responsabilidad unica dentro del codigo.
3. Manejo de una arquitectura que permita que el codigo sea legible y escalable.
4. Realizacion de pruebas unitarias.


