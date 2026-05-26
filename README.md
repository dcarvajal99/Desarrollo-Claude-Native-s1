# Desarrollo-Claude-Native-s1

Microservicio **Spring Boot 3 / Java 17** para la actividad de **Semana 1 — Desarrollo Cloud
Native (CDY2204)**: *"Desplegando aplicaciones en la nube"*.

Implementa el **sistema de inscripción a cursos** de una plataforma educativa virtual, siguiendo
la arquitectura por capas del ramo Desarrollo Backend (model → repository → service → controller,
DTOs vía `EntityMapper`, respuestas uniformes `ApiResponse`/`ErrorResponse`, manejo global de
errores y AOP de logging).

## Caso y funcionalidades

| # | Requisito del caso | Endpoint |
|---|---|---|
| 1 | Consultar la lista de cursos disponibles (nombre, instructor, duración, costo) | `GET /api/cursos` |
| 2 | Agregar nuevos cursos a la oferta educativa | `POST /api/cursos` |
| 3 | Inscribir a un estudiante en uno o más cursos, con resumen y total a pagar | `POST /api/inscripciones` |

## Stack

- Spring Boot 3.5, Java 17, Maven (Maven Wrapper `./mvnw`).
- Starters: web, data-jpa, validation, aop, actuator.
- **Base de datos: H2 en memoria** con datos precargados desde `src/main/resources/data.sql`.
- Sin autenticación (endpoints abiertos) — el caso no requiere login.

> **Nota sobre la base de datos:** el caso menciona *Oracle Cloud*. Para esta entrega se usa
> **H2 en memoria** (la BD estándar de desarrollo del ramo) por simplicidad. Migrar a Oracle solo
> requiere cambiar `application.properties` (url/driver/dialect) y agregar el driver `ojdbc11` al
> `pom.xml`.

## Cómo ejecutar

```bash
./mvnw spring-boot:run
# La app queda en http://127.0.0.1:8080
```

Compilar / empaquetar / tests:

```bash
./mvnw clean package    # genera target/*.jar y corre los tests
./mvnw test
```

- **Consola H2**: http://127.0.0.1:8080/h2-console
  (JDBC URL: `jdbc:h2:mem:inscripciones`, usuario: `sa`, sin contraseña)
- **Actuator**: http://127.0.0.1:8080/actuator/health

## Endpoints (ejemplos con curl)

### 1) Listar cursos
```bash
curl http://127.0.0.1:8080/api/cursos
```

### 2) Agregar un curso
```bash
curl -X POST http://127.0.0.1:8080/api/cursos \
  -H "Content-Type: application/json" \
  -d '{"nombre":"DevOps Avanzado","instructor":"Luis Vera","duracion":"35 horas","costo":160000}'
```

### 3) Inscribir a un estudiante en uno o más cursos
```bash
curl -X POST http://127.0.0.1:8080/api/inscripciones \
  -H "Content-Type: application/json" \
  -d '{"estudiante":"Diego","cursoIds":[1,2]}'
```

Respuesta (resumen con costo de cada curso y total a pagar):

```json
{
  "status": 201,
  "message": "Enrollment completed successfully",
  "data": {
    "estudiante": "Carolina Solis, Diego ",
    "cursos": [
      { "cursoId": 1, "nombre": "Introduccion a Java", "costo": 120000 },
      { "cursoId": 2, "nombre": "Spring Boot desde cero", "costo": 180000 }
    ],
    "total": 300000
  },
  "timestamp": "2026-05-25 12:00:00"
}
```

## CI/CD (GitHub Actions → Docker Hub → EC2)

El workflow `.github/workflows/deploy.yml` se gatilla con cada `push` a `main`: construye la
imagen Docker, la publica en Docker Hub y la despliega por SSH en una instancia EC2 (según la guía
de la semana). Requiere configurar estos **secrets** en el repositorio
(*Settings → Secrets and variables → Actions*):

| Secret | Descripción |
|---|---|
| `DOCKERHUB_USERNAME` | Usuario de Docker Hub |
| `DOCKERHUB_TOKEN` | Personal Access Token de Docker Hub |
| `AWS_ACCESS_KEY_ID` / `AWS_SECRET_ACCESS_KEY` / `AWS_SESSION_TOKEN` | Credenciales de AWS Academy |
| `EC2_HOST` | IP elástica de la instancia EC2 |
| `USER_SERVER` | Usuario SSH del servidor (ej. `ec2-user`) |
| `EC2_SSH_KEY` | Llave privada SSH de la instancia |

Construir y correr la imagen localmente:

```bash
docker build -t desarrollo-claude-native-s1 .
docker run -p 8080:8080 desarrollo-claude-native-s1
```

## Estructura

```
com.duoc.inscripciones
├── model/        Curso, Inscripcion (entidades JPA)
├── repository/   CursoRepository, InscripcionRepository
├── dto/          DTOs + EntityMapper + ApiResponse + ErrorResponse
├── service/      CursoService, InscripcionService (@Transactional)
├── controller/   CursoController, InscripcionController
├── exception/    ResourceNotFoundException, GlobalExceptionHandler
└── aspect/       LoggingAspect (AOP)
```
