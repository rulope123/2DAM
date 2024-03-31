/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class Eliminar extends JFrame{
    private JLabel nombre;
    private JTextField nombreFiltrado;
    private JButton eliminar,volverPaginaPrincipal;
    private JPanel panel;
    private ControlContactos paginaPrincipal;
    private int cont=0;
    
    public Eliminar(ArrayList<Contactos> Contacto){
        
        nombre = new JLabel();
        nombreFiltrado = new JTextField(10);
        eliminar = new JButton();
        volverPaginaPrincipal = new JButton();
        panel = new JPanel();
        
        nombre.setText("Introduce el nombre del contacto a eliminar");
        eliminar.setText("Eliminar");
        volverPaginaPrincipal.setText("Volver a la página principal");
        eliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                for(int i=0;i<Contacto.size();i++){
                    if(Contacto.get(i).getNombre().equals(nombreFiltrado.getText())){
                        Contacto.remove(i);
                        System.out.println("El contacto ha sido eliminado");
                        nombreFiltrado.setText("");
                        cont++;
                    }
                }
                
                if(cont == 0){
                        System.out.println("El contacto introducido no existe");
                        nombreFiltrado.setText("");
                    }
                
            }  
        });
        
        volverPaginaPrincipal.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                paginaPrincipal = new ControlContactos();
                paginaPrincipal.setVisible(true);
                paginaPrincipal.actualizarLista(Contacto);
            }
            
        });
        
        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(nombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nombreFiltrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volverPaginaPrincipal))
                .addGap(86, 86, 86)
                .addComponent(eliminar)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreFiltrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombre)
                    .addComponent(eliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(volverPaginaPrincipal)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
            ));
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }
}
