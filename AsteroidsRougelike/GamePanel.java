import javax.swing.*;      // JFrame, JPanel, Timer, JButton, etc.
import java.awt.*;         // Graphics, Graphics2D, Color, Font, etc.
import java.awt.event.*;   // KeyListener, ActionListener, MouseListener, etc.
import java.awt.image.BufferedImage;  // for holding loaded PNGs in memory
import javax.imageio.ImageIO;         // for actually reading PNG files from disk
import java.io.File;       // or java.io.IOException, depending on how you load images

public class GamePanel extends JPanel{
    int xCord = 300;
    int yCord = 300;
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawString("Hola Brochacho", 50, 50);
        g.fillOval(xCord, yCord, 10, 10);
    }

    public void movingOval(int x, int y){
        xCord += x;
        yCord += y;
    }
}
