var MovieModel = require('../models/movie_model'),

    MovieController = () => {



    }

MovieController.getAll = (req, res, next) => {

    MovieModel.getAll((err, rows) => { //se llama al método get all del modelo y definimos su callback

        if (err) {

            let locals = {

                title: 'Error al consultar la base de datos',
                descripcion: 'Error de syntaxis SQL',
                error: err

            }

            res.render('error', locals);

        } else {

            let locals = {

                title: 'Lista de películas',
                datos: rows

            }

            res.render('pelis', locals);

        }

    })

}



MovieController.addForm = (req, res, next) => { //como se llama a una vista no es necesario definir una función en el modelo para ejecutar esta función

    res.render("añadirPeliculas", {

        mensaje: "agregar película",

    })

}

MovieController.getOne = (req, res, next) => {

    let movie_id = req.params.movie_id;

    MovieModel.getOne(movie_id, (err, rows) => {

        if (err) {

            let locals = {

                title: `Error al buscar el registro con el id: ${movie_id}`,
                descripcion: 'Error de syntaxis SQL',
                error: err

            }

            res.render('error', locals);

        } else {

            let locals = {

                mensaje: "Editar película",
                data: rows

            }

            res.render("editar", locals);

        }

    })

}

MovieController.insert = (req, res, next) => {

    let movie = {

        título: req.body.titulo,
    
        imagen: req.body.poster,
    
        valoracion: req.body.valoracion
    
      }

    MovieModel.insert(movie, (err) => {

        if (err) {

            let locals = {

                title: `Error al agregar el registro`,
                descripcion: 'Error de syntaxis SQL',
                error: err

            }

            res.render('error', locals);

        } else {

            res.redirect('/pelis');

        }


    })

}

MovieController.update = (req, res, next) => {

    let movie = {

        título: req.body.titulo,
    
        imagen: req.body.poster,
    
        valoracion: req.body.valoracion
    
      }

    MovieModel.update(movie, (err) => {

        if (err) {

            let locals = {

                title: `Error al actualizar el registro con id: ${movie.movie_id}`,
                descripcion: 'Error de syntaxis SQL',
                error: err

            }

            res.render('error', locals);

        } else {

            res.redirect('/pelis');

        }


    })

}

MovieController.delete = (req, res, next) => {

    let movie_id = req.params.movie_id;

    MovieModel.delete(movie_id, (err, rows) => {

        if (err) {

            let locals = {

                title: `Error al eliminar el registro con el id: ${movie_id}`,
                descripcion: 'Error de syntaxis SQL',
                error: err

            }

            res.render('error', locals);

        } else{

            res.redirect('/pelis');
            
        }

    })

}

MovieController.error404 = (req, res, next) => { //este metodo no existe en el modelo porque no tiene nada que ver con rutas, simplemente controla un error

    let error = new Error(),

        locals = {

            title: 'Error 404',
            descripcion: 'Recurso no encontrado',
            error: error

        }

    error.status = 404;

    res.render('error', locals);

    next();

}

module.exports = MovieController;