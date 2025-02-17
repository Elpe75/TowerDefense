package Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vague extends JFrame {
    private int initialNumber = 30; // Nombre initial
    private int remainingNumber = initialNumber;
    private Timer timer;
    private JPanel panel;

    public Vague() {
        setTitle("Compteur Dynamique");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTimer(g);
            }
        };
        add(panel);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingNumber--;
                if (remainingNumber < 15) {
                    remainingNumber = 15;
                    timer.stop();
                }
                panel.repaint();
            }
        });
        timer.start();
    }

    private void drawTimer(Graphics g) {
        int width = panel.getWidth();
        int height = panel.getHeight();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.BLUE);
        double ratio = (double) remainingNumber / initialNumber;
        int arcAngle = (int) (360 * ratio);
        g.fillArc(50, 50, 200, 200, 90, -arcAngle);

        g.setColor(Color.BLACK);
        g.drawString("Nombre restant: " + remainingNumber, 80, 280);
    }

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(new Runnable() {
    // public void run() {
    // new Vague().setVisible(true);
    // }
    // });
    // }
}
