package lesson14_inclass;

import java.awt.*;

public class Entity {
    int gridWidth = 10; //# of pixels in a grid width/height
    int width, height; //in grid
    String imageFile;
    int speed; //vertical speed, in grid
    int x, y; //in grid
    MarioGame14_InClass panel;
    void show(Graphics g){
        Image img = Toolkit.getDefaultToolkit().getImage(imageFile);
        g.drawImage(img, x*gridWidth, y*gridWidth, width*gridWidth,
                height*gridWidth, panel);
    }
}
