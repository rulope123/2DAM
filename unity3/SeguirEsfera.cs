/*using UnityEngine;

public class SeguirEsfera : MonoBehaviour
{
    public string tagEsfera = "Player"; // El tag del personaje que quieres seguir

    private Transform esferaTransform; // Referencia al Transform del personaje

    void Start()
    {
        GameObject esfera = GameObject.FindWithTag(tagEsfera); // Busca el GameObject con el tag especificado
        if (esfera != null)
        {
            esferaTransform = esfera.transform; // Obtiene el Transform del personaje
        }
        else
        {
            Debug.LogError("No se encontró un GameObject con el tag especificado: " + tagEsfera);
        }
    }

    void Update()
    {
        if (esferaTransform != null)
        {
            transform.position = esferaTransform.position;
        }
    }
}*/

