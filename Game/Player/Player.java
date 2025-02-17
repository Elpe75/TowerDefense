package Game.Player;

public class Player implements PlayerInterface {
    public int money;
    public int life;
    public int score;

    public Player() {
        money = 160;
        life = 15;
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public boolean dead() {
        return (life == 0);
    }

    public void setScore() {
        score++;
    }

    public void perds() {
        money = 0;
        life = 0;
    }

    public int getLife() {
        return life;
    }

    public int getMoney() {
        return money;
    }

    public boolean asPerdu() {
        return (life == 0);
    }

    public void touched() {
        if (life - 1 <= 0)
            life = 0;
        else
            life -= 1;
    }

    public boolean achete(int number) {
        if (number <= money) {
            money -= number;
            return true;
        }
        else {
            return false;
        }
    }

    public void vend(int number){
        money+=number;
    }

    public void start() {
        money = 500;
        life = 10;
    }

    @Override
    public void setLife() {
        life -= 1;
    }
}
