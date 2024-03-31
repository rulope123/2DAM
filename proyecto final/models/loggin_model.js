var conn = require('./movie_connection'),

    LogginModel = () => {



    }

    LogginModel.usuarios = (data,callback) => conn.query('SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?', data, callback)

    LogginModel.registrar = (data,callback) => conn.query('INSERT INTO usuarios (usuario, contraseña) VALUES (?, ?)', data,callback)

    LogginModel.buscarUsuario = (data, callback) => conn.query('SELECT * FROM usuarios WHERE usuario = ?', [data], callback);

    LogginModel.comprobarContraseña = (usu, nuevaContra, callback) => conn.query('SELECT COUNT(*) as count FROM usuarios WHERE usuario = ? AND contraseña = ?', [usu, nuevaContra], callback);

    LogginModel.actualizarContraseña = (nuevaContra, usu, callback) => conn.query('UPDATE usuarios SET contraseña = ? WHERE usuario = ?', [nuevaContra, usu], callback);
      
    module.exports=LogginModel;