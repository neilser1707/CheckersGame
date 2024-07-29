package com.checkersgame.clases;

import java.util.ArrayList;
import javax.swing.JPanel;

public abstract class Ficha {
    
    boolean camino;
    boolean corono;
    int turnoSiguiente;
    int cambioDeTurno;
    int casillaPasada;
    int repeticiones;

    public abstract void inicializarPosiciones();

    public abstract void decidir(int numCasilla1, int numCasilla2, int[] estadoCasillas, ArrayList<JPanel> casillas);

    public abstract void caminar(int numCasilla1, int numCasilla2, int[] estadoCasillas, ArrayList<JPanel> casillas);

    public abstract void comer(int numCasilla1, int[] estadoCasillas, int num, ArrayList<JPanel> casillas, int estado);

    public abstract void verificarComida(int numCasilla1, int numCasilla2, int[] estadoCasillas);

    public abstract void coronar(int numCasilla1, int numCasilla2, int[] estadoCasillas, ArrayList<JPanel> casillas);

    public abstract void moverReina(int numCasilla1, int numCasilla2, int[] estadoCasillas, ArrayList<JPanel> casillas);

    public abstract int validarComidaReina(int numCasilla2, int[] estadoCasillas);
}
