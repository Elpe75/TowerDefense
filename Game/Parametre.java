package Game;

import java.io.*;

import javax.sound.sampled.*;

public class Parametre {
    // La misique
    private Clip musique;
    // Meilleur score
    private int bestScore;
    private boolean modeDeJeu;
    public int difficult;
    // true Mode Marathon
    // False mode Normal;
    private boolean modeView;
    private boolean textOrImage;

    // true View 1
    // False view 2
    public Parametre(boolean modeDeJeu, boolean modeView, int difficult, boolean textOrImage) {
        this.modeDeJeu = modeDeJeu;
        this.modeView = modeView;
        this.difficult = difficult;
        this.textOrImage = textOrImage;
    }

    public void setTextOrImage(boolean change) {
        textOrImage = change;
    }

    public boolean getTextOrImage() {
        return textOrImage;
    }

    public Clip getMusique() {

        return musique;
    }

    public boolean getModeDeJeu() {
        return modeDeJeu;
    }

    public boolean getView() {
        return modeView;
    }

    public Parametre() {
        modeDeJeu = true;
        modeView = true;
        bestScore = 0;
        difficult = 0;
    }

    public int getDiffucult() {
        return difficult;
    }

    public void setDifficult(int x) {
        this.difficult = x;
    }

    public Parametre(int bestScore) {
        modeDeJeu = true;
        modeView = true;
        this.bestScore = bestScore;
    }

    public Parametre(boolean mode) {
        modeDeJeu = true;
        modeView = mode;
        bestScore = 0;
    }

    public Parametre(boolean modeView, boolean modeDeJeu) {
        this.modeDeJeu = modeDeJeu;
        this.modeView = modeView;
    }

    public void setModeJeu(boolean test) {
        modeDeJeu = test;
    }

    public boolean getModeView() {
        return modeView;
    }

    public void setModeView(boolean test) {
        modeView = test;
    }

    public void makeSound(String musik) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(musik));
            musique = AudioSystem.getClip();
            musique.open(audio);
            do {
                musique.start();
            } while (!musique.isActive());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSound() {
        musique = null;
    }
}
