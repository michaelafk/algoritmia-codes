
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kirky
 */
public class Pieza extends JLabel {

    private int DIMX;
    private String tipo;

    public Pieza(int DIMX) {
        this.DIMX = DIMX;
        this.setSize(DIMX, DIMX);
        tipo = null;
    }

    public void seticon(String pieza) {
        if (pieza != null) {
            tipo = pieza;
            ImageIcon icon = new ImageIcon(pieza);
            this.setIcon(redimension(icon));
        }else{
            this.setIcon(null);
        }
    }

    public String type() {
        return tipo;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DIMX, DIMX);
    }

    private ImageIcon redimension(ImageIcon image) {
        Image imgEscalada = image.getImage().getScaledInstance(DIMX,
                DIMX, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(imgEscalada);
    }
}
