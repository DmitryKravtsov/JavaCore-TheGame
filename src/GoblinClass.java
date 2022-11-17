import java.util.Random;

public class GoblinClass extends FighterClass{
    public GoblinClass() {
        Random r = new Random();
        name = "Smeagol";
        agility = 31;
        hitpoints = 80;
        strength = 11;
        hitpoints = 68 + r.nextInt(18);  // случайное здоровье от 68 до 85
        strength = 9 + r.nextInt(4); // случайная сила от 9 до 12
    }

    @Override
    void strike(FighterClass victim, int chance) {
        System.out.println(name + ": Grrrraaaahhh!!!");
        if ((agility * 3) > chance) {
            victim.getDamaged(this.strength);
        } else {
            System.out.println(name + " missed...");
        }
    }

    @Override
    public void getDamaged(int damage) {
        /// для гоблина шанс получить крит выше, чем для героя
        Random n = new Random();
        if (n.nextInt(100) <= 15) {
            damage *= 2; //для крита увеличиваем вдвое урон
        }
        super.getDamaged(damage);
    }

    @Override
    public String toString() {
        return "GoblinClass{" +
                "name='" + name + '\'' +
                ", agility=" + agility +
                ", hitpoints=" + hitpoints +
                ", experience=" + experience +
                ", gold=" + gold +
                ", strength=" + strength +
                '}';
    }
}
