#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]){
    FILE *archivo;
    char *operacion = argv[1];
    char *bbdd = argv[2];
    char *nombreArchivo = argv[3];
    char ruta[100];
    char *where;
    strcpy(ruta,bbdd);
    strcat(ruta,"-");
    strcat(ruta,nombreArchivo);
    strcat(ruta,".txt");
    
    if(strcmp(operacion,"select")==0){
        archivo = fopen(ruta,"r");
        char line[1024];
        if(argv[4] != NULL && strcmp(nombreArchivo,"clientes") == 0){
            where = argv[4];

            while(fgets(line,sizeof(line),archivo) != NULL){
                size_t len = strlen(line);
                if (len > 0 && line[len - 1] == '\n') {
                    line[len - 1] = '\0';
                }
                if(strcmp(where,line) == 0){
                    printf("Los datos buscados coinciden con: %s",line);
                }
            }
        }


        while(fgets(line,sizeof(line),archivo) != NULL){
            printf("Linea: %s",line);
        }
    }else if(strcmp(operacion,"insert")==0){

        archivo = fopen(ruta,"r");
        char line[1024];
        char *texto = argv[4];
        int cont = 0;
        while(fgets(line,sizeof(line),archivo) != NULL){
            size_t len = strlen(line);
            if (len > 0 && line[len - 1] == '\n') {
                line[len - 1] = '\0';
                if(strcmp(texto,line) == 0){
                    cont = 1;
                    break;
                }
            }
        }
        if(cont==1){
                printf("Los datos que quieres introducir ya existen");
                    }else{
                        archivo = fopen(ruta,"a");
                        fputs(strcat(texto,"\n"), archivo);
                        fclose(archivo);
                        printf("Los datos se han introducido correctamente");
                    }
    }else{
        printf("Operación no válida");
    }

    return 0;
}