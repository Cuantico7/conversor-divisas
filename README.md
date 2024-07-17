# CHALLENGE _CONVERSOR DE MONEDAS_

## Descripción.
Este proyecto es un desafío del programa Oracle Next Education y Alura Latam. El objetivo es construir un conversor de monedas utilizando Java 17. El conversor obtiene las tasas de cambio actuales desde una API y permite convertir cantidades entre diferentes monedas.

El conversor de monedas proporciona un menú interactivo donde el usuario puede seleccionar opciones para convertir entre diferentes monedas. Las tasas de cambio se obtienen en tiempo real a través de una solicitud HTTP a la API de ExchangeRate. El programa permite convertir entre las siguientes monedas:

- Dólar (USD) a Peso argentino (ARS)
- Peso argentino (ARS) a Dólar (USD)
- Dólar (USD) a Real brasileño (BRL)
- Real brasileño (BRL) a Dólar (USD)
- Dólar (USD) a Peso colombiano (COP)
- Peso colombiano (COP) a Dólar (USD)


### Estructura del Proyecto
El proyecto tiene tres clases:

1. `ConexionApi`: Esta clase se encarga de realizar la solicitud HTTP a la API de ExchangeRate y obtener las tasas de cambio.
2. `Main`: Esta clase contiene el menú interactivo y la lógica principal del programa.
3. `Monedas`: Esta clase almacena los datos de las tasas de cambio obtenidos de la API.


### Recursos Utilizados
- Java 17
- IntelliJ IDEA: IDE.
- ExchangeRate: API para obtener tipo de cambio.
- Library Gson.



### AUTOR

Giovanni Roque




