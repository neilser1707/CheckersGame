package com.checkersgame.clases;

import static com.checkersgame.clases.Principal.jPanelPrincipal;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.sql.*;

public final class Juego {

    private ArrayList<JPanel> casillas = new ArrayList<>();
    private final FichaBlanca blancas = new FichaBlanca();
    private final FichaNegra negras = new FichaNegra();
    private Tablero tab;

    public Tablero getTab() {
        return tab;
    }

    //Variables Lógicas
    private boolean caminoBlanca;
    private boolean caminoNegra;
    private int turnoSiguiente;
    private int cambioDeTurno;
    private int pasadaBlanca;
    private int pasadaNegra;
    static int valorPartida;
    private String partida;

    public Juego() {
        initVariables();
    }

    public void initVariables() {
        partida = CargarPartida.partida;
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select CambioTurno, TurnoSiguiente, PasadaNegra, PasadaBlanca, CaminoNegra, CaminoBlanca from partida where NombrePartida = '" + partida + "'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cambioDeTurno = rs.getInt("CambioTurno");
                turnoSiguiente = rs.getInt("TurnoSiguiente");
                pasadaNegra = rs.getInt("PasadaNegra");
                pasadaBlanca = rs.getInt("PasadaBlanca");
                if (rs.getInt("CaminoNegra") == 1) {
                    caminoNegra = true;
                }
                if (rs.getInt("CaminoBlanca") == 1) {
                    caminoBlanca = true;
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar variables " + e);
        }
    }

    public void iniciarJuego() {
        inicializarEstados();
        tab = new Tablero();
        tab.setSize(1020, 670);
        tab.setLocation(0, 0);

        tab.presionarCasilla();

        jPanelPrincipal.removeAll();
        jPanelPrincipal.add(tab);
        jPanelPrincipal.revalidate();
        jPanelPrincipal.repaint();

        if (cambioDeTurno == 1) {
            JOptionPane.showMessageDialog(null, "Turno de las fichas blancas");
        } else {
            JOptionPane.showMessageDialog(null, "Turno de las fichas Negras");
        }
    }

    public void inicializarEstados() {

        String partida = CargarPartida.partida;
        String estados = "";
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select EstadoCasillas from partida where NombrePartida = '" + partida + "'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                estados = rs.getString("EstadoCasillas");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar variables " + e);
        }
        try {
            for (int i = 0; i < 64; i++) {
                Tablero.estadoCasillas[i] = estados.charAt(i) - '0';
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas para inicializar estados " + e);
        }
    }

    public void recibirCasillas(ArrayList<JPanel> casillas) {
        this.casillas = casillas;
    }

    public void asignarTurno(int[] estadoCasillas, int numCasilla1, int numCasilla2) {
        switch (cambioDeTurno) {
            case 1:
                turnoBlancas(estadoCasillas, numCasilla1, numCasilla2);
                break;
            case 2:
                turnoNegras(estadoCasillas, numCasilla1, numCasilla2);
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Si desea seguir jugando debe comenzar otra partida");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Pase el turno por favor");
                break;
        }

        int sumaBlancas = 0;
        int sumaNegras = 0;

        for (int i = 0; i < 64; i++) {
            if ((i + 18 < 64) && ((estadoCasillas[i] == 1 && ((estadoCasillas[i + 7] == 0 || estadoCasillas[i + 9] == 0) || ((estadoCasillas[i + 14] == 0 || estadoCasillas[i + 18] == 0) && ((estadoCasillas[i + 7] == 2 || estadoCasillas[i + 7] == 4) || (estadoCasillas[i + 9] == 2 || estadoCasillas[i + 9] == 4))))) || estadoCasillas[i] == 3)) {
                sumaBlancas++;
            } else if ((i - 18 >= 0) && ((estadoCasillas[i] == 2 && ((estadoCasillas[i - 7] == 0 || estadoCasillas[i - 9] == 0) || ((estadoCasillas[i - 14] == 0 || estadoCasillas[i - 18] == 0) && ((estadoCasillas[i - 7] == 1 || estadoCasillas[i - 7] == 3) || (estadoCasillas[i - 9] == 1 || estadoCasillas[i - 9] == 3))))) || estadoCasillas[i] == 4)) {
                sumaNegras++;
            }
        }

        if (sumaBlancas == 0) {
            JOptionPane.showMessageDialog(null, "Ganador: Jugador de las Fichas Negras");
            cambioDeTurno = 3;
        } else if (sumaNegras == 0) {
            JOptionPane.showMessageDialog(null, "Ganador: Jugador de las Fichas Blancas");
            cambioDeTurno = 3;
        }

        SalvarPartida.pasadaNegra = pasadaNegra;
        SalvarPartida.pasadaBlanca = pasadaBlanca;
        SalvarPartida.caminoNegra = caminoNegra;
        SalvarPartida.caminoBlanca = caminoBlanca;
        SalvarPartida.turnoSiguiente = turnoSiguiente;
    }

    public void turnoBlancas(int[] estadoCasillas, int numCasilla1, int numCasilla2) {

        blancas.camino = caminoBlanca;
        blancas.casillaPasada = pasadaBlanca;
        negras.repeticiones = 0;
        negras.camino = false;

        if ((estadoCasillas[numCasilla1] == 1 || estadoCasillas[numCasilla1] == 3) && estadoCasillas[numCasilla2] == 0) {
            blancas.decidir(numCasilla1, numCasilla2, estadoCasillas, casillas);

            SalvarPartida.estadoCasillas = estadoCasillas;
            cambioDeTurno = blancas.cambioDeTurno;
            turnoSiguiente = blancas.turnoSiguiente;
            caminoBlanca = blancas.camino;
            pasadaBlanca = blancas.casillaPasada;

        } else {
            JOptionPane.showMessageDialog(null, "Intento Inválido");
        }
    }

    public void turnoNegras(int[] estadoCasillas, int numCasilla1, int numCasilla2) {

        negras.camino = caminoNegra;
        negras.casillaPasada = pasadaNegra;
        blancas.repeticiones = 0;
        blancas.camino = false;

        if ((estadoCasillas[numCasilla1] == 2 || estadoCasillas[numCasilla1] == 4) && estadoCasillas[numCasilla2] == 0) {
            negras.decidir(numCasilla1, numCasilla2, estadoCasillas, casillas);

            SalvarPartida.estadoCasillas = estadoCasillas;
            cambioDeTurno = negras.cambioDeTurno;
            turnoSiguiente = negras.turnoSiguiente;
            caminoNegra = negras.camino;
            pasadaNegra = negras.casillaPasada;

        } else {
            JOptionPane.showMessageDialog(null, "Intento Inválido");
        }
    }

    //Métodos Getters y Setters
    //Getters
    public boolean isCaminoBlanca() {
        return caminoBlanca;
    }

    public boolean isCaminoNegra() {
        return caminoNegra;
    }

    public int getTurnoSiguiente() {
        return turnoSiguiente;
    }

    public int getCambioDeTurno() {
        return cambioDeTurno;
    }

    public int getPasadaBlanca() {
        return pasadaBlanca;
    }

    public int getPasadaNegra() {
        return pasadaNegra;
    }

    //Setters
    public void setCambioDeTurno(int cambioDeTurno) {
        this.cambioDeTurno = cambioDeTurno;
    }

    public void setPasadaBlanca(int pasadaBlanca) {
        this.pasadaBlanca = pasadaBlanca;
    }

    public void setPasadaNegra(int pasadaNegra) {
        this.pasadaNegra = pasadaNegra;
    }

    public void setCaminoBlanca(boolean caminoBlanca) {
        this.caminoBlanca = caminoBlanca;
    }

    public void setCaminoNegra(boolean caminoNegra) {
        this.caminoNegra = caminoNegra;
    }
}
