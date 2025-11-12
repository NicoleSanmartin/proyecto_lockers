🎒 Proyecto Lockers — Sistema de Gestión de Lockers Escolares

Aplicación web desarrollada con Spring Boot 3 y Java 21, que permite gestionar el alquiler de lockers en una institución educativa.
El sistema ofrece funcionalidades para registrar estudiantes, administrar lockers, alquilar y liberar casilleros, garantizando trazabilidad y control en el uso de los mismos.

🚀 Tecnologías Utilizadas
Lenguaje:                  |Java 21 (Amazon Corretto)| 
Framework:                 |backend	Spring Boot 3.3.5| 
Arquitectura:      	       |MVC / Arquitectura por capas (Controller, Service, Repository, Model)| 
Base de datos:       	     |SQLite (JDBC)| 
API Documentation:	       |Springdoc OpenAPI / Swagger UI| 
Gestión de dependencias:   |Maven| 
IDE sugerido:	             |IntelliJ IDEA o Eclipse| 
Control de versiones:	     |Git & GitHub| 

💡 Definición del Proyecto de Software
Proyecto Lockers es un sistema de información diseñado para digitalizar la administración de lockers escolares, reemplazando el manejo manual de asignaciones y entregas por un sistema web eficiente, seguro y auditable.

El software se desarrolla bajo una metodología incremental, implementando principios de la ingeniería de software y siguiendo la arquitectura por capas para lograr una solución escalable y mantenible.

🎯 Objetivos SMART
##Tipo	Descripción
Específico	Desarrollar una aplicación web que permita registrar, alquilar y liberar lockers, asociándolos a estudiantes de una institución.
Medible	Lograr que al menos el 100% de los lockers puedan gestionarse digitalmente, sin intervención manual.
Alcanzable	Implementar las funcionalidades utilizando tecnologías accesibles (Spring Boot y SQLite), garantizando compatibilidad multiplataforma.
Relevante	Automatizar un proceso institucional frecuente, optimizando tiempo y reduciendo errores humanos.
Temporal	Completar la implementación y pruebas en un periodo máximo de 4 semanas.

🧩 Alcance del Sistema

##✅ Funcionalidades incluidas
Registro de estudiantes.
Registro y gestión de lockers.
Asignación de lockers a estudiantes (alquiler).
Liberación de lockers.
Consulta del estado de todos los lockers.
Historial de alquileres.
Documentación de la API REST mediante Swagger.

##🚫 Fuera de alcance
Integración con pagos o facturación.
Autenticación de usuarios.
Envío de notificaciones.

# Diagrama ER

<img width="780" height="413" alt="image" src="https://github.com/user-attachments/assets/f692a31b-c4f7-4abf-ba37-3611fb8df84d" />


# Diagrama de clases
<img width="1302" height="795" alt="image" src="https://github.com/user-attachments/assets/4ca937cf-98ee-4c0b-814f-04cd6f04ae79" />

    
🎯 Ejecución local
Clonar el repositorio:
|git clone https://github.com/usuario/proyecto-lockers.git|

Abrir el proyecto en IntelliJ.
Ejecutar la clase:

|ProyectoLockersApplication.java|



Acceder a:
http://localhost:8080/swagger-ui.html


🧑‍💻 Autor Nicole Sanmartín - Alhan Rendon
