
import java.awt.Dimension;
import java.util.List;
import javafx.scene.paint.Color;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kirky
 */
public class Chessmate extends JPanel {

    private Pieza tab[][];
    private int DIMX;
    private final String reina = "piezas//reina.png";
    private boolean esValido[][];

    public Chessmate(int n) {
        tab = new Pieza[n][n];
        esValido = new boolean[n][n];
        DIMX = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tab[i][j] = new Pieza((720 / n) - 10);
                if (i == 0 || i % 2 == 0) {
                    if (j == 0 || j % 2 == 0) {
                        tab[i][j].setBackground(java.awt.Color.WHITE);
                    } else {
                        tab[i][j].setBackground(java.awt.Color.BLACK);
                    }
                } else {
                    if (j == 0 || j % 2 == 0) {
                        tab[i][j].setBackground(java.awt.Color.BLACK);
                    } else {
                        tab[i][j].setBackground(java.awt.Color.WHITE);
                    }
                }
                tab[i][j].setOpaque(true);
                esValido[i][j] = true;
                this.add(tab[i][j]);
            }
        }
    }

    public void put(int fila, int columna) {
        tab[fila][columna].seticon(reina);
    }

    public boolean valido(int[] solution, int etapa) {
        for (int i = 0; i <= etapa - 1; i++) {
            if ((solution[i] == solution[etapa]) || (abs(solution[i], solution[etapa]) == abs(i, etapa))) {
                return false;
            }
        }
        return true;
    }

    public boolean validov2(int[] solution) {
        for (int i = 0; i < solution.length; i++) {
            for (int j = i+1; j < solution.length; j++) {
                if (solution[j] != -1) {
                    if ((solution[i] == solution[j]) || (abs(solution[i], solution[j]) == abs(i, j))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int abs(int x, int y) {
        int abs = Math.abs(x - y);
        return abs;
    }

    public void mostrarsol(int[] solution) {
        for (int i = 0; i < solution.length; i++) {
            put(solution[i], i);
        }
    }

    public boolean solveNQueen(List<int[]> comb, int[] solution, int etapa) {
        int n = solution.length;
        if (etapa == n) {
            int[] sol = new int[n];
            System.arraycopy(solution, 0, sol, 0, n);
            comb.add(sol);
            return true;
        }
        solution[etapa] = -1;
        boolean exito = false;
        for (int i = 0; i < n; i++) {
            solution[etapa] = i;
            if (valido(solution, etapa)) {
                exito = solveNQueen(comb, solution, etapa + 1) || exito;
            }
        }
        return !(comb.isEmpty());
    }

    public boolean solveNQueenv2(int[] solution, int etapa, int filaR, int columnaR) {
        int n = solution.length;
        if (etapa == n) {
            return true;
        }
        boolean exito = false;
        for (int i = 0; i < n && !exito; i++) {
            if (etapa == columnaR) {
                solution[etapa] = filaR;
            }else{
                solution[etapa]=i;
            }
            if (validov2(solution)) {
                exito = solveNQueenv2(solution, etapa + 1,filaR,columnaR) || exito;
            }
        }
        if(!exito){
            solution[etapa]=-1;
        }
        return exito;
    }

    public void borrar() {
        for (int i = 0; i < DIMX; i++) {
            for (int j = 0; j < DIMX; j++) {
                tab[i][j].seticon(null);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(720, 720);
    }
}
