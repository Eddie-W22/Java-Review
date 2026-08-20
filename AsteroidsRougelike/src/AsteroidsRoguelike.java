package src;
import javax.swing.*;      // JFrame, JPanel, Timer, JButton, etc.

import java.awt.*;         // Graphics, Graphics2D, Color, Font, etc.
import java.awt.event.*;   // KeyListener, ActionListener, MouseListener, etc.
import java.awt.image.BufferedImage;  // for holding loaded PNGs in memory
import javax.imageio.ImageIO;         // for actually reading PNG files from disk
import java.io.File;       // or java.io.IOException, depending on how you load images
import java.util.ArrayList;

public class AsteroidsRoguelike{
    public static void main(String[] args){
        JFrame frame = new JFrame("Frame Test #1");
        frame.setSize(1200, 600);
        PlayerShip p1 = new PlayerShip(120, 300);
        EnemyShip e1 = new EnemyShip(1080, 300, p1);
        GamePanel gp = new GamePanel(p1, e1);
        gp.requestFocusInWindow();
        frame.add(gp);
        frame.addKeyListener(p1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Timer timer = new Timer(16, e -> {
            gp.requestFocusInWindow();
            e1.movement();
            p1.movement();
            gp.repaint();
        });
        timer.start();

    }
}