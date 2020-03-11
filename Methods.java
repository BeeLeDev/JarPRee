import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Methods {

    static Game game = new Game();

    Scanner sc = new Scanner(System.in);
    Random rand = new Random();
    Monsters monster = new Monsters();
    static Spells spell = new Spells();

    int pMaxHealh = 100;
    static int pHealth = 100; // figure out why without static keyword, mAttack() and Status() have different values for pHealth
    static int pMana = 100;

    String mName = monster.mName;
    static int mHealth = Monsters.mHealth; //tracks the hp for the game, not the one in Monsters class

    /******************
        MISS CHANCE
    ******************/

    public boolean missChance() {
        int miss = rand.nextInt(100) + 1;
        if (miss >= 20 && miss <= 24) { // 20 - 24, 5%
            return true;
        } else {
            return false;
        }
    }// missChance

    /**********************
        CRITICAL CHANCE
    **********************/

    public boolean critChance() {
        int chance = rand.nextInt(100) + 1;
        if (chance >= 10 && chance <= 14) { // 10 - 14, 5%
            return true;
        } else {
            return false;
        }
    }// critChance

    /********************
        PLAYER ATTACK
    ********************/

    public void pAttack() {
        int damage = rand.nextInt(16) + 10;
        boolean nAnd = false;
        while (nAnd == false) {
            missChance();
            critChance();
            if (missChance() == critChance()) {
                nAnd = false; // don't really need this assignment but eh.
            }// if miss and crit are both true
            else {
                nAnd = true;
            }// else
        }// while
        if (missChance() == true) {
            damage = damage * 0;
            System.out.println("Missed");
        }// if miss passes
        if (critChance() == true) {
            damage = damage * 2;
            System.out.println("Critical");
        }// if crit passes
        System.out.println("You attacked for " + damage + " damage!");
        mHealth -= damage;
        monster.monIsDead();
        JarPRee.turn++;
    }// pAttack

    /*********************
        MONSTER ATTACK
    *********************/

    public void mAttack() {
        try {
            TimeUnit.SECONDS.sleep(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        int atkRoll = rand.nextInt(100) + 1;
        if (atkRoll >= 25 && atkRoll <= 49) {
            spell.mFreeze();
            JarPRee.turn++;
        }// if mFreeze
        else {
            int damage = rand.nextInt(41) + 10;
            if (missChance() == true) { // crit would be broken so, didn't add it
                damage = damage * 0;
                System.out.println("Missed");
            }
            System.out.println(mName + " attacked for " + damage + " damage!");
            setpHealth(pHealth - damage);
            if (pHealth <= 0) {
                setpHealth(0);
                System.out.println("You were killed...");
                System.out.println("\n");
                System.exit(0);
            }// if pHealth <= 0
            if (pHealth > 0) {
                System.out.println("You have " + pHealth + " HP" + " remaining!");
            }// if pHealth > 0
            //JarPRee.turn++; placed into JarPRee.java to simplify method procedence.
        }// else baseAttack
    }// mAttack

    /*********************
        DISPLAY STATUS
    *********************/

    public void Status() {

        System.out.println("[Your Status]");
        System.out.println("HP: " + pHealth);
        System.out.println("Mana: " + pMana);
        if (Monsters.visibleHP == true)
        {
        System.out.println("[Monster Status]");
        System.out.println("HP: " + mHealth);
        displayWeakness();
        }// if

    }// Status

    int potions = 3;

    /**********************
        CHECK INVENTORY
    **********************/

    public void inventory() {

        int invAnswer;

        System.out.println("[Items]");
        System.out.println("Potions: " + potions); // un-hardcode it, make inventory array or something
        System.out.println("What do you want to use?");
        System.out.println("1. Potion");
        System.out.println("2. Back");
        invAnswer = sc.nextInt();
        try {
            switch (invAnswer) {
            case 1:
                usePotion();
                JarPRee.turn++;
                break;
            case 2:
                game.selection();
                break;
            default:
                System.out.println("Not a valid option.");
                break;
            }// switch
        } catch (java.util.InputMismatchException ex) {
            sc.nextLine();
            System.out.println("Try again.");
        }// catch

    }// inventory

    /*****************
        USE POTION
    *****************/


    public void usePotion() {
        if (potions > 0) {
            int phealVal = 50;
            potions--;
            System.out.println("You were healed " + phealVal + " HP" + ".");
            setpHealth(pHealth + phealVal);
            if (pHealth >= 100) {
                setpHealth(100);
                System.out.println("You have " + pHealth + " HP" + ".");
            } else {
                System.out.println("You have " + pHealth + " HP" + ".");
            }
            System.out.println(potions + " potions remaining.");
        } else {
            System.out.println("You don't have enough.");
        }

    }// usePotion

    /*********************
        DISPLAY SPELLS 
    *********************/

    public void pSpellSelection() { // only 4 spells available in slot
        System.out.println("What spell would you like to use?");
        System.out.println("1. Ignite");
        System.out.println("2. Minor Heal");
        System.out.println("3. Identify");
        System.out.println("5. Back");
        System.out.print(">> ");        
        try {
            int pSelectSpell = sc.nextInt();
            switch (pSelectSpell) {
            case 1:
                if (pMana >= 25) {
                    spell.ignite();
                    break;
                } else {
                    System.out.println("Not enough mana.");
                    break;
                }
            case 2:
                if (pMana >= 15) {
                    spell.minorHeal();
                    break;
                } else {
                    System.out.println("Not enough mana.");
                    break;
                }
            case 3:
                spell.identify();
                break;
            case 5:
                game.selection();
                break;
            default:
                System.out.println("Not a valid option.");
                break;
            }// switch
        } catch (java.util.InputMismatchException ex) {
            sc.nextLine();
            System.out.println("Try again.");
        }// catch
    }// pSpellSelection

    /*****************
        SET HEALTH
    *****************/

    public void setpHealth(int pHealth) {
        Methods.pHealth = pHealth;
    }// setpHealth

    /*************
        SECRET
    *************/
    
    public void uwu() {
        String sCode = "uwu";
        System.out.println("What's the secret?");
        System.out.print(">> ");
        String sInput = sc.nextLine();
        if (sInput.equalsIgnoreCase(sCode)) {
            System.out.println("You found the Sword of OWO");
            System.out.println("So far though it does nothing so. lul");
        } else {
            System.out.println("Wrong\n");
            System.out.println("You took " + pHealth + " damage.");
            setpHealth(pHealth - pHealth);
        }// i should make it if they ge tit wrong they die, if right they get something
    }// uwu

    /***********************
        DISPLAY WEAKNESS
    ***********************/

    public void displayWeakness() {
        if (monster.hasWeakness == true) {
            for(int i = 0; i < 1; ++i) { // change the i < [1] where 1 would be the amount of weaknesses the monster has
                System.out.println("Weaknesses:");
                System.out.println(monster.monWeakness[i]);
            }// for
        }// if
        else {
            System.out.println(monster.mName + " has no weaknesses.");
        }// else
    }// displayWeakness

}// Methods
