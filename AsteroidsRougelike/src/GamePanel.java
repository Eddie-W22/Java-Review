package src;
import javax.swing.*;      // JFrame, JPanel, Timer, JButton, etc.
import java.awt.*;         // Graphics, Graphics2D, Color, Font, etc.
import java.awt.event.*;   // KeyListener, ActionListener, MouseListener, etc.
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;  // for holding loaded PNGs in memory
import javax.imageio.ImageIO;         // for actually reading PNG files from disk
import java.io.File;       // or java.io.IOException, depending on how you load images

public class GamePanel extends JPanel implements KeyListener{
    public boolean rightKeyHeld = false;
    public boolean leftKeyHeld = false;
    public boolean upKeyHeld = false;
    public boolean downKeyHeld = false;
    private Ship s1;

    public GamePanel(Ship s){
        s1 = s;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        setFocusable(true);
        addKeyListener(s1);
        s1.draw(g);
        //TODO: Particle effects
    }

    @Override
    public void keyTyped(KeyEvent e){

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            System.out.println("Right Typed");
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            System.out.println("Left Typed");
        }
    }

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            rightKeyHeld = true;
            System.out.println("Right Pressed " + rightKeyHeld);
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            System.out.println("Left Pressed");
            leftKeyHeld = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            System.out.println("Up Pressed");
            upKeyHeld = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            System.out.println("Down Pressed");
            downKeyHeld = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            System.out.println("Right Released");
            rightKeyHeld = false;

        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            System.out.println("Left Released");
            leftKeyHeld = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            upKeyHeld = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            downKeyHeld = false;
        }
    }
}
