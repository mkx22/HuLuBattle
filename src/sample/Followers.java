package sample;

import javafx.scene.image.Image;

public class Followers extends Enemy {
    public Followers() {
        super(new Image("file:enemy.jpg", 80, 90, false, false));
        this.setX(970);
        this.setY(100);
        this.setAlive(true);

    }
    public Followers(int i) {
        super(new Image("file:enemy.jpg", 80, 90, false, false));

        setX(890);
        if(i<5)
            setY(200+i*100);
        setAlive(true);
    }


}
