public class Monsters {

    static int mHealth = 150; // player doesn't know until they identify, health for THIS MON, PLACE INTO AN OBJECT LIKE TIM()
    String mName = "Bon";
    static boolean visibleHP = false;
    boolean fire = true; // fire weakness
    boolean hasWeakness = true;
    String monWeakness[] = {"Fire"}; //update identify when creating more than one mon
    static boolean hasAilment = false;

    public void Tim() {

    }// Tim

    public void monIsDead() {
        if (mHealth <= 0) {
            mHealth = 0;
            System.out.println("You killed " + mName + "!");
            System.exit(0);
        }// if
    }// monIsDead

}// class Monsters
