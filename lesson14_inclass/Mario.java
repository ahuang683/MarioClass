package lesson14_inclass;

public class Mario extends Entity {
    int score;
    boolean movingUp;
    int life;
    Mario(MarioGame14_InClass s) {
        life = 3;
        panel = s;
        width = 6;
        height = 8;
        imageFile = "mario-running.gif";
        speed = 2;//y increase by this
        score = 0;
        y = 50 / 2;
        x = 80 / 3;
    }

    void move() {
        if (movingUp) {
            for (Block b: panel.platform){
                if (y >= b.y + b.height && y-speed < b.y + b.height
                        && x >= b.x && x <= b.x + b.width) {
                    y = b.y + b.height;
                    if (b.hasMushroom) {
                        Mushroom m = new Mushroom(panel, b.x, b.y - b.height);
                        //assuming mushroom's height is the same
                        panel.mushrooms.add(m);
                    }
                    return;
                }
            }
            y = Math.max(y - speed, 0);
        }
        else {
            for (Block b: panel.platform) {
                if (y+height <= b.y && y+height+speed >=b.y && x>=b.x && x <=b.x+b.width) {
                    y = b.y - height;
                    return;
                }
            }
            y = Math.min(y + speed, panel.getHeight() / gridWidth - height);
        }
    }
}



