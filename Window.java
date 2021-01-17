import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    private static final long serialVersion = -509607860;
    public Window (int width, int height, String title, VideoGameAttempt game){
        JFrame j = new JFrame(title);
        j.setPreferredSize(new Dimension(width,height));
        j.setMaximumSize(new Dimension(width,height));
        j.setMinimumSize(new Dimension(width,height));
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setResizable(false);
        j.setLocationRelativeTo(null);
        j.add(game);
        j.setVisible(true);
        game.start();
    }
}
