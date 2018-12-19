package sample;

import javafx.scene.image.Image;

//在这个世界里，坏蛋的数量根据阵型的设置不是固定的，所以不设成enum
//Enemy里面有小喽啰和蝎子精，因此该类作为子类被小喽啰和蝎子精的父类继承
public class Enemy extends Creature {

    private Image image;

    public Enemy() {

    }

    public Enemy(Image image) {
        this.image = image;
    }

    public Image getImage(){
        return image;
    }

}
