package Tours;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaserEffectGridLayout extends JPanel {
    class LaserComponent extends JPanel {
        private Timer timer;
        private int laserSpeed = 30; // Vitesse du laser
        private int rows = 10;
        private int cols = 10;
        private int currentRow = cols / 2; // Ligne centrale de la grille
        private int currentCol = rows / 2; // Colonne centrale de la grille
        private boolean movingSouth = true;
        private boolean move = true;

        public LaserComponent() {
            timer = new Timer(laserSpeed, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    moveLaserBis();
                    repaint();
                }
            });
            timer.start();
        }

        private void moveLaserBis() {
            if (movingSouth) {
                currentRow++;
                if (currentRow >= rows) {
                    currentRow = rows - 1;
                    movingSouth = false;
                }

            } else {
                currentRow--;
                if (currentRow == 0) {
                    currentRow = rows - 1;
                    movingSouth = true;
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int cellWidth = getWidth() / cols;
            int cellHeight = getHeight() / rows;

            int x = currentCol * cellWidth;
            int y = currentRow * cellHeight;

            g.setColor(Color.RED);
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            ((Graphics2D) g).setStroke(new BasicStroke(3));

            // Dessiner le laser sur la ligne centrale de la grille
            if (move) {
                g.drawLine(x, y, x + cellWidth, y);
                move = false;
            } else {
                g.drawLine(x, y, x + cellWidth, y);
                move = true;
            }
            // Ou dessiner un rectangle à la place
            // g.fillRect(x, y, x + cellWidth, y);
        }
    }

    public LaserEffectGridLayout() {
        // JFrame frame = new JFrame();
        // frame.setTitle("Effet Laser avec GridLayout");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(400, 400);
        // frame.setVisible(true);
        // frame.setLayout(new GridLayout(1, 1));
        // LaserComponent laserComponent = new LaserComponent();
        // frame.add(laserComponent);
        setLayout(new GridLayout(1, 1));
        setLayout(new GridLayout(1, 1));
        // setBorder(BorderFactory.createLineBorder(Color.BLUE));
        // setBackground(Color.GRAY);
        add(new LaserComponent());
    }

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(() -> {
    // LaserEffectGridLayout laserGridLayout = new LaserEffectGridLayout();
    // laserGridLayout.setVisible(true);
    // });
    // }
}