# Practicas-PDS-2025

Proyecto de la asignatura PDS 2024-2025

Hecho por:
  * Daniel Antonio Martínez Sánchez
  * Miguel Ángel Marín Valero
  * Benjamín Manchado Gómez

Profesor:
  * Jesús Sánchez Cuadrado

## Ejecución

La clase que contiene el fichero a ejecutar para iniciar la aplicación se encuentra en el **paquete interfaz** y se llama **InicioApp.java**

## Casos de Uso y Modelado de dominio

Se encuentran en [#1](https://github.com/MigueAngel21/Practicas-PDS-2025/issues/1) y [#4](https://github.com/MigueAngel21/Practicas-PDS-2025/issues/4) respectivamente.

## Objetivo de la aplicación

La aplicación está inspirada en la popular aplicación para aprender idiomas Duolingo. En nuestra aplicación se pueden hacer cursos de todo tipo con varios tipos de preguntas y distintas estrategias de aprendizaje.

## Funcionalidad implementada 

Hemos implementado todo lo que una app de este tipo necesita. Lo primero un mecanismo de registro y login para los usuarios. Una vez se haya iniciado la sesión, se accede al panel de cursos donde por defecto (clonando el proyecto desde este enlace) aparecen dos cursos, uno sobre fútbol y otro sobre informática. Estos cursos no son más que ficheros en formato JSON que se encuentran en el directorio *cursos* y, por tanto, siguiendo la estructura de dichos ficheros se pueden crear más cursos (la aplicación avisará al arrancar si el formato de algún curso es incorrecto).

Desde la pantalla principal también podemos elegir la estrategia de aprendizaje que queremos usar:

* Secuencial: Normal, una pregunta tras otra según el orden definido en el fichero
* Aleatoria: Como la anterior, pero en este caso el orden de las preguntas es aleatorio
* Repetición Espaciada: Como secuencial, pero en este caso las preguntas que el usuario falle se irán añadiendo al final de la cola otra vez hasta que las responda correctamente

Gracias a la persistencia de la aplicación, se puede cerrar un curso en cualquier momento y volver a empezar por donde uno estaba (nota: el mismo curso, pero con otra estrategia de aprendizaje se considera un curso diferente). Además, desde la pestaña de perfil podemos ver nuestras estadísticas referentes a sus cursos y en general. Los cursos tienen tres tipos de preguntas

* MultipleChoice: Una pregunta con N respuestas textuales posibles
* Flashcard: Una tarjeta que al clicarla se voltea mostrando la solución, el usuario puede indicar si lo sabía (bien) o no (mal)
* MultiplceChoice con Imagen: Una pregunta con N respuestas en forma de imagen.

## Funcionalidad adicional

Como funcionalidad adicional hemos implementado el login con google usando la API de [GCP](https://developers.google.com/identity/protocols/oauth2). Nos ha parecido muy buen idea, ya que esta asignatura se basa aparte de en la construcción de software en sí misma en los elementos que la rodean y creemos que la interacción con una API de este tipo encaja muy bien con el propósito de la asignatura.

## Otros aspectos relevantes

También hemos añadido al repositorio de github a través de github actions una pequeña pipeline de CI/CD que hace que cada vez que se haga un nuevo commit en la rama main se compile el proyecto y se ejecuten los test usando los runners que proporciona github. Adicionalmente, hemos usado SpotBugs para revisar el código y nos hemos asegurado de que esta herramienta no reporte ningún bug y en caso necesario hemos suprimido los que hemos creído convenientes. 

## Cobertura de los test

Tenemos una cobertura que consideramos bastante alta, un 76% en el dominio, y +95% en las estrategias de aprendizaje y el controlador. La cobertura del dominio es realmente más de la que podría pensarse, ya que la clase encargada del login con google no podemos probarla con Junit (porque requiere interacción con el navegador, quizás se podría con Selenium), muchas clases tiene el 100% de cobertura (Usuario, Progreso, Estadistica) y otras muchas clases coberturas bastante altas, pero al tener una clase entera con un 0% nos baja bastante la media.

Hemos puesto bastante esfuerzo en los test, ya que creemos que era una de las partes más importantes del proyecto, hemos usado mockito para en las pruebas de integración del controlador hacer mocks de la BD y del servicio de OAuth2 (aunque también tenemos test que se ejecutan sobre la propia BD) y también hemos usado test parametrizados para poder con menos código probar todas las estrategias en un solo test. En total (contando los test parametrizados que inflan un poco el número) se ejecutan 68 test.
