package src;
import javax.swing.*;      // JFrame, JPanel, Timer, JButton, etc.
import java.awt.*;         // Graphics, Graphics2D, Color, Font, etc.
import java.awt.event.*;   // KeyListener, ActionListener, MouseListener, etc.
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;  // for holding loaded PNGs in memory
import javax.imageio.ImageIO;         // for actually reading PNG files from disk
import java.io.File;       // or java.io.IOException, depending on how you load images

public class GamePanel extends JPanel{
    private PlayerShip s1;
    private EnemyShip e1;

    public GamePanel(PlayerShip s, EnemyShip e){
        s1 = s;
        e1 = e;
    }

    public GamePanel(PlayerShip s){
        s1 = s;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        setFocusable(true);
        addKeyListener(s1);
        s1.draw(g);
        e1.draw(g);
    }
}
