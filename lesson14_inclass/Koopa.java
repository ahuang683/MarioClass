package lesson14_inclass;

public class Koopa extends Entity {
    Koopa(MarioGame14_InClass s) {
        panel = s;
        width = 5;
        height = 6;
        imageFile = "goombaCoding.gif";
        speed = 5;
        x = 80;
        y = (int)(Math.random()*50);
    }
    void move(){
        x -= speed;
        int r = (int)(Math.random()*2);
        if (r==0)
            y+=2;
        else
            y-=2;
    }
}
