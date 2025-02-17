package Tours;

import java.util.*;

import javax.swing.*;

import Game.View;
import Game.Player.Player;
import Game.geometry.IntPosition;
import Game.geometry.Map;

public interface fonctionsTower {

    void tirer(View view); // fonction permettant a la tour de tirer

    List<IntPosition> casesAtteignables(); // crée une liste de position sur lesquelles la tour peut tirer

}