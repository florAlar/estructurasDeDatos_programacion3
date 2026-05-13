# Programación 3 - Trabajo Práctico Especial
**Cursada 2026**

## Contexto
El trabajo práctico propone resolver problemas asociados a un contexto simplificado de **paquetes y camiones de reparto** [1]. 
* **Etapa 1:** Resolución de servicios simples de búsqueda y consulta sobre los paquetes [1].
* **Etapa 2:** Asignación de paquetes a camiones utilizando técnicas de **Backtracking** y **Greedy** [1].

## Formato de los Datos de Entrada
Los datos provienen de dos archivos de texto con el siguiente formato [2]:

* **Camiones.csv**: `<camiones_totales> <id_camion>;<patente>;<esta_refrigerado>;<capacidad_kg>` [2].
* **Paquetes.csv**: `<paquetes_totales> <id_paquete>;<codigo_paquete>;<peso_kg>;<contiene_alimentos>;<nivel_urgencia>` [2].

**Definiciones:**
* Un **camión** posee un ID único, patente, indicador de refrigeración y capacidad máxima en kg [2].
* Un **paquete** posee un ID único, código identificador, peso en kg, indicador de alimentos y un nivel de urgencia (1 a 100) [2].
* El primer problema a resolver es la **carga y procesamiento** de estos archivos [3].

---

## Primera Parte: Servicios de Consulta
Se debe plantear una resolución eficiente para los siguientes servicios [3]:

1. **Servicio 1:** Dado un código de paquete (String), retornar su información o `null` si no existe [3].
2. **Servicio 2:** Dado un booleano, retornar el listado de paquetes que contienen (true) o no (false) alimentos [3].
3. **Servicio 3:** Dados un nivel de urgencia mínimo y máximo, retornar los paquetes dentro de ese rango inclusive [3].

### Estructura de la Clase `Servicios`
Se debe completar la siguiente estructura expresando la **complejidad temporal** de cada método [4, 5]:

```java
public class Servicios {
    // Estructuras y métodos privados necesarios
    
    public Servicios(String pathCamiones, String pathPaquetes) { }
    
    public Paquete servicio1(String codigoPaquete) { }
    
    public List<Paquete> servicio2(boolean contieneAlimentos) { }
    
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) { }
}

--------------------------------------------------------------------------------

## Segunda Parte

Esta etapa tiene como objetivo establecer una asignación de todos los paquetes a los camiones disponibles, buscando **minimizar el peso total de los paquetes que no pudieron ser asignados** a ningún vehículo [1]. 

### Restricciones de Asignación
Para asignar un paquete a un camión se deben cumplir las siguientes condiciones:
* **Capacidad máxima:** Ningún camión puede superar su capacidad de carga definida en el archivo de entrada [2].
* **Refrigeración:** Los paquetes que contienen alimentos solo pueden ser asignados a camiones que cuenten con refrigeración [2].

### Técnicas Algorítmicas Requeridas
El problema debe resolverse utilizando dos enfoques distintos [2]:
1. **Backtracking:** El algoritmo debe explorar el espacio de soluciones correctamente respetando las restricciones impuestas [2], [3].
2. **Greedy:** Se debe definir y justificar claramente una función de selección para la resolución [2], [3].

### Presentación de Resultados
Para ambas técnicas, se deben presentar los resultados siguiendo este formato [4]:
* **Solución obtenida:** Listado de cada camión con sus paquetes asignados [4].
* **Peso no asignado:** Total de kilogramos de los paquetes que quedaron fuera de la asignación [4].
* **Métrica de costo:** 
    * En **Backtracking**, se debe informar la cantidad de **estados generados** [4].
    * En **Greedy**, se debe informar la cantidad de **candidatos considerados** [4].

### Documentación
Se solicita incluir una **breve explicación de la estrategia** elegida para cada técnica mediante un comentario previo a la implementación de la función principal [4], [5].