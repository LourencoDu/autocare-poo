package br.com.autocare;

//import br.com.autocare.FuncionarioMapa.FuncionarioMapaController;
//import br.com.autocare.model.FuncionarioMapa;
import br.com.autocare.prestador.PrestadorController;
import br.com.autocare.usuario.UsuarioController;
//import br.com.autocare.veiculo.VeiculoController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

         this.setRota(this.rota);
    }

    public void setRota(String rota) {
        this.rota = rota;

        switch (rota) {
            case "usuario" -> this.container.setCenter((new UsuarioController()).show());
            //case "funcionariomapa" -> this.container.setCenter((new FuncionarioMapaController()).show());
            case "prestador" -> this.container.setCenter((new PrestadorController()).show());
            //case "veiculo" -> this.container.setCenter((new VeiculoController()).show());
            case "exemplo" -> this.container.setCenter(new Label("Exemplo"));
        }
    }
}
