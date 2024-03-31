/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication9;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
/**
 *
 * @author raulp
 */
public class Editar extends JFrame{
    
    private javax.swing.JLabel apellidos;
    private javax.swing.JButton cambiarApellido;
    private javax.swing.JButton cambiarCorreo;
    private javax.swing.JButton cambiarNombre;
    private javax.swing.JButton cambiarTelefono;
    private javax.swing.JLabel correo;
    private javax.swing.JButton filtrar;
    private javax.swing.JButton volverParaAnadir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nom;
    private javax.swing.JLabel nombre;
    private javax.swing.JTextField nuevoApellido;
    private javax.swing.JTextField nuevoCorreo;
    private javax.swing.JTextField nuevoNombre;
    private javax.swing.JTextField nuevoTelefono;
    private javax.swing.JLabel telefono;
    private javax.swing.JTextField textoFiltrado;
    private javax.swing.JLabel textoInformativo;
    private Contactos contactoEditar;
    private boolean arroba, punto;
    private ControlContactos volver;
    
    public Editar(ArrayList<Contactos> Contactos){
        
        jPanel1 = new javax.swing.JPanel();
        nom = new javax.swing.JLabel();
        textoFiltrado = new javax.swing.JTextField(10);
        filtrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        nombre = new javax.swing.JLabel();
        apellidos = new javax.swing.JLabel();
        telefono = new javax.swing.JLabel();
        correo = new javax.swing.JLabel();
        nuevoNombre = new javax.swing.JTextField(10);
        nuevoApellido = new javax.swing.JTextField(10);
        nuevoTelefono = new javax.swing.JTextField(10);
        nuevoCorreo = new javax.swing.JTextField(10);
        cambiarNombre = new javax.swing.JButton();
        cambiarApellido = new javax.swing.JButton();
        cambiarTelefono = new javax.swing.JButton();
        cambiarCorreo = new javax.swing.JButton();
        volverParaAnadir = new javax.swing.JButton();
        textoInformativo = new javax.swing.JLabel();
        
        nom.setText("Introduce el nombre del contacto a editar");
        filtrar.setText("Buscar");
        nombre.setText("nombre: ");
        nuevoNombre.setEnabled(false);
        cambiarNombre.setText("Pulsa aquí para cambiar el nombre");
        cambiarNombre.setEnabled(false);
        apellidos.setText("Apellido: ");
        nuevoApellido.setEnabled(false);
        cambiarApellido.setText("Pulsa aquí para cambiar el apellido");
        cambiarApellido.setEnabled(false);
        telefono.setText("telefono: ");  
        nuevoTelefono.setEnabled(false);
        cambiarTelefono.setText("Pulsa aquí para cambiar el telefono");
        cambiarTelefono.setEnabled(false);
        correo.setText("correo: ");
        nuevoCorreo.setEnabled(false);
        cambiarCorreo.setText("Pulsa aquí para cambiar el correo");
        cambiarCorreo.setEnabled(false);
        volverParaAnadir.setText("Pulsa para volver a la página principal");
        
        filtrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Contactos nombreFiltrado:Contactos){
                    if(textoFiltrado.getText().equals(nombreFiltrado.getNombre())){
                        contactoEditar = new Contactos(nombreFiltrado.getNombre(),nombreFiltrado.getApellidos(),nombreFiltrado.getTelefono(),nombreFiltrado.getCorreo_electronico());
                        textoInformativo.setText("Aquí podrás editar tus contactos");
                        nuevoNombre.setEnabled(true);
                        nuevoApellido.setEnabled(true);
                        nuevoTelefono.setEnabled(true);
                        nuevoCorreo.setEnabled(true);
                        cambiarNombre.setEnabled(true);
                        cambiarApellido.setEnabled(true);
                        cambiarTelefono.setEnabled(true);
                        cambiarCorreo.setEnabled(true);
                        nuevoNombre.setText(contactoEditar.getNombre());
                        nuevoApellido.setText(contactoEditar.getApellidos());
                        nuevoTelefono.setText(String.valueOf(contactoEditar.getTelefono()));
                        nuevoCorreo.setText(contactoEditar.getCorreo_electronico());
                        break;
                    }else{
                        textoInformativo.setText("El nombre del contacto introducido no existe");
                        nuevoNombre.setEnabled(false);
                        nuevoApellido.setEnabled(false);
                        nuevoTelefono.setEnabled(false);
                        nuevoCorreo.setEnabled(false);
                        cambiarNombre.setEnabled(false);
                        cambiarApellido.setEnabled(false);
                        cambiarTelefono.setEnabled(false);
                        cambiarCorreo.setEnabled(false);
                        nuevoNombre.setText("");
                        nuevoApellido.setText("");
                        nuevoTelefono.setText("");
                        nuevoCorreo.setText("");
                    }
                }
            }
            
        });
        
        cambiarNombre.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comprobacionGenerica(nuevoNombre.getText())){
                    if(nuevoNombre.getText().equals(contactoEditar.getNombre())){
                        System.out.println("El nuevo nombre no puede ser el mismo que el ya existente");
                    }else{
                        contactoEditar.setNombre(nuevoNombre.getText());
                        System.out.println("El nuevo nombre es: " + contactoEditar.getNombre());
                    }
                        }else{
                    System.out.println("El nuevo nombre no puede estar vacio");
                }
                    }
        });
        
        cambiarApellido.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comprobacionGenerica(nuevoApellido.getText())){
                    if(nuevoApellido.getText().equals(contactoEditar.getApellidos())){
                        System.out.println("El nuevo apellido no puede ser el mismo que el ya existente");
                    }else{
                        contactoEditar.setApellidos(nuevoApellido.getText());
                        System.out.println("El nuevo apellido es: " + contactoEditar.getApellidos());
                        }
                    }else{
                    System.out.println("El nuevo apellido no puede estar vacio");
                }
                        
                    }
                
            
        });
                
        cambiarTelefono.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nuevoTelefono.getText().length()==9 && comprobacionGenerica(nuevoTelefono.getText())){
                    if(Integer.parseInt(nuevoTelefono.getText())==contactoEditar.getTelefono()){
                        System.out.println("El nuevo telefono no puede ser el mismo que el ya existente");
                    }else{
                        contactoEditar.setTelefono(Integer.parseInt(nuevoTelefono.getText()));
                        System.out.println("El nuevo telefono es: " + String.valueOf(contactoEditar.getTelefono()));
                    }
                }else{
                    System.out.println("Por favor, el teléfono debe de constar de 9 dígitos");
                }
                    }
        });
                        
        cambiarCorreo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
                if(comprobarCorreo(nuevoCorreo.getText()) && comprobacionGenerica(nuevoCorreo.getText())){
                    if(nuevoCorreo.getText().equals(contactoEditar.getCorreo_electronico())){
                        System.out.println("El nuevo cooreo tiene que ser distinto al anterior");
                    }else{
                        contactoEditar.setCorreo_electronico(nuevoCorreo.getText());
                        System.out.println("El nuevo correo es: " + contactoEditar.getCorreo_electronico());
                    }
                }else{
                    System.out.println("El correo debe de incluir un @ y un . y no puede estar vacio");
                }
                        
                    }
        });
        

        volverParaAnadir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                reemplazar(Contactos);
                dispose();
                volver = new ControlContactos();
                volver.actualizarLista(Contactos);
                volver.setVisible(true);
            }
            
        });
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nom)
                .addGap(61, 61, 61)
                .addComponent(textoFiltrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(filtrar)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nom)
                    .addComponent(textoFiltrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filtrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textoInformativo)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombre)
                                    .addComponent(apellidos)
                                    .addComponent(telefono))
                                .addGap(84, 84, 84))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(correo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nuevoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nuevoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nuevoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nuevoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(volverParaAnadir))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cambiarCorreo)
                    .addComponent(cambiarNombre)
                    .addComponent(cambiarApellido)
                    .addComponent(cambiarTelefono))
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(textoInformativo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre)
                    .addComponent(nuevoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cambiarNombre))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidos)
                    .addComponent(nuevoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cambiarApellido))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(telefono)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nuevoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cambiarTelefono)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nuevoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(correo))
                    .addComponent(cambiarCorreo))
                .addGap(33, 33, 33)
                .addComponent(volverParaAnadir)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }
    
            public boolean comprobacionGenerica(String campo){
             boolean resultado = !campo.equals("")? true:false;
             return resultado;
        }

        public boolean comprobarCorreo(String correo){

            arroba=false;
            punto=false;
            for(int i=0;i<correo.length();i++){
                char letra = correo.charAt(i);
                if(letra=='@') arroba=true;
                if(letra=='.') punto=true;
            }
            return arroba && punto;
        }
        
        public void reemplazar(ArrayList<Contactos> contactos){
            for (int i=0;i<contactos.size();i++){
                if(textoFiltrado.getText().equals(contactos.get(i).getNombre())){
                    contactos.set(i, contactoEditar);
                }
            }
        }
        
        
}
