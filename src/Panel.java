import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import javax.swing.Timer;
import java.awt.geom.Rectangle2D;

public class Panel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, ComponentListener {

    private Rectangle2D.Double[][] map;
    private boolean[][] clicked;
    private boolean[][] next;
    private static final int DELAY = 1000;
    private static int frame;
    private static JFrame frameWeIn;
    private boolean play;
    private final int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    private final int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    private final int scaleFactor = 20;


    public Panel() {
        super();
        setBorder(null);
        addMouseListener(this);
        addMouseMotionListener(this);
        addComponentListener(this);

        play = false;

        frame = 0;

        Timer t = new Timer(DELAY, new Listener());
        t.start();
    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void componentResized(ComponentEvent e) {
        frameWeIn.getContentPane().setBackground(Color.BLACK);
        initializeGame();
    }
    private void initializeGame()
    {
        map = new Rectangle2D.Double[(int) frameWeIn.getSize().getWidth() / scaleFactor][(int) frameWeIn.getSize().getHeight() / scaleFactor];
        clicked = new boolean[(int) frameWeIn.getSize().getWidth() / scaleFactor][(int) frameWeIn.getSize().getHeight() / scaleFactor];
        next = new boolean[(int) frameWeIn.getSize().getWidth() / scaleFactor][(int) frameWeIn.getSize().getHeight() / scaleFactor];
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
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            play = !play;
            System.out.println(play);
        }
        if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)
        {
            play= false;
            initializeGame();
            repaint();
        }
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
        click(e);
        repaint();
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
        click(e);
        repaint();
    }

    private void click(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        int col = mouseY / scaleFactor;
        int row = mouseX / scaleFactor;
        if (row >= 0 && row < map.length && col >= 0 && col < map[0].length) {
            clicked[row][col] = true;
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
                    if (clicked[row][col]) {
                        g2.setColor(Color.YELLOW);
                        g2.draw(map[row][col]);
                        g2.fill(map[row][col]);
                    }
                    g2.setColor(Color.WHITE);
                    g2.draw(map[row][col]);
                }
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

            if (play) {
                for (int row = 0; row < map.length; row++) {
                    for (int col = 0; col < map[row].length; col++) {
                        int neighbors = 0;

                        for (int i = 0; i < 8; i++) {
                            int nr = row + dr[i];
                            int nc = col + dc[i];

                            if (nr >= 0 && nr < clicked.length &&
                                    nc >= 0 && nc < clicked[0].length &&
                                    clicked[nr][nc]) {
                                neighbors++;
                            }
                        }

                        if(clicked[row][col])
                        {
                            if(neighbors==0 || neighbors==1 || neighbors>=4)
                            {
                                next[row][col]=false;
                            }
                        }
                        else
                        {
                            if(neighbors==3)
                                next[row][col]=true;
                        }
                    }
                }
            }
            clicked = next;
            repaint();
        }
    }
}
