import javax.swing.*;      // JFrame, JPanel, Timer, JButton, etc.
import java.awt.*;         // Graphics, Graphics2D, Color, Font, etc.
import java.awt.event.*;   // KeyListener, ActionListener, MouseListener, etc.
import java.awt.image.BufferedImage;  // for holding loaded PNGs in memory
import javax.imageio.ImageIO;         // for actually reading PNG files from disk
import java.io.File;       // or java.io.IOException, depending on how you load images

public class AsteroidsRoguelike{
    public static void main(String[] args){
        JFrame frame = new JFrame("Frame Test #1");
        JLabel label = new JLabel("Label Test #1");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public class GamePanel extends JPanel{
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            //TODO drawing code goes here
        }
    }
}