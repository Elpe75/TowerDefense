package Game.Game;

import Game.Parametre;
import Game.View;
import Game.Player.*;
import java.awt.*;

public class GameManager {
    private Player player;
    private View view;

    public GameManager(Player player, View view) {
        this.player = player;
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public Player getPlayer() {
        return player;
    }

    public void test() {
        view.test();
    }

    public void animate() {
    }
}
