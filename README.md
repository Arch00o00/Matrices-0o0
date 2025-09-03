
# Barcosss Java MVC

## Descripción del Proyecto

Implementación completa del clásico juego....

##  Características Principales

### Arquitectura MVC Estricta
- **Modelo**: Lógica de negocio y estado del juego
- **Vista**: Interfaz de usuario (consola)
- **Controlador**: Coordinación entre Modelo y Vista

### Matriz de Objetos
- **`Celda[][] tablero`** en lugar de tipos primitivos
- Cada celda contiene estado, barco asignado, historial de disparos
- Implementación orientada a objetos completa

### Juego Completo
- Sistema de turnos alternados
- Flota completa de 11 barcos con diferentes longitudes
- Colocación manual y automática de barcos
- Sistema de disparos con feedback detallado
- Condiciones de victoria y estadísticas

##  Estructura del Proyecto

```
BarcosssMVC/
├── Codigo/
│   ├── modelo/                    #  CAPA MODELO
│   │   ├── Juego.java            # Lógica principal del juego
│   │   ├── Jugador.java          # Estado y acciones del jugador
│   │   ├── Tablero.java          # MATRIZ DE OBJETOS Celda[][]
│   │   ├── Celda.java            # Estado individual de cada posición
│   │   ├── Barco.java            # Representación de las naves
│   │   ├── EstadoJuego.java      # Enum: estados del juego
│   │   ├── EstadoCelda.java      # Enum: estados de celda
│   │   ├── Orientacion.java      # Enum: orientación de barcos
│   │   ├── ResultadoDisparo.java # Enum: resultados de disparo
│   │   └── TipoBarco.java        # Enum: tipos de barcos
│   ├── vista/                     # CAPA VISTA
│   │   ├── VistaConsola.java     # Interfaz de consola básica
│   │   ├── VistaTablero.java     # Visualización de tableros
│   │   ├── VistaMenu.java        # Menús del juego
│   │   └── VistaColocacionBarcos.java # Interfaz colocación
│   ├── controlador/               # CAPA CONTROLADOR
│   │   ├── ControladorJuego.java # Coordinación principal
│   │   ├── ControladorEntrada.java # Validación de entrada
│   │   └── ControladorColocacion.java # Lógica colocación
│   └── Main.java                  #  Punto de entrada
└── README.md                      #  Este archivo
```

##  Flota del Juego

| Tipo | Cantidad | Longitud | Total Celdas |
|------|----------|----------|--------------|
| Portaaviones | 1 | 5 celdas | 5 |
| Acorazado | 1 | 4 celdas | 4 |
| Crucero | 2 | 3 celdas | 6 |
| Destructor | 3 | 2 celdas | 6 |
| Submarino | 4 | 1 celda | 4 |
| **TOTAL** | **11** | - | **25** |

##  Funcionalidades

### Configuración
- Registro de nombres de jugadores
- Colocación manual o automática de barcos
- Validación de posiciones y orientaciones
- Vista previa del tablero durante colocación

###  Gameplay
- Turnos alternados entre jugadores
- Sistema de coordenadas alfanumérico (A1-J10)
- Feedback inmediato de disparos:
  -  **Agua**: No hay barco
  -  **Impacto**: Golpe a barco
  -  **Hundido**: Barco completamente destruido
- Tableros duales (propio y enemigo)

###  Estadísticas
- Disparos realizados e impactos logrados
- Cálculo de precisión en tiempo real
- Historial completo de jugadas
- Estadísticas finales del juego

###  Controles
- Pausa y reanudación del juego
- Visualización de estadísticas durante partida
- Confirmaciones para acciones importantes
- Manejo robusto de errores

##  Compilación y Ejecución

### Prerrequisitos
- **Java 8** o superior
- Terminal/Consola de comandos

### Compilación
```bash
cd BarcosssMVC

# Compilar todos los archivos Java
javac -d . Codigo.
# tambien se puede compilar de forma recursiva
find Codigo -name "*.java" -exec javac -cp Codigo {} \;
```

### Ejecución
```bash
# Ejecutar desde el directorio raíz
java -cp Codigo Main

# O si compiló con -d .
java Main
```

### Compilación y Ejecución en Un Comando
```bash
# Compilar y ejecutar directamente
javac Codigo/**/*.java Codigo/*.java && java -cp Codigo Main
```

## 🎯 Cómo Jugar

### 1. Inicio del Juego
1. Ejecutar la aplicación
2. Seleccionar "Nuevo Juego" en el menú principal
3. Ingresar nombres de los dos jugadores

### 2. Colocación de Barcos
1. Elegir modo manual o automático
2. **Manual**: Especificar posición (ej: A1) y orientación (H/V) para cada barco
3. **Automático**: El sistema coloca todos los barcos aleatoriamente

### 3. Batalla
1. Los jugadores alternan turnos
2. Ingresar coordenadas de disparo (ej: B5, J10)
3. Observar resultado y actualización de tableros
4. Continuar hasta hundir toda la flota enemiga

### 4. Victoria
- El primer jugador en hundir todos los barcos enemigos gana
- Se muestran estadísticas finales y historial completo

##  Detalles Técnicos

### Matriz de Objetos
```java
// En la clase Tablero
private Celda[][] tablero;  // MATRIZ DE OBJETOS (NO primitivos)

// Inicialización
for (int i = 0; i < tamaño; i++) {
    for (int j = 0; j < tamaño; j++) {
        tablero[i][j] = new Celda(i, j);  // Objeto Celda
    }
}
```

### Separación MVC
- **Modelo**: No conoce la Vista, solo lógica pura
- **Vista**: No accede directamente al Modelo
- **Controlador**: Único intermediario entre Modelo y Vista

### Validaciones Implementadas
- Coordenadas dentro del tablero (A1-J10)
- Nombres de jugador (2-20 caracteres alfanuméricos)
- Colocación de barcos sin solapamiento
- Prevención de disparos repetidos
- Orientación válida de barcos

##  Manejo de Errores

- **Entradas inválidas**: Mensajes claros y reintento automático
- **Posiciones incorrectas**: Validación y feedback específico
- **Estados inconsistentes**: Verificaciones de integridad
- **Errores de compilación**: Mensajes de diagnóstico detallados

## Extensibilidad

El diseño MVC permite fácil extensión:
- **Nuevos tipos de vista**: GUI, web, móvil
- **Diferentes tamaños de tablero**: Modificar constante
- **Nuevos tipos de barco**: Agregar a enum TipoBarco
- **Modos de juego**: IA, multijugador en red
- **Persistencia**: Guardar/cargar partidas

## Cumplimiento de Requerimientos

✅ **Lenguaje**: Java  
✅ **Arquitectura**: MVC estrictamente separada  
✅ **Estructura de datos**: Matriz de objetos `Celda[][]`  
✅ **Juego por turnos**: 2 jugadores alternando  
✅ **Reglas Battleship**: Implementación completa  
✅ **Persistencia**: Historial de jugadas en memoria  
✅ **Interfaz**: Vista desacoplada del Modelo  
✅ **Validaciones**: Manejo robusto de entradas inválidas  

##  Notas de Desarrollo

- **Patrón de diseño**: MVC con separación estricta
- **Principios SOLID**: Aplicados en el diseño de clases
- **Encapsulación**: Atributos privados con getters/setters apropiados
- **Polimorfismo**: Uso de enums y interfaces donde corresponde
- **Mantenibilidad**: Código documentado y estructura clara

---

**¡Disfruta jugando Barcoss!** ⚓

*Desarrollado siguiendo las mejores prácticas de programación orientada a objetos y arquitectura MVC.*
