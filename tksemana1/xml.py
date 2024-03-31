from bs4 import BeautifulSoup
import tkinter as tk
from Personas import Personas

raiz = tk.Tk()

nombre = tk.Entry(raiz)
apellidos = tk.Entry(raiz)
email = tk.Entry(raiz)
telefono = tk.Entry(raiz) 
lista_personas = []
diccionario = dict()

def clic_boton_1():
    num = False
    if len(telefono.get()) == 9:
        try:
            telefono_numero = int(telefono.get())
            print("Número de teléfono correcto:", telefono_numero)
            num = True
        except ValueError:
            print("El texto del teléfono no es un número válido.")
            num = False
    else:
        print("El telefono no tiene 9 digitos")
        
    if  not nombre.get().__eq__("") and not apellidos.get().__eq__("") and num and not email.get().__eq__("") and '@' in email.get() and '.' in email.get(): 
        persona = Personas(nombre.get(),apellidos.get(),email.get(),telefono.get())
        diccionario = {"nombre":persona.getNombre(),
            "apellidos":persona.getApellidos(),
            "email":persona.getEmail(),
            "telefono":persona.getTelefono()}
        lista_personas.append(diccionario)
        nombre.delete(0, tk.END)
        apellidos.delete(0, tk.END)
        email.delete(0, tk.END)
        telefono.delete(0, tk.END)
    else:
        print("No puede haber campos vacios, el email debe tener una @ y un .")
def clic_boton_2():
    print(lista_personas)
    
archivo = open("interfaz.xml","r")
contenido = archivo.read()
xml = BeautifulSoup(contenido,"xml")

for campo in xml.find_all("campo"):
    tipo = campo.get("tipo")
    texto = campo.get("texto")
    if tipo == "etiqueta":
        tk.Label(raiz, text = texto).pack(padx = 20, pady = 20)
    elif tipo == "entrada":
        if campo.get("id") == "nombre":
            nombre.pack(padx=20, pady=20)
        elif campo.get("id") == "apellidos":
            apellidos.pack(padx=20, pady=20)
        elif campo.get("id") == "email":
            email.pack(padx=20, pady=20)
        elif campo.get("id") == "telefono":
            telefono.pack(padx=20, pady=20)
    elif tipo == "boton":
        if campo.get("evento") == "clic_boton_1":
            tk.Button(raiz, text = texto, command=clic_boton_1).pack(padx = 20, pady = 20)
        elif campo.get("evento") == "clic_boton_2":
            tk.Button(raiz, text = texto, command=clic_boton_2).pack(padx = 20, pady = 20)
tk.mainloop()
