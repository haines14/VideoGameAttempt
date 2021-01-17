import java.awt.*;
import java.awt.*;
public class mediumEnemy extends GameObject {
    private Handler handler;
    public mediumEnemy(int x, int y, ID id,Handler handler) {
            super(x, y, id);
            this.handler = handler;
            velX = 2;
            velY = 9;
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
            handler.addObject(new easyTrail((int)x,(int)y,ID.easyTrail,Color.BLUE,20,20,0.02,handler));
        }
        public void render(Graphics g){
            g.setColor(Color.BLUE);
            g.fillRect((int)x,(int)y,20,20);
        }
    }

