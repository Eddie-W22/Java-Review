import javax.swing.*;      // JFrame, JPanel, Timer, JButton, etc.
import java.awt.*;         // Graphics, Graphics2D, Color, Font, etc.
import java.awt.event.*;   // KeyListener, ActionListener, MouseListener, etc.
import java.awt.image.BufferedImage;  // for holding loaded PNGs in memory
import javax.imageio.ImageIO;         // for actually reading PNG files from disk
import java.io.File;  

public class Ship {
    private int xPos;
    private int yPos;
    public Ship(int x, int y){
        xPos = x;
        yPos = y;
    }

    public void move(int x, int y){
        xPos += x;
        yPos += y;
    }
}
