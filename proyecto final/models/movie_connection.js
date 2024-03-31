// Requerimos el módulo de `mysql`
var mysql = require('mysql');

var datos=require('./db_conf.json')

// Creamos un objeto con las opciones de configuración de la base de datos
/*var conf = {
  host: process.env.DB_HOST,
  port: process.env.DB_PORT,
  user: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_NAME
};*/

var conf = {

  host: datos.mysql.host,
  port: datos.mysql.port,
  user: datos.mysql.user,
  password: datos.mysql.password,
  database: datos.mysql.database

}

console.log(datos.mysql.host)
console.log(datos.mysql.port)
console.log(datos.mysql.user)
console.log(datos.mysql.password)
console.log(datos.mysql.database)

// Usamos `mysql.createConnection` para crear un objeto de conexión a la base de datos
var myConn = mysql.createConnection(conf);

// Conectamos a la base de datos
myConn.connect(function(err) {
  if (err) {
    console.error('Error al conectarse a mysql:', err.stack);
    return;
  }
  console.log('Conexión establecida con Mysql con id:', myConn.threadId);
});

module.exports = myConn;
