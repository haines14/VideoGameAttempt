import java.util.Random;

public class putMeInCoach {
    private Handler handler;
    private HUD hud;
    private int scoreKeeper = 0;
    private Random r = new Random();

    public putMeInCoach(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
        scoreKeeper++;
        if (scoreKeeper >= 125) {
            scoreKeeper = 0;
            hud.setLevel(hud.getLevel() + 1);
            if (hud.getLevel() % 4 == 0){
                handler.addObject(new thodeEnemy(r.nextInt(VideoGameAttempt.WIDTH - 50),
                        r.nextInt(VideoGameAttempt.HEIGHT - 50),ID.thodeEnemy,handler));
            }
            if (hud.getLevel() <3){
                handler.addObject(new EasyEnemy(r.nextInt(VideoGameAttempt.WIDTH-50),
                        r.nextInt(VideoGameAttempt.HEIGHT-50),ID.EasyEnemy,handler));
            }
            if (hud.getLevel() < 5){
                    handler.addObject(new EasyEnemy(r.nextInt(VideoGameAttempt.WIDTH-50),
                            r.nextInt(VideoGameAttempt.HEIGHT-50),
                            ID.EasyEnemy, handler));
            }
            if (hud.getLevel() == 2) {
                handler.addObject(new mediumEnemy(r.nextInt(VideoGameAttempt.WIDTH-50),
                        r.nextInt(VideoGameAttempt.HEIGHT-50),
                        ID.mediumEnemy, handler));
            } else if (hud.getLevel() == 5) {
                handler.addObject(new hardEnemy(r.nextInt(VideoGameAttempt.WIDTH-50),
                        r.nextInt(VideoGameAttempt.HEIGHT-50),
                        ID.hardEnemy, handler));

            }else if (hud.getLevel() == 7){
                handler.addObject(new mediumEnemy(r.nextInt(VideoGameAttempt.WIDTH-50),
                        r.nextInt(VideoGameAttempt.HEIGHT-50),ID.mediumEnemy,handler));
            }
            else if (hud.getLevel() == 9){
                handler.addObject(new hardEnemy(r.nextInt(VideoGameAttempt.WIDTH-50),
                        r.nextInt(VideoGameAttempt.HEIGHT-50),ID.hardEnemy,handler));
            }
            else if (hud.getLevel() == 10){
                handler.clearEnemy();
                handler.addObject(new firstWaveBoss((VideoGameAttempt.WIDTH/2)-48, -120, ID.firstWaveBoss,
                        handler));
            }
            else if (hud.getLevel() ==14){
                for (int i =0; i < 4;i++){
                    handler.addObject(new EasyEnemy(r.nextInt(VideoGameAttempt.WIDTH-50),
                            r.nextInt(VideoGameAttempt.HEIGHT-50),
                            ID.EasyEnemy, handler));
                }
            }
            else if (hud.getLevel() == 15){
                for (int i = 0; i <2;i++){
                    handler.addObject(new mediumEnemy(r.nextInt(VideoGameAttempt.WIDTH-50),
                            r.nextInt(VideoGameAttempt.HEIGHT-50),
                            ID.mediumEnemy, handler));
                }
                handler.addObject(new hardEnemy(r.nextInt(VideoGameAttempt.WIDTH-50),(VideoGameAttempt.HEIGHT-50),
                        ID.hardEnemy,handler));
            }
            if (hud.getLevel() > 12){
                System.out.println("Good job");
            }
            if (hud.getLevel() == 20){
                handler.clearEnemy();
                for (int i = 0; i < 12; i++){
                    handler.addObject(new mediumEnemy(r.nextInt(VideoGameAttempt.WIDTH-50),
                            r.nextInt(VideoGameAttempt.HEIGHT-50), ID.mediumEnemy,handler   ));
                    if (i > 10){
                        handler.addObject(new EasyEnemy(r.nextInt(VideoGameAttempt.WIDTH-50),
                                r.nextInt(VideoGameAttempt.HEIGHT-50),ID.mediumEnemy,handler));
                    }
                }
            }
        }
    }
}
