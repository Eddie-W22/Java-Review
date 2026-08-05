import javax.swing.*;      // JFrame, JPanel, Timer, JButton, etc.
import java.awt.*;         // Graphics, Graphics2D, Color, Font, etc.
import java.awt.event.*;   // KeyListener, ActionListener, MouseListener, etc.
import java.awt.image.BufferedImage;  // for holding loaded PNGs in memory
import javax.imageio.ImageIO;         // for actually reading PNG files from disk
import java.io.File;       // or java.io.IOException, depending on how you load images

public class AsteroidsRoguelike{
    public static void main(String[] args){
        JFrame frame = new JFrame("Frame Test #1");
        // JLabel label = new JLabel("Label Test #1");
        frame.setSize(1200, 600);
        GamePanel gp = new GamePanel();
        gp.requestFocusInWindow();
        // Ship testDummy = new Ship(300, 300);
        frame.add(gp);
        frame.addKeyListener(gp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Timer timer = new Timer(16, e -> {
            if(gp.rightKeyHeld){
                gp.shipAngle -= gp.rotationAngle;
            }
            if(gp.leftKeyHeld){
                gp.shipAngle += gp.rotationAngle;
            }
            if(gp.upKeyHeld){
                gp.thrust = .5;
                System.out.println("Speeding up");
            }
            if(!gp.upKeyHeld){
                gp.thrust = 0;
                if(gp.thrust <= 0){
                    gp.thrust = 0;
                }else{
                gp.thrust -= .05;
                System.out.println("Slowing down");
                }
            }
            gp.yV -= gp.gravity;
            gp.xV += Math.cos(gp.shipAngle) * gp.thrust;
            gp.yV += Math.sin(gp.shipAngle) * gp.thrust;
            gp.xCord += gp.xV;
            gp.yCord -= gp.yV;
            gp.repaint();
        });
        timer.start();

    }
}