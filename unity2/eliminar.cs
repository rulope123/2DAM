using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TriggerMono : MonoBehaviour
{
    private void OnTriggerEnter(Collider other)
    {
        DestruirObjetos();
    }

    void Victoria(){
    // Reiniciar la escena (ajusta según tu configuración)
    UnityEngine.SceneManagement.SceneManager.LoadScene(UnityEngine.SceneManagement.SceneManager.GetActiveScene().buildIndex);
    }


    void DestruirObjetos(){
        Destroy(gameObject);
        int monos = PlayerPrefs.GetInt("Monos",0);
        monos--;
        Debug.Log(monos);

        if (monos == 0)
        {
            // Aquí puedes realizar acciones de victoria
            PlayerPrefs.SetInt("Monos",3);
            monos=3;
            Debug.Log("¡Has ganado!");
            Victoria();
        }

        PlayerPrefs.SetInt("Monos",monos);
    }
}
