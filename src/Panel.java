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

    private Rectangle2D.Double[][] map;
    private static final int DELAY = 1;
    private static Timer t;
    private static int frame;
    private static JFrame frameWeIn;
    private Dimension screenSize;
    private ArrayList<Rectangle2D.Double> clicked;
    private int scaleFactor;


    public Panel() {
        super();
        setBorder(null);
        addMouseListener(this);
        addMouseMotionListener(this);
        addComponentListener(this);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();



        frame = 0;
        clicked = new ArrayList<Rectangle2D.Double>();
        scaleFactor = 20;

        t = new Timer(DELAY, new Listener());
        t.start();
    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void componentResized(ComponentEvent e) {
        frameWeIn.getContentPane().setBackground(Color.BLACK);
        map = new Rectangle2D.Double[(int) frameWeIn.getSize().getWidth() / scaleFactor][(int) frameWeIn.getSize().getHeight() / scaleFactor];
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                map[row][col] = new Rectangle2D.Double(row * scaleFactor, col * scaleFactor, scaleFactor, scaleFactor);
            }
        }
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
        int mouseX = e.getX();
        int mouseY = e.getY();
        int col = mouseY / scaleFactor;
        int row = mouseX / scaleFactor;
        if (row >= 0 && row < map.length && col >= 0 && col < map[0].length) {
            clicked.add(map[row][col]);
        }
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
        int mouseX = e.getX();
        int mouseY = e.getY();
        int col = mouseY / scaleFactor;
        int row = mouseX / scaleFactor;

        if (row >= 0 && row < map.length && col >= 0 && col < map[0].length) {
            clicked.add(map[row][col]);
        }
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

    public void showGraphics(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        if (map != null) {
            for (int row = 0; row < map.length; row++) {
                for (int col = 0; col < map[row].length; col++) {
                    g2.draw(map[row][col]);
                }
            }
            g2.setColor(Color.YELLOW);
            for(int i =0; i<clicked.size(); i++)
            {
                g2.draw(clicked.get(i));
                g2.fill(clicked.get(i));

            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        showGraphics(g);
    }

    private class Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) //evoked each iteration, basically its always being called as the program runs
        {
            frame++;
            if (frame == Integer.MAX_VALUE) {
                frame = 0;
            }
            repaint();
        }
    }
}
