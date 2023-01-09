package com.aetxabao.connect4;

/**
 * @author Nombre Apellido
 */
public class Tablero {

    public final static char O = 'O';
    public final static char X = 'X';
    public final static char L = ' ';
    private final static int W = 7;
    private final static int H = 6;
    private int contador;
    private char turno;
    private final int ancho;
    private final int alto;
    private final char[][] m;

    public Tablero() {
        contador = 0;
        turno = O;
        ancho = W;
        alto = H;
        m = new char[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                m[i][j] = L;
            }
        }
    }

    public Tablero(char[][] m) {
        //TODO: Tablero(m)
        int cntRojo = 0;
        int cntAmarillo = 0;
        this.m = m;
        ancho = m.length;
        alto = m[0].length;
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                if (m[i][j] == O) {
                    cntRojo++;
                }else if (m[i][j] == X) {
                    cntAmarillo++;
                }
            }
        }
        contador = cntAmarillo + cntRojo;
        if (cntRojo>cntAmarillo) {
            turno = X;
        }else{
            turno = O;
        }
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public int getContador() {
        return contador;
    }

    public char[][] getMatriz(){
        return m;
    }

    public char getTurno() {
        return turno;
    }

    public void iniciaTurno() {
        //TODO: iniciaTurno
        int r = (int) Math.round(Math.random());
        if (r == 0){
            turno = X;
        } else {
            turno = O;
        }
    }

    public void cambiaTurno() {
        //TODO: cambiaTurno
        if (turno == O){
            turno = X;
        } else {
            turno = O;
        }
    }

    public boolean estaColumnaLibre(int columna) {
        //TODO: estaColumnaLibre
        if (columna<0 || columna>=m.length){
            return false;
        }
        return m[columna][alto-1] == L;
    }

    public void inserta(char ficha, int columna) {
        //TODO: insertar
        for (int i = 0; i < alto; i++) {
            if (m[columna][i] == L) {
                m[columna][i] = ficha;
                contador++;
                break;
            }
        }
    }

    public boolean estaLleno() {
        //TODO: estaLleno
        return contador == alto*ancho;
    }

    public boolean gana(char jugador) {
        boolean b;
        b = ganaHorizontal(jugador);
        b = b || ganaVertical(jugador);
        b = b || ganaDiagonalArriba(jugador);
        b = b || ganaDiagonalAbajo(jugador);
        return b;
    }

    private boolean ganaHorizontal(char jugador) {
        //TODO: ganaHorizontal
        boolean b = false;
        int limAncho = ancho - 4;
        for (int col = 0; col <= limAncho; col++) {
            for (int fil = 0; fil < alto; fil++) {
                if (m[col][fil] == L) {
                    break;
                }
                if (hay4Horizontales(col, fil, jugador)) {
                    return true;
                }
            }
        }
        return b;
    }

    private boolean hay4Horizontales(int columna, int fila, char jugador){
        //TODO: hay4Horizontales
        for (int col = columna; col < columna + 4; col++) {
            if (m[col][fila] != jugador) {
                return false;
            }
        }
        return true;
    }

    private boolean ganaVertical(char jugador) {
        //TODO: ganaVertical
        boolean b = false;
        int limAlto = alto - 4;
        for (int col = 0; col < ancho; col++) {
            for (int fil = 0; fil <= limAlto; fil++) {
                if (m[col][fil] == L) {
                    break;
                }
                if (hay4Verticales(col, fil, jugador)) {
                    return true;
                }
            }
        }
        return b;
    }

    private boolean hay4Verticales(int columna, int fila, char jugador){
        //TODO: hay4Verticales
        for (int fil = fila; fil < fila + 4; fil++) {
            if (m[columna][fil] != jugador) {
                return false;
            }
        }
        return true;
    }

    private boolean ganaDiagonalArriba(char jugador) {
        //TODO: ganaDiagonalArriba
        boolean b = false;
        int limAncho = ancho - 4;
        int limAlto = alto - 4;
        for (int col = 0; col <= limAncho; col++) {
            for (int fil = 0; fil <= limAlto; fil++) {
                if (m[col][fil] == L) {
                    break;
                }
                if (hay4DiagonalesArriba(col, fil, jugador)) {
                    return true;
                }
            }
        }
        return b;
    }

    private boolean hay4DiagonalesArriba(int columna, int fila, char jugador){
        //TODO: hay4DiagonalesArriba
        for (int i = 0; i < 4; i++) {
            if (m[columna+i][fila+i] != jugador) {
                return false;
            }
        }
        return true;
    }

    private boolean ganaDiagonalAbajo(char jugador) {
        //TODO: ganaDiagonalAbajo
        boolean b = false;
        int limAncho = ancho - 4;
        int iniFil = 4 - 1;
        for (int col = 0; col <= limAncho; col++) {
            for (int fil = iniFil; fil < alto; fil++) {
                if (m[col][fil] == L) {
                    break;
                }
                if (hay4DiagonalesAbajo(col, fil, jugador)) {
                    return true;
                }
            }
        }
        return b;
    }

    private boolean hay4DiagonalesAbajo(int columna, int fila, char jugador){
        //TODO: hay4DiagonalesAbajo
        for (int i = 0; i < 4; i++) {
            if (m[columna+i][fila-i] != jugador) {
                return false;
            }
        }
        return true;
    }

    public boolean estaFinalizado() {
        //TODO: finalizado
        return gana(turno) || estaLleno();
    }

}
