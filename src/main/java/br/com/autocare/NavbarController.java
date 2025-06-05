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
        Menu menu = new Menu("Escolher classe");

        MenuItem mItemUsuario = new MenuItem("Usuário");
        mItemUsuario.setOnAction(e -> {
            this.layout.setRota("usuario");
        });
        menu.getItems().add(mItemUsuario);

        MenuItem mItemExemplo = new MenuItem("Exemplo");
        mItemExemplo.setOnAction(e -> {
            this.layout.setRota("exemplo");
        });
        menu.getItems().add(mItemExemplo);
        MenuItem  mItemfuncionariomapa= new MenuItem("Funcionariomapa");
        mItemfuncionariomapa.setOnAction(e -> {
            this.layout.setRota("funcionariomapa");
        });
        menu.getItems().add(mItemfuncionariomapa);

        MenuItem  mItemPrestador= new MenuItem("Prestador");
        mItemPrestador.setOnAction(e -> {
            this.layout.setRota("prestador");
        });
        menu.getItems().add(mItemPrestador);
        navbar.getMenus().addAll(menu);
    }

    public Node getNavBarNode() {
        return new VBox(this.navbar);
    }
}
