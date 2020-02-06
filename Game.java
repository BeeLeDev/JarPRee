import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    Scanner sc = new Scanner(System.in);
    Random rand = new Random();
    Methods methods = new Methods();

    public void prompt() {

        System.out.println("What is your name?");
        System.out.print(">> ");
        String pName = sc.nextLine();
        System.out.println("Welcome to JarPRee " + pName + ". Let's begin the battle right away!");
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("Select an action by typing a number and hit enter.");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // prompt

    public void mAppearPrompt() {

        // this gonna be hard af, so let's do turn mechanic first

    }

    public void selection() {
        
        System.out.println("What would you like to do?");
        System.out.println("1. Attack");
        System.out.println("2. Spell");
        System.out.println("3. Status");
        System.out.println("4. Inventory");
        System.out.print(">> ");
        try {
            int pSelect = sc.nextInt();
            switch (pSelect) {
            case 1:
                methods.pAttack();
                break;
            case 2:
                methods.pSpellSelection();
                break;
            case 3:
                methods.Status();
                break;
            case 4:
                methods.inventory();
                break;
            case 1022:
                methods.uwu();
                break;
            default:
                System.out.println("\nNot a valid option.");
                break;
            } // switch
        } catch (java.util.InputMismatchException ex) {
            sc.nextLine();
            System.out.println("\nTry again.\n");
        } // catch
    } // selection

} // Game
