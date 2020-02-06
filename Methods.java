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
    static int pHealth = 100;
    static int pMana = 100;

    String mName = monster.mName;

    public void pAttack() {
        int damage = rand.nextInt(16) + 10;
        do 
        {
            critChance();
            missChance();
        } while (critChance() != true &&
                    missChance() != true);
        if (critChance() == true) {
            damage = damage * 2;
            System.out.println("Critical");
        }
        if (missChance() == true) {
            damage = damage * 0;
            System.out.println("Missed");
        }
        String cDamage = Integer.toString(damage);
        System.out.println("You attacked for " + cDamage + " damage!");
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
    }// pAttack

    public void mAttack() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        int atkRoll = rand.nextInt(100) + 1;
        if (atkRoll >= 25 && atkRoll <= 49) {
            spell.mFreeze();
            JarPRee.turn++;
        } // if mFreeze
        else {
            int damage = rand.nextInt(41) + 10;
            if (missChance() == true) {
                damage = damage * 0;
                System.out.println("Missed");
            }
            String cDamage = Integer.toString(damage);
            System.out.println(mName + " attacked for " + cDamage + " damage!");
            setpHealth(pHealth - damage);
            if (pHealth <= 0) {
                setpHealth(0);
                System.out.println("You were killed...");
                System.out.println("\n");
                System.exit(0);
            } // if pHealth <= 0
            if (pHealth > 0) {
                System.out.println("You have " + pHealth + " HP" + " remaining!");
            } // if pHealth > 0
            JarPRee.turn++;
        } // else baseAttack
    }// mAttack

    public boolean critChance() {
        int chance = rand.nextInt(100) + 1;
        if (chance >= 10 && chance <= 19) {
            return true;
        } else {
            return false;
        }
    } // critChance

    public boolean missChance() {
        int miss = rand.nextInt(100) + 1;
        if (miss >= 10 && miss <= 19) {
            return true;
        } else {
            return false;
        }
    } // missChance

    public void Status() {

        System.out.print("Your Status{ ");
        System.out.print("HP: " + pHealth);
        System.out.print(" Mana: " + pMana);
        System.out.print(" }\t");

        if (monster.visibleHP == true)
        {
        System.out.print("Monster Status{ ");
        System.out.print("HP: " + monster.mHealth);
        System.out.println(" }");
        } //if

    }

    int potions = 3;

    public void inventory() {

        int invAnswer;

        System.out.print("Items{ Potions: " + potions);
        System.out.println(" }");
        System.out.println("What do you want to use?");
        System.out.println("1. Potion");
        System.out.println("2. Back");
        invAnswer = sc.nextInt();
        switch (invAnswer) {
        case 1:
            usePotion();
            break;
        case 2:
            game.selection();
            break;
        default:
            System.out.println("Must choose.");
            break;
        }// switch

    }// inventory

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

    }// useItem

    public void pSpellSelection() { //only 4 spells available in slot
        System.out.println("What spell would you like to use?");
        System.out.println("1. Ignite");
        System.out.println("2. Minor Heal");
        System.out.println("3. Identify");
        System.out.println("5. Back");
        try {
            int pSelectSpell = sc.nextInt();
            switch (pSelectSpell) {
            case 1:
                if (pMana >= 25) {
                    spell.Ignite();
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
                System.out.println("Must choose.");
                break;
            } // switch
        } catch (java.util.InputMismatchException ex) {
            sc.nextLine();
            System.out.println("\nNot a valid option.\n");
        } // catch
    } // pSpellSelection

    public void setpHealth(int pHealth) {
        this.pHealth = pHealth;
    } // setpHealth

    public void uwu() {
        String sCode = "uwu";
        System.out.println("What's the secret?");
        System.out.print(">> ");
        String sInput = sc.nextLine();
        if (sInput.equalsIgnoreCase(sCode)) {
            System.out.println("Psyche bitch.");
            System.out.println("You took " + pHealth + " damage.");
            pHealth = pHealth - pHealth;
        } else {
            System.out.println("Wrong\n");
        } // i should make it if they ge tit wrong they die, if right they get something
    }// uwu

}// Methods
