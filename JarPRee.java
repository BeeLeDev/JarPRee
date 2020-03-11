public class JarPRee {

    static Game game = new Game();
    static Methods method = new Methods();
    static Spells spell = new Spells();

    static int turn = 0;

    public static void main(String[] args) {
        game.prompt();
        while (Methods.pHealth > 0) {
            if (turn % 2 == 0) {
                game.selection();
            } // if turn == true
            else {
                method.mAttack();
                spell.burn();
                turn++;
            } // else
        } // while pHealth > 0
    } // main

} // JarPRee
