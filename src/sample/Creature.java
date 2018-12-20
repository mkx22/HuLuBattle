package sample;

import javafx.scene.image.Image;
//这一子类暂时不起作用
//之后会以坐标属性起作用
abstract class Creature {
    //private String name;
    private double x;
    private double y;
    private boolean alive;

    public Creature() {
        alive = true;
    }

    public Creature(double x, double y,boolean alive) {
        //this.name=name;
        this.x = x;
        this.y = y;
        this.alive=alive;
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


    public boolean isAlive() {
        return alive;
    }
    //加入图片后姓名变量变为指代意义 暂时不用
    //public String getName() {
    //    return this.name;
    //}

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
