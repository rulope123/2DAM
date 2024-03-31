#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <cJSON.h>
#include <direct.h>  
#include <sys/stat.h>

int main(int argc, char *argv[]){
    FILE *archivo;
    char *operacion = argv[1];
    char *bbdd = argv[2];
    char *coleccion = argv[3];
    /*time_t tiempoActual;
    tiempoActual = time(NULL);
    printf("tiempo actual %lld \n",(long long)tiempoActual);*/
    
    if(strcmp(operacion,"select")==0){
        char ruta[100];
        strcpy(ruta,bbdd);
        strcat(ruta,"/");
        strcat(ruta,coleccion);
        strcat(ruta,".json");
        archivo = fopen(ruta,"r");
        if (archivo != NULL) {
            char fileContent[1024 * 10];
            char line[1024];
            while (fgets(line, sizeof(line), archivo) != NULL) {
                strcat(fileContent, line);
            }

            printf("JSON Completo:\n%s\n", fileContent);

            fclose(archivo);
        } else {
            printf("No se pudo abrir el archivo.\n");
        }
    }else if(strcmp(operacion,"insert")==0){
        char ruta[100];
        strcpy(ruta,bbdd);
        strcat(ruta,"/");
        strcat(ruta,coleccion);
        strcat(ruta,".json");
        archivo = fopen(ruta,"r");
        char line[1024];
        char *texto = argv[4];
        int cont = 0;
        cJSON *root,*nombre, *array;
        root = cJSON_CreateObject();
        nombre = cJSON_CreateArray();
        int primerCaracter = fgetc(archivo);
        fclose(archivo);
            if(primerCaracter == EOF){
                cJSON_AddItemToObject(root, "nombre", cJSON_CreateString(texto));
                cJSON_AddItemToArray(nombre, root);
                char *jsonString = cJSON_Print(nombre);
                archivo = fopen(ruta, "w");
                if (archivo == NULL) {
                    printf("Error al abrir el archivo.\n");
                    return 1;
                }
                printf("%s\n", jsonString);
                fputs(jsonString, archivo);
                fclose(archivo);
                printf("El archivo estaba vacio, se ha introducido los datos correctamente\n");
                cJSON_free(jsonString);
                cJSON_Delete(nombre);
            }
                else{
                     archivo = fopen(ruta, "r");
                        if (archivo != NULL) {
                            fseek(archivo, 0, SEEK_END);
                            long fileSize = ftell(archivo);
                            fseek(archivo, 0, SEEK_SET);
                            char *fileContent = (char *)malloc(fileSize + 1);
                            fread(fileContent, 1, fileSize, archivo);
                            fclose(archivo);
                            fileContent[fileSize] = '\0';
                            array = cJSON_Parse(fileContent);
                            free(fileContent);
                            }

                            cJSON_AddItemToObject(root, "nombre", cJSON_CreateString(texto));
                            cJSON_AddItemToArray(array, root);
                            archivo = fopen(ruta, "w");
                                if (archivo == NULL) {
                                    printf("Error al abrir el archivo.\n");
                                    return 1;
                                }

                                char *jsonString = cJSON_Print(array);
                                printf("%s\n", jsonString);
                                fputs(jsonString, archivo);
                                fclose(archivo);

                                printf("Los datos se han introducido correctamente\n");

                                cJSON_free(jsonString);
                                cJSON_Delete(array);
                            }
                

    }else if(strcmp(operacion,"create_collection") == 0){
        char ruta[100];
        strcpy(ruta,bbdd);
        strcat(ruta,"/");
        strcat(ruta,coleccion);

        if(mkdir(ruta) == 0){
            printf("ok");
        }else{
            printf("error");
        }
        }else if(strcmp(operacion,"delete") == 0){
            char ruta[100];
            strcpy(ruta,bbdd);
            strcat(ruta,"/");
            strcat(ruta,coleccion);
            strcat(ruta,".json");
            struct stat st;
            if (stat(ruta, &st) == 0) {
                printf("La ruta existe.\n");
                if (remove(ruta) == 0) {
                    printf("El archivo %s fue borrado exitosamente.\n", ruta);
                }else{
                    printf("El archivo no ha sido borrado");
                } 
            }else{
                printf("la ruta no existe. \n");
            } 
        }else if(strcmp(operacion,"update") == 0 ){
            char ruta[100];
            strcpy(ruta,bbdd);
            strcat(ruta,"/");
            strcat(ruta,coleccion);
            strcat(ruta,".json");
            archivo = fopen(ruta,"r");
            if (archivo == NULL) {
            fprintf(stderr, "No se pudo abrir el archivo JSON.\n");
            return 1;
            }
            char line[1024];
            char *texto = argv[4];
            char *nuevo_valor  = argv[5];
            fseek(archivo, 0, SEEK_END);
            long length = ftell(archivo);
            fseek(archivo, 0, SEEK_SET);
            char *json_data = malloc(length);
            fread(json_data, 1, length, archivo);
            fclose(archivo);
            cJSON *root = cJSON_Parse(json_data);
            free(json_data);

            if (root == NULL || !cJSON_IsArray(root)) {
                fprintf(stderr, "Error al analizar el archivo JSON o el JSON no es un array.\n");
                return 1;
            }
            int encontrado = 0;

            cJSON *objeto;
            cJSON *nombre;

            cJSON_ArrayForEach(objeto, root) {
                nombre = cJSON_GetObjectItem(objeto, "nombre");

                if (cJSON_IsString(nombre) && strcmp(cJSON_GetStringValue(nombre), texto) == 0) {

                    encontrado = 1;
                    //printf("Campo encontrado. Introduce el nuevo valor: ");
                    cJSON_ReplaceItemInObject(objeto, "nombre", cJSON_CreateString(nuevo_valor));
                    break;  
                }
            }

            if (encontrado) {
                archivo = fopen(ruta, "w");
                if (archivo == NULL) {
                    fprintf(stderr, "No se pudo abrir el archivo JSON para escritura.\n");
                    return 1;
                }

                char *json_string = cJSON_Print(root);
                fprintf(archivo, "%s", json_string);
                fclose(archivo);

                printf("Campo actualizado exitosamente.\n");

                free(json_string);
            } else {
                printf("Campo a editar no encontrado.\n");
            }

                    cJSON_Delete(root);
                }else{
                    printf("Operación no válida");
                }

    return 0;
}