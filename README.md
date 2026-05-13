# TPE Prog3 - Estructuras de Datos

Aplicacion Java que carga camiones y paquetes desde archivos CSV, construye repositorios en memoria y ejecuta consultas del dominio junto con estrategias de resolucion.

## Requisitos

- Java 17
- Maven

## Dependencias

El proyecto se apoya en herramientas del ecosistema Java:

- JDK 17
- Maven para compilacion y ejecucion

No requiere dependencias externas de aplicacion configuradas manualmente.

## Estructura del proyecto

```text
src/main/java
|- algoritmos/   # Estrategias de resolucion
|- model/        # Entidades del dominio
|- repo/         # Repositorios en memoria
|- service/      # Servicios de consulta
|- util/         # Utilidades de salida
`- Main.java     # Punto de entrada

src/main/resources
|- camiones.csv
`- paquetes.csv
```

## Como levantarlo

Desde la raiz del repositorio:

```bash
mvn compile
java -cp target/classes Main
```

Tambien podes hacerlo en un solo comando:

```bash
mvn -q compile && java -cp target/classes Main
```

## Ejecucion

Al iniciar, la aplicacion:

1. Carga los datos desde `src/main/resources`.
2. Construye los repositorios y servicios.
3. Ejecuta las consultas y muestra los resultados por consola.

## Como probarlo

### Compilacion

```bash
mvn -q compile
```

### Prueba manual

```bash
java -cp target/classes Main
```

La ejecucion correcta debe producir salida por consola con resultados de las consultas disponibles.

## Datos de entrada

Los datos de ejemplo se encuentran en:

- `src/main/resources/camiones.csv`
- `src/main/resources/paquetes.csv`

Si queres probar otros escenarios, reemplaza esos archivos respetando el formato esperado y volve a compilar.
