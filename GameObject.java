import java.awt.*;

public abstract class GameObject {
    //Player and Enemy object are GameObject
    //Youtube video said everything, coins, my fireball
    //What do every object need
    //Protected needed so that we can access and manipulate
    //Only the objects that extend my class will have
    //access to my variables... Only useful in video games tbh
    //Initialize in here, since abstract not interface
    protected float x,y;
    //Need to see which is user and enemy, or powerup
    //Need enum for which object
    protected ID id;
    //ID in enum.
    protected float velX,velY;
    public GameObject(float x, float y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
        //Used for protected reasons, also getters and
        //setters so they can change and be updated
        //this is used because it's in this class GameObject

    }
    public abstract void tick();
    public abstract void render(Graphics g);
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public void setID(ID id){
        this.id = id;
    }
    public ID getID(){
        return id;
    }
    public void setVelX(float velX){
        this.velX = velX;
    }
    public void setVelY(float velY){
        this.velY = velY;
    }
    public float getVelX(){
        return velX;
    }
    public float getVely(){
        return velY;
    }
    //Basic getters and setters, since abstract, we don't
    //Need to implement everything inside like
    //With implementation
    //Since Abstract needs to be used in all player and enemy
    //Not in player class, but all classes
    // are available in all of them
    public abstract Rectangle getBounds();


}
