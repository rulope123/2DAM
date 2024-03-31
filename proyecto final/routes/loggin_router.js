const LogginController = require('../controllers/loggin_controllers.js');

var express = require('express'),

    router = express.Router()

router //en el movie_router solo se va a encontrar la definición a la ruta y la llamada a la función del controlador

    .get('/',LogginController.loggin)
    .post('/',LogginController.usuarios)
    .get("/registrar",LogginController.mostrarRegistro)
    .post('/registrar',LogginController.registrar)
    .get('/contrasena',LogginController.comprobarUsuario)
    .post('/contrasena',LogginController.buscarUsuario)
    .get('/cambiar_contrasena/:usu',LogginController.introducirContraseña)
    .post('/cambia_contrasena',LogginController.actualizarContraseña)
    .use('/',LogginController.error404); //este middleware debe ejecutarse al final porque controla las rutas de error, si lo inovoco antes de las rutas me va a marcar error404 porque es lo primero que se esta invocando

module.exports = router;