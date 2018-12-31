package sample;

import javafx.scene.image.Image;

abstract class Cheer extends Creature {
    //变换动作 Frame-Based Animation
    private Image image;

    public Cheer(){
        super("Cheer",0,450,true,true);
    }

    public Cheer(Image image){
        this.image=image;
    }

    public Image getImage(){
        return image;
    }

//    public void Ready() {
//        Image[] yeyeArray = new Image[2];
//        Image[] sjArray = new Image[2];
//
//        //爷爷和蛇精 图像需要大一点
//        Image yeye1 = new Image("file:yeye.png", 130, 180, false, false);
//        Image yeye2 = new Image("file:yeye2.png", 130, 180, false, false);
//        Image sj1 = new Image("file:snake.png", 120, 180, false, false);
//        Image sj2 = new Image("file:snake2.png", 120, 180, false, false);
//
//        yeyeArray[0] = yeye1;
//        yeyeArray[1] = yeye2;
//        sjArray[0]=sj1;
//        sjArray[1]=sj2;
//
//        cheer.frames=yeyeArray;
//        cheer.duration=0.5;
//    }
}
