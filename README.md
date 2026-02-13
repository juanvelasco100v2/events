# Events Service - DynamoDB CRUD

Este proyecto implementa un servicio RESTful para gestionar eventos utilizando **Amazon DynamoDB** como persistencia. Está diseñado siguiendo los principios de **Clean Architecture** y utiliza características modernas de **Java 21+** y **Spring Boot 3.2+**.

## 🚀 Características Principales

*   **Clean Architecture**: Separación estricta de responsabilidades en capas:
    *   **Domain**: Entidades y contratos de repositorio (sin dependencias externas).
    *   **Application (Use Cases)**: Lógica de negocio pura.
    *   **Infrastructure**: Implementación de base de datos, configuración de Spring y controladores REST.


## 📂 Estructura del Proyecto

```
src/main/java/com/nequi/events
├── application
│   └── usecase          # Casos de uso (Lógica de negocio)
├── domain
│   ├── model            # Modelos de dominio (Records)
│   └── repository       # Interfaces de repositorio
└── infrastructure
    ├── adapter
    │   ├── entity       # Entidades de DynamoDB (@DynamoDbBean)
    │   └── repository   # Implementación del repositorio con AWS SDK
    ├── config           # Configuración de Spring y DynamoDB
    └── entrypoint       # Controladores REST
```


## 🏃‍♂️ Cómo Ejecutar

1.  **Construir el proyecto**:
    ```bash
    ./gradlew build
    ```

2.  **Ejecutar la aplicación**:
    ```bash
    ./gradlew bootRun
    ```

La aplicación iniciará en el puerto `8080`.

## 🔌 API Endpoints

| Método | Endpoint           | Descripción                  |
| :----- | :----------------- | :--------------------------- |
| POST   | `/events`          | Crear un nuevo evento        |
| GET    | `/events/{eventId}`| Obtener un evento por ID     |
| GET    | `/events`          | Listar todos los eventos     |
| DELETE | `/events/{eventId}`| Eliminar un evento por ID    |

### Ejemplo de JSON (Crear Evento)

```json
{
  "eventId": "123",
  "name": "Concierto de Rock",
  "date": "2023-12-25",
  "location": "Estadio Nacional",
  "totalCapacity": 50000,
  "availableCapacity": 50000
}
```

## 💻 Ejemplos cURL

### Crear un Evento
```bash
curl -X POST http://localhost:8080/events \
  -H "Content-Type: application/json" \
  -d '{
    "eventId": "1",
    "name": "Tech Conference 2024",
    "date": "2024-05-15",
    "location": "Convention Center",
    "totalCapacity": 500,
    "availableCapacity": 500
  }'
```

### Obtener un Evento por ID
```bash
curl -X GET http://localhost:8080/events/1
```

### Listar Todos los Eventos
```bash
curl -X GET http://localhost:8080/events
```

### Eliminar un Evento
```bash
curl -X DELETE http://localhost:8080/events/1
```
