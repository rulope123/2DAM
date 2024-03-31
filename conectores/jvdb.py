import subprocess

class Jvdb:
    def __init__(self,bbdd):
        self.bbdd = bbdd
        
    def insert(self,tabla,contenido):
        self.operacion = " insert "
        self.tabla = tabla
        self.contenido = contenido
        comando = '"C:\\Users\\raulp\\OneDrive\\Escritorio\\acceso a datos\\coleccion.exe "' + ' ' + self.operacion + ' '  + self.bbdd + ' '  + self.tabla + ' '  + self.contenido
        print(comando)
        resultado = subprocess.run(comando, shell = True, stdout = subprocess.PIPE, stderr = subprocess.PIPE, text = True)

        if resultado.returncode == 0:
            print("okey")
        else:
            print("Mal")

    def select(self,tabla):
        self.operacion = " select "
        self.tabla = tabla
        comando = '"C:\\Users\\raulp\\OneDrive\\Escritorio\\acceso a datos\\coleccion.exe "' + ' ' + self.operacion + ' '  + self.bbdd + ' '  + self.tabla
        print(comando)
        resultado = subprocess.run(comando, shell = True, stdout = subprocess.PIPE, stderr = subprocess.PIPE, text = True)

        if resultado.returncode == 0:
            print("Datos del SELECT:")
            print(resultado.stdout)
        else:
            print("Error al ejecutar el SELECT")
            print(resultado.stderr)

    def delete(self,tabla):
        self.operacion = " delete "
        self.tabla = tabla
        comando = '"C:\\Users\\raulp\\OneDrive\\Escritorio\\acceso a datos\\coleccion.exe "' + ' ' + self.operacion + ' '  + self.bbdd + ' '  + self.tabla
        print(comando)
        resultado = subprocess.run(comando, shell = True, stdout = subprocess.PIPE, stderr = subprocess.PIPE, text = True)

        if resultado.returncode == 0:
            print("Archivo eliminado")
            print(resultado.stdout)
        else:
            print("Error al eliminar el archivo")
            print(resultado.stderr)

    def update(self,tabla,contenido,nuevo_valor):
        self.operacion = " update "
        self.tabla = tabla
        self.contenido = contenido
        self.nuevo_valor = nuevo_valor
        comando = '"C:\\Users\\raulp\\OneDrive\\Escritorio\\acceso a datos\\coleccion.exe "' + ' ' + self.operacion + ' '  + self.bbdd + ' '  + self.tabla + ' ' + self.contenido + ' ' + self.nuevo_valor
        print(comando)
        resultado = subprocess.run(comando, shell = True, stdout = subprocess.PIPE, stderr = subprocess.PIPE, text = True)

        if resultado.returncode == 0:
            print("Archivo va a ser updateado")
            print(resultado.stdout)
        else:
            print("Error al updatear el archivo")
            print(resultado.stderr)
