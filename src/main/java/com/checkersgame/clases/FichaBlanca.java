package com.checkersgame.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public final class FichaBlanca extends Ficha {

    ArrayList<JLabel> fichasBlancas = new ArrayList<>();
    ArrayList<Integer> posicionesBlancas = new ArrayList<>();
    JLabel reinaBlanca;

    public FichaBlanca() {
        reinaBlanca = new JLabel();
        reinaBlanca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/P3.png")));

        for (int i = 0; i < 12; i++) {
            JLabel ficha = new JLabel();
            ficha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/P4.png")));
            fichasBlancas.add(ficha);
        }

        inicializarPosiciones();
    }

    @Override
    public void inicializarPosiciones() {
        int [] estadoCasillas = new int[64];
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

       for(int i = 0; i < 64; i++){
           if(estadoCasillas[i] == 1 || estadoCasillas[i] == 3){
               posicionesBlancas.add(i);
           }
       }

    }

    @Override
    public void decidir(int numCasilla1, int numCasilla2, int[] estadoCasillas, ArrayList<JPanel> casillas) {

        int estado = 1;
        int numero = numCasilla2 - numCasilla1;

        if (numero % 7 == 0 || numero % 9 == 0) {

            if (estadoCasillas[numCasilla1] == 1) {
                if ((numero == 7 || numero == 9) && (!camino && repeticiones == 0)) {

                    caminar(numCasilla1, numCasilla2, estadoCasillas, casillas);
                    estadoCasillas[numCasilla1] = 0;
                    if (corono) {
                        estadoCasillas[numCasilla2] = 3;
                        corono = false;
                    } else {
                        estadoCasillas[numCasilla2] = 1;
                    }
                    turnoSiguiente = 2;
                    cambioDeTurno = 0;
                    SalvarPartida.cambioDeTurno = 2;

                } else if (((numero == 14 && (estadoCasillas[numCasilla1 + 7] == 2 || estadoCasillas[numCasilla1 + 7] == 4)) || (numero == 18 && (estadoCasillas[numCasilla1 + 9] == 2 || estadoCasillas[numCasilla1 + 9] == 4))) && (casillaPasada == -1 || casillaPasada == numCasilla1)) {

                    caminar(numCasilla1, numCasilla2, estadoCasillas, casillas);
                    estadoCasillas[numCasilla1] = 0;
                    if (corono) {
                        estadoCasillas[numCasilla2] = 3;
                    } else {
                        estadoCasillas[numCasilla2] = 1;
                    }
                    comer(numCasilla1, estadoCasillas, numero, casillas, estado);
                    verificarComida(numCasilla1, numCasilla2, estadoCasillas);
                    repeticiones++;

                } else {
                    cambioDeTurno = 1;
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
            cambioDeTurno = 1;
            camino = false;

        }
    }

    @Override
    public void caminar(int numCasilla1, int numCasilla2, int[] estadoCasillas, ArrayList<JPanel> casillas) {
        int index = posicionesBlancas.indexOf(numCasilla1);
        posicionesBlancas.set(index, numCasilla2);
        casillas.get(numCasilla1).removeAll();

        if (numCasilla2 == 57 || numCasilla2 == 59 || numCasilla2 == 61 || numCasilla2 == 63 || estadoCasillas[numCasilla1] == 3) {
            coronar(numCasilla1, numCasilla2, estadoCasillas, casillas);
        } else {
            casillas.get(numCasilla2).add(fichasBlancas.get(index));
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

        if (estado == 1) {
            if (num == 14) {
                indicador = 7;
            } else {
                indicador = 9;
            }
        } else {
            indicador = num - numCasilla1;
        }

        casillas.get(numCasilla1 + indicador).removeAll();
        casillas.get(numCasilla1 + indicador).revalidate();
        casillas.get(numCasilla1 + indicador).repaint();
        estadoCasillas[numCasilla1 + indicador] = 0;
    }

    @Override
    public void verificarComida(int numCasilla1, int numCasilla2, int[] estadoCasillas) {
        if ((numCasilla2 + 16 <= 63) || (estadoCasillas[numCasilla2] == 3 && !corono)) {
            if (corono) {
                corono = false;
            }
            int suma = 0;
            int numero = 0;

            if (estadoCasillas[numCasilla2] == 3) {
                numero = validarComidaReina(numCasilla2, estadoCasillas);
            }

            if ((estadoCasillas[numCasilla2] == 1) && (((estadoCasillas[numCasilla2 + 7] == 2 || estadoCasillas[numCasilla2 + 7] == 4) && estadoCasillas[numCasilla2 + 14] == 0) || ((estadoCasillas[numCasilla2 + 9] == 2 || estadoCasillas[numCasilla2 + 9] == 4) && estadoCasillas[numCasilla2 + 18] == 0))) {
                suma++;
                casillaPasada = numCasilla2;
            } else if (estadoCasillas[numCasilla2] == 3 && numero == 1) {
                suma++;
                casillaPasada = numCasilla2;
            }

            if (suma == 0) {
                turnoSiguiente = 2;
                cambioDeTurno = 0;
                SalvarPartida.cambioDeTurno = 2;
            } else {
                cambioDeTurno = 1;
                turnoSiguiente = 1;
                SalvarPartida.cambioDeTurno = 1;
            }

        } else {
            turnoSiguiente = 2;
            cambioDeTurno = 0;
            SalvarPartida.cambioDeTurno = 2;
        }

        corono = false;

    }

    @Override
    public void coronar(int numCasilla1, int numCasilla2, int[] estadoCasillas, ArrayList<JPanel> casillas) {
        casillas.get(numCasilla2).add(reinaBlanca);
        estadoCasillas[numCasilla2] = 3;
        if (estadoCasillas[numCasilla1] == 1) {
            corono = true;
        }
    }

    @Override
    public void moverReina(int numCasilla1, int numCasilla2, int[] estadoCasillas, ArrayList<JPanel> casillas) {

        int estado = 3;
        int num = numCasilla2 - numCasilla1;
        int casillaComida = -1;
        int casillaInicial = numCasilla1;
        int casillaFinal = numCasilla2;
        int sumaNegras = 0;
        int sumaBlancas = 0;
        int sumando;

        if (num < 0) {
            casillaInicial = numCasilla2;
            casillaFinal = numCasilla1;
        }

        if (num % 7 == 0) {
            sumando = 7;
        } else {
            sumando = 9;
        }

        for (int i = casillaInicial + sumando; i < casillaFinal; i += sumando) {
            if (estadoCasillas[i] == 2 || estadoCasillas[i] == 4) {
                sumaNegras++;
                casillaComida = i;
            }
            if (estadoCasillas[i] == 1 || estadoCasillas[i] == 3) {
                sumaBlancas++;
            }
        }
        if (sumaBlancas != 0 || sumaNegras > 1) {
            JOptionPane.showMessageDialog(null, "Invalido");
        } else {
            switch (sumaNegras) {
                case 0:
                    if (!camino && repeticiones == 0) {

                        caminar(numCasilla1, numCasilla2, estadoCasillas, casillas);
                        estadoCasillas[numCasilla1] = 0;
                        estadoCasillas[numCasilla2] = 3;
                        turnoSiguiente = 2;
                        cambioDeTurno = 0;

                    }
                    break;
                case 1:

                    caminar(numCasilla1, numCasilla2, estadoCasillas, casillas);
                    estadoCasillas[numCasilla1] = 0;
                    estadoCasillas[numCasilla2] = 3;
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

        if ((sumaBlancasArribaDerecha == 0) && (sumaNegrasArribaDerecha == 1 && numCasilla2 - i > 0 && estadoCasillas[numCasilla2 - i] == 0)) {
            return 1;
        } else if ((sumaBlancasAbajoIzquierda == 0) && (sumaNegrasAbajoIzquierda == 1 && numCasilla2 + k < 63 && estadoCasillas[numCasilla2 + k] == 0)) {
            return 1;
        } else if ((sumaBlancasArribaIzquierda == 0) && (sumaNegrasArribaIzquierda == 1 && numCasilla2 - j >= 0 && estadoCasillas[numCasilla2 - j] == 0)) {
            return 1;
        } else if ((sumaBlancasAbajoDerecha == 0) && (sumaNegrasAbajoDerecha == 1 && numCasilla2 + h <= 63 && estadoCasillas[numCasilla2 + h] == 0)) {
            return 1;
        } else {
            return 0;
        }
    }
}
