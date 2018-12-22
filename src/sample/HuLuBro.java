package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;


public class HuLuBro extends Creature {

    private enum Bro {
        RED("file:1.jpg", "hulu1"),
        ORANGE("file:2.jpg", "hulu2"),
        YELLOW("file:3.jpg", "hulu3"),
        GREEN("file:4.jpg", "hulu4"),
        CYAN("file:5.jpg", "hulu5"),
        BLUE("file:6.jpg", "hulu6"),
        PURPLE("file:7.jpg", "hulu7");

        private String url;
        private String name;
//        private double x;
//        private double y;

//        private Bro(){
//            this.x = 250;//or 330
//            this.y = this.ordinal()*100+100;
//
//        }
        private Bro(String url, String name) {
            this.url = url;
            this.name = name;
            //初始化坐标
//            this.x = x;
//            this.y = y;
        }

        public String getUrl() {
            return url;
        }

//        public double getX(){
//            return x;
//        }
//        public double getY(){
//            return y;
//        }
    }

    private Bro[] bro = {Bro.RED, Bro.YELLOW, Bro.GREEN, Bro.ORANGE, Bro.CYAN, Bro.BLUE, Bro.PURPLE};


    public HuLuBro(int i) {
        if(i%2==0)
            setX(250+80*(int)((Math.abs(i-4))/2));
        else
            setX(250);
        if(i<5)
            setY(100+i*100);
        else
            setY(100*(i-4));
        setAlive(true);
        setName("hulu");
    }

    public Image getImage(int i) {
        return new Image(bro[i].url, 80, 90, false, false);
    }

    public String getName(int i){
        return bro[i].name;
    }

//    public double getX(int i){
//        return bro[i].x;
//    }
//
//    public double getY(int i){
//        return bro[i].y;
//    }
}
