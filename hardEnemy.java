import java.awt.*;

public class hardEnemy extends GameObject {
    private Handler handler;
    private GameObject player;
    public hardEnemy(float x, float y, ID id,Handler handler) {
        super(x, y, id);
        this.handler = handler;
        //Goal difference between player and this enemy
        for (int i = 0; i < handler.object.size();i++){
            if (handler.object.get(i).getID() == ID.Player){
                player = handler.object.get(i);
                //Looped through handler object
                //Found player ID, store in private pleyer
            }
        }
    }
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,20,20);
    }
    public void tick(){
        x+=velX;
        y+=velY;
        float diffX = x - player.getX() - 8;
        float diffY = y- player.getY() - 8;
        //Distance formula
        float distance = (float)Math.sqrt((x-player.getX()) * (x-player.getX()) + (y-player.getY())*(y-player.getY()));
        //Creating velocity determined by distance away
        velX = (float)((-1.0/distance) * diffX);
        velY = (float)((-1.0/distance) * diffY);
        //Borders
        if (y <=0 || y >= VideoGameAttempt.HEIGHT-40){
            velY*=-1;
        }
        if (x <=0 || x >= VideoGameAttempt.WIDTH-40){
            velX*=-1;
        }
        handler.addObject(new easyTrail((int)x,(int)y,ID.easyTrail,Color.green,20,20,0.02,handler));
    }
    public void render(Graphics g){
        g.setColor(Color.green);
        g.fillRect((int)x,(int)y,20,20);
    }
}
