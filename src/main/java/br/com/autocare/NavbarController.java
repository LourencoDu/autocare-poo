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
        menuUsuario.setOnAction(e -> {
            this.layout.setRota("usuario");
        });

        navbar.getMenus().addAll(menuUsuario);
    }

    public Node getNavBarNode() {
        return new VBox(this.navbar);
    }
}
