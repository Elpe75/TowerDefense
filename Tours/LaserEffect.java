package Tours;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LaserEffect extends JPanel {
    private BufferedImage image;

    class LaserComponent extends JPanel {
        private int laserPosition = 0;
        private Timer timer;
        private int laserSpeed = 30; // Vitesse du laser
        private int flecheSpeed = 50;
        private int rows = 10;
        private int cols = 10;
        private int currentRow = rows / 2; // Ligne centrale de la grille
        private int currentCol = cols / 2; // Colonne centrale de la grille
        private boolean movingRight = true;
        private boolean movingSouth = true;
        private boolean move = true;

        public LaserComponent() {
            timer = new Timer(flecheSpeed, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    moveLaser();
                    repaint();
                }
            });
            timer.start();
        }

        private void moveLaser() {
            if (movingRight) {
                currentCol++;
                if (currentCol >= cols) {
                    currentCol = cols - 1;
                    movingRight = false;
                }
            } else {
                currentCol--;
                if (currentCol < 0) {
                    currentCol = 0;
                    movingRight = true;
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

            // Mettre une image d'animation
            g.drawImage(image, x, y, x + cellWidth, y, this);
            // Dessiner le laser sur la ligne centrale de la grille
            // g.drawLine(x, y, x + cellWidth, y);
            // Ou dessiner un rectangle à la place
            // g.fillRect(x, y, x + cellWidth, y);
        }
    }

    public LaserEffect(String image) {
        // setSize(400, 400);

        // setLayout(new GridLayout(1, 1));
        // // LaserComponent laserComponent = new LaserComponent();
        // // frame.add(laserComponent);
        // frame.add(panel);
        setLayout(new GridLayout(1, 1));
        // setBorder(BorderFactory.createLineBorder(Color.BLUE));
        setBackground(Color.GRAY);
        try {
            this.image = ImageIO.read(new File(image));
        } catch (IOException e) {
            System.out.println("immage Error");
        }

        add(new LaserComponent());
    }

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(() -> {
    // LaserEffect laserGridLayout = new
    // LaserEffect("ressources\\menu\\fleche.png");
    // JFrame frame = new JFrame();
    // frame.setSize(600, 600);
    // frame.setVisible(true);
    // frame.setLayout(new GridLayout(1, 1));
    // frame.setTitle("Effet Laser avec GridLayout");
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // // frame.add(laserGridLayout);
    // frame.add(new LaserEffect("ressources\\menu\\fumee1.png"));
    // // frame.add(new LaserEffect("ressources\\menu\\feu2.png"));
    // // frame.add(new LaserEffect("ressources\\menu\\fumee1.png"));
    // });
    // }
}
