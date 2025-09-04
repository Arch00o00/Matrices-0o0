# Justificación Técnica del Diseño - Barcosss MVC

## 1. Matriz de Objetos - Ubicación y Justificación

### Ubicación Exacta
La matriz de objetos se implementa en la clase `Tablero.java` mediante la declaración:
```java
private Celda[][] tablero;  // MATRIZ DE OBJETOS (NO PRIMITIVOS)
```
// En este caso se pidio a chat gpt para que descrbiera el de que se trataba el codigo
// o porque se usaba cada cosa o cadad imputt. 
### Justificación Técnica
**¿Por qué objetos en lugar de primitivos?**
- **Encapsulación de Estado**: Cada `Celda` mantiene su propio estado (coordenadas, ocupación, impactos) sin exponer detalles internos
- **Comportamiento Específico**: Los objetos `Celda` implementan métodos como `recibirDisparo()` y `estaOcupada()`, centralizando la lógica de negocio
- **Extensibilidad**: Facilita agregar nuevas funcionalidades (efectos especiales, tipos de celda) sin modificar la estructura del tablero
- **Polimorfismo Futuro**: Permite crear subtipos de `Celda` (CeldaEspecial, CeldaMina) manteniendo la misma interfaz

### Beneficios para Mantenimiento
- **Responsabilidad Única**: Cada celda gestiona su propio estado, reduciendo la complejidad del `Tablero`
- **Debugging Simplificado**: Estado encapsulado facilita la depuración y testing unitario
- **Modificaciones Localizadas**: Cambios en comportamiento de celdas no afectan otras clases

## 2. Decisiones de Diseño MVC

### Separación Estricta de Capas
**Modelo (`modelo/`)**: Contiene toda la lógica de negocio sin dependencias de presentación
- `Tablero`, `Barco`, `Celda`: Estado y reglas del juego
- `Juego`: Coordinador principal del modelo

**Vista (`vista/`)**: Responsable únicamente de presentación y captura de entrada
- `VistaConsola`: Interfaz de usuario sin lógica de negocio
- `VistaTablero`: Renderizado especializado del tablero

**Controlador (`controlador/`)**: Mediador entre Modelo y Vista
- `ControladorJuego`: Orquesta el flujo principal
- `ControladorColocacion`: Gestiona colocación de barcos
- `ControladorEntrada`: Valida y procesa entrada del usuario

### Estrategias de Comunicación
- **Flujo Unidireccional**: Vista → Controlador → Modelo → Vista
- **Interfaces Definidas**: Métodos públicos claros entre capas
- **Sin Referencias Cruzadas**: El modelo nunca conoce la vista directamente

## 3. Decisiones Arquitectónicas

### Estructura de Paquetes
```
Codigo/
├── modelo/     # Lógica de negocio pura
├── vista/      # Presentación e interacción
└── controlador/ # Coordinación y flujo
```

### Patrones de Diseño Aplicados
- **MVC**: Separación clara de responsabilidades
- **Strategy**: `Orientacion` enum para diferentes estrategias de colocación
- **State**: `EstadoCelda` y `EstadoJuego` para gestión de estados
- **Observer Implícito**: Controladores observan cambios en modelo

### Facilitación de Pruebas
- **Inyección de Dependencias**: Constructores reciben dependencias externas
- **Métodos Públicos Testeable**: Cada clase expone interfaz clara para testing
- **Estado Verificable**: Métodos getter permiten verificar estado interno
- **Separación de Concerns**: Cada clase tiene responsabilidad única y testeable

### Consideraciones de Mantenimiento
- **Bajo Acoplamiento**: Cambios en una capa no afectan otras
- **Alta Cohesión**: Cada clase tiene propósito específico y bien definido
- **Extensibilidad**: Arquitectura permite agregar nuevas funcionalidades sin refactoring masivo
- **Legibilidad**: Nombres descriptivos y estructura clara facilitan comprensión del código

