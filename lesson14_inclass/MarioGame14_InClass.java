package lesson14_inclass;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;


public class MarioGame14_InClass extends JPanel implements Runnable {
    Mario m;
    ArrayList<Coin> coins; //declaration
    ArrayList<Block> platform; //declaration
    ArrayList<Koopa> koopas; //declaration
    ArrayList<Mushroom> mushrooms;
    boolean GameStatis=true;
    MarioGame14_InClass(){
        m = new Mario(this);
        platform = new ArrayList<Block>(); //initialize it to empty
        coins = new ArrayList<Coin>(); //initialize it to empty
        koopas = new ArrayList<>(); //initialize it to empty
        mushrooms = new ArrayList<>(); //initialize it to empty
        setFocusable(true);
        KeyAdapter myKeyAdapter = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode()==KeyEvent.VK_SPACE)
                    m.movingUp = true;
            }

            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode()==KeyEvent.VK_SPACE)
                    m.movingUp = false;
            }
        };
        addKeyListener(myKeyAdapter);
    }

    void playSound(String soundFileName){
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File(soundFileName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();   // create clip reference
            clip.open(audioInputStream);    // open audioInputStream to the clip
            clip.loop(0);
        } catch (Exception exception ) { }

    }
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        m.show(g);
        for (Coin c: coins) {
            c.show(g);
        }
        for (Koopa k: koopas) {
            k.show(g);
        }
        for (Block b: platform) {
            b.show(g);
        }
        for (Mushroom b: mushrooms) {
            b.show(g);
        }
        //display the score at the top left
        g.drawString(m.score + "", 20, 20);
        g.drawString(m.life+"",100,20);
        if(GameStatis==false){
            g.drawString("GAME OVER", 400,250);
        }
    }

    public void run(){
        int counter = 0;
        while(true){
            //tell the computer to pause (sleep)
            m.move();
            for (Coin c: coins)
                c.move();
            for (Block b: platform)
                b.move();
            for (Koopa k: koopas)
                k.move();
            for (Mushroom m: mushrooms)
                m.move();
            //generate a coin
            if (counter%1000==0){
                Coin c = new Coin(this);
                coins.add(c);
            }
            if (counter%1000==0){
                Koopa k = new Koopa(this);
                koopas.add(k);
            }
            if (counter%1000==0){
                int y = (int)(Math.random()*50);
                int numOfBlocks = (int)(Math.random()*10);
                for (int i = 0; i < numOfBlocks; i++) {
                    Block b = new Block(this, 80+i*3, y);
                    platform.add(b);
                }
            }

            counter+=100;
            //detect collision
           // for (Coin c: coins) {
            // can't use for-each if you want to remove an element as part of the iteration
            // because of the concurrentmodificationexception
            for (int i = 0; i < coins.size(); i++) {
                Coin c = coins.get(i);
                //detect to see if mario runs into any coin (c)
                if (checkOverlapping(m,c)) {
                    System.out.println("run into a coin");
                    m.score += 10;
                    coins.remove(c);
                    playSound("mariomedia/smb_coin.wav");
                    i--; //reset i so that the element right after the removed element wont' be skipped over
                }
            }
            if(GameStatis==false){
                return;
            }
            //eat mushroom
            for (int i = 0; i < mushrooms.size(); i++) {
                Mushroom c = mushrooms.get(i);
                //detect to see if mario runs into any coin (c)
                if (checkOverlapping(m,c)) {
                    System.out.println("run into a mushroom");
                    m.score += 100;
                    m.life ++;
                    m.width += 2;
                    m.height += 2;
                    mushrooms.remove(c);
               //     playSound("mariomedia/smb_coin.wav");
                    i--; //reset i so that the element right after the removed element wont' be skipped over
                }
            }

            //run into koopas
            //eat mushroom
            for (int i = 0; i < koopas.size(); i++) {
                Koopa c = koopas.get(i);
                //detect to see if mario runs into any coin (c)
                if (checkOverlapping(m,c)) {
                    System.out.println("run into a koopa");
                    m.score -= 100;
                    m.life --;
                    if (m.width>2) {
                        m.width -= 2;
                        m.height -= 2;
                    }
                    koopas.remove(c);
                    if(m.life<=0)GameStatis=false;
                    //     playSound("mariomedia/smb_coin.wav");
                    i--; //reset i so that the element right after the removed element wont' be skipped over
                }
            }
            repaint();
            try {
                Thread.sleep(100); //milliseconds
            } catch (InterruptedException e) {
            }
        }
    }
    static boolean checkOverlapping(Entity e1, Entity e2){
        if (e1.x > e2.x + e2.width) return false;
        if (e1.x + e1.width < e2.x) return false;
        if (e1.y + e1.height < e2.y) return false;
        if (e2.y+ e2.height < e1.y) return false;
        return true;
    }


    public static void main (String argv[]) {
        JFrame myFrame = new JFrame("MarioGame1 HW");
        MarioGame14_InClass myPanel = new MarioGame14_InClass();
        myPanel.setPreferredSize(new Dimension(800, 500));
        myFrame.add(myPanel);
        myFrame.pack();
        myFrame.setResizable(false);
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        new Thread(myPanel).start();
    }
}

