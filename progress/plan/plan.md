# Plan de implementación — Refactor arquitectónico TPE Prog3

## Objetivo
Aplicar mejoras de SOLID, Clean Code y Clean Architecture identificadas en `ANALISIS_ARQUITECTONICO.md`.
Organizado en 4 fases con dependencias entre ellas. Dentro de cada fase los pasos marcados como *paralelo* son independientes entre sí.

---

## Fase 1 — Modelos y fundamentos
*Sin dependencias externas. Todos los pasos son paralelos entre sí.*

- [ ] **Paso 1** — Renombrar snake_case → camelCase en `Camion` y `Paquete`:
  - Campos: `id_camion` → `idCamion`, `esta_refrigerado` → `estaRefrigerado`, `capacidad_kg` → `capacidadKg`, `codigo_paquete` → `codigoPaquete`, `peso_kg` → `pesoKg`, `contiene_alimentos` → `contieneAlimentos`, `nivel_urgencia` → `nivelUrgencia`
  - Actualizar constructor, getters, `toString`, `compareTo` en ambas clases
  - Renombrar `getCodigo_Paquete()` → `getCodigoPaquete()`
  - Actualizar referencias en: `PaquetesLoader`, `PaquetesRepository`, `CondicionUrgencia`, `ContieneAlimentos`

- [ ] **Paso 2** — Hacer setters `private` en `Camion` y `Paquete`:
  - Reemplazar llamadas a setters en el constructor por asignación directa `this.campo = param`

- [ ] **Paso 3** — Fix bug null check en `CsvLoader`:
  - Mover `if (is == null) throw ...` **antes** de `new BufferedReader(new InputStreamReader(is))`

- [ ] **Paso 4** — Corregir raw type en `CsvLoader`:
  - `public abstract Repository almacenarEnRepo()` → `public abstract Repository<T, ?> almacenarEnRepo()`

**Verificación Fase 1:** `mvn compile` sin errores ni warnings.

---

## Fase 2 — Repositorios
*Depende de Fase 1.*

- [ ] **Paso 5** — Eliminar todos los `System.out.println` de:
  - `CamionRepository`
  - `PaquetesRepository`
  - `CondicionUrgencia`
  - `ContieneAlimentos`
  - `PaqueteService`

> **Pendiente (consultar con el equipo y el profesor):**
> - ¿Conviene `TreeMap<Integer, List<Paquete>>` en `PaquetesRepository` para mejorar servicio3 de O(n) a O(log n + k)?
> - ¿Conviene pre-indexar listas por alimentos para mejorar servicio2 de O(n) a O(k)?

**Verificación Fase 2:** Ejecutar `Main` y confirmar que servicio1, servicio2 y servicio3 retornan resultados correctos.

---

## Fase 3 — Arquitectura
*Depende de Fases 1 y 2.*

- [ ] **Paso 6** — Mover `Solucion` al paquete `model`:
  - Cambiar `package algoritmos` → `package model`
  - Actualizar todos los imports en `Service`, `CamionService`, `Backtracking`, `Greedy`

- [ ] **Paso 7** — Crear clase abstracta `algoritmos/Algoritmo.java`: *(depende de paso 6)*
  - Método abstracto: `public abstract Solucion resolver(List<Camion> camiones, List<Paquete> paquetes)`
  - Método concreto compartido: `protected boolean puedeAsignar(Camion camion, Paquete paquete, double pesoActual)`

- [ ] **Paso 8** — `Backtracking extends Algoritmo`, renombrar `Greedy_para_cuando_exista` → `Greedy extends Algoritmo` *(depende de paso 7)*

- [ ] **Paso 9** — Eliminar `almacenarEnRepo()` de `CsvLoader` y subclases: *(paralelo con pasos 6–8)*
  - Quitar método abstracto de `CsvLoader`
  - Quitar implementación en `CamionesLoader` y `PaquetesLoader`
  - Ambos loaders quedan solo con `parsearLinea()`

- [ ] **Paso 10** — Composition Root + renombrar `Service` → `Servicios`: *(depende de pasos 7, 8 y 9)*
  - Renombrar clase `Service` → `Servicios`
  - Cambiar firmas a `List<Paquete>` (en lugar de `ArrayList<Paquete>`)
  - Cambiar constructor para recibir `PaquetesRepository`, `CamionRepository`, `Algoritmo backtracking`, `Algoritmo greedy`
  - Eliminar `CamionService` — sus métodos de delegación pasan directamente a `Servicios`
  - Refactorizar `PaqueteService` para recibir `PaquetesRepository` por constructor (sin construirlo internamente)
  - En `Main`: construir loaders → construir repos → construir algoritmos → construir `Servicios`

**Verificación Fase 3:** Ejecutar los 3 servicios y confirmar resultados idénticos a antes del refactor.

---

## Fase 4 — Presentación
*Depende de Fase 3.*

- [ ] **Paso 11** — Actualizar `Main` para usar `util/Consola` (ya creada):
  - Reemplazar `System.out.println` restantes por llamadas a `Consola`
  - Dar formato de salida según el enunciado para Backtracking y Greedy

**Verificación Fase 4:** Confirmar que no existe ningún `System.out.println` fuera de `util/Consola`.

---

## Archivos afectados

| Archivo | Pasos | Cambio |
|---|---|---|
| `model/Camion.java` | 1, 2 | Renombrado + inmutabilidad |
| `model/Paquete.java` | 1, 2 | Renombrado + inmutabilidad |
| `model/Solucion.java` | 6 | Movido desde `algoritmos/` |
| `data/loader/CsvLoader.java` | 3, 4, 9 | Fix NPE + raw type + eliminar `almacenarEnRepo()` |
| `data/loader/CamionesLoader.java` | 9 | Eliminar `almacenarEnRepo()` |
| `data/loader/PaquetesLoader.java` | 9 | Eliminar `almacenarEnRepo()` |
| `repo/PaquetesRepository.java` | 5 | Eliminar println |
| `repo/CamionRepository.java` | 5 | Eliminar println |
| `repo/Repository.java` | — | Sin cambios |
| `service/filters/CondicionUrgencia.java` | 1, 5 | Renombrado + eliminar println |
| `service/filters/ContieneAlimentos.java` | 5 | Eliminar println |
| `service/PaqueteService.java` | 5, 10 | Eliminar println + recibir repo por constructor |
| `service/Service.java` → `Servicios.java` | 10 | Renombrado + inyección + firmas `List<>` |
| `service/CamionService.java` | 10 | Eliminado |
| `algoritmos/Algoritmo.java` | 7 | Nuevo |
| `algoritmos/Backtracking.java` | 8 | `extends Algoritmo` |
| `algoritmos/Greedy.java` | 8 | Renombrado + `extends Algoritmo` |
| `Main.java` | 10, 11 | Composition Root + `Consola` |
| `util/Consola.java` | — | Ya creado |
