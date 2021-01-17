import java.awt.*;
import java.util.Random;

public class firstWaveBoss extends GameObject {
    private Handler handler;
    private int timer2 = 50;
    private int timer = 80;
    Random r = new Random();
    public firstWaveBoss(int x,int y, ID id, Handler handler){
        super(x,y,id);
        this.handler = handler;
        velX = 0;
        velY = 2;
    }
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,96,96);
    }
    public void tick() {
        x += velX;
        y += velY;
        //Stop for second... This boss will shoot
        //Need to use trig (enemy(sin(theta) in relation to current position of player.
        if (timer <=0) {
            velY = 0;
        }
        else{
            timer--;
        }
        if (timer <=0) {
            timer2--;
        }
        if (timer2 <=0){
            if (velX ==0) {
                velX = 2;
            }
            int spawn = r.nextInt(10);
            if (spawn == 0){
                handler.addObject(new enemyBossBullet((int)x+48,(int)y+48, ID.EasyEnemy,handler));
            }
        }


        if (x <= 0 || x >= VideoGameAttempt.WIDTH-96){
            velX*=-1;

        }
        }
    public void render(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect((int)x,(int)y,96,96);
    }
}

