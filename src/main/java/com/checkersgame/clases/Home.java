package com.checkersgame.clases;

public class Home extends javax.swing.JPanel {

    public Home() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelLogo = new javax.swing.JLabel();
        jLabelEmpecemos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaInfo = new javax.swing.JTextArea();

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo1.0.png"))); // NOI18N

        jLabelEmpecemos.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabelEmpecemos.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEmpecemos.setText("¿Listo para Jugar? Empecemos!!!");

        jScrollPane1.setBorder(null);

        jTextAreaInfo.setEditable(false);
        jTextAreaInfo.setBackground(new java.awt.Color(0, 0, 0));
        jTextAreaInfo.setColumns(20);
        jTextAreaInfo.setFont(new java.awt.Font("Lucida Calligraphy", 1, 12)); // NOI18N
        jTextAreaInfo.setForeground(new java.awt.Color(255, 255, 255));
        jTextAreaInfo.setRows(5);
        jTextAreaInfo.setText("El juego de las damas es un juego de mesa para dos personas en un tablero de 64 cuadros de \n8x8 celdas, el mismo que se utiliza para jugar al ajedrez. Cada jugador dispone de 12 fichas \nde un mismo color situadas en las casillas negras más próximas a él. \n\nLas fichas se mueven en diagonal sobre los cuadros negros del tablero y pueden comer las \nfichas del contrario saltando sobre ellas. La dama o reina se mueve también en diagonal, \npero puede hacerlo hacia delante y hacia atrás. El jugador que capture todas las piezas del \noponente o lo acorrale para que no pueda realizar ningún tipo de movimiento, gana la \npartida.\n");
        jTextAreaInfo.setBorder(null);
        jTextAreaInfo.setOpaque(false);
        jTextAreaInfo.setSelectionColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(jTextAreaInfo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(jLabelLogo))
            .addGroup(layout.createSequentialGroup()
                .addGap(370, 370, 370)
                .addComponent(jLabelEmpecemos, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabelLogo)
                .addGap(15, 15, 15)
                .addComponent(jLabelEmpecemos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelEmpecemos;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaInfo;
    // End of variables declaration//GEN-END:variables
}
