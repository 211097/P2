package com.example.portofshipmen;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class GameMain extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setMainMenuEnabled(true);
        settings.setWidth(768);
        settings.setHeight(432);
        settings.setTitle("Port of Shipmen");
        settings.setVersion("0.1");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initGame(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            AnchorPane root = loader.load();
            FXGL.getGameScene().addUINode(root);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        getGameScene().setBackgroundRepeat("bg1.png");
    }
}