package Basic_Classes;

import java.util.Random;

public class Sector {

    private boolean revealed;
    private final String image;
    private String revealCode;

    public Sector(String image){
        this.image = image;
        revealed = false;
        int one = new Random().nextInt(9);
        int two = new Random().nextInt(9);
        int three = new Random().nextInt(9);
        int four = new Random().nextInt(9);
        int five = new Random().nextInt(9);
        this.revealCode = one + String.valueOf(two) + three + four + five;
    }

    public boolean reveal(int revealCode){
        if(revealed){
            return false;
        }
        revealed = true;
        return true;
    }
}
