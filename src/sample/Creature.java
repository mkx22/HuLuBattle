package sample;

import javafx.scene.image.Image;
//这一子类暂时不起作用
//之后会以坐标属性起作用
abstract class Creature {
    //private int type;
    private String name;
    private double x;
    private double y;
    private boolean alive;
    private boolean in;

    public Creature() {
        alive = true;
        in = true;
    }

    public Creature(String name,double x, double y, boolean alive, boolean in) {
        //this.type=type;//0:hulu 1:xll 2:xzj 3:yeye 4:snake
        this.name=name;
        this.x = x;
        this.y = y;
        this.alive = alive;
        this.in = in;
//        this.image=image;
    }

    public void setX(double x) {
        this.x = x;

    }

    public void setY(double y) {
        this.y = y;
    }

    public void setAlive(boolean x) {
        this.alive = x;
    }

    public void setName(String name){this.name=name;}
    public void setIn(boolean x) {
        this.in = x;
    }

    public boolean isAlive() {
        return alive;
    }
    //加入图片后姓名变量变为指代意义 暂时不用
    //public String getName() {
    //    return this.name;
    //}

    public boolean isIn() {
        return in;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public String getName(){return name;}
}
