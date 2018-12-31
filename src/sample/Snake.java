package sample;

import javafx.scene.image.Image;

public class Snake extends Cheer {
    public Snake(){

        super(new Image("file:snake.png", 160, 180, false, false));
    this.setX(1150);
    this.setY(450);
    this.setAlive(true);
    this.setIn(true);
    this.setName("snake");
    }

}
