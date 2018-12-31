package sample;

import javafx.scene.image.Image;

//蝎子精
public class Scorpion extends Enemy {
    public Scorpion() {
        super(new Image("file:xzj.png", 100, 90, false, false));
        this.setX(890);
        this.setY(400);
        this.setAlive(true);
        this.setIn(true);
        this.setName("x");//xzj
    }

}
