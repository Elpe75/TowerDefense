package Tours;

import java.awt.Graphics;
import javax.swing.*;

import Game.geometry.*;

import java.awt.*;;

public class EffetTir extends JPanel {
    private PairPosition pos;

    public EffetTir(PairPosition pos) {
        this.pos = pos;
    }

    public void paint(Graphics g) {

        g.drawLine(pos.getDepart().getX(), pos.getDepart().getY(), pos.getArrived().getX(), pos.getArrived().getY());
    }

    // public static void main(String[] args) {
    // JFrame f = new JFrame("Dessiner une ligne");
    // f.getContentPane().add(new EffetTir(new PairPosition(new IntPosition(20, 30),
    // new IntPosition(100, 60))));
    // f.setSize(250, 250);
    // f.setVisible(true);
    // f.setResizable(false);
    // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // }
}
