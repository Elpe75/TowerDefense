package Fonctionnalites;

import Game.View;
import Game.Player.Player;
import java.util.*;

public class Sauvegarde {
    private Player player;
    private View view;
    private int id;
    private static int numId;
    private static LinkedList<Sauvegarde> listSauvegardes = new LinkedList<>();

    public Sauvegarde(Player player, View view) {
        this.player = player;
        this.view = view;
        id = numId++;
        listSauvegardes.add(this);
    }

    public int getId() {
        return id;
    }

    public int getNumSaved() {
        return numId;
    }

    public Player getPlayer() {
        return player;
    }

    public View getView() {
        return view;
    }

    public static LinkedList<Sauvegarde> getList() {
        return listSauvegardes;
    }

    public static void addSaved(Sauvegarde saved) {
        listSauvegardes.add(saved);
    }
}