package src;
import javax.swing.*;      // JFrame, JPanel, Timer, JButton, etc.
import java.awt.*;         // Graphics, Graphics2D, Color, Font, etc.
import java.awt.event.*;   // KeyListener, ActionListener, MouseListener, etc.
import java.awt.image.BufferedImage;  // for holding loaded PNGs in memory
import javax.imageio.ImageIO;         // for actually reading PNG files from disk
import java.io.File;  
import java.awt.geom.*;


public class Ship implements KeyListener{
    public double xCord = 0;
    public double yCord = 0;
    public boolean rightKeyHeld = false;
    public boolean leftKeyHeld = false;
    public boolean upKeyHeld = false;
    public boolean downKeyHeld = false;
    public double shipAngle = 0;
    public double xV = 0;
    public double yV = 0;
    public double thrust = 0;
    public double gravity = .05;
    public double drag = 0;
    public double rotationAngle = Math.PI/24;

    public Ship(int x, int y){
        xCord = x;
        yCord = y;
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(-shipAngle, xCord, yCord);
        g2d.fill(new Ellipse2D.Double(xCord - 7.5, yCord - 5, 15, 10));
    }

    public void movement(){
        if(rightKeyHeld){
            shipAngle -= rotationAngle;
        }
        if(leftKeyHeld){
            shipAngle += rotationAngle;
        }
       if(upKeyHeld){
            thrust = .5;
            System.out.println("Speeding up");
        }else{
            thrust = 0;
        }
        yV -= gravity;
        if(xV > 0){
            xV -= drag;
        }else if(xV < 0){
            xV += drag;
        }
        xV += Math.cos(shipAngle) * thrust;
        yV += Math.sin(shipAngle) * thrust;
        drag = xV/75;
        xCord += xV;
        yCord -= yV;
        if(yCord >= 500 || yCord <= 100){
            yV = -yV;
        }
        if(xCord >= 1100 || xCord <= 100){
            xV = -xV;
        }
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
