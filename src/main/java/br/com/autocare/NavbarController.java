package br.com.autocare;

import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class NavbarController {
    private MenuBar navbar;
    private LayoutController layout;

    public NavbarController(LayoutController layout) {
        this.navbar = new MenuBar();
        this.setMenuUsuario();
        this.layout = layout;
    }

    private void setMenuUsuario() {
        Menu menuUsuario = new Menu("Usuário");

        MenuItem itemListar = new MenuItem("Listar");
        MenuItem itemCadastrar = new MenuItem("Cadastrar");
        MenuItem itemAtualizar = new MenuItem("Atualizar");
        MenuItem itemDeletar = new MenuItem("Deletar");

        itemListar.setOnAction(e -> {
            this.layout.setRota("usuario-listar");
        });

        itemCadastrar.setOnAction(e -> {
            this.layout.setRota("usuario-cadastrar");
        });

        itemAtualizar.setOnAction(e -> {
            this.layout.setRota("usuario-atualizar");
        });

        itemDeletar.setOnAction(e -> {
            this.layout.setRota("usuario-deletar");
        });

        menuUsuario.getItems().addAll(itemListar, itemCadastrar, itemAtualizar, itemDeletar);

        navbar.getMenus().addAll(menuUsuario);
    }

    public Node getNavBarNode() {
        return new VBox(this.navbar);
    }
}
