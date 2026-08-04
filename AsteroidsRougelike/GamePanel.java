import javax.swing.*;      // JFrame, JPanel, Timer, JButton, etc.
import java.awt.*;         // Graphics, Graphics2D, Color, Font, etc.
import java.awt.event.*;   // KeyListener, ActionListener, MouseListener, etc.
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;  // for holding loaded PNGs in memory
import javax.imageio.ImageIO;         // for actually reading PNG files from disk
import java.io.File;       // or java.io.IOException, depending on how you load images

public class GamePanel extends JPanel implements KeyListener{
    public double xCord = 50;
    public double yCord = 150;
    public boolean rightKeyHeld = false;
    public boolean leftKeyHeld = false;
    public boolean upKeyHeld = false;
    public boolean downKeyHeld = false;
    public double angle = 0;
    public double xV = 0;
    public double yV = 0;
    public double thrust = 0;
    public double gravity = .2;
    public double rotationAngle = Math.PI/24;

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        setFocusable(true);
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(-angle, xCord, yCord);
        g2d.fill(new Ellipse2D.Double(xCord - 7.5, yCord - 5, 15, 10));
        
    }

    public void movingOval(int x, int y){
        xCord += x;
        yCord += y;
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
