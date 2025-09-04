# Análisis de Requerimientos - Barcoss Java MVC

## Requerimientos Identificados

### Obligatorios (No negociables)
1. **Lenguaje**: Java
2. **Arquitectura**: MVC claramente separada
3. **Estructura de datos**: Matriz de objetos (no primitivos)
4. **Juego por turnos**: 2 jugadores alternando
5. **Reglas Battleship**:
   - Flota con al menos 4 barcos de longitudes distintas
   - Sistema de disparos por coordenadas
   - Registro de impactos/agua/hundimientos
   - Condición de victoria
6. **Persistencia**: Historial de jugadas
7. **Interfaz**: Vista desacoplada del Modelo
8. **Validaciones**: Manejo de entradas inválidas

### Decisiones de Diseño Propuestas

#### 1. Matriz de Objetos
**Ubicación**: En la clase `Tablero` dentro del Modelo
**Propósito**: Representar el estado de cada celda del tablero
```java
Celda[][] tablero = new Celda[10][10];
```

**¿Por qué objetos y no primitivos?**
- Cada `Celda` puede contener múltiple información:
  - Estado (agua, barco, impactado, hundido)
  - Referencia al barco que ocupa (si hay uno)
  - Coordenadas
  - Historial de disparos

#### 2. Arquitectura MVC

**Modelo**:
- `Juego`: Lógica principal y estado del juego
- `Tablero`: Matriz de celdas y operaciones del tablero
- `Barco`: Representación de cada nave
- `Celda`: Estado individual de cada posición
- `Jugador`: Información y estado de cada participante

**Vista**:
- `VistaConsola`: Interfaz de texto para interacción
- `VistaTablero`: Visualización del tablero
- `VistaMenu`: Menús y opciones

**Controlador**:
- `ControladorJuego`: Coordina la lógica del juego
- `ControladorEntrada`: Maneja input del usuario
- `ControladorPartida`: Gestiona turnos y estados

#### 3. Configuración Propuesta
- **Tablero**: 10x10 (estándar)
- **Flota**: 
  - 1 Portaaviones (5 celdas)
  - 1 Acorazado (4 celdas)  
  - 2 Cruceros (3 celdas cada uno)
  - 3 Destructores (2 celdas cada uno)
  - 4 Submarinos (1 celda cada uno)

## Próximos Pasos
1. Crear diagrama UML detallado
2. Implementar estructura de clases
3. Desarrollar lógica del juego
4. Crear interfaz de usuario
5. Integrar componentes MVC
