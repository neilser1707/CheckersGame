package com.checkersgame.clases;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public final class Principal extends javax.swing.JFrame {

    private final JButton[] botones = new JButton[6];
    private boolean estaJugando;

    public Principal() {
        initComponents();
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/Logo1.0.png"));
        setIconImage(icon.getImage());
        initStyleButton();
        initHome();
    }

    public void initHome() {
        Home home = new Home();
        home.setSize(1020, 670);
        home.setLocation(0, 0);

        jPanelPrincipal.removeAll();
        jPanelPrincipal.add(home);
        jPanelPrincipal.revalidate();
        jPanelPrincipal.repaint();
    }

    public void initStyleButton() {
        botones[0] = jButtonInicio;
        botones[1] = jButtonNuevaPartida;
        botones[2] = jButtonSalvarPartida;
        botones[3] = jButtonCargarPartida;
        botones[4] = jButtonConfiguracion;
        botones[5] = jButtonAcercaDe;

        for (JButton i : botones) {
            i.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    i.setFont(new Font("Tahoma", Font.BOLD, 24));
                    i.setForeground(new Color(193, 152, 120));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    i.setFont(new Font("Tahoma", Font.BOLD, 18));
                    i.setForeground(Color.WHITE);
                }
            });
        }
    }

    public void initJuego() {
        Partida.iniciarPartida();
    }

    public void salvarPartida() {
        SalvarPartida fondo = new SalvarPartida();
        fondo.setSize(1020, 670);
        fondo.setLocation(0, 0);

        jPanelPrincipal.removeAll();
        jPanelPrincipal.add(fondo);
        jPanelPrincipal.revalidate();
        jPanelPrincipal.repaint();
    }

    public void cargarPartida() {
        CargarPartida fondo = new CargarPartida();
        fondo.setSize(1020, 670);
        fondo.setLocation(0, 0);

        jPanelPrincipal.removeAll();
        jPanelPrincipal.add(fondo);
        jPanelPrincipal.revalidate();
        jPanelPrincipal.repaint();
    }

    public void configuracion() {
        Configuracion fondo = new Configuracion();
        fondo.setSize(1020, 670);
        fondo.setLocation(0, 0);

        jPanelPrincipal.removeAll();
        jPanelPrincipal.add(fondo);
        jPanelPrincipal.revalidate();
        jPanelPrincipal.repaint();
    }

    public void acercaDe() {
        AcercaDe fondo = new AcercaDe();
        fondo.setSize(1020, 670);
        fondo.setLocation(0, 0);

        jPanelPrincipal.removeAll();
        jPanelPrincipal.add(fondo);
        jPanelPrincipal.revalidate();
        jPanelPrincipal.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBackground = new javax.swing.JPanel();
        jPanelMenu = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButtonInicio = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonNuevaPartida = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jButtonSalvarPartida = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jButtonCargarPartida = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jButtonConfiguracion = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jButtonAcercaDe = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jPanelPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanelBackground.setPreferredSize(new java.awt.Dimension(1350, 670));

        jPanelMenu.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ficha.png"))); // NOI18N

        jButtonInicio.setBackground(new java.awt.Color(0, 0, 0));
        jButtonInicio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonInicio.setForeground(new java.awt.Color(255, 255, 255));
        jButtonInicio.setText("Inicio");
        jButtonInicio.setBorder(null);
        jButtonInicio.setBorderPainted(false);
        jButtonInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonInicio.setRolloverEnabled(false);
        jButtonInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInicioActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jButtonNuevaPartida.setBackground(new java.awt.Color(0, 0, 0));
        jButtonNuevaPartida.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonNuevaPartida.setForeground(new java.awt.Color(255, 255, 255));
        jButtonNuevaPartida.setText("Nueva Partida");
        jButtonNuevaPartida.setBorder(null);
        jButtonNuevaPartida.setBorderPainted(false);
        jButtonNuevaPartida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonNuevaPartida.setRolloverEnabled(false);
        jButtonNuevaPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevaPartidaActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        jButtonSalvarPartida.setBackground(new java.awt.Color(0, 0, 0));
        jButtonSalvarPartida.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonSalvarPartida.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSalvarPartida.setText("Salvar Partida");
        jButtonSalvarPartida.setBorder(null);
        jButtonSalvarPartida.setBorderPainted(false);
        jButtonSalvarPartida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSalvarPartida.setRolloverEnabled(false);
        jButtonSalvarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarPartidaActionPerformed(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));

        jButtonCargarPartida.setBackground(new java.awt.Color(0, 0, 0));
        jButtonCargarPartida.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonCargarPartida.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCargarPartida.setText("Cargar Partida");
        jButtonCargarPartida.setBorder(null);
        jButtonCargarPartida.setBorderPainted(false);
        jButtonCargarPartida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCargarPartida.setRolloverEnabled(false);
        jButtonCargarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargarPartidaActionPerformed(evt);
            }
        });

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        jButtonConfiguracion.setBackground(new java.awt.Color(0, 0, 0));
        jButtonConfiguracion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonConfiguracion.setForeground(new java.awt.Color(255, 255, 255));
        jButtonConfiguracion.setText("Configuración");
        jButtonConfiguracion.setBorder(null);
        jButtonConfiguracion.setBorderPainted(false);
        jButtonConfiguracion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonConfiguracion.setRolloverEnabled(false);
        jButtonConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfiguracionActionPerformed(evt);
            }
        });

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));

        jButtonAcercaDe.setBackground(new java.awt.Color(0, 0, 0));
        jButtonAcercaDe.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonAcercaDe.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAcercaDe.setText("Acerca de");
        jButtonAcercaDe.setBorder(null);
        jButtonAcercaDe.setBorderPainted(false);
        jButtonAcercaDe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAcercaDe.setRolloverEnabled(false);
        jButtonAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcercaDeActionPerformed(evt);
            }
        });

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuLayout.createSequentialGroup()
                .addGap(0, 44, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(38, 38, 38))
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMenuLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelMenuLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonNuevaPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonAcercaDe, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSalvarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonCargarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelMenuLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jButtonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonNuevaPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSalvarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCargarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAcercaDe, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanelPrincipal.setPreferredSize(new java.awt.Dimension(886, 670));
        jPanelPrincipal.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelBackgroundLayout = new javax.swing.GroupLayout(jPanelBackground);
        jPanelBackground.setLayout(jPanelBackgroundLayout);
        jPanelBackgroundLayout.setHorizontalGroup(
            jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                .addGap(309, 309, 309)
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelBackgroundLayout.setVerticalGroup(
            jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicioActionPerformed
        initHome();
        estaJugando = false;
    }//GEN-LAST:event_jButtonInicioActionPerformed

    private void jButtonNuevaPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevaPartidaActionPerformed
        initJuego();
        estaJugando = true;
    }//GEN-LAST:event_jButtonNuevaPartidaActionPerformed

    private void jButtonSalvarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarPartidaActionPerformed
        if (estaJugando) {
            salvarPartida();
            estaJugando = false;
        } else {
            JOptionPane.showMessageDialog(null, "Inicie una partida para que pueda guardarla");
        }
    }//GEN-LAST:event_jButtonSalvarPartidaActionPerformed

    private void jButtonCargarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargarPartidaActionPerformed
        estaJugando = false;
        cargarPartida();
    }//GEN-LAST:event_jButtonCargarPartidaActionPerformed

    private void jButtonConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfiguracionActionPerformed
        estaJugando = false;
        configuracion();
    }//GEN-LAST:event_jButtonConfiguracionActionPerformed

    private void jButtonAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcercaDeActionPerformed
        estaJugando = false;
        acercaDe();
    }//GEN-LAST:event_jButtonAcercaDeActionPerformed

    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Failed to initialize LaF");
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAcercaDe;
    private javax.swing.JButton jButtonCargarPartida;
    private javax.swing.JButton jButtonConfiguracion;
    private javax.swing.JButton jButtonInicio;
    private javax.swing.JButton jButtonNuevaPartida;
    private javax.swing.JButton jButtonSalvarPartida;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelBackground;
    private javax.swing.JPanel jPanelMenu;
    public static javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    // End of variables declaration//GEN-END:variables
}
