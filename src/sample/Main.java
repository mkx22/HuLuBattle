package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.io.File;
import java.util.ArrayList;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("HuLu Battle");

        //BorderPane root = new BorderPane();
        Group root=new Group();

        GamePanel gamePanel=new GamePanel();
        //scene大小设置
        final Scene scene = new Scene(gamePanel, 1320, 740, Color.WHITE);
        //加载人物，（在空格键上）加入点击事件
        gamePanel.load();
//        gamePanel.clean();
        gamePanel.show_it();
        //敲击空格，变化阵型
        gamePanel.change(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void FileIO(Stage primaryStage){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(primaryStage);
        String path = file.getPath();


    }


}
