/*var express = require('express'),
    body_parser = require('body-parser'),
    mysql = require('mysql')
const app = express(),
    PORT = 3004,
    conexion = mysql.createConnection({
        host: "localhost",
        user: "root",
        password: "",
        database: "pelis"
    })

app.use(express.json());

app.use(body_parser.urlencoded({ extended: true }));

app.use(express.static('public'));

app.engine('html', require('ejs').renderFile);

app.set('view engine', 'pug')

    .set('views', './views');

const VIEWS = __dirname + '/views';

function error404(req, res, next) {

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

conexion.connect((err) => {

    return (err) ? console.log(`Error al conectarse a mysql: ${err.stack}`) : console.log(`Conexión establecida con Mysql con id: ${conexion.threadId}`)

})

app.get('/', (req, res) => {

    res.sendFile(VIEWS + '/index.html');

}).get('/registrar', (req, res) => {

    res.sendFile(VIEWS + '/registrar.html');

}).get('/contrasena', (req, res) => {

    res.sendFile(VIEWS + '/comprobar_usuario.html');

})

app.post('/', (req, res) => {

    var usuario = req.body.usu,

        contraseña = req.body.contra

    conexion.query('SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?', [usuario, contraseña], (error, results) => {

        if (error) throw error;

        if (results.length === 0) {

            res.render('usuario_no_encontrado', {

                title: 'Usuario no encontrado',

                mensaje: 'Tus credenciales no coinciden con ninguna de la base de datos'

            });

        } else {

            res.redirect('/pelis')
        }

    })

}).post('/registrar', (req, res) => {

    var nuevoUsuario = req.body.nuevoUsu,

        nuevaContraseña = req.body.nuevaContra

    conexion.query('INSERT INTO usuarios (usuario, contraseña) VALUES (?, ?)', [nuevoUsuario, nuevaContraseña], (err) => {

        if (err) {

            res.render('usuario_encontrado', {

                mensaje: 'El usuario ya existe en la base de datos'

            });

        } else {

            res.render('usuario_insertado', {

                mensaje: 'Tus credenciales se han incluido correctamente'

            });

        }

    });

}).post('/contrasena', (req, res) => {

    const usu = req.body.usu;
  
    conexion.query('SELECT * FROM usuarios WHERE usuario = ?', usu, (err, results) => {

      if (err) throw err;
  
      if (results.length !== 0) {

        res.redirect('/actualizar_contrasena?usu=' + usu); 

      } else {

        res.render('usuario_no_encontrado', {

          title: 'Usuario no encontrado',

          mensaje: 'Tus credenciales no coinciden con ninguna de la base de datos'

        });

      }

    });

  }).get('/actualizar_contrasena', (req, res) => {

    const usu = req.query.usu; 
  
    res.render('cambiar_contraseña', {

      mensaje: 'muy bien vamos a cambiar tu contraseña',

    });

  }).post('/actualizar_contrasena', (req, res) => {

    console.log(req.body)

    const usu = req.query.usu;

    console.log(usu)

    const nuevaContra = req.body.nuevaContraseña;
  
    conexion.query('UPDATE usuarios SET contraseña = ? WHERE usuario = ?', [nuevaContra, usu], (err, results) => {

      if (err) throw err;
  
      if (results.length!==0) {

        res.render('actualiza_contraseña', {

          mensaje: 'tu contraseña ha sido actualizada correctamente'

        });

      } else {

        res.render('actualiza_contraseña', {

          mensaje: 'No se ha podido actualizar la contraseña'

        });

      }

    });

  }).get('/pelis', (req, res) => {

    conexion.query("SELECT * FROM peliculas",(err,rows)=>{

        if(err) throw err

        res.render('pelis',{

            mensaje:'Bienvenido a una enciclopeida de la triología original de star wars',
            datos:rows
    
        })

    })

}).get('/agregar',(req,res)=>{

  res.render('añadirPeliculas',{

    mensaje:'Añade la película que desees'

  })

}).post("/agregar",(req,res)=>{

  const titulo=req.body.titulo,

      poster=req.body.poster,

      valoracion=req.body.valoracion

      conexion.query("INSERT INTO peliculas (título,imagen,valoracion) values (?,?,?)",[titulo,poster,valoracion],(err,results)=>{

        if(err) throw err

        if (results.length!==0) {

          res.redirect('/pelis')

        }else{

          res.end('Los datos no se han insertado correctamente')

        }

      })

}).get('/editar/:id',(req,res)=>{

  const id=req.params.id

  console.log(id)

  conexion.query("SELECT * FROM peliculas WHERE id=?",[id],(err,rows)=>{

    if (err) throw err

    if(rows.length!==0){

      res.render('editar',{

        mensaje:'Aquí puedes editar el campo que desees de la película',

        data: rows[0]

    });
    
    }else{

      res.end('Los datos no se han obtenido correctamente')

    }

  })

}).post('/actualizar/:id', (req, res, next) => {

  let movie = {

    título: req.body.titulo,

    imagen: req.body.poster,

    valoracion: req.body.valoracion

  }

  conexion.query('UPDATE peliculas SET ? WHERE id = ?', [movie, req.params.id], (err, rows) => {

    return (err) ? next(new Error('Error al actualizar')) : res.redirect("/pelis");

  });

}).post('/eliminar/:id',(req,res,next)=>{

        let id=req.params.id;

            conexion.query("DELETE FROM peliculas WHERE id = ?",id,(err,rows)=>{

                return (err) ? next(new Error('Registro no encontrado')):res.redirect('/pelis')

            })

        }).use(error404);

app.listen(PORT, () => {

    console.log(`Servidor iniciado en el puerto ${PORT}`);

});*/