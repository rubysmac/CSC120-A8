import java.util.ArrayList;
import java.util.Scanner;

public class Troll implements Contract {

    String name;
    int height;
    boolean asleep = false;
    ArrayList<String> inHand = new ArrayList<String>();
    Scanner input = new Scanner(System.in);
    int positionX = 0;
    int positionY = 0;

    /**
     * Constructs Troll class with given name and height
     * 
     * @param name   name of troll
     * @param height height of troll
     */
    public Troll(String name, int height) {
        this.name = name;
        this.height = height;
        System.out.println("The troll " + name + " has been made!");
    }

    /**
     * Checks whether the troll is asleep
     * 
     * @return true if asleep, otherwise false
     */
    private boolean checkAsleep() {
        if (asleep) {
            System.out.println("The troll is asleep and cannot perform any actions.");
            return true;
        }
        return false;
    }

    /**
     * Grab the item(supposedly human) in their hand
     * 
     * @param item the human
     */
    public void grab(String item) {
        if (checkAsleep())
            return;
        try {
            if (inHand.contains(item)) {
                System.out.println("This human is already in troll's hands");
            } else {
                this.inHand.add(item);
                System.out.println(item + " successfully captured by " + this.name);
            }
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Drops the item(supposedly human) from their hands
     * 
     * @param item the human
     * @return the item(human)
     */
    public String drop(String item) {
        if (checkAsleep())
            return item;
        try {
            if (inHand.contains(item)) {
                this.inHand.remove(item);
                System.out.println(item + " successfully ditched by " + this.name);
            } else {
                System.out.println("This " + item + " is not in troll's hands");
            }
            return item;
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return item;
    }

    /**
     * Examines the name of the item(human)
     * 
     * @param item the human
     */
    public void examine(String item) {
        if (checkAsleep())
            return;
        try {
            System.out.println("Hmm, Let me see... This human's name is " + item);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Uses up the human, (in fact, which is killing it)
     * 
     * @param item the human
     */
    public void use(String item) {
        if (checkAsleep())
            return;
        try {
            if (inHand.contains(item)) {
                this.inHand.remove(item);
                this.inHand.add("dead-" + item);
                System.out.println(
                        "Now " + item
                                + " is used up.. which means it's dead....");
            } else {
                System.out.println(
                        "You cannot use this " + item + " as your food, as it's not in troll's hands currently");
            }
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Attempting to walk, but cannot, as they're trolls
     * 
     * @param direction where to walk
     * @return false
     */
    public boolean walk(String direction) {
        if (checkAsleep())
            return false;
        try {
            System.out.println("Did you really think troll can walk? HAHAHA\nThey are rocks! Ofc they can't walk!");
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Flying to the given position
     * 
     * @param x horizontal location to move to
     * @param y vertical location to move to
     * @return
     */
    public boolean fly(int x, int y) {
        if (checkAsleep())
            return false;
        try {
            this.positionX = x;
            this.positionY = y;
            System.out.println("The troll teleported to (" + x + ", " + y + ") by its magic-!");
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return true;
    }

    /**
     * Decreasing its height by user-given number
     * 
     * @return height
     */
    public Number shrink() {
        if (checkAsleep())
            return this.height;
        try {
            System.out.println("How much do you want to be shrunk by?");
            int num = input.nextInt();
            if ((height - num) > 0) {
                this.height -= num;
            } else {
                System.out.println("Impossible to be smaller than 0");
            }
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return this.height;
    }

    /**
     * Increasing its height by user-given number
     * 
     * @return height
     */
    public Number grow() {
        if (checkAsleep())
            return this.height;
        try {
            System.out.println("How much do you want to be grown by?");
            int num = input.nextInt();
            this.height += num;
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return this.height;
    }

    /**
     * Going into permanent sleep
     */
    public void rest() {
        if (checkAsleep()) {
            System.out.println("This troll " + this.name + " is already asleep");
        } else {
            this.asleep = true;
            System.out.println("The troll " + this.name
                    + " has fallen asleep, and there's no way to wake it up until 100 years later-!");
        }
    }

    /**
     * Receiving what action they want to undo,
     * but not letting them to do so
     */
    public void undo() {
        System.out.println("What do you want to undo?");
        String word = input.nextLine();
        System.out.println("Well... there's no way for troll to undo " + word);
    }

    /**
     * Printing the current items(humans) list in the troll's hand
     */
    public void printAll() {
        System.out.println("-----list of humans in troll's hand-----");
        for (String item : inHand) {
            System.out.println(item);
        }
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        Troll myTroll = new Troll("ruby", 160);
        Troll yourTroll = new Troll("zia", 140);
        yourTroll.grab("human0");
        yourTroll.grab("human1");
        yourTroll.grab("human2");
        yourTroll.drop("human0");
        yourTroll.use("human0");
        yourTroll.use("human1");
        yourTroll.drop("human1");
        myTroll.walk("smith college");
        yourTroll.printAll();
        myTroll.rest();
        myTroll.grab("human7");
    }
}
