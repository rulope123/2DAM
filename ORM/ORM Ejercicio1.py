import tkinter as tk
import random
import math
import json

personas = []

class Persona:
    def __init__(self):
        self.posx = random.randint(0,1024)
        self.posy = random.randint(0,1024)
        self.radio = 30
        self.direccion = random.uniform(0, 2*math.pi)  # Usar uniform para obtener un ángulo entre 0 y 2*pi
        self.color = self.generar_color_aleatorio()
        self.entidad = ""
        self.energia = 100
        self.descanso = 100
        self.entidadenergia = ""
        self.entidaddescanso = ""
        self.dibuja()
        
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
                           self.posx - self.radio/2 + (self.energia/100) * self.radio,
                           self.posy - self.radio/2 - 8,
                           fill = "green")
        self.entidaddescanso = lienzo.create_rectangle(
                           self.posx - self.radio/2,
                           self.posy - self.radio/2 - 16,
                           self.posx - self.radio/2 + (self.descanso/100) * self.radio,
                           self.posy - self.radio/2 - 14,
                           fill = "blue")
    
    def mueve(self):
        if self.energia > 0:
            self.energia -= 0.25
        if self.descanso > 0 and self.energia <= 0:
            self.descanso -= 0.25
        if self.energia <= 0 and self.descanso <= 0:
            self.despertar()
        
        # Calcular componentes del movimiento
        factor_velocidad = (self.energia + self.descanso) / 100
        velocidad = 0.35 * factor_velocidad  # Ajusta el factor de escala para la velocidad base
        dx = velocidad * math.cos(self.direccion)
        dy = velocidad * math.sin(self.direccion)
        
        self.colisiona()
        
        # Mover entidad
        lienzo.move(self.entidad, dx, dy)
        
        # Mover rectángulos de energía y descanso
        lienzo.coords(self.entidadenergia, 
                      self.posx - self.radio/2, 
                      self.posy - self.radio/2 - 10,
                      self.posx - self.radio/2 + (self.energia/100) * self.radio,
                      self.posy - self.radio/2 - 8)
        
        lienzo.coords(self.entidaddescanso,
                      self.posx - self.radio/2,
                      self.posy - self.radio/2 - 16,
                      self.posx - self.radio/2 + (self.descanso/100) * self.radio,
                      self.posy - self.radio/2 - 14)        
        
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

    def despertar(self):
        # Restablecer energía y descanso después de 5 segundos
        raiz.after(5000, self.restablecer_energia_descanso)

    def restablecer_energia_descanso(self):
        self.energia = 100
        self.descanso = 100

def guardar():
    print("guardo a los jugadores")

    # Guardar en JSON
    cadena = json.dumps([vars(persona) for persona in personas])
    print(cadena)
    with open('datos.json', 'w') as archivo:
        archivo.write(cadena)

def cargar():
    personas.clear()
    with open('datos.json', 'r') as archivo:
        cargado = archivo.read()
        cargadolista = json.loads(cargado)
        for elemento in cargadolista:
            persona = Persona()
            persona.posx = elemento['posx']
            persona.posy = elemento['posy']
            persona.radio = elemento['radio']
            persona.direccion = elemento['direccion']
            persona.color = elemento['color']
            persona.energia = elemento['energia']
            persona.descanso = elemento['descanso']
            personas.append(persona)
            
    # Limpiar lienzo antes de volver a dibujar
    lienzo.delete(tk.ALL)
    
    # Pintar cada persona de la colección
    for persona in personas:
        persona.dibuja()

# Creo una ventana
raiz = tk.Tk()

# En la ventana creo un lienzo
boton = tk.Button(raiz, text="guardar", command=guardar)
boton.grid(row=0, column=0)
mostrar = tk.Button(raiz, text = "mostrar personas", command = cargar)
mostrar.grid(row=0, column=1)
lienzo = tk.Canvas(raiz, width=1024, height=1024)
lienzo.grid(row=2, column=0, columnspan=2)

# En la colección introduzco instancias de las personas
if len(personas) == 0:
    numeropersonas = 50
    for i in range(0, numeropersonas):
        personas.append(Persona())

# Pinto cada persona de la colección
'''for persona in personas:
    persona.dibuja()'''

# Creo un bucle repetitivo
def bucle():
    # Muevo cada persona cada segundo
    for persona in personas:
        persona.mueve()
    raiz.after(10, bucle)

# Ejecuto el bucle
bucle()

raiz.mainloop()
