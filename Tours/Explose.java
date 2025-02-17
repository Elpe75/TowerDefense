package Tours;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Explose extends JPanel {
    private Timer timer;
    private int x, y, radius;

    public Explose() {
        setSize(20, 20);
        x = getWidth();
        y = getHeight();
        radius = 0;
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radius += 5;
                if (radius > y) {
                    timer.stop();
                    radius = 0;
                    timer.start();
                }
                repaint();
            }
        });
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.RED);
        g2d.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    // public static void main(String[] args) {
    // JFrame frame = new JFrame("Explosion");
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Explose explosion = new Explose();
    // frame.add(explosion, BorderLayout.CENTER);
    // frame.setSize(300, 300);
    // frame.setVisible(true);
    // }
}
