import java.util.Random;
public abstract class FighterClass extends GameUnit {
    abstract void strike(FighterClass victim, int chance);

    void getDamaged(int damage){
        System.out.println(name + " hit!!! Suffered damage by " + damage + " hitpoints.");
        if (hitpoints <= damage) {
            hitpoints = 0;
            System.out.println(name + " deceased.... ");
        } else {
            hitpoints -= damage;
        }
    };

    public boolean isDestroyed(){
        return hitpoints == 0;
    }

    public int getStrength(){
        return strength;
    }

}
