# Simulador Pokémon

## Descripción del proyecto

Aplicación desarrollada en Java que simula un juego de Pokémon por consola. El jugador puede iniciar una nueva partida o continuar una guardada, capturar Pokémon en distintas zonas, gestionar su equipo mediante el PC, retar gimnasios en orden y desafiar al Alto Mando. El sistema utiliza archivos `.txt` para cargar la información base del juego y para guardar el progreso del jugador. Los combates se resuelven comparando estadísticas totales de cada Pokémon, aplicando una tabla de efectividad de tipos de 18x18. El sistema incorpora validación de entradas para evitar errores al ingresar opciones en los menús.

---

# Integrantes

- **Nombre:** Andy Alfaro Perez  
- **RUT:** 21.918.973-7  
- **GitHub:** AndyAlfaroPerez  

---

# Tecnologías utilizadas

- Java
- Programación Orientada a Objetos
- Manejo de archivos `.txt`
- ArrayList
- Scanner
- BufferedWriter
- Random

---

# Estructura del proyecto

## Main.java

Contiene la lógica completa del juego, incluyendo:

- Menú inicial y menú principal del juego
- Captura de Pokémon con porcentaje de aparición por zona
- Acceso al PC para intercambiar Pokémon del equipo
- Sistema de combate contra gimnasios y Alto Mando
- Lectura de archivos mediante Scanner
- Escritura y guardado de partida mediante BufferedWriter

---

## pokemon.java
Representa a un Pokémon con nombre, hábitat, porcentaje de aparición, estadísticas, tipo y estado (`Vivo/Debilitado`).

---

## entrenador.java
Clase base que representa a un entrenador con nombre y equipo de Pokémon.

---

## jugador.java
Extiende entrenador. Agrega medallas, último líder derrotado y Pokémon almacenados en el PC.

---

## pokedex.java
Repositorio de todos los Pokémon disponibles. Permite buscar por nombre y filtrar por hábitat.

---

## gimnasio.java
Representa un gimnasio con número, líder, estado y equipo de Pokémon.

---

## altomando.java
Representa a un miembro del Alto Mando con su equipo de 6 Pokémon.

---

## TablaTipos.java
Clase utilitaria con la matriz de efectividad de tipos 18x18.

---

## Pokedex.txt

Archivo con todos los Pokémon disponibles en el formato:
nombre;habitat;probabilidad;vida;ataque;defensa;ataqueEsp;defensaEsp;velocidad;tipo

---

## Gimnasios.txt

Archivo con los 8 gimnasios en el formato:
numero;lider;estado;cantPokemons;pokemon1;pokemon2;...

---

## Alto Mando.txt

Archivo con los 7 miembros del Alto Mando en el formato:
numero;nombre;pokemon1;pokemon2;...;pokemon6

---

## Habitats.txt

Archivo con las zonas disponibles para explorar.

---

## Registros.txt
Archivo donde se guarda la partida del jugador en el formato:
nombreJugador;ultimoLiderDerrotado
pokemon;estado

---

# Funcionalidades implementadas

- Menú inicial y menú principal del juego
- Nueva partida y continuar partida guardada
- Guardado de progreso en Registros.txt
- Carga de Pokémon, gimnasios, hábitats y Alto Mando desde archivos `.txt`
- Captura de Pokémon según hábitat y porcentaje de aparición
- Validación para no capturar Pokémon repetidos
- Revisión del equipo del jugador
- Acceso al PC para intercambiar posiciones de Pokémon
- Curación de Pokémon debilitados
- Combate contra gimnasios con validación de orden
- Combate consecutivo contra el Alto Mando
- Aplicación de efectividad de tipos en combate usando matriz 18x18

---

# Cómo ejecutar el programa

1. Tener instalado Java (JDK).  
2. Clonar o descargar el proyecto.  
3. Verificar que los archivos `.txt` estén en la raíz del proyecto.  
4. Compilar el programa: `javac Taller2/*.java`  
5. Ejecutar el programa: `java Taller2.Main`  
6. Interactuar con el sistema mediante el menú en consola.
