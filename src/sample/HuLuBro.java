package sample;

import javafx.scene.image.Image;


public class HuLuBro extends Creature{
    private enum Bro {
        RED("file:1.png", "老大"),
        ORANGE("file:2.png", "老二"),
        YELLOW("file:3.png", "老三"),
        GREEN("file:4.png", "老四"),
        CYAN("file:5.png", "老五"),
        BLUE("file:6.png", "老六"),
        PURPLE("file:7.png", "老七");

        private String url;
        private String name;
//        private int x;
//        private int y;

        private Bro(String url, String name) {
            this.url=url;
            this.name = name;
            //初始化坐标
//            this.x = this.ordinal() + 1;
//            this.y = 3;
        }

        public String getUrl(){
            return url;
        }
        public String getName() {
            return name;
        }
    }

    private Bro[] bro = {Bro.RED, Bro.YELLOW, Bro.GREEN, Bro.ORANGE, Bro.CYAN, Bro.BLUE, Bro.PURPLE};

    public HuLuBro(){

    }
    public Image getImage(int i){
        return new Image(bro[i].url,80,80,false,false);
    }
}
