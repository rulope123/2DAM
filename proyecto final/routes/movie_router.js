const MovieController = require('../controllers/movie_controllers.js');

var express = require('express'),

    router = express.Router()

router //en el movie_router solo se va a encontrar la definición a la ruta y la llamada a la función del controlador

    .get('/pelis',MovieController.getAll)
    .get("/pelis/agregar",MovieController.addForm)
    .post("/pelis/agregar",MovieController.insert)
    .get('/pelis/editar/:movie_id',MovieController.getOne)
    .post('/pelis/actualizar/:movie_id',MovieController.update)
    .post('/pelis/eliminar/:movie_id',MovieController.delete)
    .use('/pelis',MovieController.error404); //este middleware debe ejecutarse al final porque controla las rutas de error, si lo inovoco antes de las rutas me va a marcar error404 porque es lo primero que se esta invocando

module.exports = router;