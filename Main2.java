
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
public class Main2 extends JFrame {

    //atributos
    private Chessmate chess;
    private JPanel PanelBotones;
    private JPanel Panel;
    private int n;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Main2().setVisible(true);
    }

    public Main2() {
        n = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el tamaño del tablero: "));
        if (n > 0) {
            /*CREO LOS BOTONES */
            JButton resolver;
            /*INICIALIZO LAS COMPONENTES*/
            Panel = new JPanel();
            Panel.setLayout(new BorderLayout());
            PanelBotones = new JPanel();
            resolver = new JButton("resolver");
            /*AÑADO LAS COMPONENTES AL PANEL*/
            //PanelBotones.setLayout(new GridLayout(2,1));
            PanelBotones.add(resolver);
            /*AÑADO EL PANEL DE BOTONES AL PANEL Y EL JTEXTAREA*/
            Panel.add(PanelBotones, BorderLayout.NORTH);
            //Panel.add(sol, BorderLayout.CENTER);
            chess = new Chessmate(n);
            //añadir restante al panel del JFRAME
            this.getContentPane().add(chess, BorderLayout.CENTER);
            this.getContentPane().add(Panel, BorderLayout.WEST);
            this.setResizable(false);
            this.pack();
            this.setDefaultCloseOperation(Main.EXIT_ON_CLOSE);

            resolver.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    resolverActionPerformed(ae);
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "POR FAVOR ESCOGE UN NUMERO MAYOR A 0");
        }
    }

    private void resolverActionPerformed(java.awt.event.ActionEvent evt) {
        chess.borrar();
        int solution[] = new int[n];
        Arrays.fill(solution, -1);
        int col= Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca la posicion de la columna de la reina: "));
        int fila=Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca la posicion de la fila de la reina: "));
        if (chess.solveNQueenv2(solution,0,fila,col)) {
            chess.mostrarsol(solution);
        } else {
            JOptionPane.showMessageDialog(null, "NO HAY SOLUCION");
        }
    }
}
