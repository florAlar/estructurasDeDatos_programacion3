# Análisis Arquitectónico — TPE Programación 3 (2026)

> El código es un WIP. Este documento no analiza lo que falta implementar sino la calidad del diseño actual: principios SOLID, Clean Code y Clean Architecture.

## 1. Arquitectura actual

El proyecto está organizado en cinco capas bien delimitadas:

```
Main
  └── service/Service          ← Fachada pública
        ├── service/CamionService
        │     ├── repo/CamionRepository
        │     └── algoritmos/Backtracking
        └── service/PaqueteService
              └── repo/PaquetesRepository

data/loader/CsvLoader (abstract)
  ├── CamionesLoader  → CamionRepository
  └── PaquetesLoader  → PaquetesRepository

model/
  ├── Camion
  └── Paquete

service/filters/
  ├── Condicion (interface)
  ├── CondicionUrgencia
  └── ContieneAlimentos

algoritmos/
  ├── Backtracking
  ├── Greedy_para_cuando_exista
  └── Solucion
```

### Lo que está bien

- **`Repository<T,K>` como interfaz**: abstracción correcta que separa el contrato del acceso a datos de su implementación concreta.
- **Patrón Strategy en filtros** (`Condicion` + implementaciones): agregar un nuevo criterio de búsqueda no requiere modificar `PaqueteService`. Correcto uso de OCP.
- **`HashMap` en repositorios con justificación de complejidad**: decisión fundamentada y documentada.
- **`Comparable` en modelos**: el orden natural encapsulado en la entidad, no disperso en los algoritmos.
- **Copia defensiva en `obtenerTodos()`**: protege el estado interno del repositorio ante mutaciones externas.
- **Generics en `CsvLoader<T>`** y subclases: extensión por herencia sin modificar la base.

---

## 2. Principios SOLID

### S — Single Responsibility Principle

**`CsvLoader` tiene dos responsabilidades de capas distintas:**

```java
// Responsabilidad 1 — parsear CSV (capa de datos): ✓
public ArrayList<T> cargarDatos(String nombreArchivo) { ... }

// Responsabilidad 2 — construir el repositorio (capa de infraestructura): ✗
public abstract Repository almacenarEnRepo();
```

Un _loader_ solo debería saber transformar líneas de texto en objetos. La decisión de qué repositorio instanciar con esos datos pertenece a quien ensambla la aplicación.

---

**`CamionService` acumula tres roles:**

1. Delegador del repositorio (`getCamion`, `getCamiones`, `existeCamion`).
2. Orquestador de algoritmos (`resolverConBacktracking`, `resolverConGreedy`).
3. Lógica de negocio de asignación (`puedeTransportar`, `getCargaActual`).

El comentario en el propio código lo reconoce: `//consultar si conviene tener esta clase o no`. La respuesta es que sus responsabilidades deberían redistribuirse.

---

### O — Open/Closed Principle

El sistema de filtros es el ejemplo correcto en el proyecto: `Condicion` está abierta a extensión (nuevas implementaciones) y cerrada a modificación (no se toca `PaqueteService`).

Sin embargo, la estrategia de _cómo_ buscar (iteración lineal) está hardcodeada dentro de `getPaquetesFiltrados()`. Si se quisiera cambiar por una búsqueda indexada, habría que modificar el método. Una forma más flexible sería que el repositorio mismo ofrezca métodos especializados, y los servicios simplemente deleguen.

---

### D — Dependency Inversion Principle

Este es el problema más importante del diseño. Los módulos de alto nivel construyen sus propias dependencias en lugar de recibirlas:

```java
// PaqueteService construye su propio loader Y su propio repositorio
public PaqueteService(String pathPaquetes) {
    PaquetesLoader paquetesLoader = new PaquetesLoader(pathPaquetes); // ← instancia concreta
    paqueteRepo = paquetesLoader.almacenarEnRepo();                    // ← instancia concreta
}

// CamionService instancia directamente un algoritmo concreto
public Solucion resolverConBacktracking(ArrayList<Paquete> paquetes) {
    Backtracking bt = new Backtracking(); // ← instancia concreta
    return bt.resolver(...);
}
```

Ninguna de estas clases podría ser testeada en aislamiento sin ejecutar el sistema de archivos y los algoritmos reales.

**Propuesta — Inyección de dependencias en el constructor:**

```java
// PaqueteService recibe lo que necesita, no lo construye
public PaqueteService(PaquetesRepository repo) {
    this.paqueteRepo = repo;
}

// El ensamblado de dependencias ocurre en un único lugar: Main
public static void main(String[] args) {
    List<Paquete> paquetes = new PaquetesLoader().cargarDatos("paquetes.csv");
    List<Camion>  camiones = new CamionesLoader().cargarDatos("camiones.csv");

    PaquetesRepository paquetesRepo = new PaquetesRepository(paquetes);
    CamionRepository   camionesRepo = new CamionRepository(camiones);

    Servicios servicios = new Servicios(paquetesRepo, camionesRepo);
}
```

Esto se llama **Composition Root**: un único punto donde se construyen y conectan todas las dependencias. El resultado es que cada clase puede ser testeada con mocks o datos de prueba sin tocar archivos reales.

---

### I — Interface Segregation Principle

`Repository<T,K>` es pequeña y bien definida. El único punto de mejora menor es que `existe(K id)` es redundante, ya que todas las implementaciones lo resuelven como `buscarPorIdentificador(id) != null`. El cliente que quiera chequear existencia puede directamente comparar con `null`. Si se mantiene por legibilidad, está justificado.

---

### L — Liskov Substitution Principle

Sin violaciones. `CamionRepository` y `PaquetesRepository` son sustitutos correctos de `Repository<T,K>`. `CamionesLoader` y `PaquetesLoader` son sustitutos correctos de `CsvLoader<T>`.

---

## 3. Clean Code

### Convenciones de nombrado (Java)

Java usa **camelCase** para campos y métodos. El código mezcla camelCase con snake_case:

| Actual | Corrección |
|---|---|
| `id_camion` | `idCamion` |
| `esta_refrigerado` | `estaRefrigerado` |
| `capacidad_kg` | `capacidadKg` |
| `codigo_paquete` | `codigoPaquete` |
| `getCodigo_Paquete()` | `getCodigoPaquete()` |
| `Greedy_para_cuando_exista` | `Greedy` |

La inconsistencia más visible es `getCodigo_Paquete()`: un getter público con underscore en el nombre.

---

### Modelos mutables cuando deberían ser inmutables

`Camion` y `Paquete` se construyen desde archivos CSV y nunca cambian durante la ejecución. Sin embargo exponen setters públicos que habilitan mutaciones no intencionales desde cualquier parte del código.

```java
// Actual — setter público innecesario
public void setCapacidad(Double capacidad_kg) {
    this.capacidad_kg = capacidad_kg;
}

// El constructor llama al setter para asignar — antipatrón
public Camion(...) {
    setCapacidad(capacidad_kg); // ← podría ser simplemente this.capacidadKg = capacidadKg
}
```

**Propuesta**: eliminar los setters o hacerlos `private`. El constructor asigna directamente los campos. Las entidades de dominio que son de solo lectura deberían expresar esa inmutabilidad.

---

### Bug silencioso: null pointer antes del null check

En `CsvLoader.cargarDatos()` el stream se pasa al `BufferedReader` **antes** de validar que no sea `null`:

```java
InputStream is = getClass().getResourceAsStream("/" + nombreArchivo);
BufferedReader br = new BufferedReader(new InputStreamReader(is)); // ← NPE aquí si is == null
if (is == null) { throw new RuntimeException(...); }              // ← nunca llega aquí
```

El null check debería ir antes de cualquier uso del stream:

```java
InputStream is = getClass().getResourceAsStream("/" + nombreArchivo);
if (is == null) { throw new RuntimeException("Archivo no encontrado: " + nombreArchivo); }
BufferedReader br = new BufferedReader(new InputStreamReader(is));
```

---

### `System.out.println` en capas de negocio y datos

Los repositorios imprimen mensajes de estado y los servicios imprimen diagnósticos de complejidad. Esto mezcla responsabilidades de presentación con lógica de dominio:

```java
// En PaqueteService — capa de negocio
System.out.println("Complejidad computacional asociada a busqueda en array: O(n)...");

// En CamionRepository — capa de datos
System.out.println(camiones.size() + " camiones cargados en repositorio...");
```

El modelo de presentación debería estar exclusivamente en `Main` o en una capa de reporte separada.

---

### Comentarios que expresan deuda técnica como código

Los comentarios tienen que explicar el **por qué**, no el estado de ánimo del desarrollador:

```java
// ✗ Nota personal embebida en código de producción
public Solucion resolverConGreedy(...) {
    //cuadno veamos greedy  ← typo + nota personal
    return null;
}

// ✗ Duda de diseño en comentario de clase
//consultar si conviene tener esta clase o no, por separacion de responsabilidad.

// ✗ TODO informal en interfaz
//aqui irian eliminar e insertar uno nuevo pero dado que son archivos csv no se como lo haria;
```

Las notas de diseño y dudas pertenecen a un documento externo (como este), no al código fuente.

---

## 4. Clean Architecture

### Regla de dependencias

En Clean Architecture las dependencias apuntan hacia adentro: infraestructura → aplicación → dominio. El diagrama actual tiene una inversión problemática:

```
data/loader ──→ repo   ✗  la capa de datos construye objetos de la capa de repositorio
service     ──→ algoritmos  (debatible: el servicio instancia algoritmos concretos)
```

`CsvLoader.almacenarEnRepo()` hace que la capa de datos conozca y cree repositorios concretos. Eso invierte la dirección: la infraestructura de lectura de archivos no debería saber nada de cómo se almacenan los datos en memoria.

---

### Ausencia de Composition Root

Actualmente cada clase construye sus propias dependencias en cascada:

```
Main
  └── new Service("camiones.csv", "paquetes.csv")
        └── new CamionService(path)
              └── new CamionesLoader(path)
                    └── .almacenarEnRepo()  → new CamionRepository(...)
        └── new PaqueteService(path)
              └── new PaquetesLoader(path)
                    └── .almacenarEnRepo()  → new PaquetesRepository(...)
```

El grafo de dependencias está escondido dentro de los constructores. Nadie desde afuera puede ver qué necesita cada clase sin leer su implementación.

Con un Composition Root explícito en `Main`, el grafo es visible, las dependencias son inyectadas y cada clase puede ser testeada de forma aislada.

---

### Clase abstracta `Algoritmo` para evitar duplicación

`Backtracking` y `Greedy` van a compartir la misma lógica de validación: ¿puede este paquete asignarse a este camión en el estado actual? Sin una base común, esa lógica se duplica en ambas clases.

Una clase abstracta es la herramienta correcta cuando hay **comportamiento concreto compartido**, no solo un contrato. Una interfaz alcanzaría para el contrato, pero no para compartir código:

```java
public abstract class Algoritmo {

    // Contrato: cada algoritmo implementa su propia estrategia
    public abstract Solucion resolver(List<Camion> camiones, List<Paquete> paquetes);

    // Comportamiento compartido: la restricción de asignación es igual para ambos
    protected boolean puedeAsignar(Camion camion, Paquete paquete, double pesoActual) {
        boolean tieneCapacidad = pesoActual + paquete.getPeso() <= camion.getCapacidad();
        boolean cumpleRefrigeracion = !paquete.contieneAlimentos() || camion.estaRefrigerado();
        return tieneCapacidad && cumpleRefrigeracion;
    }
}
```

```java
public class Backtracking extends Algoritmo { ... }
public class Greedy      extends Algoritmo { ... }
```

El beneficio adicional es que `Servicios` pasa a depender del tipo abstracto en lugar de las clases concretas, lo que refuerza el DIP:

```java
public class Servicios {
    private final Algoritmo backtracking;
    private final Algoritmo greedy;

    public Servicios(PaquetesRepository paquetes, CamionRepository camiones,
                     Algoritmo backtracking, Algoritmo greedy) { ... }
}
```

---

### `Solucion` en el paquete equivocado

`Solucion` vive en `algoritmos` pero representa un concepto del dominio del problema: una asignación de paquetes a camiones con sus métricas. Al estar en `algoritmos`, el paquete `service` (capa superior) depende del paquete `algoritmos` (capa de implementación) para usar el tipo de retorno. Esto acopla capas que deberían estar separadas.

**Propuesta**: mover `Solucion` al paquete `model` o a un paquete `resultado`, haciéndolo un tipo del dominio que tanto `service` como `algoritmos` conocen.

---

## 5. Diagrama de arquitectura propuesta

```
┌─────────────────────────────────────────────────────┐
│  Main (Composition Root)                            │
│  Construye y conecta todas las dependencias         │
└─────────────────┬───────────────────────────────────┘
                  │
┌─────────────────▼───────────────────────────────────┐
│  service/Servicios                                  │
│  Recibe repo y algoritmos por constructor           │
│  Expone servicio1..5 del enunciado                  │
└──────┬──────────────────────┬───────────────────────┘
       │                      │
┌──────▼──────┐    ┌──────────▼──────────┐
│  repo/      │    │  algoritmos/        │
│  Paquetes   │    │  Algoritmo (abs)    │
│  Repository │    │    ├─ Backtracking  │
│  Camion     │    │    └─ Greedy        │
│  Repository │    └──────────┬──────────┘
└──────┬──────┘               │
       │           ┌──────────▼──────────┐
       │           │  model/Solucion     │
       │           └─────────────────────┘
┌──────▼──────────────────────────────────────────────┐
│  model/  Camion · Paquete  (inmutables)             │
└─────────────────────────────────────────────────────┘

data/loader/  (solo parseo, retorna List<T>, no crea repos)
service/filters/  (estrategias de filtrado, sin cambios)
```

---

## 6. Resumen de mejoras

| Principio | Problema | Corrección |
|---|---|---|
| **SRP** | `CsvLoader` parsea CSV Y construye repositorios | Separar: loader retorna `List<T>`, repo se crea afuera |
| **SRP** | `CamionService` delega repo + orquesta algoritmos + lógica de negocio | Redistribuir responsabilidades |
| **DIP** | Servicios construyen sus propias dependencias con `new` | Inyección por constructor, Composition Root en `Main` |
| **DIP** | `CamionService` instancia `Backtracking` directamente | El algoritmo se inyecta o `Servicios` lo instancia |
| **DIP / OCP** | `Backtracking` y `Greedy` sin tipo común | Clase abstracta `Algoritmo` con `puedeAsignar()` compartido |
| **Clean Code** | snake_case en campos y métodos Java | Usar camelCase consistentemente |
| **Clean Code** | Setters públicos en entidades inmutables | Setters `private`, asignación directa en constructor |
| **Clean Code** | `System.out.println` en repositorios y servicios | Presentación solo en `Main` o capa de reporte |
| **Clean Code** | Comentarios con notas personales y dudas de diseño | Documentos externos (como este), no en el código |
| **Bug** | Null check en `CsvLoader` después de usar el stream | Mover la validación antes del `new BufferedReader(...)` |
| **Clean Arch** | `data/loader` crea instancias de `repo` | Invertir: loader retorna datos, repo se construye en Composition Root |
| **Clean Arch** | `Solucion` en paquete `algoritmos` | Mover a `model` — es un tipo del dominio, no del algoritmo |
| **Raw type** | `public abstract Repository almacenarEnRepo()` | `public abstract Repository<T, ?> almacenarEnRepo()` (o eliminar el método) |
