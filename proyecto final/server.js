var app=require('./app'),

    server=app.listen(app.get('port'), ()=>{ //utiliza el getter para extraerla el set del 'port' que incluye el valor del puerto que vamso a utilizar

        console.log(`Iniciamos express en el puerto ${app.get('port')}`)

    })