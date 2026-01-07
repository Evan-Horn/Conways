//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.JFrame;
import java.awt.*;

public class Main {
    public static Panel screen;

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)(screenSize.getWidth()*0.7);
        int height = (int)(screenSize.getHeight()*0.7);
        screen = new Panel();
        JFrame frame = new JFrame("Conways Game of Life");
        Panel.setFrameIn(frame);
        frame.setSize(width, height);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(screen);
        frame.setVisible(true);
        frame.addKeyListener(screen);
        /*double width2 = frame.getSize().getWidth();
        double height2 = frame.getSize().getHeight();*/
    }
}