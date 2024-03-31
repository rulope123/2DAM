using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class variable_globales : MonoBehaviour
{
    // Start is called before the first frame update
    public void Start()
    {
        PlayerPrefs.SetInt("Monos",3);
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
