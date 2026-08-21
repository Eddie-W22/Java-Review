package src;
import javax.swing.*;      // JFrame, JPanel, Timer, JButton, etc.
import java.awt.*;         // Graphics, Graphics2D, Color, Font, etc.
import java.awt.event.*;   // KeyListener, ActionListener, MouseListener, etc.
import java.awt.image.BufferedImage;  // for holding loaded PNGs in memory
import javax.imageio.ImageIO;         // for actually reading PNG files from disk
import java.io.File;  
import java.awt.geom.*;

public class Projectile {
    private double xCord;
    private double yCord;
    private double xV;
    private double yV;
    private double angle;
    private double speed = 5;
    private boolean isHit = false;

    public Projectile(double x, double y, double a){
        xCord = x;
        yCord = y;
        angle = a;
        xV = Math.cos(-angle) * speed;
        yV = Math.sin(-angle) * speed;
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform();
        g2d.setColor(Color.BLUE);
        g2d.rotate(-angle, xCord, yCord);
        g2d.fill(new Ellipse2D.Double(xCord - 2.5, yCord - 2.5, 5, 5));
        g2d.setTransform(originalTransform);
    }

    public void action(){
        this.movement();
        this.collisionCheck();
    }

    private void movement(){
        xCord += xV;
        yCord += yV;
    }

    private void collisionCheck(){
        if(xCord > 1100 || yCord > 500 || xCord < 100 || yCord < 100){
            isHit = true;
        }
    }

    public boolean getIsHit(){
        return isHit;
    }



}
