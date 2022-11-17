import java.util.Random;
public class HeroClass extends FighterClass {

    public HeroClass(String name) {
        this.name = name;
        agility = 21;
        hitpoints = 100;
        experience = 10;
        gold = 10;
        strength = 15;
    }

    public int getHitpoints(){
        return hitpoints;
    }

    public int getGold(){
        return gold;
    }
    public void receiveAward(int monsterStrength){
        // прирост золота количественно равен силе убитого монстра
        gold += monsterStrength;
        // прирост опыта - 1/10 силы убитого монстра
        experience += monsterStrength / 10;
    }

    public int acceptPotion(Potions p){
        int prevHP = hitpoints;
        hitpoints += p.getHP();
        if (hitpoints > 100 ) { hitpoints = 100;}
        gold -= p.getGold();
        return (hitpoints - prevHP);
    }

    @Override
    public void strike(FighterClass victim, int chance) {
        System.out.println(name + ": I attack " + victim.getName() + "!!!");
        if ((agility * 3) > chance) {
            victim.getDamaged(this.strength);
        } else {
            System.out.println(name + ": I missed!!!");
        }
    }

    @Override
    public void getDamaged(int damage) {
        System.out.println(name + ": I'm under attack!!! ");
        /// для героя шанс получить крит ниже, чем для монстра
        Random n = new Random();
        if (n.nextInt(100) <= 5 ) {
            damage *= 2; //для крита увеличиваем вдвое урон
        }
        super.getDamaged(damage);
    }

    public void printStatus(){
        System.out.println("\n****** " + name + " - Health: " + hitpoints + " Gold: " + gold + " ******\n");
    }
}
