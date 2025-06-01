package br.com.autocare;

import br.com.autocare.usuario.UsuarioController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LayoutController {
    private Stage stage;
    private String rota;
    private BorderPane container;

    public LayoutController(Stage stage, String rota) {
        this.stage = stage;
        this.rota = rota;
        this.container = new BorderPane();
    }

     public void show() {
        this.stage.setTitle("Autocare");

        Node navbar = (new NavbarController(this)).getNavBarNode();
        this.container.setTop(navbar);

        Scene scene = new Scene(this.container, 1080, 600);
        this.stage.setScene(scene);

        this.stage.show();

         this.setRota("usuario");
    }

    public void setRota(String rota) {
        this.rota = rota;

        switch (rota) {
            case "usuario" -> this.container.setCenter((new UsuarioController()).show());
        }
    }
}
