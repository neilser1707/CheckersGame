package com.checkersgame.clases;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

public final class Tablero extends javax.swing.JPanel {

    private Juego game = new Juego();

    private int numCasilla1;
    private int numCasilla2;
    private final FichaBlanca blancas = new FichaBlanca();
    private final FichaNegra negras = new FichaNegra();
    static int[] estadoCasillas = new int[64];
    ArrayList<JPanel> casillas = new ArrayList<>();

    public Tablero() {
        numCasilla1 = -1;

        initComponents();
        initStyle();
        dibujarTablero();
        ponerFichas();
    }

    public void initStyle() {
        if (game.getCambioDeTurno() == 2) {
            jLabelTurnoNegras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turno1.png")));
            jLabelNegras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turnoN.png")));
            jLabelTurnoBlancas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/parche.png")));
            jLabelBlancas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turnoBno.png")));
        } else {
            jLabelTurnoNegras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/parche.png")));
            jLabelNegras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turnoNno.png")));
            jLabelTurnoBlancas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turno.png")));
            jLabelBlancas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turnoB.png")));
        }
        setBackground(new Color(193, 152, 120));
    }

    public void dibujarTablero() {
        jPanel1.setLayout(new GridLayout(8, 8));
        for (int i = 0; i < 64; i++) {
            JPanel cuadrado = new JPanel();
            if (i % 2 == 0) {
                cuadrado.setBackground((i % 16 < 8) ? Color.BLACK : Color.WHITE);
            } else {
                cuadrado.setBackground((i % 16 < 8) ? Color.WHITE : Color.BLACK);
            }
            jPanel1.add(cuadrado);
            casillas.add(cuadrado);
        }
    }

    public void ponerFichas() {
        for (int i = 0; i < blancas.posicionesBlancas.size(); i++) {
            casillas.get(blancas.posicionesBlancas.get(i)).add(blancas.fichasBlancas.get(i));
        }
        for (int i = 0; i < negras.posicionesNegras.size(); i++) {
            casillas.get(negras.posicionesNegras.get(i)).add(negras.fichasNegras.get(i));
        }

        for (int i = 0; i < 64; i++) {
            if (estadoCasillas[i] == 3) {
                casillas.get(i).removeAll();
                casillas.get(i).revalidate();
                casillas.get(i).add(blancas.reinaBlanca);
                casillas.get(i).repaint();
            }
            if (estadoCasillas[i] == 4) {
                casillas.get(i).removeAll();
                casillas.get(i).revalidate();
                casillas.get(i).add(negras.reinaNegra);
                casillas.get(i).repaint();
            }
        }
    }

    public void presionarCasilla() {
        jPanel1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                JPanel casilla = (JPanel) jPanel1.getComponentAt(x, y);
                int index = casillas.indexOf(casilla);
                if (casilla.getBackground().equals(Color.BLACK)) {
                    if (numCasilla1 == -1) {
                        numCasilla1 = index;
                    } else {
                        numCasilla2 = index;
                        game.recibirCasillas(casillas);
                        game.asignarTurno(estadoCasillas, numCasilla1, numCasilla2);
                        numCasilla1 = -1;
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonPasarTurno = new javax.swing.JButton();
        jLabelNegras = new javax.swing.JLabel();
        jLabelBlancas = new javax.swing.JLabel();
        jLabelTurnoBlancas = new javax.swing.JLabel();
        jLabelTurnoNegras = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 153, 153));
        setPreferredSize(new java.awt.Dimension(664, 664));

        jPanel1.setPreferredSize(new java.awt.Dimension(664, 664));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 591, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButtonPasarTurno.setFont(new java.awt.Font("Leelawadee UI", 1, 11)); // NOI18N
        jButtonPasarTurno.setForeground(new java.awt.Color(51, 255, 255));
        jButtonPasarTurno.setText("Pasar Turno");
        jButtonPasarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPasarTurnoActionPerformed(evt);
            }
        });

        jLabelNegras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turnoNno.png"))); // NOI18N

        jLabelBlancas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turnoB.png"))); // NOI18N

        jLabelTurnoBlancas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turno.png"))); // NOI18N

        jLabelTurnoNegras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turno1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonPasarTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelBlancas)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabelTurnoBlancas)
                                .addGap(12, 12, 12)))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelNegras)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelTurnoNegras)
                        .addGap(32, 32, 32))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabelBlancas)
                .addGap(61, 61, 61)
                .addComponent(jLabelTurnoBlancas)
                .addGap(42, 42, 42)
                .addComponent(jButtonPasarTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabelTurnoNegras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jLabelNegras)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPasarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPasarTurnoActionPerformed

        int turnoActual = game.getCambioDeTurno();
        int turnoSiguiente = game.getTurnoSiguiente();

        boolean caminoBlanca = game.isCaminoBlanca();
        boolean caminoNegra = game.isCaminoNegra();

        if (turnoActual != 3) {
            if ((turnoActual == 0) || (caminoBlanca && turnoActual == 1) || (caminoNegra && turnoActual == 2)) {

                if (turnoActual == turnoSiguiente && turnoSiguiente == 1) {
                    game.setCambioDeTurno(2);
                    turnoSiguiente = 2;
                } else if (turnoActual == turnoSiguiente && turnoSiguiente == 2) {
                    game.setCambioDeTurno(1);
                    turnoSiguiente = 1;
                } else {
                    game.setCambioDeTurno(turnoSiguiente);
                }

                game.setPasadaBlanca(-1);
                game.setPasadaNegra(-1);

                if (turnoSiguiente == 2) {

                    jLabelBlancas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turnoBno.png")));
                    jLabelBlancas.revalidate();
                    jLabelBlancas.repaint();

                    jLabelNegras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turnoN.png")));
                    jLabelNegras.revalidate();
                    jLabelNegras.repaint();

                    jLabelTurnoBlancas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/parche.png")));
                    jLabelTurnoBlancas.revalidate();
                    jLabelTurnoBlancas.repaint();

                    jLabelTurnoNegras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turno1.png")));
                    jLabelTurnoNegras.revalidate();
                    jLabelTurnoNegras.repaint();

                } else {

                    jLabelBlancas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turnoB.png")));
                    jLabelBlancas.revalidate();
                    jLabelBlancas.repaint();

                    jLabelNegras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turnoNno.png")));
                    jLabelNegras.revalidate();
                    jLabelNegras.repaint();

                    jLabelTurnoBlancas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turno.png")));
                    jLabelTurnoBlancas.revalidate();
                    jLabelTurnoBlancas.repaint();

                    jLabelTurnoNegras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/parche.png")));
                    jLabelTurnoNegras.revalidate();
                    jLabelTurnoNegras.repaint();

                }

                game.setCaminoNegra(false);
                game.setCaminoBlanca(false);

            } else {
                JOptionPane.showMessageDialog(null, "Debe mover una ficha");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ya se acabó el juego");
        }
    }//GEN-LAST:event_jButtonPasarTurnoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPasarTurno;
    private javax.swing.JLabel jLabelBlancas;
    private javax.swing.JLabel jLabelNegras;
    public javax.swing.JLabel jLabelTurnoBlancas;
    public javax.swing.JLabel jLabelTurnoNegras;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
