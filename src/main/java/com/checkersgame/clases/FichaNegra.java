package com.checkersgame.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public final class FichaNegra extends Ficha {

    ArrayList<JLabel> fichasNegras = new ArrayList<>();
    ArrayList<Integer> posicionesNegras = new ArrayList<>();
    JLabel reinaNegra;

    public FichaNegra() {
        reinaNegra = new JLabel();
        reinaNegra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/P1.png")));

        for (int i = 0; i < 12; i++) {
            JLabel ficha = new JLabel();
            ficha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/P2.png")));
            fichasNegras.add(ficha);
        }

        inicializarPosiciones();
    }

    @Override
    public void inicializarPosiciones() {
        int[] estadoCasillas = new int[64];
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
        for (int i = 0; i < 64; i++) {
            estadoCasillas[i] = estados.charAt(i) - '0';
        }

        for (int i = 0; i < 64; i++) {
            if (estadoCasillas[i] == 2 || estadoCasillas[i] == 4) {
                posicionesNegras.add(i);
            }
        }
    }

    @Override
    public void decidir(int numCasilla1, int numCasilla2, int[] estadoCasillas, ArrayList<JPanel> casillas) {

        int estado = 2;
        int num = numCasilla1 - numCasilla2;

        if (num % 7 == 0 || num % 9 == 0) {

            if (estadoCasillas[numCasilla1] == 2) {
                if ((num == 7 || num == 9) && (!camino && repeticiones == 0)) {

                    caminar(numCasilla1, numCasilla2, estadoCasillas, casillas);
                    estadoCasillas[numCasilla1] = 0;
                    if (corono) {
                        estadoCasillas[numCasilla2] = 4;
                        corono = false;
                    } else {
                        estadoCasillas[numCasilla2] = 2;
                    }
                    cambioDeTurno = 0;
                    turnoSiguiente = 1;
                    SalvarPartida.cambioDeTurno = 1;

                } else if (((num == 14 && (estadoCasillas[numCasilla1 - 7] == 1 || estadoCasillas[numCasilla1 - 7] == 3)) || (num == 18 && (estadoCasillas[numCasilla1 - 9] == 1 || estadoCasillas[numCasilla1 - 9] == 3))) && (casillaPasada == -1 || casillaPasada == numCasilla1)) {

                    caminar(numCasilla1, numCasilla2, estadoCasillas, casillas);
                    estadoCasillas[numCasilla1] = 0;
                    if (corono) {
                        estadoCasillas[numCasilla2] = 4;
                    } else {
                        estadoCasillas[numCasilla2] = 2;
                    }
                    comer(numCasilla1, estadoCasillas, num, casillas, estado);
                    verificarComida(numCasilla1, numCasilla2, estadoCasillas);
                    repeticiones++;

                } else {
                    cambioDeTurno = 2;
                    JOptionPane.showMessageDialog(null, "Invalido");
                }
            } else {
                if (casillaPasada == -1 || casillaPasada == numCasilla1) {
                    moverReina(numCasilla1, numCasilla2, estadoCasillas, casillas);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalido");
                }
            }
        } else {

            JOptionPane.showMessageDialog(null, "Invalido");
            cambioDeTurno = 2;
            camino = false;

        }
    }

    @Override
    public void caminar(int numCasilla1, int numCasilla2, int[] estadoCasillas, ArrayList<JPanel> casillas) {
        int index = posicionesNegras.indexOf(numCasilla1);
        posicionesNegras.set(index, numCasilla2);
        casillas.get(numCasilla1).removeAll();

        if (numCasilla2 == 0 || numCasilla2 == 2 || numCasilla2 == 4 || numCasilla2 == 6 || estadoCasillas[numCasilla1] == 4) {
            coronar(numCasilla1, numCasilla2, estadoCasillas, casillas);
        } else {
            casillas.get(numCasilla2).add(fichasNegras.get(index));
        }

        casillas.get(numCasilla1).revalidate();
        casillas.get(numCasilla1).repaint();
        casillas.get(numCasilla2).revalidate();
        casillas.get(numCasilla2).repaint();
        camino = true;

    }

    @Override
    public void comer(int numCasilla1, int[] estadoCasillas, int num, ArrayList<JPanel> casillas, int estado) {

        int indicador;
        if (estado == 2) {
            if (num == 14) {
                indicador = 7;
            } else {
                indicador = 9;
            }
        } else {
            indicador = num;
        }

        casillas.get(numCasilla1 - indicador).removeAll();
        casillas.get(numCasilla1 - indicador).revalidate();
        casillas.get(numCasilla1 - indicador).repaint();
        estadoCasillas[numCasilla1 - indicador] = 0;
    }

    @Override
    public void verificarComida(int numCasilla1, int numCasilla2, int[] estadoCasillas) {
        if ((numCasilla2 - 16 >= 0) || (estadoCasillas[numCasilla2] == 4 && !corono)) {
            if (corono) {
                corono = false;
            }
            int suma = 0;
            int numero = 0;
            if (estadoCasillas[numCasilla2] == 4) {
                numero = validarComidaReina(numCasilla2, estadoCasillas);
            }

            if ((estadoCasillas[numCasilla2] == 2) && (((estadoCasillas[numCasilla2 - 7] == 1 || estadoCasillas[numCasilla2 - 7] == 3) && estadoCasillas[numCasilla2 - 14] == 0) || ((estadoCasillas[numCasilla2 - 9] == 1 || estadoCasillas[numCasilla2 - 9] == 3) && estadoCasillas[numCasilla2 - 18] == 0))) {
                suma++;
                casillaPasada = numCasilla2;
            } else if (estadoCasillas[numCasilla2] == 4 && numero == 1) {
                suma++;
                casillaPasada = numCasilla2;
            }

            if (suma == 0) {
                turnoSiguiente = 1;
                cambioDeTurno = 0;
                SalvarPartida.cambioDeTurno = 1;
            } else {
                cambioDeTurno = 2;
                turnoSiguiente = 2;
                SalvarPartida.cambioDeTurno = 2;
            }

        } else {
            turnoSiguiente = 1;
            cambioDeTurno = 0;
            SalvarPartida.cambioDeTurno = 1;
        }

        corono = false;

    }

    @Override
    public void coronar(int numCasilla1, int numCasilla2, int[] estadoCasillas, ArrayList<JPanel> casillas) {
        casillas.get(numCasilla2).add(reinaNegra);
        estadoCasillas[numCasilla2] = 4;
        if (estadoCasillas[numCasilla1] == 2) {
            corono = true;
        }
    }

    @Override
    public void moverReina(int numCasilla1, int numCasilla2, int[] estadoCasillas, ArrayList<JPanel> casillas) {

        int estado = 4;
        int num = numCasilla2 - numCasilla1;
        int casillaComida = -1;
        int casillaInicial = numCasilla2;
        int casillaFinal = numCasilla1;
        int sumaNegras = 0;
        int sumaBlancas = 0;
        int sumando;

        if (num < 0) {
            casillaInicial = numCasilla1;
            casillaFinal = numCasilla2;
        }

        if (num % 7 == 0) {
            sumando = 7;
        } else {
            sumando = 9;
        }

        int acumulado = sumando;
        for (int i = casillaInicial - sumando; i > casillaFinal; i -= sumando) {
            if (estadoCasillas[i] == 2 || estadoCasillas[i] == 4) {
                sumaNegras++;
            }
            if (estadoCasillas[i] == 1 || estadoCasillas[i] == 3) {
                sumaBlancas++;
                casillaComida = acumulado;
            }
            acumulado += sumando;
        }

        if (sumaNegras != 0 || sumaBlancas > 1) {
            JOptionPane.showMessageDialog(null, "Invalido");
        } else {
            switch (sumaBlancas) {
                case 0:
                    if (!camino && repeticiones == 0) {

                        caminar(numCasilla1, numCasilla2, estadoCasillas, casillas);
                        estadoCasillas[numCasilla1] = 0;
                        estadoCasillas[numCasilla2] = 4;
                        turnoSiguiente = 1;
                        cambioDeTurno = 0;

                    }
                    break;
                case 1:

                    caminar(numCasilla1, numCasilla2, estadoCasillas, casillas);
                    estadoCasillas[numCasilla1] = 0;
                    estadoCasillas[numCasilla2] = 4;
                    comer(casillaInicial, estadoCasillas, casillaComida, casillas, estado);
                    verificarComida(numCasilla1, numCasilla2, estadoCasillas);
                    repeticiones++;
                    break;

            }
        }
    }

    @Override
    public int validarComidaReina(int numCasilla2, int[] estadoCasillas) {
        int i = 7;
        int k = 7;
        int j = 9;
        int h = 9;

        //Diagonal Suoerior Izquierda
        int sumaBlancasArribaDerecha = 0;
        int sumaNegrasArribaDerecha = 0;

        while (((numCasilla2 - i > 0) && estadoCasillas[numCasilla2 - i] != -1) && (sumaNegrasArribaDerecha != 1 && sumaBlancasArribaDerecha != 1)) {

            if (estadoCasillas[numCasilla2 - i] == 2 || estadoCasillas[numCasilla2 - i] == 4) {
                sumaNegrasArribaDerecha++;
            } else if (estadoCasillas[numCasilla2 - i] == 1 || estadoCasillas[numCasilla2 - i] == 3) {
                sumaBlancasArribaDerecha++;
            }
            i += 7;
        }

        //Variables Abajo Izquierda
        int sumaBlancasAbajoIzquierda = 0;
        int sumaNegrasAbajoIzquierda = 0;

        while ((numCasilla2 + k < 63 && estadoCasillas[numCasilla2 + k] != -1) && (sumaNegrasAbajoIzquierda != 1 && sumaBlancasAbajoIzquierda != 1)) {

            if (estadoCasillas[numCasilla2 + k] == 2 || estadoCasillas[numCasilla2 + k] == 4) {
                sumaNegrasAbajoIzquierda++;
            } else if (estadoCasillas[numCasilla2 + k] == 1 || estadoCasillas[numCasilla2 + k] == 3) {
                sumaBlancasAbajoIzquierda++;
            }
            k += 7;

        }

        //Variables Arriba Izquierda
        int sumaBlancasArribaIzquierda = 0;
        int sumaNegrasArribaIzquierda = 0;

        while ((numCasilla2 - j >= 0 && estadoCasillas[numCasilla2 - j] != -1) && (sumaNegrasArribaIzquierda != 1 && sumaBlancasArribaIzquierda != 1)) {

            if (estadoCasillas[numCasilla2 - j] == 2 || estadoCasillas[numCasilla2 - j] == 4) {
                sumaNegrasArribaIzquierda++;
            } else if (estadoCasillas[numCasilla2 - j] == 1 || estadoCasillas[numCasilla2 - j] == 3) {
                sumaBlancasArribaIzquierda++;
            }
            j += 9;

        }

        //Variables Abajo Derecha
        int sumaBlancasAbajoDerecha = 0;
        int sumaNegrasAbajoDerecha = 0;

        while ((numCasilla2 + h <= 63 && estadoCasillas[numCasilla2 + h] != -1) && (sumaNegrasAbajoDerecha != 1 && sumaBlancasAbajoDerecha != 1)) {

            if (estadoCasillas[numCasilla2 + h] == 2 || estadoCasillas[numCasilla2 + h] == 4) {
                sumaNegrasAbajoDerecha++;
            }
            if (estadoCasillas[numCasilla2 + h] == 1 || estadoCasillas[numCasilla2 + h] == 3) {
                sumaBlancasAbajoDerecha++;
            }
            h += 9;

        }

        if ((sumaNegrasArribaDerecha == 0) && (sumaBlancasArribaDerecha == 1 && numCasilla2 - i > 0 && estadoCasillas[numCasilla2 - i] == 0)) {
            return 1;
        } else if ((sumaNegrasAbajoIzquierda == 0) && (sumaBlancasAbajoIzquierda == 1 && numCasilla2 + k < 63 && estadoCasillas[numCasilla2 + k] == 0)) {
            return 1;
        } else if ((sumaNegrasArribaIzquierda == 0) && (sumaBlancasArribaIzquierda == 1 && numCasilla2 - j >= 0 && estadoCasillas[numCasilla2 - j] == 0)) {
            return 1;
        } else if ((sumaNegrasAbajoDerecha == 0) && (sumaBlancasAbajoDerecha == 1 && numCasilla2 + h <= 63 && estadoCasillas[numCasilla2 + h] == 0)) {
            return 1;
        } else {
            return 0;
        }
    }
}
