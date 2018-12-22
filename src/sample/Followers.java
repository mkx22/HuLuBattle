package sample;

import javafx.scene.image.Image;

import java.util.Random;

public class Followers extends Enemy {
    public Followers() {
        super(new Image("file:enemy.jpg", 80, 90, false, false));
        this.setX(970);
        this.setY(100);
        this.setAlive(true);
        this.setIn(true);
        this.setName("e");

    }
    public Followers(int i) {
        super(new Image("file:enemy.jpg", 80, 90, false, false));


        if(i<4) {
            setX(810);
            setY(100 + i * 100);
        }
        else if(i<7){
            setX(890);
            setY(100*(7-i));
        }
        setAlive(true);
        setIn(true);
        setName("e");//xll

    }


}
