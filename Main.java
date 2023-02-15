
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kirky
 */
public class Main extends JFrame {

    //atributos
    private Chessmate chess;
    private JPanel PanelBotones;
    private JPanel Panel;
    private JTextArea sol;
    private List<int[]> comb;
    private int n;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Main().setVisible(true);
    }

    public Main() {
        n = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el tamaño del tablero: "));
        if (n > 0) {
            /*CREO LOS BOTONES */
            JButton resolver;
            JButton probar;
            /*INICIALIZO LAS COMPONENTES*/
            Panel = new JPanel();
            Panel.setLayout(new BorderLayout());
            PanelBotones = new JPanel();
            resolver = new JButton("resolver");
            probar = new JButton("probar solucion");
            sol = new JTextArea();
            /*AÑADO LAS COMPONENTES AL PANEL*/
            //PanelBotones.setLayout(new GridLayout(2,1));
            PanelBotones.add(resolver);
            PanelBotones.add(probar);
            /*AÑADO EL PANEL DE BOTONES AL PANEL Y EL JTEXTAREA*/
            Panel.add(PanelBotones, BorderLayout.NORTH);
            //Panel.add(sol, BorderLayout.CENTER);
            chess = new Chessmate(n);

            //crear el scroll pane
            JScrollPane scroll = new JScrollPane(sol, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            //añadirlo al panel
            Panel.add(scroll, BorderLayout.CENTER);
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
            probar.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    mostrarActionPerformed(ae);
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "POR FAVOR ESCOGE UN NUMERO MAYOR A 0");
        }
    }

    private void resolverActionPerformed(java.awt.event.ActionEvent evt) {
        int solution[] = new int[n];
        comb = new ArrayList<>();
        if (chess.solveNQueen(comb, solution, 0)) {
            sol.append("SOLUCIONES PARA N=" + n + "\n");
            comb.forEach(r -> {
                sol.append(("" + comb.indexOf(r) + ".") + Arrays.toString(r) + "\n");
            });
        } else {
            JOptionPane.showMessageDialog(null, "NO HAY SOLUCION");
        }
    }

    private void mostrarActionPerformed(java.awt.event.ActionEvent evt) {
        chess.borrar();
        int i = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca la posicion i de las soluciones mostradas: "));
        chess.mostrarsol(comb.get(i));
    }

}
