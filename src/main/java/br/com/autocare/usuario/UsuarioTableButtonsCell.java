package br.com.autocare.usuario;

import br.com.autocare.components.DeleteConfimAlert;
import br.com.autocare.model.Usuario;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

public class UsuarioTableButtonsCell extends TableCell<Usuario, Void> {
    private final Button btnEditar = criarBotao("Editar", this::editarUsuario);
    private final Button btnExcluir = criarBotao("Excluir", this::excluirUsuario);
    private final HBox box = new HBox(5, btnEditar, btnExcluir);

    private final UsuarioForm form;
    private final UsuarioTable table;

    public UsuarioTableButtonsCell(UsuarioForm form, UsuarioTable table) {
        this.form = form;
        this.table = table;
        this.box.setAlignment(Pos.CENTER);
    }

    private Button criarBotao(String texto, Runnable acao) {
        Button botao = new Button(texto);
        botao.setOnAction(e -> acao.run());
        return botao;
    }

    private Usuario getUsuarioAtual() {
        return getTableView().getItems().get(getIndex());
    }

    private void editarUsuario() {
        if (form != null) {
            form.setUsuario(getUsuarioAtual());
        }
    }

    private void excluirUsuario() {
        if (DeleteConfimAlert.deleteConfirm()) {
            getUsuarioAtual().delete();
            table.updateDados();

            if (form != null) {
                form.setUsuario(null);
            }
        }
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(empty ? null : box);
    }
}
