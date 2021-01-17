import java.awt.*;
import java.util.Random;

public class thodeEnemy extends GameObject {
    //This enemy will spawn once every 4 rounds, and will be a thick enemy, low speed, random color.
    private Random r = new Random();
    private int green = r.nextInt(255);
    private int blue = r.nextInt(255);
    private int red = r.nextInt(255);
    private Color color;
    private Handler handler;

    public thodeEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 1;
        velY = 1;
        color = new Color(red,green,blue);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 100, 100);
    }

    public void tick() {
        x += velX;
        y += velY;
        //-40 Comes from the size of the EasyEnemy *2 for
        //Size of border
        if (y <= 0 || y >= VideoGameAttempt.HEIGHT - 40) {
            velY *= -1;
        }
        if (x <= 0 || x >= VideoGameAttempt.WIDTH - 40) {
            velX *= -1;
        }
        handler.addObject(new easyTrail((int) x, (int) y, ID.easyTrail, color, 100, 100, 0.02, handler));
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x, (int)y, 100, 100);
    }
}
