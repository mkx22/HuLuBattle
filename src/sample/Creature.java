package sample;

import javafx.scene.image.Image;
//这一子类暂时不起作用
//之后会以坐标属性起作用
public class Creature {
    private String name;
    private int x;
    private int y;

    public Creature(){

    }
    public Creature(String name,int x,int y){
        this.name=name;
        this.x=x;
        this.y=y;
//        this.image=image;
    }

    //加入图片后姓名变量变为指代意义 暂时不用
    public String getName() {
        return this.name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
