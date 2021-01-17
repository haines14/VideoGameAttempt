import javafx.scene.shape.Circle;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {
    //Default constructor. Used super because
    //The values are found and protected
    //In GameObject
    Random r = new Random();
    Handler handler;
    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

    }
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,42,20);
    }
    @Override
    public void tick() {
        x+=velX;
        y+=velY;
        x = VideoGameAttempt.keepBetween((int)x,0,VideoGameAttempt.WIDTH-50);
        y = VideoGameAttempt.keepBetween((int)y,0,VideoGameAttempt.HEIGHT-60);
        collision();
        handler.addObject(new easyTrail((int)x,(int)y,ID.easyTrail,Color.WHITE,42,20,.09,handler));
    }


    @Override
    public void render(Graphics g) {
        if (id == ID.Player){
            g.setColor(Color.WHITE);
        }
        g.setColor(Color.WHITE);
        g.fillOval((int)x,(int)y,42,20);

    }
    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if (temp.getID() == ID.EasyEnemy || temp.getID() == ID.mediumEnemy || temp.getID() == ID.hardEnemy || temp.getID() == ID.firstWaveBoss || temp.getID() == ID.thodeEnemy) {
                    if (getBounds().intersects(temp.getBounds())) {
                        HUD.HEALTH -= 2;
                    }
                }
            }
        }
}
