package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends Group {
    private ArrayList<String> input = new ArrayList<String>();
    //背景图
    Image background=new Image("file:background2.jpg");
    //葫芦娃
    HuLuBro bro=new HuLuBro();
    //爷爷和蛇精，图像需要大一点
    //两个图像的大小、含义不同，需要分开初始化
    YeYe yeye=new YeYe();//        Image yeye=new Image("file:yeye.png",130,180,false,false);
    Snake snake=new Snake();//        Image sj=new Image("file:snake.png",120,180,false,false);

    //蝎子精和小喽啰
    Followers e1=new Followers();//Image e1=new Image("file:enemy.jpg",60,60,false,false);
    Scorpion xzj=new Scorpion();//Image xzj=new Image("file:xzj1.png",60,60,false,false);

    GraphicsContext gc;

    public GamePanel() {

    }

    public void load() {
        //画布 load gc
        Canvas canvas=new Canvas(1320,740);
        gc=canvas.getGraphicsContext2D();
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        getChildren().add(canvas);
        getScene().setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                //获取按键信息getCode
                String code = event.getCode().toString();

                if ( !input.contains(code) )
                    input.add( code );

            }
        });

    }
    public void change(){
        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime) {
                //实时获得位置后，作为creature的属性对打斗过程进行设置
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                double x = 20 * t;//232 + 128 * Math.cos(t);
                double y = 80;//232 + 128 * Math.sin(t);

                //背景 必须在前（第一个）
                gc.drawImage(background, 0, 0);
                if (input.contains("SPACE")) {

                    //葫芦娃、小喽啰和蝎子精
                    for (int i = 0; i < 7; i++) {
                        gc.drawImage(bro.getImage(i), x * (1.3+Math.abs(7-i) * 0.25), y + 80 * i);
                    }
                    int num=10;
                    for (int i = 0; i < num; i++) {
                        if (i != num/2)
                            gc.drawImage(e1.getImage(), 1200 - x * (1.3+i * 0.25), y + 60 * i);
                        else
                            gc.drawImage(xzj.getImage(), 1200 - x * (1.3+i * 0.25), y + 60 * i);
                    }
                    //爷爷和蛇精
                    gc.drawImage(yeye.getImage(), 0, 500);
                    gc.drawImage(snake.getImage(), 1200, 500);
                }

                if (x > 100) {
                    gc.drawImage(background, 0, 0);
                    for (int i = 0; i < 7; i++) {
                        gc.drawImage(bro.getImage(i), 100 * (1.3+Math.abs(7-i) * 0.25), y + 80 * i);
                    }
                    int num=10;
                    for (int i = 0; i < num; i++) {
                        if (i != num/2)
                            gc.drawImage(e1.getImage(), 900, y + 60 * i);
                        else
                            gc.drawImage(xzj.getImage(), 900, y + 60 * i);
                    }
                    gc.drawImage(yeye.getImage(), 0, 500);
                    gc.drawImage(snake.getImage(), 1200, 500);
                }
                if (x > 120) {
                    gc.drawImage(background, 0, 0);
                    for (int i = 0; i < 7; i++) {
                        gc.drawImage(bro.getImage(i), 100 * (1.3+Math.abs(7-i) * 0.25), y + 80 * i);
                    }
                    int num=11;
                    for (int i = 0; i < num; i++) {
                        if (i != num/2)
                            gc.drawImage(e1.getImage(),680+i*30,560-Math.abs(num/2-i)*60);
                        else
                            gc.drawImage(xzj.getImage(),680+i*30,560-Math.abs(num/2-i)*60);
                    }
                    gc.drawImage(yeye.getImage(), 0, 500);
                    gc.drawImage(snake.getImage(), 1200, 500);
                }

            }
        }.start();

    }
    public ArrayList<String> getInput(){
        return input;
    }



    public void update(long now){
    }



}
