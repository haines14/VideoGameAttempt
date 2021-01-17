import java.awt.*;
import java.awt.*;
import java.util.Random;

public class enemyBossBullet extends GameObject {
    private Handler handler;
    Random r = new Random();
    public enemyBossBullet(int x, int y, ID id,Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = (r.nextInt(5- -5)+-5);
        velY = 5;
    }
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }
    public void tick(){
        x+=velX;
        y+=velY;
        //-40 Comes from the size of the EasyEnemy *2 for
        //Size of border
        if (y >= VideoGameAttempt.HEIGHT){
            handler.removeObject(this);
        }
        handler.addObject(new easyTrail((int)x,(int)y,ID.easyTrail,Color.RED,20,20,0.02f,handler));
    }
    public void render(Graphics g){
        g.setColor(Color.RED);
        g.fillRect((int)x,(int)y,16,16);
    }
}

