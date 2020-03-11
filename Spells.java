import java.util.Random;
import java.util.Scanner;

public class Spells extends Methods {

    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    Monsters monster = new Monsters();
    Methods method = new Methods();

    static int brnTimer;

    /* NOTE */
    //Spells contaning the word "Monster" in front of the spell name
    //are meant specifically for the monster

    /*************
        IGNITE
    *************/

    public void ignite() {
        int brnChance = rand.nextInt(100) + 1;
        if (brnChance >= 65 && brnChance <= 71) { // 65 - 71, 7%
            Monsters.hasAilment = true;
            System.out.println(mName + " is burned.");
            brnTimer = 3;
        }// if burned
        Methods.pMana = Methods.pMana - 25;
        int damage = rand.nextInt(11) + 15;
        if (monster.fire == true) {
            damage = damage * 2;
        }// if mon weak to fire
        System.out.println("You attacked for " + damage + " damage!");
        mHealth -= damage;
        monster.monIsDead();
        JarPRee.turn++;
    }// ignite


    /***************
        BURN DoT
    ***************/

    public void burn() { // i dont know right now, maybe next time
        int brnDamage = 10;
        if (monster.fire == true) {
            brnDamage *= 2;
        }// if
        if (brnTimer > 0) {
            System.out.println(monster.mName + " took " + brnDamage + " burn damage.");
            mHealth -= brnDamage;
            brnTimer = brnTimer - 1;
        }// if brnTimer
    }// burn

    /****************
        MINOR HEAL
    *****************/
    
    public void minorHeal() {
        if (Methods.pHealth < 100) {
            Methods.pMana = Methods.pMana - 15;
            setpHealth(pHealth + 20);
            if (Methods.pHealth > 100) {
                setpHealth(100);
            }// if
            System.out.println("You now have " + pHealth + " HP");
            JarPRee.turn++;
        } else {
            System.out.println("Your HP is already full.");
        }// else

    }// minorHeal

    /*********************
        MONSTER FREEZE
    *********************/

    public void mFreeze() {
        int damage = rand.nextInt(16) + 20;
        int frzChance = rand.nextInt(100) + 1;
        System.out.println(mName + " used Freeze for " + damage + " damage!");
        setpHealth(pHealth - damage);
        if (pHealth <= 0) {
            setpHealth(0);
            System.out.println("You were killed...");
            System.exit(0);
        }// if pHealth <= 0
        if (pHealth > 0) {
            System.out.println("You have " + pHealth + " HP" + " remaining!");
        }// if pHealth > 0
        if (frzChance >= 51 && frzChance <= 57) { // 51 - 57, 7%
            System.out.println("You are now frozen");
            JarPRee.turn++;
        }// if frzChance
        JarPRee.turn++;
    }// mFreeze

    /***************
        IDENTIFY
    ***************/

    public void identify() {
        Monsters.visibleHP = true;
        System.out.println("You have identified " + monster.mName + ".");
        method.displayWeakness();
        JarPRee.turn++;
    }// identify

} // Spells
