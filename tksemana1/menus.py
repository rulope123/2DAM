import tkinter as tk
from tkinter import  messagebox,filedialog
'''import subprocess
subprocess.run(['python', '-m', 'pip', 'install', 'pyperclip'])'''
import pyperclip
def salir():
    raiz.destroy()
def nuevo():
    #raiz.destroy()
    nueva_ventana = tk.Tk()
    tk.Label(nueva_ventana, text = "Esta es una nueva página").pack(padx = 50, pady = 50)
def abrir_archivo():
    ruta_archivo = filedialog.askopenfilename(title="Seleccionar archivo", filetypes=[("Archivos de texto", "*.txt"), ("Todos los archivos", "*.*")])
    if ruta_archivo:
        mostrar_archivo = tk.Tk()
        texto = tk.Text(mostrar_archivo, wrap=tk.WORD, width=40, height=10)
        texto.pack(padx=20, pady=20)
        with open(ruta_archivo, 'r') as archivo:
            contenido = archivo.read()
            texto.insert(tk.END, contenido)
def guadar_archivo():
    contenido = texto_original.get("1.0", tk.END)
    ruta_archivo = filedialog.asksaveasfilename(title="Guardar archivo", defaultextension=".txt", filetypes=[("Archivos de texto", "*.txt"), ("Todos los archivos", "*.*")])
    if ruta_archivo:
        with open(ruta_archivo, 'w') as archivo:
            archivo.write(contenido)
def copiar():
    try:
        inicio = texto_original.index(tk.SEL_FIRST)
        fin = texto_original.index(tk.SEL_LAST)
        texto_seleccionado = texto_original.get(inicio, fin)
        pyperclip.copy(texto_seleccionado)
        messagebox.showinfo("Copiado", "Texto copiado al portapapeles.")
    except tk.TclError:
        messagebox.showwarning("Advertencia", "No se ha seleccionado texto para pegar.")
def pegar():
    try:
        contenido = texto_original.get("1.0", tk.END)
        contenido = contenido + pyperclip.paste()
        texto_original.delete("1.0", tk.END)
        texto_original.insert(tk.END,contenido)
        messagebox.showinfo("Pegado", "Texto pegado al portapapeles.")
    except tk.TclError:
        messagebox.showwarning("Advertencia", "No se ha seleccionado texto para pegar.")
def cortar():
    try:
        inicio = texto_original.index(tk.SEL_FIRST)
        fin = texto_original.index(tk.SEL_LAST)
        texto_seleccionado = texto_original.get(inicio, fin)
        texto_original.delete(inicio, fin)
        pyperclip.copy(texto_seleccionado)
        messagebox.showinfo("Cortado", "Texto cortado al portapapeles.")
    except tk.TclError:
        messagebox.showwarning("Advertencia", "No se ha seleccionado texto para cortar.")
    
        
raiz = tk.Tk()
barra = tk.Menu(raiz) #crea la barra del menú
raiz.config(menu=barra) #configura la ventana para usar la barra del menú
archivo = tk.Menu(barra,tearoff=0) #crea el menu desplegable en la barra del menú, tearoff = 0 evita que el menu desplegable se pueda separar de la ventana principal
barra.add_cascade(label = "Archivo",menu=archivo) #add_cascade añade una entrada en la barra del menú, menu = archivo añade a la barra del menú el menú desplegable
archivo.add_command(label="nuevo",command=nuevo)
archivo.add_command(label="abrir", command=abrir_archivo)
archivo.add_command(label="guardar",command=guadar_archivo)
archivo.add_command(label="salir",command=salir)
editar = tk.Menu(barra,tearoff=0)
barra.add_cascade(label = "Editar",menu=editar)
editar.add_command(label="Copiar", command=copiar)
editar.add_command(label="Pegar", command=pegar)
editar.add_command(label="Cortar", command=cortar)
'''ayuda = tk.Menu(barra,tearoff=0)
barra.add_cascade(label = "Ayuda",menu=ayuda)
ayuda.add_command(label="Ayuda")
ayuda.add_command(label="Documentacion en linea")
ayuda.add_command(label="Acerca de")
ayuda.add_command(label="Soporte")
editar.add_command(label="Deshacer")'''
texto_original = tk.Text(raiz, wrap=tk.WORD, width=40, height=10)
texto_original.pack(padx=80, pady=80)

raiz.mainloop()

