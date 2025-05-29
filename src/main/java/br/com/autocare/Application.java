package br.com.autocare;

import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        LayoutController layout = new LayoutController(stage, "usuario");
        layout.show();
    }

    public static void main(String[] args) {
        launch();
    }
}