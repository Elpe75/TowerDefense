package Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Compteur extends JPanel {
    private static final int TOTAL_SECONDS = 60; // Durée totale en secondes
    private int remainingSeconds = TOTAL_SECONDS;
    private Timer timer;
    private JPanel panel;

    public Compteur() {
        setSize(10, 10);
        setLayout(new GridLayout(1, 1));
        setLayout(new GridLayout(1, 1));
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
                remainingSeconds--;
                if (remainingSeconds < 0) {
                    remainingSeconds = 0;
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
        double ratio = (double) remainingSeconds / TOTAL_SECONDS;
        int arcAngle = (int) (360 * ratio);
        g.fillArc(50, 50, 200, 200, 90, -arcAngle);

        g.setColor(Color.BLACK);
        g.drawString("Temps restant: " + remainingSeconds + " secondes", 50, 280);
    }

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(new Runnable() {
    // public void run() {
    // JFrame frame = new JFrame();
    // frame.setSize(400, 400);
    // frame.setVisible(true);
    // frame.setLayout(new GridLayout(1, 1));
    // frame.add(new Compteur());
    // }
    // });
    // }
}
