import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {
    //Update and render in real time
    //TODO create objects and process them
    //Need to search how to render to screen from object.
    //Abstract class needed
    //Create gameobject using linked list
    //Don't forget linked list are in reverse order
    //Use pop most likely
    ArrayList<GameObject> object = new ArrayList<GameObject>();
    public void tick(){
        //Values of objects created... Anything not instance of
        //a player,enemy,coin, but all
        for (int i = 0; i < object.size();i++){
            GameObject temp = object.get(i);
            //get(i) function in linkedList, not sure ask google
            //Update it's recursion
            temp.tick();
        }
    }
    public void render(Graphics g){
        try{
            for(int i = 0; i < object.size(); i++){
                GameObject tempObject = object.get(i);
                if (tempObject == null){
                    return;
                }
                tempObject.render(g);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public void clearEnemy(){
        for (int i =0; i < object.size();i++){
            GameObject temp = object.get(i);
            if (temp.getID() == ID.Player) {
                object.clear();
                if (VideoGameAttempt.gameSTATE != STATE.END) {
                    addObject(new Player((int) temp.getX(), (int) temp.getY(), ID.Player, this));
                }
            }
        }
    }
    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
}
