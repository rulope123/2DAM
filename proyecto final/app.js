var express = require('express'),

    body_parser = require('body-parser'),

    movieRoutes=require('./routes/movie_router.js'),

    logginRoutes=require('./routes/loggin_router.js'),

    port=(3006),

    publicDir=express.static(`${__dirname}/public`),

    app=express();


app
    .use(express.json())

    .use( body_parser.json() )

    .use(body_parser.urlencoded({ extended: true }))

    .use(express.static('public'))

    .use(publicDir) 

    .use(movieRoutes)

    .use(logginRoutes)

    .engine('html', require('ejs').renderFile)

    .set('view engine', 'pug')

    .set('views', './views')

    .set('port',port)

    const VIEWS = __dirname + '/views';

    

module.exports=app