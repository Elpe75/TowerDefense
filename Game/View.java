package Game;

import javax.swing.*;

import Game.Game.*;
import Game.Player.Player;
import Game.geometry.IntPosition;
import Game.geometry.Map;
import Game.geometry.PairPosition;
import Java.*;
import Tours.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import Fonctionnalites.*;

public class View extends JFrame {

    // Taille de la map :
    // nombre total de cases
    private static final int ROWS = 24;
    private static final int COLS = 24;
    private static final int CELL = 50;
    private static Map map;
    private JPanel gridPanel;
    // Tableau des bouttons de Tower
    private LinkedList<JButton> buttonTower = new LinkedList<>();
    /// Drawline
    private LinkedList<PairPosition> posTouched = new LinkedList<>();
    // Coordonnes de positionnement dans le graphique
    private static final int[] coord = { 5, 9, 10, 13, 16, 20, 21, 18, 12, 4, 3, 7, 8, 16, 3 };
    private Player player;
    private IntPosition exploseA;
    private LinkedList<IntPosition> exploseAList=new LinkedList<>();
    private IntPosition exploseB;
    private LinkedList<IntPosition> exploseBList=new LinkedList<>();
    private IntPosition exploseC;
    private LinkedList<IntPosition> exploseCList=new LinkedList<>();
    private View INSTANCEVIEW = this;

    // Listes des parties Sauvegardées
    public static LinkedList<Sauvegarde> listSaved = new LinkedList<>();
    // parametrage du jeu
    private Parametre param;
    //
    private int levelModeNormal;
    // Timer d'animation
    Timer timer = new Timer();
    // choix de la maquette
    private int bestScore;

    public View() {
        this(new Player(), new Parametre());
    }

    /**
     * 
     * @param player               joueur
     * @param param                parametres du jeu
     * @param modeView             choisir la maquette du jeu
     * @param param.getModeDeJeu() le mode du jeu
     * @param levelModeNormal      le level du jeu
     * @param difficult            le niveau de difficuluté du jeu
     */
    public View(Player player, Parametre param, int levelModeNormal, int score) {
        this.param = param;
        this.bestScore = score;
        this.player = player;
        map = new Map(ROWS, 5 + levelModeNormal, this.player, param.getModeView(), param.getModeDeJeu(),
                param.getDiffucult());
        setTitle("JEU TOWER DEFENCE ");
        setSize(CELL * COLS, CELL * ROWS);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(false);
        this.player = player;
        gridPanel = creatJPanel();
        setLayout(new BorderLayout());
        add(gridPanel, BorderLayout.CENTER);
        add(decorationPanel(), BorderLayout.NORTH);

        setResizable(true);
        setVisible(false);
    }

    public View(Player player, Parametre param) {
        this(player, param, 1, Serialize.deserialize());
    }

    public void playerTouched() {
        player.setLife();
    }

    public void test() {
        map.test();
    }

    public LinkedList<PairPosition> getPairPos() {
        return posTouched;
    }

    public Player getPlayer() {
        return player;
    }

    public static int getRows() {
        return ROWS;
    }

    public JPanel getGridJPanel() {
        return gridPanel;
    } // Tracer les lignes de tirs des Tours

    public void setExploseA(IntPosition pos) {
        exploseA = pos;
    }

    public void setExploseB(IntPosition pos) {
        exploseB = pos;
    }

    public void setExploseC(IntPosition pos) {
        exploseC = pos;
    }

    public LinkedList<IntPosition> getExploseAList(){
        return exploseAList;
    }
     public LinkedList<IntPosition> getExploseBList(){
        return exploseBList;
    }
     public LinkedList<IntPosition> getExploseCList(){
        return exploseCList;
    }

    private void drawLine() {
        for (PairPosition pos : posTouched) {
            getContentPane().add(new EffetTir(pos));
        }
    }

    // Ajouter une pôsition de tir
    public void addDrawLine(PairPosition pos) {
        if (!posTouched.contains(pos))
            posTouched.add(pos);
    }

    // Supprimer toutes les positions de tirs
    private void deleteDrawLine() {
        posTouched = new LinkedList<>();
    }

    public void addExploseB(int x, int y){
        exploseBList.add(new IntPosition(x, y+1));
        exploseBList.add(new IntPosition(x, y-1));
        exploseBList.add(new IntPosition(x+1, y-1));
        exploseBList.add(new IntPosition(x+1, y));
        exploseBList.add(new IntPosition(x+1, y+1));
        exploseBList.add(new IntPosition(x-1, y-1));
        exploseBList.add(new IntPosition(x-1, y));
        exploseBList.add(new IntPosition(x-1, y+1));
    }

    public void addExploseC(int x, int y){
        exploseCList.add(new IntPosition(x, y+1));
        exploseCList.add(new IntPosition(x, y-1));
        exploseCList.add(new IntPosition(x+1, y-1));
        exploseCList.add(new IntPosition(x+1, y));
        exploseCList.add(new IntPosition(x+1, y+1));
        exploseCList.add(new IntPosition(x-1, y-1));
        exploseCList.add(new IntPosition(x-1, y));
        exploseCList.add(new IntPosition(x-1, y+1));
    }
    public void addExploseA(int x, int y){
        exploseAList.add(new IntPosition(x, y+1));
        exploseAList.add(new IntPosition(x, y-1));
        exploseAList.add(new IntPosition(x+1, y-1));
        exploseAList.add(new IntPosition(x+1, y));
        exploseAList.add(new IntPosition(x+1, y+1));
        exploseAList.add(new IntPosition(x-1, y-1));
        exploseAList.add(new IntPosition(x-1, y));
        exploseAList.add(new IntPosition(x-1, y+1));
    }

    public boolean animTirsAlentoursA(int i, int j){
        for (IntPosition pos : exploseAList){
            if(pos.getX()==i && pos.getY()==j) return true;
        }
        return false;
    }
    public boolean animTirsAlentoursB(int i, int j){
        for (IntPosition pos : exploseBList){
            if(pos.getX()==i && pos.getY()==j) return true;
        }
        return false;
    }
    public boolean animTirsAlentoursC(int i, int j){
        for (IntPosition pos : exploseCList){
            if(pos.getX()==i && pos.getY()==j) return true;
        }
        return false;
    }

    // Les fonctions d'animation des tirs
    private boolean tirerHaut(int i, int j) {
        // Pour le moment c'est arbitraire , il faut tout rédifinir après
        // int[][] tab = { { 8, 3 }, { 8, 4 }, { 9, 3 }, { 9, 4 }, { 10, 3 },
        // { 10, 4 }, { 17, 18 }, { 17, 19 }, { 18, 18 }, { 18, 19 } };
        if (exploseA == null)
            return false;
        return (exploseA.getX() == i && exploseA.getY() == j);
    }

    public boolean getMode() {
        return param.getModeDeJeu();
    }

    private boolean tirerGaucheDroit(int i, int j) {
        // Pour le moment c'est arbitraire , il faut tout rédifinir après
        // int[][] tab = { { 7, 5 }, { 7, 6 }, { 7, 7 }, { 7, 8 }, { 19, 17 }, { 19, 16
        // } };
        /*
         * for (IntPosition posTir : exploseB) {
         * if (posTir.getX() == i && posTir.getY() == j)
         * return true;
         * }
         * return false;
         */
        if (exploseB == null)
            return false;
        return (exploseB.getX() == i && exploseB.getY() == j);
    }

    // Les cercles rouges : explosion
    private boolean explose(int i, int j) {
        if (exploseC == null)
            return false;
        return (exploseC.getX() == i && exploseC.getY() == j);
    }

    private void addOrSuppTower(int x, int y) {

        if (map.getMap()[x][y] == null) {
            JPopupMenu menu = new JPopupMenu();
            JMenuItem towerA = new JMenuItem("Explosion Tower ("+TowerA.cost+")");
            JMenuItem towerB = new JMenuItem("Fire Tower ("+TowerB.cost+")");
            JMenuItem towerC = new JMenuItem("Laser Tower ("+TowerC.cost+")");
            menu.add(towerA);
            menu.add(towerB);
            menu.add(towerC);
            towerA.addActionListener(event -> {
                boolean hasMoney = player.achete(TowerA.cost);
                if (!hasMoney){
                    JOptionPane.showMessageDialog(this, "Achat impossible!", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    IntPosition pos = new IntPosition(x, y);
                    TowerA tower = new TowerA(pos, map);
                    map.getTours().add(tower);
                    map.getMap()[x][y] = tower;
                    tower.tirer(INSTANCEVIEW);
                    addExploseA(x,y);
                    //System.out.println("tour A tire");
                    // System.out.println("TowerA ajoutée. Total towers: " + map.getTours().size());
                }
            });
            towerB.addActionListener(event -> {
                boolean hasMoney = player.achete(TowerB.cost);
                if (!hasMoney){
                     JOptionPane.showMessageDialog(this, "Achat impossible!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    
                }
                else{
                    IntPosition pos = new IntPosition(x, y);
                    TowerB tower = new TowerB(pos, map);
                    map.getTours().add(tower);
                    map.getMap()[x][y] = tower;

                    tower.tirer(INSTANCEVIEW);
                    addExploseB(x, y);
                    // System.out.println("TowerB ajoutée. Total towers: " + map.getTours().size());
                }
            });
            towerC.addActionListener(event -> {
                boolean hasMoney = player.achete(TowerC.cost);
                if (!hasMoney){
                    JOptionPane.showMessageDialog(this, "Achat impossible!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    
                }
                else{
                    TowerC tower = new TowerC(new IntPosition(x, y), map);
                    map.getTours().add(tower);
                    map.getMap()[x][y] = tower;
                    tower.tirer(INSTANCEVIEW);
                    addExploseC(x, y);
                    // System.out.println("TowerC ajoutée. Total towers: " + map.getTours().size());
                }
            });
            Dimension buttonSize = this.getSize();

            int xPosition = (buttonSize.width - menu.getPreferredSize().width) / 2;
            int yPosition = (buttonSize.height - menu.getPreferredSize().height) / 2;

            menu.show(this, xPosition, yPosition);
        }

        else {
            Entity entity = map.getMap()[x][y];
            if (entity instanceof TowerA){
                ((TowerA)entity).setStop(true);
                player.vend(TowerA.cost);
            }
            else if (entity instanceof TowerB){
                ((TowerB)entity).setStop(true);
                player.vend(TowerB.cost);
            }
            else if (entity instanceof TowerC){
                ((TowerC)entity).setStop(true);
                player.vend(TowerC.cost);
            }
            map.getTours().remove(entity);
            map.getMap()[x][y] = null;
            exploseAList=new LinkedList<>();
            exploseBList=new LinkedList<>();
            exploseCList=new LinkedList<>();
            // System.out.println(map.getTours().size());
        }

    }

    // Pour afficher les vies au dessues des ennemis
    private void placeVie(int i, int j, JPanel grid) {
        switch (map.lifEnemy(i, j).getName()) {
            case "XXX":
                ImageIcon image = new ImageIcon(Paths.get("ressources", "menu", "vie1.jpg").toString());
                JLabel cell = new JLabel("", image, JLabel.CENTER);
                grid.add(cell);
                break;
            case "XX-":
                ImageIcon image2 = new ImageIcon(
                        Paths.get("ressources", "menu", "vie2.jpg").toString());
                JLabel cell2 = new JLabel("", image2, JLabel.CENTER);
                grid.add(cell2);
                break;
            case "X--":
                ImageIcon image3 = new ImageIcon(
                        Paths.get("ressources", "menu", "vie3.jpg").toString());
                JLabel cell3 = new JLabel("", image3, JLabel.CENTER);
                grid.add(cell3);
                break;
            default:
                ImageIcon image4 = new ImageIcon(
                        Paths.get("ressources", "menu", "vie2.jpg").toString());
                JLabel cell4 = new JLabel("", image4, JLabel.CENTER);
                grid.add(cell4);
                break;
        }
    }

    // premier affichage graphique
    public JPanel creatJPanel() {
        JPanel grid = new JPanel(new GridLayout(ROWS, COLS));

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (enemyWay(i, j)) {
                    JLabel cell = new JLabel();
                    cell.setBackground(param.getModeView() ? Color.GREEN : Color.GRAY);
                    cell.setForeground(param.getModeView() ? Color.GREEN : Color.GRAY);
                    cell.setOpaque(true);
                    cell.setBorder(BorderFactory.createLineBorder(param.getModeView() ? Color.GREEN : Color.GRAY));
                    grid.add(cell);
                    map.add(i, j, null);
                } else if (tourWay(i, j)) {
                    /**
                     * JButton button = new JButton(i + " " + j);
                     * button.setBackground(Color.WHITE);
                     * button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                     */
                    JButton button = new JButton(
                            new ImageIcon(Paths.get("ressources", "musiques", "terre.jpg").toString()));
                    // Prendre les coordonnees nécessaires
                    int x = i;
                    int y = j;
                    // // Ajout de la possibilité d'acheter des tours
                    button.addActionListener(event -> {
                        // TowerA tower = new TowerA(new IntPosition(x, y), map);
                        // map.add(x, y, tower);
                        // player.achete(tower.getCost());
                        // button.setText("TTT");
                        // On achéte à un prix de 100
                        // player.achete(100);
                        addOrSuppTower(x, y);
                    });
                    grid.add(button);
                    // :Ajouter à la liste des butttons
                    buttonTower.add(button);
                } else
                    // Ajouter les autres décorations
                    paintElements(i, j, grid);
            }
        }
        return grid;
    }

    private boolean correspont(int n, int[] tab) {
        for (int i : tab) {
            if (i == n)
                return true;
        }
        return false;
    }

    // Elments de decoration de la map

    private void paintElements(int i, int j, JPanel grid) {
        if (((i >= coord[0] && i <= coord[2]) && (j > coord[1] && j < coord[4]))
                || ((i >= coord[4] && i <= coord[5])
                        && (j > coord[1] && j < coord[4]))) {
            map.add(i, j, null);
            ImageIcon image = new ImageIcon(
                    param.getModeView() ? Paths.get("ressources", "menu", "herbeJaune.jpeg").toString()
                            : Paths.get("ressources", "menu", "herbe3.jpg").toString());
            JLabel cell = new JLabel("", image, JLabel.CENTER);
            grid.add(cell);
        } else if (i == 12 || i == 14) {
            map.add(i, j, null);
            ImageIcon image = new ImageIcon(
                    param.getModeView() ? Paths.get("ressources", "menu", "sable.jpeg").toString()
                            : Paths.get("ressources", "menu", "sable2.jpg").toString());
            JLabel cell = new JLabel("", image, JLabel.CENTER);
            grid.add(cell);
        } else {
            // BufferedImage image = new BufferedImage();
            ImageIcon image = new ImageIcon(
                    param.getModeView() ? Paths.get("ressources", "menu", "herbe3.jpg").toString()
                            : Paths.get("ressources", "menu", "herbeJaune.jpeg").toString());
            JLabel cell = new JLabel("", image, JLabel.CENTER);
            grid.add(cell);
        }
    }

    public int getLevel() {
        return levelModeNormal;
    }

    // Afficher les elements sur le graphique
    // Principale fonction de recharge dans le jeu
    private JPanel paintGrid() throws FileNotFoundException {
        JPanel grid = new JPanel(new GridLayout(ROWS, COLS));
        int buttons = 0;
        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < COLS; j++) {
                /**
                 * Afficher les vies sur les ennemis
                 */
                // if (map.lifeWay(i, j))
                // placeVie(i, j, grid);
                /**
                 * Crééer un effet d'explosion sur les positions ciblées
                 */
                // if (map.lifeWay(i, j) && map.lifEnemy(i, j) != null)
                // placeVie(i, j, grid);

                /**
                 * Creeer des effets d'explosions de tirs
                 */
                // if (param.getTextOrImage() && map.lifeWay(i, j) && map.lifEnemy(i, j) !=
                // null)
                // placeVie(i, j, grid);
                if (exploseA != null && tirerHaut(i, j)) {
                    grid.add(new Explose());
                    exploseA = null;
                } else if (tirerGaucheDroit(i, j)) {
                    grid.add(new LaserEffect(Paths.get("ressources", "menu", "feu2.png").toString()));
                    exploseB = null;
                } else if (exploseC != null && explose(i, j)) {
                    grid.add(new LaserEffectGridLayout());
                    exploseC = null;
                } else if (enemyWay(i, j)) {
                    JLabel cell;
                    Entity en = map.getMap()[i][j];
                    if (en != null) {
                        /**
                         * Mettre une image
                         */
                        if (param.getTextOrImage()) {
                            ImageIcon image3;
                            if (en instanceof EnemyA)
                                image3 = new ImageIcon(
                                        Paths.get("ressources", "menu", "giftA.gif").toString());
                            else if (en instanceof EnemyB)
                                image3 = new ImageIcon(
                                        Paths.get("ressources", "menu", "test.png").toString());
                            else
                                image3 = new ImageIcon(
                                        Paths.get("ressources", "menu", "enPetit.jpg").toString());
                            cell = new JLabel("", image3, JLabel.CENTER);
                            grid.add(cell);
                        }
                        /**
                         * Mettre du texte
                         * cell = new JLabel (en.getName(), JLabel.CENTER);
                         */
                        else {
                            cell = new JLabel(en.getName(), JLabel.CENTER);
                        }
                    } else
                        cell = new JLabel();
                    cell.setBackground(Color.GREEN);
                    cell.setOpaque(true);
                    cell.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                    grid.add(cell);
                } else if (tourWay(i, j)) {
                    Entity entity = map.getMap()[i][j];
                    ImageIcon image = new ImageIcon();
                    if (entity != null) {
                        if (entity instanceof TowerA)
                            image = new ImageIcon(Paths.get("ressources", "tour1.jpg").toString());
                        if (entity instanceof TowerB)
                            image = new ImageIcon(Paths.get("ressources", "tour2.png").toString());
                        if (entity instanceof TowerC)
                            image = new ImageIcon(Paths.get("ressources", "tour3.png").toString());

                    } else {
                        image = new ImageIcon(Paths.get("ressources", "terre.jpg").toString());
                    }
                    // cell = new JLabel(i + " " + j, JLabel.CENTER);
                    JButton botton = buttonTower.get(buttons++);
                    botton.setIcon(image);
                    grid.add(botton);
                    // grid.add(cell);
                }
                else if (animTirsAlentoursA(i, j)){
                        grid.add(new Explose());
                        //System.out.println("tir alentour");
                }
                else if (animTirsAlentoursB(i, j)) grid.add(new LaserEffect(Paths.get("ressources", "menu" ,"feu2.png").toString()));
                else if (animTirsAlentoursC(i, j)) grid.add(new LaserEffectGridLayout());
                else
                    paintElements(i, j, grid);
            }
        }

        return grid;
    }

    // Tracer le chemin des ennemis , avec les cases correspondantes
    public static boolean enemyWay(int i, int j) {
        if (map.enemyWay(i, j))
            return true;
        return false;
    }

    // Colorier les contours des tours
    public static boolean tourWay(int i, int j) {
        if ((i == coord[7] && (j == coord[8]
        /* || j == coord[3] */)))
            return true;
        else if ((i == coord[12] && (j == coord[8] /* || j == coord[3] */)))
            return true;
        else if ((i == coord[13] && (j == coord[14] || j == coord[9] || j == coord[5] || j == coord[6]))
                || (i == coord[11] && (j == coord[10] /* || j == coord[9] || j == coord[5] */ || j == coord[6])))
            return true;
        else if ((i == 19 && (j == 5 /* || j == 6 || j == 19 */ || j == 18)))
            return true;
        return false;
    }

    private JPanel decorationPanel() {
        JPanel result = new JPanel();
        result.setLayout(new GridLayout(1, 4));
        JPanel deco = new JPanel();
        deco.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        deco.add(createLandBis(Color.WHITE, String.valueOf(player.getLife()),
                Paths.get("ressources", "menu", "coeur.jpg").toString()));
        deco.add(createLandBis(Color.WHITE, String.valueOf(player.getMoney()),
                Paths.get("ressources", "menu", "argent.jpg").toString()));
        deco.add(createLand(Color.BLUE, "PAUSE"));
        JPanel other = new JPanel();
        other.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        other.add(createLandBis(Color.GRAY, "SCORE : " + player.getScore(), null));
        if (param.getModeDeJeu())
            other.add(new JLabel("MODE MARATHON - LEVEL : " + levelModeNormal));
        else
            other.add(new JLabel("MODE NORMAL - LEVEL : " + levelModeNormal));
        result.add(deco);
        result.add(other);

        return result;
    }

    /**
     * 
     * @param color   couleur d'affichage
     * @param message message d'affichage
     * @return un jpanel à inserer dans la fenetre principale
     */
    private JButton createLand(Color color, String message) {
        JButton land = new JButton(message);
        land.setPreferredSize(new Dimension(CELL * 3, CELL));
        land.setBackground(color);
        land.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        if (message.equals("PAUSE")) {
            land.addActionListener(event -> {
                if (bestScore < player.getScore())
                    Serialize.serialize(player.getScore());
                setVisible(false);
                dispose();
                timer.cancel();
                Sauvegarde.addSaved(new Sauvegarde(player, INSTANCEVIEW));
                Game frame = new Game(param, null, false);
                frame.getFrame().setVisible(true);
            });
        }
        return land;
    }

    private JLabel createLandBis(Color color, String message, String image) {
        ImageIcon im;
        JLabel land;
        if (image != null) {
            im = new ImageIcon(
                    image);
            land = new JLabel(message, im, JLabel.CENTER);
        } else
            land = new JLabel(message, JLabel.CENTER);
        land.setPreferredSize(new Dimension(CELL * 3, CELL));
        land.setBackground(color);
        land.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        return land;
    }

    // Principale fonction animation du Jeu ///MODE MARATHON
    public void animateModeMarathon() {
        /**
         * // Lancer le tirs des Tours
         * sur la vague des ennemis
         */
        // for (Tower towers : map.getTours()) {
        //     towers.tirer(this);
        // }
        // Tâche à effectuer lorsque le timer s'exécute
        TimerTask tache = new TimerTask() {

            @Override
            public void run() {
                if (player.dead()) {
                    if (player.getScore() > bestScore)
                        Serialize.serialize(player.getScore());
                    timer.cancel();
                   // System.out.println("On  est mort");
                    setVisible(false);
                    dispose();
                    GameOver over = new GameOver(player.getScore(), param);
                    over.getFrame().setVisible(true);
                }
                map.moveEnemies();
                try {
                    gridPanel = paintGrid();
                } catch (FileNotFoundException e) {
                    // System.out.println("echec");
                    e.printStackTrace();
                }
                JPanel frame = new JPanel();
                frame.setLayout(new BorderLayout());
                frame.add(gridPanel);
                frame.add(decorationPanel(), BorderLayout.NORTH);
                setContentPane(frame);
                setVisible(true);
                if (map.allDead())
                    map.sendEnemies();
            }
        };

        /**
         * // Planifiez la tâche pour s'exécuter toutes les secondes (en
         * // millisecondes)
         * // Vitesse en fonction de la difficluté et du level
         */
        timer.schedule(tache, 0, 1000 - 50 * levelModeNormal - 10 * param.getDiffucult());
    }

    // Principale fonction animation du Jeu ///MODE NORMAL
    public void animateModeNormal() {
        // Lancer le tirs des Tours
        // for (Tower towers : map.getTours()) {
        //     towers.tirer(this);
            // Tâche à effectuer lorsque le timer s'exécute
            TimerTask tache = new TimerTask() {
                
                @Override
                public void run() {
                    //System.out.println("Okiii ça passe");
                    if (player.dead()) {
                        if (player.getScore() > bestScore)
                            Serialize.serialize(player.getScore());
                        timer.cancel();
                       // System.out.println("On  est mort");
                        setVisible(false);
                        dispose();
                        GameOver over = new GameOver(player.getScore(), param);
                        over.getFrame().setVisible(true);
                    }
                    map.moveEnemies();
                    try {
                        gridPanel = paintGrid();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                         //System.out.println("echec");
                        e.printStackTrace();
                    }
                    JPanel frame = new JPanel();
                    frame.setLayout(new BorderLayout());
                    frame.add(gridPanel);
                    frame.add(decorationPanel(), BorderLayout.NORTH);
                    setContentPane(frame);
                    setVisible(true);
                    if (map.allDead()) {
                        timer.cancel();
                       // System.out.println("On est mort");
                        setVisible(false);
                        dispose();
                        Level test = new Level(levelModeNormal, param, player);
                        test.getFrame().setVisible(true);
                    }

                }
            };
            /**
             * // Planifiez la tâche pour s'exécuter toutes les secondes (en
             * // millisecondes)
             * // Vitesse en fonction de la difficluté et du level
             */
            timer.schedule(tache, 0, 1000 - 50 * levelModeNormal - 10 * param.getDiffucult());

    }

    /**
     * // Fonction d'animation prinicipale
     * Gère les différentes animations possibles
     */
    public void animate() {
        if (param.getModeDeJeu()) {
            animateModeMarathon();
            //System.out.println("animateModeMarathon");
        } else {
            animateModeNormal();
            //System.out.println("animateModeNormal");
        }
    }

}
