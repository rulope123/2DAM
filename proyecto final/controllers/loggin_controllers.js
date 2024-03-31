var LogginModel = require('../models/loggin_model'),

    LogginController = () => { }

LogginController.loggin = (req, res, next) => {

    res.render('index', {

        iniciarSesion: 'Inciar sesión'

    })

}

LogginController.usuarios = (req, res, next) => {

    var usuario = req.body.usu,

        contraseña = req.body.contra

    LogginModel.usuarios([usuario, contraseña], (err, results) => {

        if (err) throw err;

        if (results.length === 0) {

            res.render('usuario_no_encontrado', {

                title: 'Usuario no encontrado',

                mensaje: 'Tus credenciales no coinciden con ninguna de la base de datos'

            });

        } else {

            res.redirect('/pelis')
        }

    })

}

LogginController.mostrarRegistro = (req, res, next) => {

    res.render('registrar', {

        mensaje: 'Aquí puedes registrarte'

    })

}

LogginController.registrar = (req, res, next) => {

    var nuevoUsuario = req.body.nuevoUsu,

        nuevaContraseña = req.body.nuevaContra

    LogginModel.registrar([nuevoUsuario, nuevaContraseña], (err) => {

        if (err) {

            res.render('usuario_encontrado', {

                mensaje: 'El usuario ya existe en la base de datos'

            });

        } else {

            res.render('usuario_insertado', {

                mensaje: 'Tus credenciales se han incluido correctamente'

            });

        }

    })


}

LogginController.comprobarUsuario = (req, res, next) => {

    res.render('comprobar_usuario')

}

LogginController.buscarUsuario = (req, res, next) => {

    const usu = req.body.usu;

    LogginModel.buscarUsuario(usu, (err, results) => {

        if (err) throw err;

        if (results.length !== 0) {

            res.redirect('/cambiar_contrasena/' + usu);

        } else {

            res.render('usuario_no_encontrado', {

                title: 'Usuario no encontrado',

                mensaje: 'Tus credenciales no coinciden con ninguna de la base de datos'

            });

        }

    });

}

LogginController.introducirContraseña = (req, res, next) => {

    const usu = req.params.usu;

    res.render('cambiar_contrasena', {

        usu: usu,

        mensaje: 'muy bien vamos a cambiar tu contraseña',

    })

}

LogginController.actualizarContraseña = (req, res, next) => {

    const usu = req.body.usu.toString();

    const nuevaContra = req.body.nuevaContraseña.toString();

    LogginModel.comprobarContraseña(usu,nuevaContra,(err,count) => {

        console.log(count)

        if (err) throw err;

        if (count<0) {

            LogginModel.actualizarContraseña(nuevaContra, usu, (err, results) => {

                if (err) throw err;
        
                if (results.length !== 0) {
        
                    res.render('actualiza_contraseña', {
        
                        mensaje: 'tu contraseña ha sido actualizada correctamente'
        
                    });
        
                } else {
        
                    res.render('actualiza_contraseña', {
        
                        mensaje: 'No se ha podido actualizar la contraseña'
        
                    });
        
                }
        
            });

        }else{

            res.render('contraseña_existente',{

                mensaje:'La contraseña introducida es la existente en la base de datos'

            })

        }

    })

}

LogginController.error404 = (req, res, next) => { //este metodo no existe en el modelo porque no tiene nada que ver con rutas, simplemente controla un error

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

module.exports = LogginController;