# Parcial AREP segundo corte
## Por: Juan Jose Mejia Celis

### Instrucciones y ejercicio

# Fibonacci con ventana (suma móvil K=3)
Su servicio matemático debe incluir una función:

fibwin(n) retorna un JSON con la serie de Fibonacci desde F_0 hasta F_n y la suma móvil de ventana K=3 aplicada a esa serie. (Recibe enteros n ≥ 2).
- Definición
Fibonacci: F_0=0, F_1=1, F_n=F_{n-1}+F_{n-2} para n ≥ 2.
Ventana K=3: para la lista [a0, a1, …, am], la suma móvil es [a0+a1+a2, a1+a2+a3, …].

- Implementación requerida
(1) Genere Fibonacci de forma iterativa. (2) Calcule la suma móvil recorriendo la lista y sumando tramos contiguos de longitud 3. No use funciones de librería que implementen rolling windows.

- Detalles de API

Responda a HTTP GET. Nombre del endpoint: /fibwin. Parámetro en query: value.

- Ejemplo de llamado (EC2)

https://amazonxxx.x.xxx.x.xxx:{port}/fibwin?value=10

- Salida (JSON)

{
  "operation": "Fibonacci con ventana K=3",
  "input": 10,
  "output": "serie: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55 | ventana: 2, 4, 6, 10, 16, 26, 42, 68, 110"
}

## Desarrollo
- Primeramente creamos un proyecto maven con spring boot initializr llamado "Mathservices" el cual contendra el proceso del ejercicio matematico y proveera las funciones para solucionarlo.
![alt text](imageMathTree.png)

- Tambien creamos otro proyecto maven con spring boot initializr llamado "proxy" el cual contendra la estructura minima del proxy el cual evitara que el servicio se caiga y tendra un sistemas activo-pasivo logrando estructurar un respaldo (pasivo) por si el servicio principal se cae (activo), esto hace que se cumpla con la caracteristica de disponiblidad para el servicio.
![alt text](imageProxyTree.png)

Se diseña de esta manera la estructura del sistema para que de esta manera haya una correcta distribucion y delegacion de responsabilidades o funciones, ayudando a mantener un orden.

- Se crea el servicio web a partir de la funcion fibwin la cual desarrolla la idea matematica planteada en el ejercicio y se almacenara en las dos instancias de Math.

- Luego creamos las instancias necesarias para el sistema, dos para el servicio de Math tanto para el servicio principal (activo) como para el respaldo (pasivo), ademas tenemos una estancia para el servidor proxy el cual administrara el flujo de informacion hacia cada estancia de Math.

<img width="691" height="146" alt="image" src="https://github.com/user-attachments/assets/09a32988-ba81-4253-b995-4fd52d1761ca" />

- Habilitamos los puertos necesarios para crear las conexiones, en el caso de Math (activo) se habilita puertos ssh (22) y http (8081), con Math2 es igual solo que se le habilita otro puerto (8080) para no interrumpir el servicio princial, con el proxy se habilita los mismos para permitir la conexion entre cada servicio.

<img width="1659" height="225" alt="image" src="https://github.com/user-attachments/assets/02bc21b3-89b9-47c2-bcac-85caab8695b2" />

- luego instalamos java en ambas instancias de Math para poder implementar lo basico y clonar el repo en ambas instancias, de esta manera se realiza un subida mas directa y se evita usar conexion ssh y depender de las claves .pem

+ imagen

El objetivo es poder apagar el servicio en una de las instancisas y que al recargar la direccion el servidor proxy mantenga en pie el servicio con el respaldo que existe en math2 (pasivo)

Se estructuro el sistema con el respectivo ejercicio matematico y toda la estructura del servicio, se crearon las instancias tanto la principal como la de respaldo y la instancia donde se aloja el servicio proxy, se les instalo las herramientas a cada instancia y se habilitaron los puertos necesarios.

