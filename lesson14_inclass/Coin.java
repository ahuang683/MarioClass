package lesson14_inclass;

public class Coin extends Entity {
    Coin(MarioGame14_InClass s) {
        panel = s;
        width = 3;
        height = 3;
        imageFile = "mario_coin2.gif";
        speed = 2;
        x = 80;
        y = (int)(Math.random()*50);
    }
    void move(){
        x -= speed;
    }
}
