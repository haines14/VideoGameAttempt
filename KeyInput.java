import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean[] keyDown = new boolean[4];
    VideoGameAttempt game;
    private Menu menu;
    public KeyInput(Handler handler, VideoGameAttempt game,Menu menu) {
        this.handler = handler;
        this.game = game;
        this.menu = menu;
        keyDown[0] = false; //w
        keyDown[1] = false; //s
        keyDown[2] = false; //d
        keyDown[3] = false; //a
        if (menu.twoPlayer == true){
            keyDown[4] = false; //Up
            keyDown[5] = false; //Down
            keyDown[6] = false; //Right
            keyDown[7] = false; //Left
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //Corresponds to the number value of whatever is pressed.
        //ASCII keys,
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            //using temp with linkedlist to see instance
            if (temp.getID() == ID.Player) {
                //Key Events for player 1 only

                //If player1 == temp
                //temp object only moves forward on W key
                //If we want Player 2 tho...
                if (key == KeyEvent.VK_W) {
                    temp.setVelY(-5);
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_S) {
                    temp.setVelY(5);
                    keyDown[1] = true;
                }
                if (key == KeyEvent.VK_A) {
                    temp.setVelX(-5);
                    keyDown[3] = true;
                }
                if (key == KeyEvent.VK_D) {
                    temp.setVelX(5);
                    keyDown[2] = true;
                }
            }
            if (temp.getID() == ID.Player){
                if (key == KeyEvent.VK_UP){
                    keyDown[4] = true;
                }
                if (key == KeyEvent.VK_DOWN){
                    keyDown[5] = true;
                }
                if (key == KeyEvent.VK_LEFT){
                    keyDown[6] = true;
                }
                if (key == KeyEvent.VK_RIGHT){
                    keyDown[7] = true;
                }
            }


        }
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        if (VideoGameAttempt.gameSTATE == STATE.Game) {
            if (key == KeyEvent.VK_P) {
                if (VideoGameAttempt.pause == true) {
                    VideoGameAttempt.pause = false;
                } else {
                    VideoGameAttempt.pause = true;
                }
            }

        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        //Corresponds to the number value of whatever is pressed.
        //ASCII keys,mostly unimportant
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            //using temp with linked list to see instance
            if (temp.getID() == ID.Player) {
                //Key Events for player 1 only
                //These stop the movement
                if (key == KeyEvent.VK_W) {
                    //temp.setVelY(0);
                    keyDown[0] = false;
                }
                if (key == KeyEvent.VK_S) {
                   // temp.setVelY(0);
                    keyDown[1] = false;
                }
                if (key == KeyEvent.VK_A) {
                    //temp.setVelX(0);
                    keyDown[3] = false;
                }
                if (key == KeyEvent.VK_D) {
                    //temp.setVelX(0);
                    keyDown[2] = false;
                }
                //Vertical movement
                if (!keyDown[0] && !keyDown[1]){
                    temp.setVelY(0);
                }
                //Horizontal movement
                if (!keyDown[2] && !keyDown[3]){
                    temp.setVelX(0);
                }
            }

        }
    }
}
