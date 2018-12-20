package sample;

import javafx.scene.image.Image;

//蝎子精
public class Scorpion extends Enemy {
    public Scorpion() {
        super(new Image("file:xzj1.png", 80, 90, false, false));
        this.setX(970);
        this.setY(300);
        this.setAlive(true);
    }

}
