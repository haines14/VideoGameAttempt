

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.Random;

import static java.awt.image.ImageObserver.HEIGHT;

public class Menu extends MouseAdapter {
   private VideoGameAttempt game;
   public boolean twoPlayer = false;
    private Handler handle;
    private HUD hud;
    private Random r = new Random();
    public Menu(VideoGameAttempt game,Handler handler,HUD hud){
        this.game = game;
        this.handle = handler;
        this.hud = hud;
    }
    public void mousePressed(MouseEvent e) {

        int xClick = e.getX();
        int yClick = e.getY();
        if (whereClick(xClick,yClick,210,150,200,64)){
            //If this is true. The click was inside the rectangle Play.
            game.gameSTATE = STATE.Game;
            handle.addObject(new Player(VideoGameAttempt.WIDTH/2-32,VideoGameAttempt.HEIGHT/2-32,ID.Player, handle));
            handle.clearEnemy();
            handle.addObject(new EasyEnemy(r.nextInt(VideoGameAttempt.WIDTH-50),
                    r.nextInt(VideoGameAttempt.HEIGHT -50),
                    ID.EasyEnemy, handle));
        }
        if (whereClick(xClick,yClick,210,400,200,64)){
            twoPlayer = true;
            handle.addObject(new Player(VideoGameAttempt.WIDTH/2-32,VideoGameAttempt.HEIGHT/2-32,ID.Player, handle));
            handle.addObject(new Player(VideoGameAttempt.WIDTH/2-32,VideoGameAttempt.HEIGHT/2-32,ID.Player, handle));
            handle.clearEnemy();
            handle.addObject(new EasyEnemy(r.nextInt(VideoGameAttempt.WIDTH-50),
                    r.nextInt(VideoGameAttempt.HEIGHT -50),
                    ID.EasyEnemy, handle));
        }
        //Quit
        if (game.gameSTATE == STATE.Menu) {
            if (whereClick(xClick, yClick, 210, 350, 200, 64)) {
                System.exit(1);
            }
        }
        //Help
        if(whereClick(xClick,yClick,210,250,200,64)){
            game.gameSTATE = STATE.help;
        }
        //If clicked help, now they can click back
        if (game.gameSTATE == STATE.help){
            if (whereClick(xClick,yClick,210,350,200,64)){
                game.gameSTATE = STATE.Menu;
                return;
            }
        }
        if (game.gameSTATE == STATE.END){
            if (whereClick(xClick,yClick,210,350,200,64)){

                game.gameSTATE = STATE.Menu;
                //Reduce score,level, and upgrades, and money to defualt
                hud.setScore(0);
                hud.setLevel(0);
                //Can't do in main or will take away from current score.
            }
        }
        }

    public void mouseReleased(MouseEvent e) {

    }
    private boolean whereClick(int mx,int my,int x,int y,int width,int height){
        if (mx > x && mx < x +width){
            if (my > y && my < y + height){
                return true;
            }
        }
        return false;
        //Poorly done, I could've manipulated
        //Jframes and Jpanels, but I didn't want to
        //Spend an absurd amount of time,
        //Instead I'm checking if mouseclick
        //Is between each rectangle.
        //May update in future.
    }

    public void tick() {

    }
    public void render(Graphics g)
    {
        if (game.gameSTATE == STATE.Menu){
        Font f = new Font("arial",1,50);
        Font f1 = new Font("arial",1,50);
        g.setFont(f);
        g.setColor(Color.WHITE);
        g.drawString("Menu",240,70);
        g.setFont(f1);
        g.drawRect(210,150,200,64);
        g.drawString("Play",270,190);
        g.setColor(Color.WHITE);
        g.drawRect(210,150,200,64);
        g.setColor(Color.WHITE);
        g.drawRect(210,250,200,64);
        g.drawString("Help",270,290);
        g.setColor(Color.WHITE);
        g.drawRect(210,350,200,64);
        g.drawString("Quit",270,390);
        g.setColor(Color.WHITE);
        g.drawRect(210,400,200,64);
        g.drawString("Play 2 player",450,500);

    }
    else if (game.gameSTATE == STATE.help){
        Font f = new Font("arial",1,50);
        Font f1 = new Font("arial",1,35);
        g.setFont(f);
        g.setColor(Color.WHITE);
        g.drawString("Help",240,70);
        g.setFont(f1);
        g.drawString("Controls, W = UP S = Down",5,125);
        g.drawString(" A = LEFT D = Right",5,175);
        g.drawString(" Move to avoid enemies",5,225);
        g.drawRect(210,350,200,64);
            g.drawString("Back",270,390);
        }
        else if (game.gameSTATE == STATE.END){
            Font f = new Font("arial",1,50);
            Font f1 = new Font("arial",1,35);
            g.setFont(f);
            g.setColor(Color.WHITE);
            g.drawString("Game Over :(",180,70);
            g.setFont(f1);
            g.drawString("You lost with a score of "+ hud.getScore(), 5,125);
            g.drawString(" You were level " + hud.getLevel(),5,175);
            g.drawString(" Move to avoid enemies",5,225);
            g.drawRect(210,350,200,64);
            g.drawString("Try Again?",230,390);
        }
}
}


