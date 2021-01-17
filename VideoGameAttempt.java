import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.length;

public class VideoGameAttempt extends Canvas implements Runnable {
    private static final long serialVersionID = 4959395545L;
    public static final int WIDTH = 640,HEIGHT = WIDTH/12*9;
    private Thread thread;
    private boolean gameRun = false;
    private Handler handle;
    private Random r;
    private HUD hud;
    private String name;
    private putMeInCoach benchWarmer;
    public Menu menu;
    public static STATE gameSTATE = STATE.Menu;
    public static boolean pause = false;
    public VideoGameAttempt(){
        handle = new Handler();
        hud = new HUD();
        menu = new Menu(this,handle,hud);
        this.addKeyListener(new KeyInput(handle,this,menu));
        this.addMouseListener(menu);

        //makes sure Java knows that we are using keys
        // Looking to add a feature here for name!
        //So then I can finish updating the high scores!
        //It should just be a JPanel at the start of the round,
        //I could create another enum state just for entering the name,
        //Or just incoporate it every time in the loop of games.

        new Window(WIDTH,HEIGHT,"VideoGameAttempt",this);
        benchWarmer = new putMeInCoach(handle,hud);
        r = new Random();
        if (gameSTATE != STATE.Game) {
            for (int i = 0; i < 10; i++) {
                handle.addObject(new menu4Particle(r.nextInt(VideoGameAttempt.WIDTH),
                        r.nextInt(VideoGameAttempt.HEIGHT), ID.menu4Particle, handle));
            }
            //Added handler to render objects
        }
    }
    public synchronized  void start(){
        //Creating start method for threads
        thread = new Thread(this);
        thread.start();
        gameRun = true;
    }
    public synchronized  void stop(){
        //Creating stop method for threads.
        try{
            thread.join();
            gameRun = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void run(){
        int count = 0;
        //No need to click on screen to use keyboard
        //Still not perfect
        this.requestFocus();
        //Math supported by in Video Java Rendering Tutorial
        //To reiterate this is not my own math matical formula but
        //One I used from the Video Java Rendering Tutorial
        long lastTime = System.nanoTime();
        double ticking = 60.0;
        double ns = 1000000000 / ticking;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (gameRun){
            long now = System.nanoTime();
            delta+= (now-lastTime)/ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if(gameRun)
                render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000){
                timer+=1000;
                System.out.println("FPS " + frames);
                frames = 0;
            }
            if (hud.getHEALTH() <= 0){
                gameRun = false;
                stop();
            }
        }
        stop();

    }
    private void tick(){
        if (gameSTATE == STATE.Game) {
            if (!pause) {
                hud.tick();
                benchWarmer.tick();
                handle.tick();
                if (HUD.HEALTH <= 0) {
                    HUD.HEALTH = 100;
                    gameSTATE = STATE.END;
                    handle.clearEnemy();
                    endGameHighScore();
                    for (int i = 0; i < 10; i++) {
                        handle.addObject(new menu4Particle(r.nextInt(VideoGameAttempt.WIDTH),
                                r.nextInt(VideoGameAttempt.HEIGHT), ID.menu4Particle, handle));
                    }
                    handle.addObject(new firstWaveBoss(r.nextInt(VideoGameAttempt.WIDTH),
                            40, ID.firstWaveBoss,handle));


                }
            }
        }
        else if(gameSTATE == STATE.Menu || gameSTATE == STATE.END){
            menu.tick();
            handle.tick();
        }

    }
    private void render(){
        //BufferStategy created tried using 2, and 4, 3 is best

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);
        handle.render(g);
        if(pause){
            g.drawString("Pause",100,100);
            g.setColor(Color.RED);
        }
        if (gameSTATE == STATE.Game){
            hud.render(g);
        }
        else if (gameSTATE == STATE.Menu || gameSTATE == STATE.END){
            menu.render(g);
        }
        else if (gameSTATE == STATE.help){
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }
    public static float keepBetween(float var, float min, float max){
        //Var is always checked between the min and max
        //The min and max will both be WIDTH and HEIGHT
        //Checking the object (Player) so they can't
        // escape... Cue nihilism PACS.
        if (var >= max){
            return var = max;
        }
        else if (var <= min){
            return var = min;
        }
        else{
            return var;
        }
    }
    public void endGame(){
        //This became Irrelevant with a more complex exit.
        JOptionPane.showMessageDialog(null,
                "Game Over your score is: " + hud.getScore() +"\nYour level is: " + hud.getLevel(),"Video " +
                        "Game Attempt",JOptionPane.INFORMATION_MESSAGE);
    }
    public void endGameHighScore() {
        Path score = Paths.get("VideoGameAttemptScore.txt");
        try (FileWriter fw = new FileWriter(score.toFile(), true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            if (score.toFile().length() != 0) {
                pw.println("— — — — — — — — — — — — — — — — — — — —");
            }
            pw.println( "Name: " + name + "\tLevel: " + hud.getLevel() + "\tScore:" + hud.getScore());
        }catch (IOException e){
            e.printStackTrace();
        }
        ArrayList<String> t = new ArrayList<>();
        try {
            String l = "";
            BufferedReader br = new BufferedReader(new FileReader(score.toFile()));
            while (( l =br.readLine()) != null) {
                t.add(l);
            }
            int[] level = new int[t.size()];
            for (int i = 0; i <  t.size(); i++){
                String ls = t.get(i);
                String[] getLevelForHighScore = ls.split("Level:");
                String[] answer = getLevelForHighScore[1].split("S");
                System.out.println(answer[0]);
                //Cycle until you find the correct name for validation of level?
                //Or you can cycle top score and print the level to it.
                //Probably going to need a way to get their name into the program efficiently without ruining
                //The basis of this program format.

            }
        }catch (Exception e){
            int l = 1;
            //This became slightly unneeded and became needed but uninvolved for after the catch is triggered.
        }
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public static void main(String[] args) {
        new VideoGameAttempt();

    }
}
