package lesson14_inclass;

public class Mushroom extends Entity {
    Mushroom(MarioGame14_InClass s, int mx, int my) {
        panel = s;
        width = 3;
        height = 3;
        imageFile = "mario_mushroom.png";
        speed = 2; //mushroom only moves down //vertical speed
        x = mx;
        y = my;
    }
    void move(){
        for (Block b: panel.platform) {
            if (y+height <= b.y && y+height+speed >=b.y && x>=b.x && x <=b.x+b.width) {
                y = b.y - height;
                return;
            }
        }
        y += speed;
    }
}
