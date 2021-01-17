import java.awt.*;

public class easyTrail extends GameObject{
    private float alpha = 1;
    private Handler handle;
    private Color color;
    private int width;
    private int height;
    private double life;
    public easyTrail(int x, int y,ID id, Color color, int width,int height,double life,Handler handle ){
        super(x,y,id);
        //Multiple colors for multiple different
        //Types of enemies, same with location but
        //This is local to easyTrail
        this.color = color;
        this.width =width;
        this.height = height;
        this.life = life;
        this.handle = handle;
    }

    public void tick(){
        if (alpha > life){
            alpha-= (life - 0.001);
        }
        else{
            handle.removeObject(this);
        }
    }
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(trailTransparent(alpha));
        g.setColor(color);
        g.fillRect((int)x,(int)y,width,height);
        g2d.setComposite(trailTransparent(1));
        //Cram the two between eachother. Alpha and 1

    }
    //Create a transparent 'trail'
    private AlphaComposite trailTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type,alpha));
    }
    public Rectangle getBounds(){
        return null;
    }
}
