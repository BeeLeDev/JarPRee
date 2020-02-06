import java.util.Random;
import java.util.Scanner;

public class Spells extends Methods {

    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    Monsters monster = new Monsters();
    Methods method = new Methods();

    public void Ignite() {
        int brnChance = rand.nextInt(100) + 1;
        if (brnChance >= 62 && brnChance <= 71) { // 62 - 71
            burn();
        }
        method.pMana = method.pMana - 25; // fix
        int damage = rand.nextInt(11) + 15;
        if (monster.fire == true) {
            damage = damage * 2;
        }
        String cDamage = Integer.toString(damage);
        System.out.println("You hit for " + cDamage + " damage!");
        monster.setmHealth(monster.mHealth - damage);
        if (monster.mHealth <= 0) {
            monster.setmHealth(0);
            System.out.println("You killed " + mName + "!");
            System.exit(0);
        } // if mHealth <= 0
        if (monster.mHealth > 0 && monster.visibleHP == true) {
            System.out.println(mName + " has " + monster.mHealth + " HP" + " remaining!");
        } // if mHealth > 0
        JarPRee.turn++;
    } // Ignite

    int brnTimer;

    public void burn() { // i dont know right now, maybe next time
        int brnDamage = 10;
        brnTimer = 3;
        if (brnTimer > 0) {
            if (JarPRee.turn % 2 != 0) {
                System.out.println("Opponent took " + brnDamage + " burn damage.");
                monster.mHealth = monster.mHealth - brnDamage;
            }
            brnTimer = brnTimer - 1;
        } // if brnTimer
    }// burn

    public void minorHeal() {
        if (method.pHealth < 100) {
            method.pMana = method.pMana - 15;
            setpHealth(pHealth + 20);
            if (method.pHealth > 100) {
                setpHealth(100);
            }
            System.out.println("\nYou now have " + pHealth + " HP");
            JarPRee.turn++;
        } else {
            System.out.println("\nYour HP is already full.");
        }

    } // minorHeal

    public void mFreeze() {
        int damage = rand.nextInt(16) + 20;
        int frzChance = rand.nextInt(100) + 1;
        String cDamage = Integer.toString(damage);
        System.out.println(mName + " used Freeze for " + cDamage + " damage!");
        setpHealth(pHealth - damage);
        if (pHealth <= 0) {
            setpHealth(0);
            System.out.println("You were killed...");
            System.out.println("\n");
            System.exit(0);
        } // if pHealth <= 0
        if (pHealth > 0) {
            System.out.println("You have " + pHealth + " HP" + " remaining!");
            System.out.println("\n");
        } // if pHealth > 0
        JarPRee.turn++;
        if (frzChance >= 51 && frzChance <= 60) {
            System.out.println("You are now frozen\n");
            JarPRee.turn++;
        } // if frzChance
        JarPRee.turn++;
    }// mFreeze

    public void identify() {
        monster.setvisibleHP(true);
        System.out.println("You have identified " + monster.mName);
        JarPRee.turn++;
    }// identify

} // Spells
