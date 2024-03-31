var conn = require('./movie_connection'),

    MovieModel = () => {



    }

    MovieModel.getAll = (callback) => conn.query('SELECT * FROM peliculas',callback); 

    MovieModel.getOne = (id,callback) => conn.query("SELECT * FROM peliculas WHERE id = ?", id , callback)

    MovieModel.insert = (data,callback) => conn.query('INSERT INTO peliculas SET ?', data , callback);

    MovieModel.update = (data,callback) => conn.query('UPDATE peliculas SET ? WHERE id=?', [data,data.movie_id], callback)

    MovieModel.delete = (id,callback) => conn.query("DELETE FROM peliculas WHERE id = ?",id,callback)

    module.exports=MovieModel;




    //aquí se define la query que trae todas las películas y recibe una callback que se ejecuta en el controlador
    //se llama a este archivo en el controlador y se define allí la callback