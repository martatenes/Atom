# Atom_Android

Coding Challenge iOS & Android Developer 
Se trataría de construir 2 vistas simples, para una app que se denominaría Atom:
- Vista 1: Lista de las películas más populares (endpoint: GET /movie/popular)
- Vista 2: Detalle de la película seleccionada por el user (endpoint: GET /movie/{id})
Requerimientos:
- Desarrollo nativo en iOS (Swift) y en Android (Java, en Kotlin no es necesario).
- Desarrollo de la UI en base a las vistas adjuntas, solo en portrait, no hace falta tablets.
- API Rest: https://developers.themoviedb.org/3/getting-started/introduction
- Nota de ayuda: Para componer la url de una imagen es necesario también llamar al
endpoint GET /configuration.
- Se puede utilizar cualquier librería de terceros tanto en iOS como en Android, aunque
como HTTP Clients se recomiendan en Android -> Retrofit (https://square.github.io/
retrofit/) y para iOS -> Alamofire (https://github.com/Alamofire/Alamofire)
- Sería un punto importante a favor, si se implementa también la paginación de la lista.
- Clean Code y deseable Git Flow.
- Se valorará MUY positivamente si se desarrolla en las 2 plataformas iOS y Android,
aunque no es obligatorio acabar completamente los desarrollos en cada plataforma.
- Lógicamente, mientras más avance tenga el ejercicio cuando se entregue, más
elementos se van a disponer para poder valorar al candidato
