# TALLER DE ARQUITECTURAS DE SERVIDORES DE APLICACIONES, META PROTOCOLOS DE OBJETOS, PATRÓN IOC, REFLEXIÓN

Para este taller los estudiantes deberán construir un servidor Web (tipo Apache) en Java. El servidor debe ser capaz de entregar páginas html e imágenes tipo PNG. Igualmente el servidor debe proveer un framework IoC para la construcción de aplicaciones web a partir de POJOS. Usando el servidor se debe construir una aplicación Web de ejemplo. El servidor debe atender múltiples solicitudes no concurrentes.


## Corriendo el proyecto

Una vez clonado el proyecto y generado el jar con los siguientes comandos:

~~~
git clone https://github.com/sebasporras14/AREP-lab04.git
~~~
~~~
mvn package
~~~

se puede ejecutar con:

~~~
java -cp "./target/classes" edu.escuelaing.arep.app.HttpServer
~~~
una vez aparezca "Listo para recibir", en su navegador ingrese:

~~~
Http://LocalHost:35000/"el archivo html, imagen o js"
~~~

### prueba HTML,JPG o PNG, .js

Al colocar los siguientes comandos se obtiene:
~~~
Http://LocalHost:35000/index.html"
~~~
~~~
Http://LocalHost:35000/img.jpg
~~~
~~~
Http://LocalHost:35000/img2.png
~~~
~~~
Http://LocalHost:35000/prueba.js"
~~~
### retorno HTML

![test](https://github.com/sebasporras14/AREP-lab04/blob/master/imagenes/pruebahtml.png)

![test](https://github.com/sebasporras14/AREP-lab04/blob/master/imagenes/pruebahtml2.png)

### retorno imagen

![test](https://github.com/sebasporras14/AREP-lab04/blob/master/imagenes/img.png)
### retorno js

![test](https://github.com/sebasporras14/AREP-lab04/blob/master/imagenes/js.png)

## Construido con 
* [Maven](https://maven.apache.org/) - Dependency Management
* [java](https://rometools.github.io/rome/) - Used to generate RSS Feeds


## Autor

* **Sebastian Porras**

### Fecha

09/20/2023 

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
