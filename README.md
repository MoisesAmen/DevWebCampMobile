# AppDevWebCampMobile

## Descripción
AppDevWebCampMobile es una aplicación móvil desarrollada en Kotlin con Jetpack Compose que sirve como cliente para el sistema DevWebCamp. Permite la gestión de eventos, ponentes y usuarios de conferencias tecnológicas.

## Características Principales
- Autenticación de usuarios
- Visualización de eventos y conferencias
- Panel de administración
- Gestión de ponentes
- Dashboard con estadísticas

## Tecnologías Utilizadas
- Kotlin
- Jetpack Compose
- Retrofit para conexiones API
- Material Design 3
- Navegación con Navigation Compose
- Arquitectura MVVM

## Estructura Detallada del Proyecto
```
app/
├── src/
│   ├── main/
│   │   ├── java/com/mss/appdevwebcampmovile_v2/
│   │   │   ├── api/
│   │   │   │   ├── AdminApiService.kt    # Servicios API para administración
│   │   │   │   ├── AuthApiService.kt     # Servicios de autenticación
│   │   │   │   ├── EventoApiService.kt   # Servicios para gestión de eventos
│   │   │   │   └── RetrofitClient.kt     # Cliente Retrofit y configuración
│   │   │   │
│   │   │   ├── models/
│   │   │   │   ├── Categoria.kt          # Modelo de categorías de eventos
│   │   │   │   ├── Dashboard.kt          # Modelo para datos del panel admin
│   │   │   │   ├── Dia.kt               # Modelo para días de eventos
│   │   │   │   ├── Evento.kt            # Modelo principal de eventos
│   │   │   │   ├── Hora.kt              # Modelo para horarios
│   │   │   │   ├── LoginRequest.kt      # Modelo para solicitud de login
│   │   │   │   ├── LoginResponse.kt     # Modelo para respuesta de login
│   │   │   │   ├── Ponente.kt          # Modelo de presentadores
│   │   │   │   ├── Regalo.kt           # Modelo para gestión de regalos
│   │   │   │   ├── Registro.kt         # Modelo de registros de usuarios
│   │   │   │   └── Usuario.kt          # Modelo de usuarios
│   │   │   │
│   │   │   ├── views/
│   │   │   │   ├── AdminPonentes.kt    # Vista de gestión de ponentes
│   │   │   │   ├── AdminScreen.kt      # Panel de administración
│   │   │   │   ├── EventosScreen.kt    # Vista principal de eventos
│   │   │   │   └── LoginScreen.kt      # Pantalla de inicio de sesión
│   │   │   │
│   │   │   ├── ui/theme/               # Configuración del tema visual
│   │   │   │   ├── Color.kt
│   │   │   │   ├── Theme.kt
│   │   │   │   └── Type.kt
│   │   │   │
│   │   │   └── MainActivity.kt         # Punto de entrada de la aplicación
│   │   │
│   │   ├── res/                        # Recursos de la aplicación
│   │   │   ├── drawable/               # Imágenes y gráficos
│   │   │   ├── layout/                 # Layouts XML (si se usan)
│   │   │   ├── values/                 # Valores (strings, colors, etc.)
│   │   │   └── mipmap/                 # Iconos de la aplicación
│   │   │
│   │   └── AndroidManifest.xml         # Configuración de la aplicación
│   │
│   └── test/                           # Pruebas unitarias
│       └── java/
│
├── build.gradle                        # Configuración de construcción
└── proguard-rules.pro                  # Reglas de ofuscación
```

### Repository
```
repository/
├── AdminRepository.kt          # Gestión de datos administrativos
│   ├── getDashboardData()      # Obtiene datos del dashboard
│   ├── getRegistradosData()    # Obtiene lista de registrados
│   └── getRegalosData()        # Obtiene lista de regalos
│
├── AuthRepository.kt           # Gestión de autenticación
│   └── login()                # Manejo de inicio de sesión
│
└── EventoRepository.kt         # Gestión de eventos
    └── getEventos()           # Obtiene lista de eventos
```

### ViewModels
```
viewModels/
├── AdminViewModel.kt           # ViewModel para administración
│   ├── dashboardData          # Estado del dashboard
│   ├── registrosData          # Estado de registros
│   └── regaloData            # Estado de regalos
│
├── AdminViewModelFactory.kt    # Factory para AdminViewModel
│
├── LoginViewModel.kt          # ViewModel para autenticación
│   ├── login()               # Lógica de inicio de sesión
│   └── showAlert()           # Manejo de alertas
│
└── LoginViewModelFactory.kt   # Factory para LoginViewModel
```

### Views
```
views/
├── AdminScreen/
│   ├── AdminScreen.kt         # Pantalla principal de administración
│   │   ├── DashboardSection  # Sección de estadísticas
│   │   ├── RegistrosSection  # Sección de usuarios registrados
│   │   └── RegalosSection   # Sección de regalos
│   │
│   └── Components/           # Componentes reutilizables admin
│       ├── StatCard.kt      # Tarjeta de estadísticas
│       └── DataTable.kt     # Tabla de datos
│
├── EventosScreen/
│   ├── EventosScreen.kt      # Pantalla principal de eventos
│   │   ├── EventosList      # Lista de eventos
│   │   ├── FilterSection    # Filtros de búsqueda
│   │   └── EventDetail      # Detalle de evento
│   │
│   └── Components/          # Componentes de eventos
│       ├── EventCard.kt     # Tarjeta de evento
│       └── FilterBar.kt     # Barra de filtros
│
└── LoginScreen/
    ├── LoginScreen.kt       # Pantalla de inicio de sesión
    │   ├── LoginForm       # Formulario de login
    │   └── ErrorDisplay    # Visualización de errores
    │
    └── Components/         # Componentes de login
        ├── CustomInput.kt  # Campo de entrada personalizado
        └── LoginButton.kt  # Botón de inicio de sesión
```

## Modelos de Datos
- `Evento`: Representa conferencias y eventos
- `Ponente`: Información de los presentadores
- `Categoria`: Clasificación de eventos
- `Dashboard`: Datos estadísticos y resumen
- `Usuario`: Información de usuarios registrados

## API Endpoints
- `/api/login` - Autenticación de usuarios
- `/api/eventos` - Listado de eventos
- `/api/admin/dashboard` - Datos del panel administrativo
- `/api/admin/registrados` - Usuarios registrados
- `/api/admin/regalos` - Gestión de regalos

## Configuración
1. Clona el repositorio
2. Actualiza la URL base en `RetrofitClient.kt`
3. Asegúrate de tener el backend en funcionamiento
4. Compila y ejecuta la aplicación

## Requisitos
- Android Studio Arctic Fox o superior
- SDK Android mínimo: API 21
- Kotlin 1.5+
- Conexión a Internet

## Autenticación
La aplicación utiliza token JWT para la autenticación:
1. El usuario ingresa credenciales
2. Se obtiene token del servidor
3. El token se usa en cabeceras de solicitudes posteriores

## Pantallas Principales
1. **LoginScreen**: Autenticación de usuarios
2. **EventosScreen**: Listado y detalles de eventos
3. **AdminScreen**: Panel de control administrativo

## Componentes Principales

### API Services
- **AdminApiService**: 
  - Gestión del dashboard administrativo
  - Control de registros y regalos
  - Requiere autenticación mediante token

- **AuthApiService**:
  - Manejo de autenticación
  - Proceso de login y tokens
  - Validación de credenciales

- **EventoApiService**:
  - Listado y gestión de eventos
  - Organización por categorías
  - Consulta de disponibilidad

### Modelos de Datos
- **Evento**: Estructura central que relaciona:
  - Categoría
  - Día y hora
  - Ponente
  - Disponibilidad

- **Dashboard**: Concentra información administrativa:
  - Registros de usuarios
  - Ingresos (virtuales y presenciales)
  - Estadísticas de disponibilidad

### Vistas Principales
- **LoginScreen**: 
  - Formulario de autenticación
  - Manejo de errores
  - Redirección según rol

- **EventosScreen**:
  - Listado de eventos
  - Filtros y búsqueda
  - Detalles de eventos

- **AdminScreen**:
  - Panel de control
  - Estadísticas
  - Gestión administrativa

## Flujo de Datos
1. El usuario inicia sesión (LoginScreen)
2. Se almacena el token JWT
3. Las peticiones posteriores incluyen el token
4. Los datos se procesan y muestran en las vistas correspondientes

## Seguridad
- Autenticación mediante JWT
- Validación de roles
- Encriptación de datos sensibles
- Manejo seguro de sesiones

## Integración con Backend
- Comunicación REST API
- Serialización JSON
- Manejo de respuestas asíncronas
- Control de errores

## Desarrollo
Para contribuir al proyecto:
1. Haz fork del repositorio
2. Crea una rama para tu feature
3. Realiza tus cambios
4. Envía un pull request

## Troubleshooting
- Verifica la URL del backend en `RetrofitClient.kt`
- Asegúrate de tener permisos de Internet en el Manifest
- Revisa los logs para errores de conexión

## Patrones de Diseño

### MVVM (Model-View-ViewModel)
- **Model**: Repositorios y datos
- **View**: Pantallas en Jetpack Compose
- **ViewModel**: Lógica de negocio y estado

### Repository Pattern
- Abstracción de fuentes de datos
- Manejo centralizado de API calls
- Cache y gestión de errores

### Factory Pattern
- Creación de ViewModels
- Inyección de dependencias
- Configuración inicial

## Flujo de Datos
```
View -> ViewModel -> Repository -> API
  ↑         |            ↑         |
  |         |            |         |
  └---------└------------└---------┘
     Flujo de respuesta
```

## Manejo de Estado
- Estado UI en ViewModels
- MutableState para reactividad
- Comunicación entre componentes

## Navegación
- NavController centralizado
- Rutas definidas
- Gestión de backstack

## Inyección de Dependencias
- ViewModelFactory
- Contexto de aplicación
- Servicios compartidos

