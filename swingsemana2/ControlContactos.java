/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author raulp
 */
public class ControlContactos extends JFrame{

    private JLabel contactos, nombre, apellidos, telefono, correo_electronico;
    private JTextField nom, ape, movil, correo;
    private JButton anadir, editar, borrar, mostrar;
    private JPanel jPanel1;
    private ArrayList<Contactos> listaContacto = new ArrayList<Contactos>();
    private boolean arroba, punto;
    private Editar ediccion;
    private Eliminar eliminar;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ControlContactos().setVisible(true);
            }
        });
    }
    
    public ControlContactos(){
        initComponents();
    }
    
     public void actualizarLista(ArrayList<Contactos> nuevaLista) {
        listaContacto = nuevaLista;
    }
    
    public void initComponents(){
        
        jPanel1 = new javax.swing.JPanel();
        contactos = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        apellidos = new javax.swing.JLabel();
        telefono = new javax.swing.JLabel();
        correo_electronico = new javax.swing.JLabel();
        nom = new javax.swing.JTextField(10);
        ape = new javax.swing.JTextField(10);
        movil = new javax.swing.JTextField(10);
        correo = new javax.swing.JTextField(10);
        anadir = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        mostrar= new JButton();        
        contactos.setText("Esta aplicación permite gestionar tus contactos");
        nombre.setText("Nombre");
        apellidos.setText("Apellidos");
        telefono.setText("Telefono");
        correo_electronico.setText("Correo");
        anadir.setText("añadir");
        editar.setText("editar");
        borrar.setText("borrar");
        mostrar.setText("Mostrar los contactos");

        anadir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                arroba = false;
                punto = false;
                for(int i=0; i<correo.getText().length();i++){
                    String validar = correo.getText();
                    char letra = validar.charAt(i);
                    if(letra=='@') arroba = true;
                    else if(letra=='.') punto = true;
                }

                if(!nom.getText().equals("") && !ape.getText().equals("") && !movil.getText().equals("")
                        && movil.getText().length()==9 && !correo.getText().equals("") && arroba && punto){
                    listaContacto.add(new Contactos(nom.getText(),ape.getText(),Integer.parseInt(movil.getText()),correo.getText()));
                            nom.setText("");
                            ape.setText("");
                            movil.setText("");
                            correo.setText("");
                    }else{
                    System.out.println("No puede haber campos vacios, el movil tiene que tener 9 dígitos y el correo debe contener al menos una @ y un .");
                }
                    
            }
            
        });
        
        editar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ediccion = new Editar(listaContacto);
                ediccion.setVisible(true);
            }
            
        });
        
        mostrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Contactos lista:listaContacto){
                    System.out.println("Nombre: " + lista.getNombre());
                    System.out.println("Apellidos: " + lista.getApellidos());
                    System.out.println("Telefono: " + lista.getTelefono());
                    System.out.println("Correo electronico: " + lista.getCorreo_electronico());
                }
            }
            
        });
        
        borrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                eliminar = new Eliminar(listaContacto);
                eliminar.setVisible(true);
            }
            
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombre)
                            .addComponent(apellidos)
                            .addComponent(telefono)
                            .addComponent(correo_electronico)
                            .addComponent(movil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(contactos)
                        .addGap(175, 175, 175))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(anadir)
                                .addGap(49, 49, 49)
                                .addComponent(editar)
                                .addGap(52, 52, 52)
                                .addComponent(borrar)
                                .addGap(59, 59, 59)
                                .addComponent(mostrar)))
                        .addGap(0, 47, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contactos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(apellidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(telefono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(movil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(correo_electronico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(anadir)
                    .addComponent(editar)
                    .addComponent(borrar)
                    .addComponent(mostrar))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }
    
    
}
