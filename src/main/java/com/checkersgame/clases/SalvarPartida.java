package com.checkersgame.clases;

import java.sql.*;
import javax.swing.JOptionPane;

public class SalvarPartida extends javax.swing.JPanel {

    static int[] estadoCasillas;
    static int cambioDeTurno;
    static int turnoSiguiente;
    static int pasadaNegra;
    static int pasadaBlanca;
    static boolean caminoNegra;
    static boolean caminoBlanca;
    static boolean repetirGuardado;

    public SalvarPartida() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Algerian", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Guardar Partida");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 380, -1));

        jLabel3.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre del jugador de las fichas Blancas");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, 330, 32));

        jTextField1.setFont(new java.awt.Font("Algerian", 0, 11)); // NOI18N
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 250, 283, 36));

        jButton1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton1.setText("Guardar Partida");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 520, 181, 69));

        jTextField2.setFont(new java.awt.Font("Algerian", 0, 11)); // NOI18N
        add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 283, 36));

        jLabel4.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre del jugador de las fichas Negras");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 320, 32));

        jTextField3.setFont(new java.awt.Font("Algerian", 0, 11)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 410, 290, 40));

        jLabel5.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre de la partida");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, 180, 32));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(repetirGuardado){
            JOptionPane.showMessageDialog(null, "Si desea guardar otra partida debe iniciar una nueva");
        }else {
            repetirGuardado = true;
            if (jTextField1.getText().equals("") || jTextField2.getText().equals("") || jTextField3.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos si desea guardar la partida");
        } else {
            String estados = "";
            int caminoNegra = 0;
            int caminoBlanca = 0;

            if (SalvarPartida.caminoNegra) {
                caminoNegra = 1;
            }
            if (SalvarPartida.caminoBlanca) {
                caminoBlanca = 1;
            }
            for (int i : estadoCasillas) {
                estados += String.valueOf(i);
            }

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "insert into partida values (?,?,?,?,?,?,?,?,?,?,?)");

                pst.setInt(1, 0);
                pst.setString(2, jTextField3.getText());
                pst.setString(3, jTextField2.getText());
                pst.setString(4, jTextField1.getText());
                pst.setInt(5, cambioDeTurno);
                pst.setInt(6, turnoSiguiente);
                pst.setString(7, estados);
                pst.setInt(8, caminoNegra);
                pst.setInt(9, caminoBlanca);
                pst.setInt(10, pasadaNegra);
                pst.setInt(11, pasadaBlanca);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Partida guardada");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al intentar guardar la partida" + e);
            }
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
