# 🔒 Sistema de Gestión de Lockers

Proyecto desarrollado en **Spring Boot** con **Thymeleaf** y **Swagger**, que permite administrar los lockers de una institución, gestionar alquileres y usuarios, y consultar su estado de forma dinámica.

---

## 📚 Descripción general

El sistema permite:
- Registrar, listar y eliminar **lockers**.
- Consultar el **estado** de cada locker (disponible u ocupado).
- Realizar **alquileres** y liberaciones de lockers.
- Administrar los **usuarios** que alquilan lockers.
- Visualizar y probar todos los endpoints desde **Swagger UI**.

Este proyecto sigue el patrón **MVC (Modelo - Vista - Controlador)** y buenas prácticas de desarrollo con separación clara de responsabilidades.

---

## ⚙️ Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA (Hibernate)**
- **Thymeleaf**
- **H2 Database (modo memoria)**
- **Swagger / OpenAPI 3**
- **Maven**

---

## 🧩 Estructura del proyecto
com.lockers

│

├── controller

│ ├── LockerController.java

│ ├── AlquilerController.java

│ └── UsuarioController.java

│

├── dto

│ └── AlquilerRequest.java

│

├── model

│ ├── Locker.java

│ ├── Usuario.java

│ └── Alquiler.java

│

├── repository

│ ├── LockerRepository.java

│ ├── UsuarioRepository.java

│ └── AlquilerRepository.java

│

├── service

│ └── LockerService.java

│

└── resources

├── application.properties

└── data.sql

---

## 🚀 Ejecución del proyecto

### 1️⃣ Clonar el repositorio
```bash
git clone https://github.com/usuario/proyecto-lockers.git
cd proyecto-lockers
```
### 2️⃣ Ejecutar la aplicación

***Si usas IntelliJ o Eclipse:***

- Abre el proyecto como Spring Boot Application

- Ejecuta la clase principal (por ejemplo, ProyectoLockersApplication.java)

## 🌐 Uso del sistema
🔹 Swagger UI

Accede a la documentación y prueba los endpoints desde:

http://localhost:8080/swagger-ui/index.html

🔹 Endpoints principales

### 🗂 Lockers
| Método | Endpoint            | Descripción                 |
| ------ | ------------------- | --------------------------- |
| GET    | `/api/lockers`      | Lista todos los lockers     |
| GET    | `/api/lockers/{id}` | Consulta un locker por ID   |
| POST   | `/api/lockers`      | Agrega un nuevo locker      |
| DELETE | `/api/lockers/{id}` | Elimina un locker existente |

### 🧍 Usuarios
| Método | Endpoint             | Descripción              |
| ------ | -------------------- | ------------------------ |
| GET    | `/api/usuarios`      | Lista todos los usuarios |
| POST   | `/api/usuarios`      | Crea un nuevo usuario    |
| DELETE | `/api/usuarios/{id}` | Elimina un usuario       |

### 📦 Alquileres
| Método | Endpoint                         | Descripción                  |
| ------ | -------------------------------- | ---------------------------- |
| GET    | `/alquileres`                    | Muestra todos los alquileres |
| POST   | `/alquileres/alquilar`           | Alquila un locker            |
| POST   | `/alquileres/liberar/{idLocker}` | Libera un locker ocupado     |

🔹 H2 Console (Base de datos en memoria)

El proyecto utiliza una base de datos H2 en memoria, ideal para pruebas rápidas sin instalación adicional.

Acceso a la consola:
http://localhost:8080/h2-console

***Credenciales predeterminadas:***

| Parámetro  | Valor                |
| ---------- | -------------------- |
| JDBC URL   | `jdbc:h2:mem:testdb` |
| Usuario    | `sa`                 |
| Contraseña | *(dejar en blanco)*  |

Pasos para acceder:

1. Ejecuta la aplicación Spring Boot. 
2. Entra al navegador y visita http://localhost:8080/h2-console. 
3. Asegúrate de que el campo JDBC URL diga: jdbc:h2:mem:testdb. 
4. Presiona Connect. 
5. Se abrirá una interfaz donde puedes ver las tablas lockers, usuarios y alquileres. 
6. Ejecuta consultas SQL, por ejemplo:

SELECT * FROM lockers; 

SELECT * FROM usuarios;

SELECT * FROM alquileres;

### 📦 Datos precargados en la base de datos
La base de datos H2 se inicializa automáticamente con algunos registros de ejemplo definidos en el archivo data.sql.
Esto permite probar las funcionalidades del sistema sin necesidad de ingresar datos manualmente al inicio.
Incluye lockers disponibles y ocupados, usuarios registrados y un alquiler activo de prueba.

### 🧠 Arquitectura del sistema
El proyecto está basado en Spring Boot MVC:

- Model: Clases Locker, Usuario, Alquiler (entidades JPA).

- Repository: Interfaces JpaRepository para operaciones CRUD.

- Service: Lógica central para manejar alquileres, lockers y usuarios.

- Controller: Expone endpoints REST y vistas web.

### 🧩 Buenas prácticas implementadas

- Uso de inyección de dependencias con @Autowired.

- Documentación automática de endpoints con Swagger.

- Respuestas controladas con ResponseEntity.

- Relaciones @OneToMany y @ManyToOne correctamente configuradas.

- Separación de capas y estructura modular.

### 👩‍💻 Autor

Nicole Sanmartin

Estudiante de Tecnología Desarrollo de Software

Proyecto académico para la asignatura programación de software