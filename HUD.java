import java.awt.*;

public class HUD {
    public static float HEALTH = 100;
    private float greenValue = 255;
    private int score = 0;
    private int level = 0;
    //Not ok programming, but only one player, easy access
    public void tick(){
        HEALTH = VideoGameAttempt.keepBetween(HEALTH,0,100);
        greenValue = VideoGameAttempt.keepBetween(greenValue,0,255);
        greenValue = HEALTH * 2;
        score++;

    }
    public void render(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(10,10,200,30);
        g.setColor(new Color(75,(int)greenValue,0));
        g.fillRect(10,10,(int)HEALTH*2,30);
        g.setColor(Color.WHITE);
        g.drawRect(10,10,200,30);
        g.drawString("Score "+ score,15,64);
        g.drawString("Level " + level, 15,80);
    }
    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }
    public void setLevel(int level){
        this.level = level;
    }
    public int getLevel(){
        return level;
    }
    public float getHEALTH(){
        return HEALTH;
    }
}