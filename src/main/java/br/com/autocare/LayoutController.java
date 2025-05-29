package br.com.autocare;

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

        Scene scene = new Scene(this.container, 480, 480);
        this.stage.setScene(scene);

        this.stage.show();

         this.setRota("usuario-listar");
    }

    public void setRota(String rota) {
        this.rota = rota;

        switch (rota) {
            case "usuario-listar" -> this.container.setCenter((new UsuarioController()).getListar());
            case "usuario-cadastrar" -> this.container.setCenter((new UsuarioController()).getCadastrar());
            case "usuario-atualizar" -> this.container.setCenter((new UsuarioController()).getAtualizar());
            case "usuario-deletar" -> this.container.setCenter((new UsuarioController()).getDeletar());
        }
    }
}
