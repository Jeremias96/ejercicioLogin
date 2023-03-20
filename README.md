# ejercicioLogin 

Proyecto usado como ejercicio basado en un login con los endpoint /sign-up y /login

Utiliza Java 8 y Spring 2.7

Para limpiar /build:

/gradlew clean

Para compilar y correr tests:

/gradlew build

Para compilar sin tests:

/gradlew build --x test

Endpoints:

/v1/sign-up: Registra un nuevo usuario
-Recibe un nombre, email, password y lista de telefonos
-Devuelve el usuario creado

/v1/login: Verifica un usuario ya registrado
-Recibe un token de login
-Devuelve el usuario guardado