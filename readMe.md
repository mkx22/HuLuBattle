# HuLuBattle
* ### 基于JavaFX的图形实现
* ### submodule的使用
* ### 相关操作（点击事件、多线程、异常处理、文件IO等）
* ### 类的介绍
* ### 使用说明

## JavaFX
------
* 使用画布API进行图像的绘制
* javaFX中的画布API提供了自定义的纹理。画布API有两个主要的类，Canvas 和 GraphicsContext,定义在javafx.scene.canvas包下。我们可
以创建一个Canvas对象，然后获得它的GraphicsContext，渲染自定义的形状。因为Canvas是Node的子类，所以可以在场景图
中使用。
* 本来因为作业要求是mxn的网格所以想用gridpane绘制图像的，但是一开始不清楚如何将gridpane做出动画效果（网格与网格之间的移动），所以用了画布。
```javascript
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
```
* 绘制图像，以背景图为例
```javascript
    //图形界面
    //背景图
    Image background = new Image("file:back2.jpg");
    //dead
    Image dead = new Image("file:dead.png", 80, 90, false, false);
    //画布进行人物设置
    Canvas canvas = new Canvas(1320, 740);
    GraphicsContext gc = canvas.getGraphicsContext2D();
       
    public void load(){
       //......
       gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
       getChildren().add(canvas);
       gc.drawImage(background, 0, 0);
       //......   
    }
```
## submodule使用
-------------
### 提交
 * 在project（java/20180925）项目中引入子模块（HuLuBattle），并提交子模块信息
 * git submodule add ../.git mkx-161220091
 * git status  //多了两个需要提交的文件
 * git diff    //看到这两项的内容
 * git add .
 * git commit -m "add submodule"
 * git push origin master

### 更新
 * 经过pull request并且被merge后，更新只需要在自己子模块内更新，更新方法同之前作业
 * 否则
 * cd java/xxxx
 * git branch//
 * git add .
 * git commit -m "add b.txt"
 * git push origin master


## 相关操作（点击事件、多线程、异常处理、文件IO等）
------
### 点击事件（空格、L键）
------
关于空格，“L”键的相关点击事件

* 设置点击事件，setOnKeyPressed函数表示按下键盘按键时的操作，setOnKeyReleased函数表示松开键盘时的操作，但是因为作业要求，此处不对单独考虑松开键盘时的情况，而是在时间轴（AnimationTimer）函数中对input的内容进行设置。

```javascript
       
       //点击事件
        getScene().setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                //获取按键信息getCode
                String code = event.getCode().toString();

                if (!input.contains(code))
                    input.add(code);

            }
        });


```


空格，L键

* 按下空格时所有生物体执行start()函数，向敌方前进，按下L键时显示文件对话框，进行文件读写与回放，由于这些都是动画效果，所以需要在AnimationTimer函数中对其进行控制。 
* 同时，异常处理，文件的保存与读取（战斗回放），多线程等都在这里进行相关操作。

```javascript
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
```
* 按下L键选择文件进行战斗回放，这里涉及到文件的读取，需要对IOException做异常处理

```javascript
                    if (input.contains("L")) {
                    //打开文件对话框，进行文件选择
                    //......
                    //异常处理（文件IO）
                    try {
                        //读取文件
                        //......
                    } catch (IOException e) {
                        e.printStackTrace();
                    } 
                    //点击事件对input的操作
                    input.clear();
                }
```

* 按下空格键，葫芦娃、蝎子精和小喽啰开始战斗，使用多线程并行，将每个生物体实现为一个线程，实现多线程之间的并发处理。
* 同时，这里涉及到文件的保存，需要对IOException做异常处理。

```javascript
                if (input.contains("SPACE")) {
                    //多线程
                    thread_it();
                    //截屏，保存到文件中
                    pictureScreen();
                    //敌人都死了，
                    if (ifAllDie()) {
                        show_it();
                        input.clear();
                    }
                }
            }
        }.start();

```
### 多线程并行（葫芦娃、蝎子精、小喽啰等）
---------------------------------------

* 将葫芦娃，小喽啰蝎子精等生物各分配一个线程，对其行动进行相应的设置。
```javascript
    public void thread_it() {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 7; i++) {
            exec.execute(new Hulu(bros[i], bros[i].getX(), bros[i].getY(), i));
        }
        for (int i = 0; i < num; i++) {
            exec.execute(new XLL(followers[i], followers[i].getX(), followers[i].getY(), i));
        }
        exec.execute(new XZJ(xzj.getX(), xzj.getY()));
//        exec.execute(new Thread(new Ye()));
//        exec.execute(new Thread(new SJ()));

        show_it();//基于JavaFX框架的图形显示
    }
```
* 葫芦娃的线程描述如下，蝎子精和小喽啰与其大致相同，相关注释在代码中

```javascript
    //Hulu行为
    public class Hulu implements Runnable {
        //坐标变量
        //......
        public Hulu(HuLuBro bro, double x, double y, int i) {
            //初始化
        }

        public void move() {
            if (!this.bro.isAlive())//这只葫芦娃已经死了qaq
                return;
            if (!ifin(ix + 1, iy)) {//前方没有路了 换位
                reset(this.bro);
                //......
            } else {
                if (place[ix + 1][iy]) {//前方有活的生物
                    if (lives[ix + 1][iy].equals("e"))//小喽啰 攻击
                        fight1(ix, iy, ix + 1, iy, index);
                    else if (lives[ix + 1][iy].equals("x"))//蝎子精 攻击
                        fight2(ix, iy, ix + 1, iy, index);
                    else {//葫芦娃 不能打 换位
                        reset(this.bro)
                        //......
                    }
                } else {//前方没有活的生物
                    if (!lives[ix + 1][iy].equals("d")) {//什么都没有 放心走
                        //......
                    } else {//有墓碑不能踩 换位
                        reset(this.bro);
                        //......
                    }
                }
            }
        }

        public void run() {
            move();//葫芦娃该做的事情
            Thread.yield();//事情做完了，可以把cpu分给其他线程了
        }
    }
```

### 文件IO
----------
* 通过按下L键这一点击事件进行文件IO的操作，需要进行文件的保存及读取，因为多线程的并行具有一定的随机性，所以单纯对生物的起点与终点进行保存是不可取的，其读取的工作量也会加大。同时，在原来的实现中，生物的行动便是通过不同格子跳转，以及图像的刷新进行实现，所以我用截屏的方式对战斗过程进行保存，将截图的图片数量以及图片所在路径记录在相应文件夹下的result.txt文件中，通过读取该文件对战斗过程进行重现。
* 缺点：因为是截屏，所以是对整个电脑桌面的截屏，并不是单纯对战斗场景的截屏。
* 代码注释见下
```javascript
    int count=0;
    public void pictureScreen() {
        //截屏，按照屏幕大小截取图片
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle rectangle = new Rectangle(dimension);
        
        //按照日期命名文件夹，如2018-12-31，也可以命名为其他名称，这里只是考虑了每天的最终截屏结果
        Date date=new Date();
        //......       
        //若文件夹不存在，则新建文件夹
        
        //新建txt文件
        File f=new File(file.getName()+"\\result.txt");
        try {//异常处理
            //将截图存储在文件夹中
            Robot robot=new Robot();
            BufferedImage bi=robot.createScreenCapture(rectangle);
            //保存图片
            ImageIO.write(bi,"png",new File(file.getName()+"\\"+count+".png"));

            //将文件名存入文件中，保存txt文件
            FileWriter w=new FileWriter(f);
            //......
            count++;
        }catch(){//......
        //......
        }
    }
```

##类的介绍
-----------
## 生物（葫芦娃、爷爷、蛇精、蝎子精小喽啰）
------------------------------------

Creature.java
-------------
一个抽象类，作为所有人物的基类，暂时仅记录人物的坐标属性（共有属性）。

Enemy.java
----------
* 表示敌人，作为蝎子精（Scorption）和小喽啰（Followers）的基类，其派生类在各自构造函数中初始化（需要调用基类的构造器）。
* 其函数（如getImage()）可以被派生类（Scorption、Followers）调用，以获取蝎子精小喽啰的图像。

```javascript
//在这个世界里，坏蛋的数量根据阵型的设置不是固定的，所以不设成enum
//Enemy里面有小喽啰和蝎子精，因此该类作为子类被小喽啰和蝎子精的父类继承
public class Enemy extends Creature {

    private Image image;//属性

    public Enemy() {

    }

    public Enemy(Image image) {
        this.image = image;
    }

    public Image getImage(){
        return image;
    }

}

```
### Scorption.java
 * 作为Enemy派生类，具有其图像属性。需要用super调用基类构造器进行构造，对蝎子精图像大小属性进行设置。
 * 因为实例化派生类时，基类也会被实例化，如果不调用基类的构造器，基类将不会被实例化。

```javascript
//蝎子精
public class Scorpion extends Enemy{
    public Scorpion(){
        super(new Image("file:xzj1.png",60,60,false,false));
    }
}

```
### Followers.java
 * 和Scorption一样作为Enemy的派生类，需要调用基类构造器进行构造，对小喽啰的图像大小属性进行设置。

```javascript
public class Followers extends Enemy {
    public Followers(){
        super(new Image("file:enemy.jpg",60,60,false,false));
    }

}
```

HuLuBro.java
------------
* 因为葫芦娃的数量是固定的，所以使用枚举类；
* 在枚举类中添加构造方法，将葫芦娃的属性（图像、名称等）与葫芦娃这一对象进行绑定；

```javascript
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

```

* 在类中定义葫芦娃数组，添加方法返回其图像；

```javascript
    //定义数组（枚举）
    private Bro[] bro = {Bro.RED, Bro.YELLOW, Bro.GREEN, Bro.ORANGE, Bro.CYAN, Bro.BLUE, Bro.PURPLE};

    public HuLuBro(){

    }
    //返回图像
    public Image getImage(int i){
        return new Image(bro[i].url,80,80,false,false);
    }
```

Cheer.java
----------
* 表示加油方，爷爷和蛇精作为加油一方，在世界中坐标暂时不做改变，且被归为一类，以便之后其进行单独操作。
* 作为爷爷（YeYe）和蛇精（Snake）的基类，其派生类在各自构造函数中对其图像进行设置。
* 其函数（如getImage()）可以被派生类（YeYe、Snake）调用，以获取爷爷和蛇精的图像。

```javascript
public class Cheer extends Creature {
    //变换动作 Frame-Based Animation
    private Image image;

    public Cheer(){
    }

    public Cheer(Image image){
        this.image=image;
    }

    public Image getImage(){
        return image;
    }
}
```
### Snake.java
表示蛇精这一对象，相关操作（初始化、返回图像）继承自父类Cheer；

```javascript
public class Snake extends Cheer {
    public Snake(){
        super(new Image("file:snake.png", 120, 180, false, false));
    }
}
```
### YeYe.java
表示老爷爷这一对象，相关操作（初始化、返回图像）继承自父类Cheer；

```javascript
public class YeYe extends Cheer{
    public YeYe(){
        super(new Image("file:yeye.png", 130, 180, false, false));
    }
}
```
## Main.class
------------------------------------
程序入口class Main
```javascript
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    //注解
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("HuLu Battle");
        Group root=new Group();
        GamePanel gamePanel=new GamePanel();
        
        //scene大小设置
        final Scene scene = new Scene(gamePanel, 1320, 740, Color.WHITE);
        //加载人物，（在空格键上）加入点击事件
        gamePanel.load();
        //显示图像
        gamePanel.show_it();
        //敲击空格，变化阵型
        gamePanel.change(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
```
## GamePanel.java
------------------
实现了上面提到的各种操作，如文件IO、多线程、异常处理等。

## 使用说明以及注意事项
----------------
* 点击空格键，葫芦娃、蝎子精和小喽啰开始战斗，遇到己方不战斗，改变位置，遇到敌方与其战斗。
* 点击L键，需要按下大写字母控制键再点击L，否则不成功。点击L键后出现文件选择框，进入HuLuBattle下的日期格式（yyyy-MM-dd）文件夹，选择txt文件进行打开，之后会进行战斗过程的回放。
* L键可以在战斗前后使用，进行对战斗过程的读取。在本次实验中，战斗前点击L键，设定为读取之前存入的回放文件，在战斗后点击L键，会回放之前的战斗过程，因为文件夹是按天存储，所以此时存储的是之前的战斗过程，回放的也就会是之前的战斗过程而不是存入的回放文件。因为战斗过程大致相同，只是因为线程的随机性有些许误差，所以这种改变我认为是可以接受的。
* 代码中的异常处理和注解体现在各种函数中以及函数之前，并未做详细的解释说明。

                                                                       From
                                                                       161220091 马可欣
