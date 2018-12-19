HuLuBattle
===========
JavaFX
==========
submodule使用
==
人物（葫芦娃、爷爷、蛇精、蝎子精小喽啰）
=
Creature.java
=
一个抽象类，作为所有人物的基类，暂时仅记录人物的坐标属性（共有属性）。
Enemy.java
=
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
* ##Scorption.java
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
* ##Followers.java
 * 和Scorption一样作为Enemy的派生类，需要调用基类构造器进行构造，对小喽啰的图像大小属性进行设置。

```javascript
public class Followers extends Enemy {
    public Followers(){
        super(new Image("file:enemy.jpg",60,60,false,false));
    }

}
```

##HuLuBro.java
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

##Cheer.java
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
* ##Snake.java
表示蛇精这一对象，相关操作（初始化、返回图像）继承自父类Cheer；

```javascript
public class Snake extends Cheer {
    public Snake(){
        super(new Image("file:snake.png", 120, 180, false, false));
    }
}
```
* ##YeYe.java
表示老爷爷这一对象，相关操作（初始化、返回图像）继承自父类Cheer；

```javascript
public class YeYe extends Cheer{
//    private Image[] yeyeArray = new Image[2];
//
//    //爷爷 图像需要大一点
//    private Image yeye1 = new Image("file:yeye.png", 130, 180, false, false);
//    private Image yeye2 = new Image("file:yeye2.png", 130, 180, false, false);

    public YeYe(){
        super(new Image("file:yeye.png", 130, 180, false, false));
    }

}
```

