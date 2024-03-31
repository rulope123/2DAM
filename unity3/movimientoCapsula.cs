using UnityEngine;

[RequireComponent(typeof(CharacterController))]
public class ExampleClass : MonoBehaviour
{
    public float speed = 3.0F;
    public float rotateSpeed = 3.0F;
    public float jumpHeight = 1.0f;
    public float gravityValue = -9.81f;
    public Transform respawnPoint; // Punto de reaparición del jugador
    public float fallThreshold = -10f; // Umbral de caída

    private CharacterController controller;
    private Vector3 playerVelocity;
    private bool groundedPlayer;

    void Start()
    {
        controller = GetComponent<CharacterController>();
    }

    void Update()
    {
        groundedPlayer = controller.isGrounded;
        if (groundedPlayer && playerVelocity.y < 0)
        {
            playerVelocity.y = 0f;
        }

        // Rotate around y - axis
        transform.Rotate(0, Input.GetAxis("Horizontal") * rotateSpeed, 0);

        // Move forward / backward
        Vector3 move = new Vector3(Input.GetAxis("Horizontal"), 0, Input.GetAxis("Vertical"));
        Vector3 forward = transform.TransformDirection(Vector3.forward);
        float curSpeed = speed * Input.GetAxis("Vertical");
        controller.SimpleMove(forward * curSpeed);

        // Changes the height position of the player..
        if (Input.GetButton("Jump") && groundedPlayer)
        {
            playerVelocity.y += Mathf.Sqrt(jumpHeight * -3.0f * gravityValue);
        }

        playerVelocity.y += gravityValue * Time.deltaTime;
        controller.Move(playerVelocity * Time.deltaTime);

        // Check if player has fallen off the platform
        if (!groundedPlayer && transform.position.y < fallThreshold)
        {
            Debug.Log("¡Has perdido!");
            // Respawn player
            RespawnPlayer();
        }
    }

    // Método para reiniciar el jugador
    void RespawnPlayer()
    {
        // Reinicia la posición del jugador al punto de reaparición
        if (respawnPoint != null)
        {
            transform.position = respawnPoint.position;
        }
        else
        {
            // Si no hay un punto de reaparición, simplemente reinicia la posición al origen
            transform.position = Vector3.zero;
        }

        // Reinicia la velocidad del jugador
        playerVelocity = Vector3.zero;
    }
}
