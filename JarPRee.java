public class JarPRee {

    static Game game = new Game();
    static Methods methods = new Methods();

    static int turn = 2;

    public static void main(String[] args) {
        game.prompt();
        while (methods.pHealth > 0) {
            if (turn % 2 == 0) {
                game.selection();
            } // if turn == true
            else {
                methods.mAttack();
            } // else
        } // while pHealth > 0
    } // main

} // JarPRee
