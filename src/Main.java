import java.util.Random;

public class Main {
    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {280, 270, 250, 200, 200, 200};
    public static int[] heroesDamage = {10, 15, 20, 30, 15+bossDamage/5, 20};
    public static int[] heroesSupportAction = {40, bossDamage/5};
    public static int[] heroesSupportDamage = {0, 5};
    public static int[] heroesSupportHealth = {400, 600};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Lucky", "Berserk", "Thor"};
    public static String[] heroesSupportType = {"Medic", "Golem"};
    public static int roundNumber;


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }



    public static void playRound() {
        roundNumber++; // roundNumber = roundNumber + 1
        chooseBossDefence();
        bossHits();
        heroesShield();
        heroesBerserkShield();
        heroesCure();
        heroesHit();
        heroesStunBoss();
        heroesAvoidance();
        printStatistics();


    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
        for (int i = 0; i < heroesSupportHealth.length; i++) {
            if (heroesSupportHealth[i] > 0) {
                if (heroesSupportHealth[i] - bossDamage < 0) {
                    heroesSupportHealth[i] = 0;
                } else {
                    heroesSupportHealth[i] -= bossDamage;
                }
            }
        }
    }
    public static void heroesBerserkShield() {
        if (heroesHealth[4] > 0) {
                    heroesHealth[4] += +bossDamage/5;
                }
            }


    public static void heroesShield() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossDefence != heroesAttackType[5]) {
                if (heroesSupportHealth[1] > 50) {
                    heroesHealth[i] += +heroesSupportAction[1];
                    heroesSupportHealth[1] -= heroesSupportAction[1];
                }
            }
        }
        for (int j = 0; j < heroesSupportHealth.length; j++) {
            if (heroesSupportHealth[j] >0 && bossDefence != heroesAttackType[5]) {
                if (heroesSupportHealth [1] > 50) {
                    heroesSupportHealth[j] += +heroesSupportAction[1];
                    heroesSupportHealth[1] -= heroesSupportAction[1];
                }
            }
        }
    }

    public static void heroesCure() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] >0) {
                if (heroesHealth[i] < 100 && heroesSupportHealth[0] > 0) {
                    heroesHealth[i] += + heroesSupportAction[0];
                }break;

            }
        }
    }
    public static void heroesStunBoss() {
        while (heroesHealth[5] > 0) {

        if (heroesAttackType[5] == bossDefence) {
            bossDamage = 0;
            System.out.println("Thor is stun Boss!");

        }
        else {
            bossDamage = 50;
        } break;
        }
    }

    public static void heroesAvoidance() {
        if (heroesHealth[3] > 0 && heroesAttackType[3] == bossDefence) {
                heroesHealth[3] += bossDamage;
                System.out.println("Lucky is avoidance!");
            }
        }


    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coefficient = random.nextInt(5) + 2; // 2,3,4,5,6
                    damage = coefficient * heroesDamage[i];
                    System.out.println("Critical damage: " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }for (int i = 0; i < heroesSupportDamage.length; i++) {
            if (heroesSupportHealth[i] > 0 && bossHealth > 0) {
                if (bossHealth - heroesSupportDamage[i] < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth -=  heroesSupportDamage[i];
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        /*String defence = "No defence";
        if (bossDefence != null) {
            defence = bossDefence;
        }*/
        System.out.println("ROUND " + roundNumber + " --------------");
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage
                + " defence: " + (bossDefence != null ? bossDefence : "No defence"));
        for (int j = 0; j < heroesSupportHealth.length; j++) {
            System.out.println(heroesSupportType[j] + " health: " + heroesSupportHealth[j]
                    + " cure: " + heroesSupportAction[j] + " damage: " + heroesSupportDamage[j]);
        }
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]
                    + " damage: " + heroesDamage[i]);
        }



    }
}