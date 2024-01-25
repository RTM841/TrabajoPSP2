/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista;

import Modelo.ClienteHilo;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ruben
 */
public class Cliente extends javax.swing.JDialog {
    
    private int jugadas = 0;
    private int premios = 0;
    private int intentos = 0;
    private int id = 0;
    ClienteHilo cliente;
    private String mensaje = "";
    
    public int getPremios() {
        return premios;
    }
    
    public void setPremios(int premios) {
        this.premios = premios;
    }
    
    public Cliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        cliente = new ClienteHilo(this);
        cliente.start();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTFid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFfila = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFcolumna = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTApanel = new javax.swing.JTextArea();
        jBenviar = new javax.swing.JButton();
        jBsalir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTFjugadas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTFpremios = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("ID:");

        jTFid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFidActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("FILA");

        jTFfila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFfilaActionPerformed(evt);
            }
        });

        jLabel3.setText("COLUMNA");

        jTFcolumna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFcolumnaActionPerformed(evt);
            }
        });

        jTApanel.setColumns(20);
        jTApanel.setRows(5);
        jScrollPane1.setViewportView(jTApanel);

        jBenviar.setText("Enviar");
        jBenviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBenviarActionPerformed(evt);
            }
        });

        jBsalir.setText("Salir");
        jBsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsalirActionPerformed(evt);
            }
        });

        jLabel4.setText("Jugadas:");

        jTFjugadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFjugadasActionPerformed(evt);
            }
        });

        jLabel5.setText("Premios:");

        jTFpremios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFpremiosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(82, 82, 82)
                                .addComponent(jLabel2)
                                .addGap(51, 51, 51))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTFid, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jTFfila, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFcolumna, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel3)))
                        .addGap(98, 98, 98)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBsalir)
                            .addComponent(jBenviar)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTFjugadas, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTFpremios, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                        .addGap(27, 27, 27)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jBenviar)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFid, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFfila, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFcolumna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jBsalir)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTFjugadas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTFpremios, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFidActionPerformed

    private void jTFfilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFfilaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFfilaActionPerformed

    private void jTFjugadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFjugadasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFjugadasActionPerformed

    private void jTFpremiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFpremiosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFpremiosActionPerformed

    private void jTFcolumnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFcolumnaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFcolumnaActionPerformed

    private void jBenviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBenviarActionPerformed
        jugadas++;
        if (jugadas <= 4) {
            
             /*final String servidorIP = "localhost";
            final int puerto = 4444;*/
            jTFjugadas.setText(String.valueOf(jugadas));
            String fila = jTFfila.getText();
            String columna = jTFcolumna.getText();
             mensaje = fila + " " + columna;
            /*try (Socket socket = new Socket(servidorIP, puerto)) {
            enviarMensajeAlServidor(socket, fila+" "+columna);
            } catch (IOException e) {
            e.printStackTrace();
            }*/
        } else {
            JOptionPane.showMessageDialog(this, "Ya has jugado demasiado, DESCANSA Y VETE!!!");
        }

    }//GEN-LAST:event_jBenviarActionPerformed

    private void jBsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsalirActionPerformed
        dispose();
    }//GEN-LAST:event_jBsalirActionPerformed
    
   /* private static void enviarMensajeAlServidor(Socket socket, String mensaje) {
        try (OutputStream outputStream = socket.getOutputStream()) {
            byte[] mensajeBytes = mensaje.getBytes();
            outputStream.write(mensajeBytes);
            System.out.println("Mensaje enviado al servidor: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    //Metodo que se utiliza para añadir texto en mi textArea, de los demas clientes
    public void appendTextArea(String fila, String columna, String premio) {
        String textoAnterior = jTApanel.getText();
        String textoFinal = textoAnterior + "\n" + "[" + fila + "]" + "[" + columna + "] " + premio;
        jTApanel.setText(textoFinal);
        if (!premio.equals("SIN PREMIO")) {
            premios++;
            jTFpremios.setText(String.valueOf(premios));
        } else {
            jTFpremios.setText(String.valueOf(premios));
        }
        
    }
    
    public void escribirId(String id){
        jTFid.setText(id);
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public void escribirId(Integer id) {
        jTFid.setText(String.valueOf(id));
    }

    /*public String getMensaje(){
        String fila = jTFfila.getText();
        String columna = jTFcolumna.getText();
        return fila + " " + columna;
    }*/
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Cliente dialog = new Cliente(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBenviar;
    private javax.swing.JButton jBsalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTApanel;
    private javax.swing.JTextField jTFcolumna;
    private javax.swing.JTextField jTFfila;
    private javax.swing.JTextField jTFid;
    private javax.swing.JTextField jTFjugadas;
    private javax.swing.JTextField jTFpremios;
    // End of variables declaration//GEN-END:variables
}
