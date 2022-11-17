import java.util.Random;

import static java.lang.Thread.sleep;

public class BattleProcess implements Runnable {
    HeroClass theHero;
    FighterClass aMonster;

    public BattleProcess(HeroClass theHero, FighterClass aMonster) {
        this.theHero = theHero;
        this.aMonster = aMonster;
    }

    @Override
    public void run() {
        Random random = new Random();
        System.out.println("\n************************************\n****** Let the battle begin!!! *****\n************************************\n");
        int chance = 0;
        try {
            while ( !theHero.isDestroyed() && !aMonster.isDestroyed()) {
                chance = random.nextInt(100);
                theHero.strike(aMonster, chance);
                if ( !aMonster.isDestroyed()){
                        sleep(1);
                    aMonster.strike(theHero, chance);
                    sleep(1);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n************************************\n******  The battle finished.  ******\n************************************\n");
    }
}
