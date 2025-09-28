# Proyecto Lockers

Sistema de Alquiler de Lockers en Java 21

## Descripción
Este proyecto implementa un sistema en **Java 21** que permite:
- Registrar estudiantes
- Registrar lockers
- Alquilar lockers disponibles
- Liberar lockers ocupados
- Mostrar el listado de lockers
- Ver el historial de alquileres

La aplicación está desarrollada siguiendo el patrón de **arquitectura en capas**:
- **Controller**: Maneja la interacción con el usuario (consola)
- **Service**: Contiene la lógica de negocio
- **Repository**: Gestiona la persistencia de datos con JDBC (SQLite)
- **Model**: Define las entidades principales (Estudiante, Locker, Alquiler)


## Estructura del proyecto
```
src/
 └── main/java/com/lockers/
     ├── controller/LockerController.java
     ├── service/LockerService.java
     ├── repository/LockerRepository.java
     └── model/
         ├── Estudiante.java
         ├── Locker.java
         └── Alquiler.java
```

## Instalación y ejecución
1. Clonar el repositorio o descargar el zip.
2. Compilar con Maven:
   ```bash
   mvn clean compile
   ```
3. Ejecutar la aplicación con:
   ```bash
   mvn exec:java
   ```

## Uso
El sistema mostrará un menú en consola con las siguientes opciones:
```
===== SISTEMA DE LOCKERS =====
1. Registrar estudiante
2. Alquilar locker
3. Liberar locker
4. Ver lockers
5. Ver historial de alquileres 
0. Salir
```
## Ejemplo de uso
```
===== SISTEMA DE LOCKERS =====

   Seleccione una opción: 1
   Nombre: Juan Perez
   Documento: 123
   Estudiante registrado (si no existía).
   
   Continue eligiendo opciones, alquilando 
   lockers y corroborando que aparezca ocupado 
```

## Autor
Proyecto desarrollado como práctica académica para aplicar principios de:
- Programación Orientada a Objetos
- Encapsulamiento y modularidad
- Arquitectura en capas
- Persistencia con JDBC (SQLite)
- Uso de Git y GitHub para control de versiones
