import java.util.Random;

public class SkeletonClass extends FighterClass {
    public SkeletonClass() {
        Random r = new Random();
        name = "Wither Skeleton";
        agility = 28;
        hitpoints = 57 + r.nextInt(14);  // случайное здоровье от 57 до 70
        strength = 6 + r.nextInt(3); // случайная сила от 6 до 9
    }

    @Override
    void strike(FighterClass victim, int chance) {
        System.out.println(name + ": Clack-clack-clack!!! ");
        if ((agility * 3) > chance) {
            victim.getDamaged(this.strength);
        } else {
            System.out.println(name + " missed...");
        }
    }
    @Override
    public void getDamaged(int damage) {
        /// для скелета шанс получить крит выше, чем для героя
        Random n = new Random();
        if (n.nextInt(100) <= 15) {
            damage *= 2; //для крита увеличиваем вдвое урон
        }
        super.getDamaged(damage);
    }

    @Override
    public String toString() {
        return "SkeletonClass{" +
                "name='" + name + '\'' +
                ", agility=" + agility +
                ", hitpoints=" + hitpoints +
                ", experience=" + experience +
                ", gold=" + gold +
                ", strength=" + strength +
                '}';
    }
}
