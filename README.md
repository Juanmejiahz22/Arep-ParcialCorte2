# Parcial AREP segundo corte+
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

# Desarrollo
- Primeramente creamos un proyecto maven llamado "Mathservices" el cual contendra el proceso del ejercicio matematico y proveera las funciones para solucionarlo.
![alt text](imageMathTree.png)

- Tambien creamos otro proyecto maven llamado "proxy" el cual contendra la estructura minima del proxy el cual evitara que el servicio se caiga y tendra un sistemas activo-pasivo logrando estructurar un respaldo (pasivo) por si el servicio principal se cae (activo), esto hace que se cumpla con la caracteristica de disponiblidad para el servicio.
![alt text](imageProxyTree.png)

Se diseña de esta manera la estructura del sistema para que de esta manera haya una correcta distribucion y delegacion de responsabilidades o funciones, ayudando a mantener un orden.
