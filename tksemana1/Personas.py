class Personas:
    def __init__(self,nombre,apellidos,email,telefono):
        self.__nombre = nombre
        self.__apellidos = apellidos
        self.__email = email
        self.__telefono = telefono

    def getNombre(self):
        return self.__nombre

    def setNombre(self,nombre):
        self.__nombre = nombre

    def getApellidos(self):
        return self.__apellidos

    def setApellidos(self,apellidos):
        self.__apellidos = apellidos

    def getEmail(self):
        return self.__email
    
    def setEmail(self,email):
        self.__email = email

    def getTelefono(self):
        return self.__telefono

    def setTelefono(self,telefono):
        self.__telefono = telefono

        
