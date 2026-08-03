import javax.swing.*;      // JFrame, JPanel, Timer, JButton, etc.
import java.awt.*;         // Graphics, Graphics2D, Color, Font, etc.
import java.awt.event.*;   // KeyListener, ActionListener, MouseListener, etc.
import java.awt.image.BufferedImage;  // for holding loaded PNGs in memory
import javax.imageio.ImageIO;         // for actually reading PNG files from disk
import java.io.File;       // or java.io.IOException, depending on how you load images

public class GamePanel extends JPanel implements KeyListener{
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
            System.out.println("Right Pressed");
            xCord += 5;
            
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            System.out.println("Left Pressed");
            xCord -= 5;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            System.out.println("Up Pressed");
            yCord -= 5;
            
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            System.out.println("Down Pressed");
            yCord += 5;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            System.out.println("Right Released");
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            System.out.println("Left Released");
        }
    }
}
