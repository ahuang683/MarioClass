package lesson14_inclass;

public class Block extends Entity {
    boolean hasMushroom;
    Block(MarioGame14_InClass s, int bx, int by) {
        panel = s;
        width = 3;
        height = 3;
        int mushroomRand = (int)(Math.random()*5);
        hasMushroom = mushroomRand == 1;
        if (hasMushroom)
            imageFile = "Mushroom block.png";
        else
            imageFile = "block.png";
        speed = 2;
        x = bx;
        y = by;
    }
    void move(){
        x -= speed;
    }
}
