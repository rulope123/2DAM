import tkinter as tk
import random
import math
import json
import sqlite3
from tkinter import ALL
from tkinter import messagebox

personas = []
def updatearVelocidad():
    try:
        with sqlite3.connect("C:/Users/raulp/OneDrive/Escritorio/acceso a datos/ORM/jugadores.db") as conexion:
            cursor = conexion.cursor()

            cursor.execute('''
                UPDATE jugadores 
                SET energia = 100, descanso = 100
            ''')

            conexion.commit()
            cursor2 = conexion.cursor()
            cursor2.execute("SELECT * FROM jugadores")

            rows = cursor2.fetchall()
                
            for fila in rows:
                persona = Persona()
                persona.posx = fila[1]
                persona.posy = fila[2]
                persona.radio = fila[3]
                persona.direccion = fila[4]
                persona.color = fila[5]
                persona.entidad = fila[6]
                persona.energia = fila[7]
                persona.entidadenergia = fila[8]
                persona.descanso = fila[9]
                persona.entidaddescanso = fila[10]
                personas.append(persona)
                '''cursor3 = conexion.cursor()
                cursor3.execute('''
                '''SELECT victorias FROM Recogible WHERE persona = '{}'
                ''''''.format(persona.entidad))

            while True:
                fila2 = cursor3.fetchone()
                if fila2 is None:
                    break
                nuevorecogible = Recogible()
                nuevorecogible.posx = fila2[2]
                nuevorecogible.posy = fila2[3]
                nuevorecogible.color = fila2[4]
                persona.inventario.append(nuevorecogible)'''
        conexion.close()
    except sqlite3.Error as e:
        print("Error en la base de datos:", e)


# Creo una ventana
raiz = tk.Tk()

#En la ventana creo un lienzo
lienzo = tk.Canvas(raiz, width = 1024, height = 1024)
lienzo.grid(row=2, column=0, columnspan=2)           
updatear = tk.Button(raiz, text = "pulsa y volveran a moverse", command = updatearVelocidad)

class Entidad:
    def __init__(self):
        self.posx = random.randint(0,1024)
        self.posy = random.randint(0,1024)
        self.color = self.generar_color_aleatorio()
    def generar_color_aleatorio(self):
        r = random.randint(0, 255)
        g = random.randint(0, 255)
        b = random.randint(0, 255)
        return f"#{r:02x}{g:02x}{b:02x}"
    
class Cuadrado(Entidad):
    def __init__(self):
        super().__init__()
        self.contador = 0
        self.PersonaGanadora = Persona()

    def dibujar(self):
        self.entidad = lienzo.create_rectangle(
            self.posx - 20,
            self.posy - 20,
            self.posx + 20,
            self.posy + 20,
            fill=self.color
        )

    def colisiona_con_persona(self, Persona):
        x0, y0, x1, y1 = lienzo.coords(self.entidad)
        persona_coords = lienzo.coords(Persona.entidad)

        # Verificar colisión
        if (
            x0 < persona_coords[2] and
            x1 > persona_coords[0] and
            y0 < persona_coords[3] and
            y1 > persona_coords[1]
        ):
            self.contador = 1
            return True
        return False

    def desaparecer(self):
        lienzo.delete(self.entidad)        
class Recogible(Entidad):
    def __init__(self):
        self.posx = random.randint(0,1024)
        self.posy = random.randint(0,1024)
        self.color = self.generar_color_aleatorio()
    def serializar(self):
        recogible_serializado = {
            "posx":self.posx,
            "posy":self.posy,
            "color":self.color,
            }
        return recogible_serializado
    def generar_color_aleatorio(self):
        r = random.randint(0, 255)
        g = random.randint(0, 255)
        b = random.randint(0, 255)
        return f"#{r:02x}{g:02x}{b:02x}"
    
class Persona:
    def __init__(self):
        self.posx = random.randint(0,1024)
        self.posy = random.randint(0,1024)
        self.radio = 30
        self.direccion = random.randint(0,360)
        self.color = self.generar_color_aleatorio()
        self.entidad = ""
        self.energia = 100
        self.descanso = 100
        self.entidadenergia = ""
        self.entidaddescanso = ""
        self.inventario = []
        self.inventario.append(Recogible())
        #self.dibuja()
        
    def dibuja(self):
        self.entidad = lienzo.create_oval(
                           self.posx - self.radio/2,
                           self.posy - self.radio/2,
                           self.posx + self.radio/2,
                           self.posy + self.radio/2,
                           fill = self.color)
        
        self.entidadenergia = lienzo.create_rectangle(
                           self.posx - self.radio/2,
                           self.posy - self.radio/2 - 10,
                           self.posx + self.radio/2,
                           self.posy - self.radio/2 - 8,
                           fill = "green")
        self.entidaddescanso = lienzo.create_rectangle(
                           self.posx - self.radio/2,
                           self.posy - self.radio/2 - 16,
                           self.posx + self.radio/2,
                           self.posy - self.radio/2 - 14,
                           fill = "blue")
    def mueve(self):
        if self.energia > 0:
            self.energia -= 0.25
        if self.descanso > 0 and self.energia <= 0:
            self.descanso -= 0.25
        if self.descanso <= 0 and self.energia <= 0:
            updatear.config(state="normal")
        else:
            updatear.config(state="disabled")

        # Calcular componentes del movimiento
        factor_velocidad = (self.energia + self.descanso) / 100
        velocidad = 0.35 * factor_velocidad  # Ajusta el factor de escala para la velocidad base
        dx = velocidad * math.cos(self.direccion)
        dy = velocidad * math.sin(self.direccion)

        self.colisiona()
        anchuradescanso = (self.descanso / 100) * self.radio
        anchuraenergia = (self.energia / 100) * self.radio

        # Mover la entidad y los rectángulos
        lienzo.move(self.entidad, dx, dy)
        
        # Obtener las coordenadas actuales de la entidad
        x0, y0, x1, y1 = lienzo.coords(self.entidad)
        
        # Actualizar las coordenadas de los rectángulos
        lienzo.coords(
            self.entidadenergia,
            x0,
            y0 - 10,
            x0 + anchuraenergia,
            y0 - 8
        )
        lienzo.coords(
            self.entidaddescanso,
            x0,
            y0 - 16,
            x0 + anchuradescanso,
            y0 - 14
        )

        # Actualizar la posición
        self.posx += dx
        self.posy += dy

    def colisiona(self):
        if self.posx - self.radio/2 < 0 or self.posx + self.radio/2 > 1024:
            # Cambiar dirección horizontal (rebote)
            self.direccion = math.pi - self.direccion
        if self.posy - self.radio/2 < 0 or self.posy + self.radio/2 > 1024:
            # Cambiar dirección vertical (rebote)
            self.direccion = -self.direccion + math.pi
        # Añadir un pequeño cambio aleatorio para evitar que quede atrapado en los bordes
        self.direccion += random.uniform(-0.1, 0.1)

    def generar_color_aleatorio(self):
        r = random.randint(0, 255)
        g = random.randint(0, 255)
        b = random.randint(0, 255)
        return f"#{r:02x}{g:02x}{b:02x}"

    def serializar(self):
        persona_serializada = {
            "posx":self.posx,
            "posy":self.posy,
            "radio":self.radio,
            "direccion":self.direccion,
            "color":self.color,
            "energia":self.energia,
            "descanso":self.descanso,
            "inventario":[item.serializar() for item in self.inventario]
            }
        return persona_serializada

# Función para verificar colisión y hacer desaparecer el cuadrado
def verificar_colision():
    entidadGanadora = ""
    for persona in personas:
        if cuadrado.contador == 0:
            if cuadrado.colisiona_con_persona(persona):
                cuadrado.desaparecer()
                cuadrado.PersonaGanadora=persona
                entidadGanadora = str(cuadrado.PersonaGanadora.entidad)
    if cuadrado.contador == 0:
        raiz.after(10, verificar_colision)
    if cuadrado.contador != 0:
        for persona in personas:
            persona.energia = 0
            persona.descanso = 0
        messagebox.showinfo("Ganador","El vencedor es: " + entidadGanadora)
        print(int(entidadGanadora))
        try:
            with sqlite3.connect("C:/Users/raulp/OneDrive/Escritorio/acceso a datos/ORM/jugadores.db") as conexion:
                cursor = conexion.cursor()
                cursor.execute('''
                    SELECT victorias from Recogible where persona = '{}'
                    '''.format(int(entidadGanadora)))
                victorias = cursor.fetchone()
                print(victorias)
                if victorias is not None:
                    victorias = victorias[0] + 1

                cursor2 = conexion.cursor()
                cursor2.execute('''
                    UPDATE Recogible SET victorias = '{}'
                    WHERE persona = '{}'
                    '''.format(victorias, int(entidadGanadora)))
        except sqlite3.Error as e:
            print("Error en la base de datos:", e)
            
cuadrado = Cuadrado()
cuadrado.dibujar()
# Iniciar funciones de verificación de colisión y movimiento
verificar_colision()
    
def guardar():
    print("guardo a los jugadores")

    # Guardar en JSON
    ''' cadena = json.dumps(persona.serializar() for persona in personas)
    print(cadena)
    with open('datos.json', 'w') as archivo:
        archivo.write(cadena)'''

    # Guardar en SQLite
    try:
        with sqlite3.connect("C:/Users/raulp/OneDrive/Escritorio/acceso a datos/ORM/jugadores.db") as conexion:
            cursor = conexion.cursor()
            cursor.execute('''
                 delete from jugadores
                ''')
            cursor.execute('''
                DELETE FROM Recogible
                ''')
            conexion.commit()
            cursor2 = conexion.cursor()
            for persona in personas:
                cursor2.execute('''
                    INSERT INTO jugadores
                    VALUES (
                        NULL,
                        ?,
                        ?,
                        ?,
                        ?,
                        ?,
                        ?,
                        ?,
                        ?,
                        ?,
                        ?
                    )'''
                    , (
                    persona.posx,
                    persona.posy,
                    persona.radio,
                    persona.direccion,
                    persona.color,
                    persona.entidad,
                    persona.energia,
                    persona.entidadenergia,
                    persona.descanso,
                    persona.entidaddescanso
                    ))
                for recogible in persona.inventario:
                    cursor.execute('''
                        INSERT INTO Recogible
                        VALUES (
                            NULL,
                            ?,
                            ?,
                            ?,
                            ?,
                            ?
                        )'''
                        , (
                        persona.entidad,
                        recogible.posx,
                        recogible.posy,
                        recogible.color,
                        0
                        ))
    except sqlite3.Error as e:
        print("Error en la base de datos:", e)

def borrar():
    try:
        lienzo.delete(ALL)
        personas.clear()
        with sqlite3.connect("C:/Users/raulp/OneDrive/Escritorio/acceso a datos/ORM/jugadores.db") as conexion:
            cursor = conexion.cursor()
            cursor.execute('''
                delete from jugadores
                ''')
            cursor2 = conexion.cursor()
            cursor2.execute('''
                DELETE FROM Recogible
                ''')
        conexion.commit()
    except sqlite3.Error as e:
        print("Error en la base de datos:", e)

def mostrarPersonasParteSuperior():
    try:
        lienzo.delete(ALL)
        personas.clear()
        with sqlite3.connect("C:/Users/raulp/OneDrive/Escritorio/acceso a datos/ORM/jugadores.db") as conexion:
            cursor = conexion.cursor()
            cursor.execute("select * from jugadores where posx < 512 and posy < 512")
            rows = cursor.fetchall()                
            for fila in rows:
                persona = Persona()
                persona.posx = fila[1]
                persona.posy = fila[2]
                persona.radio = fila[3]
                persona.direccion = fila[4]
                persona.color = fila[5]
                persona.entidad = fila[6]
                persona.energia = fila[7]
                persona.entidadenergia = fila[8]
                persona.descanso = fila[9]
                persona.entidaddescanso = fila[10]
                personas.append(persona)
    except sqlite3.Error as e:
        print("Error en la base de datos:", e)
    for persona in personas:
        persona.dibuja()

def mostrarPersonasParteInferior():
    try:
        lienzo.delete(ALL)
        personas.clear()
        with sqlite3.connect("C:/Users/raulp/OneDrive/Escritorio/acceso a datos/ORM/jugadores.db") as conexion:
            cursor = conexion.cursor()
            cursor.execute("select * from jugadores where posx > 512 and posy > 512")
            rows = cursor.fetchall()            
            for fila in rows:
                persona = Persona()
                persona.posx = fila[1]
                persona.posy = fila[2]
                persona.radio = fila[3]
                persona.direccion = fila[4]
                persona.color = fila[5]
                persona.entidad = fila[6]
                persona.energia = fila[7]
                persona.entidadenergia = fila[8]
                persona.descanso = fila[9]
                persona.entidaddescanso = fila[10]
                personas.append(persona)
    except sqlite3.Error as e:
        print("Error en la base de datos:", e)
    for persona in personas:
        persona.dibuja()
def mostrarTodo():
    try:
        lienzo.delete(ALL)
        personas.clear()
        with sqlite3.connect("C:/Users/raulp/OneDrive/Escritorio/acceso a datos/ORM/jugadores.db") as conexion:
            cursor = conexion.cursor()
            cursor.execute("select * from jugadores")
            rows = cursor.fetchall()
                
            for fila in rows:
                persona = Persona()
                persona.posx = fila[1]
                persona.posy = fila[2]
                persona.radio = fila[3]
                persona.direccion = fila[4]
                persona.color = fila[5]
                persona.entidad = fila[6]
                persona.energia = fila[7]
                persona.entidadenergia = fila[8]
                persona.descanso = fila[9]
                persona.entidaddescanso = fila[10]
                personas.append(persona)
    except sqlite3.Error as e:
        print("Error en la base de datos:", e)
    for persona in personas:
        persona.dibuja()    

#En la colección introduzco instancias de las personas
if len(personas) == 0:
    numeropersonas = 30
    for i in range(0,numeropersonas):
        personas.append(Persona())
    for persona in personas:
        persona.dibuja()

#else:
    #cargar personas

'''with open('datos.json','r') as archivo:
        cargado = archivo.read()
        cargadolista = json.loads(cargado)
        for elemento in cargadolista:
            persona = Persona()
            persona.__dict__.update(elemento)
            personas.append(persona)   '''

#Pinto cada persona de la colección
    
#Creo un bucle repetitivo
def bucle():
        #Muevo cada persona cada segundo
    for persona in personas:
        persona.mueve()
    raiz.after(10,bucle)
            
    #Ejecuto el bucle
bucle()

mostrar = tk.Button(raiz, text = "mostrar personas", command = mostrarTodo)
mostrar.grid(row=0, column=0)
boton = tk.Button(raiz, text = "guardar", command = guardar)
boton.grid(row=0, column=1)
updatear.grid(row=0, column=2)
borrar = tk.Button(raiz, text = "pulsa y borraras los datos", command = borrar)
borrar.grid(row=1, column=0)
personasParteSuperior = tk.Button(raiz, text = "pulsa y veras las personas de arriba", command = mostrarPersonasParteSuperior)
personasParteSuperior.grid(row=1, column=1)
personasParteInferior = tk.Button(raiz, text = "pulsa y veras las personas de abajo", command = mostrarPersonasParteInferior)
personasParteInferior.grid(row=1, column=2)
updatear.config(state="disabled") 

raiz.mainloop()
