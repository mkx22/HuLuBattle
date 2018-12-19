package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("HuLu Battle");

        //BorderPane root = new BorderPane();
        Group root=new Group();

        GamePanel gamePanel=new GamePanel();
        //scene大小设置
        Scene scene = new Scene(gamePanel, 1320, 740, Color.WHITE);
        //加载人物，（在空格键上）加入点击事件
        gamePanel.load();
        //敲击空格，变化阵型
        gamePanel.change();
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
