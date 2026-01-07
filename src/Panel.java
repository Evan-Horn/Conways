import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import javax.swing.Timer;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;

public class Panel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, ComponentListener {

    private Rectangle2D map;
    private static final int DELAY = 1;
    private static Timer t;
    private static int frame;
    private static JFrame frameWeIn;
    private Dimension screenSize;


    public Panel() {
        super();
        setBorder(null);
        addMouseListener(this);
        addMouseMotionListener(this);
        addComponentListener(this);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        t = new Timer(DELAY, new Listener());
        t.start();

        frame=0;
    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void componentResized(ComponentEvent e) {

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void componentMoved(ComponentEvent e) {

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void componentShown(ComponentEvent e) {

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void componentHidden(ComponentEvent e) {

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    protected static void setFrameIn(JFrame state) {
        frameWeIn = state;
    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private class Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) //evoked each iteration, basically its always being called as the program runs
        {
            frame++;
            if (frame == Integer.MAX_VALUE) {
                frame = 0;
            }
        }
    }
}
