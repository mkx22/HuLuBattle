package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GamePanel extends Group {
    private ArrayList<String> input = new ArrayList<String>();

    //背景图
    Image background = new Image("file:back2.jpg");
    //dead
    Image dead = new Image("file:dead.png", 80, 90, false, false);

    //葫芦娃
    HuLuBro[] bros = new HuLuBro[7];

    //爷爷和蛇精，图像需要大一点
    //两个图像的大小、含义不同，需要分开初始化
    YeYe yeye = new YeYe();//        Image yeye=new Image("file:yeye.png",130,180,false,false);
    Snake snake = new Snake();//        Image sj=new Image("file:snake.png",120,180,false,false);

    //蝎子精和小喽啰
    //Followers e1 = new Followers();//Image e1=new Image("file:enemy.jpg",60,60,false,false);
    Followers[] followers=new Followers[3];
    Scorpion xzj = new Scorpion();//Image xzj=new Image("file:xzj1.png",60,60,false,false);

    //画布进行人物设置
    Canvas canvas = new Canvas(1320, 740);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    private boolean[][] place = new boolean[10][5];
    private String[][] lives=new String[10][5];
    //private int[][] lives = new int[10][5];//好坏无012

    public GamePanel() {
    }


    public void initialize() {
        int i = 0, j = 0;
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 5; j++) {
                place[i][j] = false;
                lives[i][j] = "";
            }
        }

        for (i = 0; i < 7; i++) {
            bros[i] = new HuLuBro(i);
//            followers[i]=new Followers(i);
        }
        for (i = 0; i < 3; i++) {
            bros[i] = new HuLuBro(i);
            followers[i]=new Followers(i);
        }

    }

    public void load() {
        initialize();
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        getChildren().add(canvas);
        gc.drawImage(background, 0, 0);

        //画布 load gc
        getScene().setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                //获取按键信息getCode
                String code = event.getCode().toString();

                if (!input.contains(code))
                    input.add(code);

            }
        });
//        getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                String code= event.getCode().toString();
//                input.remove(code);
//            }
//        });

    }

    public void try_it() {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 7; i++) {
            exec.execute(new Hulu(bros[i], bros[i].getX(), bros[i].getY()));
        }
        for (int i = 0; i < 3; i++) {
            exec.execute(new XLL(followers[i], followers[i].getX(), followers[i].getY(),i));
        }
        //exec.execute(new XLL(e1, e1.getX(), e1.getY()));
        exec.execute(new XZJ(xzj.getX(), xzj.getY()));
//        exec.execute(new Thread(new Ye()));
//        exec.execute(new Thread(new SJ()));
        show_it();
    }

    public void show_it() {
        gc.drawImage(background, 0, 0);
        for (int i = 0; i < 7; i++){
            if (bros[i].isAlive())
                gc.drawImage(bros[i].getImage(i), bros[i].getX(), bros[i].getY());//
        }
//        if (bro1.isAlive())
//            gc.drawImage(bro1.getImage(1), bro1.getX(), bro1.getY());//
        for (int i = 0; i < 3; i++) {
            if (followers[i].isAlive())
                gc.drawImage(followers[i].getImage(), followers[i].getX(), followers[i].getY());
            else
                gc.drawImage(dead, followers[i].getX(), followers[i].getY());
        }
        if (xzj.isAlive())
            gc.drawImage(xzj.getImage(), xzj.getX(), xzj.getY());
        else
            gc.drawImage(dead, xzj.getX(), xzj.getY());
        //if (e1.isAlive())
        //    gc.drawImage(e1.getImage(), e1.getX(), e1.getY());
        //else
        //    gc.drawImage(dead, e1.getX(), e1.getY());
        if (yeye.isAlive())
            gc.drawImage(yeye.getImage(), yeye.getX(), yeye.getY());
        if (snake.isAlive())
            gc.drawImage(snake.getImage(), snake.getX(), snake.getY());
    }

    boolean ifAllDie() {
        if (xzj.isAlive()) {//蝎子精
            return false;
        } else {
            for (int i = 0; i < followers.length; i++) {
                if (followers[i].isAlive())
                    return false;
            }
        }
        return true;
    }
    public void change() {
//        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
////                //实时获得位置后，作为creature的属性对打斗过程进行设置

                if (input.contains("SPACE")) {
                    try_it();

                }
                if (ifAllDie()) {//
                    //System.out.print("?????");
                    stop();

                }
            }

        }.start();
//
    }

    public ArrayList<String> getInput() {
        return input;
    }

    public void update(long now) {
    }

    public class Hulu implements Runnable {
        private double px;
        private double py;
        private int ix;
        private int iy;
        private int index;
        private HuLuBro bro;

        public Hulu(HuLuBro bro, double x, double y) {
            this.bro = bro;
            px = x;
            py = y;
            ix = (int) (x - 250) / 80;
            iy = (int) (y - 100) / 100;
            place[ix][iy] = true;
            lives[ix][iy] = "hulu";
        }

        public void move() {

            if (place[ix + 1][iy]) {
                if (lives[ix + 1][iy].equals("e"))//
                    fight1(ix,iy,ix+1,iy);//
                if(lives[ix + 1][iy].equals("x"))
                    fight2(ix,iy,ix+1,iy);
            } else {
                System.out.print("move");
                place[ix][iy] = false;
                lives[ix][iy] = "";
                ix++;
                place[ix][iy] = true;
                lives[ix][iy] = "hulu";
                px += 80;//
                this.bro.setX(px);
                this.bro.setY(py);
            }
        }

        public void run() {
            move();
            Thread.yield();
        }
    }

    //葫芦娃和小喽啰打架
    public void fight1(int x1,int y1,int x2,int y2) {
        System.out.print("fight1");
        //先默认葫芦娃肯定能赢1
        //小喽啰输了
        lives[x2][y2]="";
        place[x2][y2]=false;
        System.out.print("e dead");

        //葫芦娃输了
        //哪个葫芦娃
    }

    //葫芦娃和蝎子精打架
    public void fight2(int x1,int y1,int x2,int y2) {

        //蝎子精输了
        //只有一只蝎子精
        xzj.setAlive(false);
        lives[x2][y2] = "";
        place[x2][y2] = false;
        System.out.print("xzj dead");

        //葫芦娃输了
        //哪个葫芦娃

    }

    //小喽啰和葫芦娃打架
    public void fight3(int x1,int y1,int x2,int y2,int i) {
        System.out.print("fight1");
        //先默认葫芦娃肯定能赢1

        //小喽啰输了
        lives[x2][y2]="";
        place[x2][y2]=false;
        //是哪个小喽啰
        followers[i].setAlive(false);
        System.out.print("e dead");
    }

    public void talk() {
        System.out.print("talk");
    }

    public class XLL implements Runnable {
        private double px;
        private double py;
        private int ix;
        private int iy;
        private Followers xll;
        private int index;

        public XLL(Followers e1,double x, double y,int i) {
            xll=e1;
            index=i;
            px = x;
            py = y;
            ix = (int) (x - 250) / 80;
            iy = (int) (y - 100) / 100;
            place[ix][iy] = true;
            lives[ix][iy] = "e";//bad
        }

        public void move() {
            if (place[ix - 1][iy]) {
                if (lives[ix - 1][iy].equals("hulu"))//good
                    fight3(ix-1, iy, ix, iy,index);
            } else {
                place[ix][iy] = false;
                lives[ix][iy] = "";//none
                ix--;
                place[ix][iy] = true;
                lives[ix][iy] = "e";//bad
                px -= 80;
                xll.setX(px);
                xll.setY(py);
            }
        }

        public void run() {
            //gc.drawImage(e1.getImage(), 970, 100);
            move();
            Thread.yield();
        }
    }


    public class XZJ implements Runnable {
        private double px;
        private double py;
        private int ix;
        private int iy;

        public XZJ(double x, double y) {
            px = x;
            py = y;
            ix = (int) (x - 250) / 80;
            iy = (int) (y - 100) / 100;
            place[ix][iy] = true;
            lives[ix][iy] = "x";//bad
        }

        public void move() {
            if (place[ix - 1][iy]) {
                if (lives[ix - 1][iy].equals("hulu"))//good
                    fight2(ix-1, iy, ix, iy);
            } else {
                place[ix][iy] = false;
                lives[ix][iy] = "";//none
                ix--;
                place[ix][iy] = true;
                lives[ix][iy] = "x";//bad
                px -= 80;
                xzj.setX(px);
                xzj.setY(py);
            }

        }

        public void run() {
            move();
            Thread.yield();

        }
    }

    public class Ye implements Runnable {
        public void run() {
            gc.drawImage(yeye.getImage(), 0, 450);
            Thread.yield();
        }
    }

    public class SJ implements Runnable {
        public void run() {
            gc.drawImage(snake.getImage(), 1200, 450);
            Thread.yield();
        }
    }

}
