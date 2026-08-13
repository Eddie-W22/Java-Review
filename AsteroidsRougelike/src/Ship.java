package src;
import javax.swing.*;      // JFrame, JPanel, Timer, JButton, etc.
import java.awt.*;         // Graphics, Graphics2D, Color, Font, etc.
import java.awt.event.*;   // KeyListener, ActionListener, MouseListener, etc.
import java.awt.image.BufferedImage;  // for holding loaded PNGs in memory
import javax.imageio.ImageIO;         // for actually reading PNG files from disk
import java.io.File;  
import java.awt.geom.*;


public class Ship{
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
}

class PlayerShip extends Ship implements KeyListener{

    public PlayerShip(int x, int y){
        super(x,y);
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.DARK_GRAY);
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
        }else{
            thrust = 0;
        }
        if(xV > 0){
            xV -= drag;
        }else if(xV < 0){
            xV += drag;
        }
        xV += Math.cos(shipAngle) * thrust;
        yV += Math.sin(shipAngle) * thrust;
        yV -= gravity;
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
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
        }
    }

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            rightKeyHeld = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            leftKeyHeld = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            upKeyHeld = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            downKeyHeld = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            rightKeyHeld = false;

        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
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

class EnemyShip extends Ship{
    Ship target;
    double distToTarget;
    double xDist;
    double yDist;
    double angleToTarget;
    public EnemyShip(int x, int y, Ship p){
        super(x,y);
        target = p;
    }

    public void movement(){
        yDist = target.yCord-yCord;
        xDist = target.xCord - xCord;
        angleToTarget = Math.atan2(-yDist, xDist);
        shipAngle = angleToTarget;
        distToTarget = Math.sqrt(Math.pow(yDist, 2) + Math.pow(xDist, 2));
        if(distToTarget >= 200){
            thrust = .2;
        }else{
            thrust = .5;
            if(xV > 0)xV = 1;
            if(xV < 0)xV = -1;
        }
        if(xV > 0){
            xV -= drag;
        }else if(xV < 0){
            xV += drag;
        }
        xV += Math.cos(shipAngle) * thrust;
        yV += Math.sin(shipAngle) * thrust;
        drag = xV/75;
        yV -= gravity;
        xCord += xV;
        yCord -= yV;
        System.out.println(distToTarget + " " + shipAngle);
        System.out.println(xCord + " " + yCord);
        
        // if(yCord >= 500 || yCord <= 100){
        //     yV = -yV;
        // }
        // if(xCord >= 1100 || xCord <= 100){
        //     xV = -xV;
        // }
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.rotate(-shipAngle, xCord, yCord);
        g2d.fill(new Ellipse2D.Double(xCord - 7.5, yCord - 5, 7.5, 5));
    }
}
