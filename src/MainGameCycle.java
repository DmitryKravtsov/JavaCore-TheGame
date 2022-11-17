import java.util.Random;
import java.util.Scanner;

public class MainGameCycle {
    HeroClass ourHero;
    FighterClass theMonster;
    Scanner scanner;

    public MainGameCycle() {
        scanner = new Scanner(System.in);
    }

    public void runMainCycle(){
        // надо создать героя (запросить у пользователя имя его)
        System.out.println(">>> You must create your hero first!");
        System.out.print(">>> Enter hero's name: ");
        ourHero = new HeroClass(scanner.nextLine());
        System.out.println("\n>>> Hello, " + ourHero.getName() + "!\n");
        // главное меню
        boolean gameFinished = false;
        while (!gameFinished) {
            System.out.println("*****************************\n******   You can go...   ****\n*** 1. to Dealer          ***\n*** 2. to The Dark Forest ***\n*** 3. out of the game    ***");
            ourHero.printStatus();
            int choice;
            if (!scanner.hasNextInt()) {
                String s = scanner.nextLine();
                choice = 10; //заведомо недопустимое число
            } else {
                choice = scanner.nextInt();
            }
            switch (choice) {
                case 1: {
                    toDealer();
                    break;
                }
                case 2: {
                    gameFinished = runBattle();  // если герой погиб в битве, возвращается true, основной цикл игры прерывается
                    break;
                }
                case 3: {
                    gameFinished = true;
                    break;
                }
                default: {
                    System.out.println(">>> Illegal command!!! Please, enter only 1, 2 or 3!\n");
                }
            }
        }
    }

    protected boolean runBattle(){
        Random r = new Random();
        boolean continueBattle = true;
        while (continueBattle) {
            if (r.nextInt(15) > 5) {
                theMonster = new GoblinClass();
            } else {
                theMonster = new SkeletonClass();
            }
            BattleProcess battle = new BattleProcess(ourHero, theMonster);
            Thread battleThread = new Thread(battle);
            try {
                battleThread.start();
                battleThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!ourHero.isDestroyed()) {
                // герой получает золото и опыт соответственно силе убитого монстра
                ourHero.receiveAward((int) (theMonster.getStrength()));
                System.out.println(ourHero.getName() + " wins!\n");
                /// здесь надо дать выбор возврата в гл.меню или снова в бой
                boolean choiceFinished = false;
                while (!choiceFinished) {
                    System.out.println("****************************\n****** Where to go to? *****\n*** 1. Back to the city  ***\n*** 2. Continue battle   ***");
                    ourHero.printStatus();
                    int choice;
                    if (!scanner.hasNextInt()) {
                        String s = scanner.nextLine();
                        choice = 10; //заведомо недопустимое число
                    } else {
                        choice = scanner.nextInt();
                    }
                    switch (choice) {
                        case 1: { continueBattle = false;
                                  choiceFinished = true;
                                  break;
                        }
                        case 2: {
                            choiceFinished = true;
                            break;
                        }
                        default: {
                            System.out.println(">>> Illegal command!!! Please, enter only 1 or 2!\n");
                        }
                    }
                }
            } else {
                System.out.println(theMonster.getName() + " wins!\n");
                continueBattle = false;
            }
        }
        return ourHero.isDestroyed();
    }

    boolean toDealer(){
        boolean leaaveDealer = false;
        while (!leaaveDealer) {
            System.out.println("***************    Magic shop    ***************\n" +
                               "******             You can buy...           ****\n" +
                               "*** 1. small potion (+30 health)  - 10 Gold ****\n" +
                               "*** 2. medium potion (+50 health) - 18 Gold ****\n" +
                               "*** 3. large potion (+80 health)  - 27 Gold ****\n" +
                               "*** 4. Go back to city                      ****");
            ourHero.printStatus();
            int choice;
            if (!scanner.hasNextInt()) {
                String s = scanner.nextLine();
                choice = 10; //заведомо недопустимое число
            } else {
                choice = scanner.nextInt();
            }
            switch (choice) {
                case 1: { buyPotion(Potions.SMALL);
                    break;
                }
                case 2: { buyPotion(Potions.MEDIUM);
                    break;
                }
                case 3: { buyPotion(Potions.LARGE);
                    break;
                }
                case 4: {
                    leaaveDealer = true;
                    break;
                }
                default: {
                    System.out.println(">>> Illegal command!!! Please, enter numbers from 1 to 4!\n");
                }
            }
        }
        return true;
    }

    void buyPotion(Potions potionType){
        if (ourHero.getHitpoints() == 100) {
            System.out.println(">>> " + ourHero.getName() + " does not need treatment!\n");
        } else if (ourHero.getGold() < potionType.getGold()) {
            System.out.println(">>> " + ourHero.getName() + " does not have enough gold to buy this potion!\n");
        } else {
            int deltaHP = ourHero.acceptPotion(potionType);
            System.out.println(">>> " + ourHero.getName() + " bought " + potionType.getTitle()
                               + " potion and improved his health for " + deltaHP
                               + " points!\n");
        }
    }

}
