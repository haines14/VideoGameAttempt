import java.awt.*;
import java.awt.*;
import java.util.Random;

public class menu4Particle extends GameObject {
    private Random r = new Random();
    private int green = r.nextInt(255);
    private int blue = r.nextInt(255);
    private int red = r.nextInt(255);
    int dir = 0;
    private Color color;
    private Handler handler;
    public menu4Particle(int x, int y, ID id,Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = (r.nextInt(7 - -7) + -7);
        velY = (r.nextInt(7 - -7) + -7);
        if (velX ==0){
            velX = 1;
        }
        if (velY == 0){
            velY = 1;
        }
        color = new Color(red,green,blue);
    }
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,20,20);
    }
    public void tick(){
        x+=velX;
        y+=velY;
        //-40 Comes from the size of the EasyEnemy *2 for
        //Size of border
        if (y <=0 || y >= VideoGameAttempt.HEIGHT-40){
            velY*=-1;
        }
        if (x <=0 || x >= VideoGameAttempt.WIDTH-40){
            velX*=-1;
        }
        handler.addObject(new easyTrail((int)x,(int)y,ID.easyTrail,color,20,20,0.02,handler));
    }
    public void render(Graphics g){
        g.setColor(color);
        g.fillRect((int)x,(int)y,20,20);
    }
}

