package br.com.autocare;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import br.com.autocare.components.control.FormControl;
import br.com.autocare.model.Usuario;
import br.com.autocare.model.Model;

import java.util.ArrayList;

public class UsuarioControllerOld {
    private final LayoutController layout;

    public UsuarioControllerOld(LayoutController layout) {
        this.layout = layout;
    }

    public Node getListar() {
        Label titulo = new Label("Listagem de Usuários");
        titulo.setFont(Font.font("Inter", FontWeight.BOLD, 16));

        VBox content = new VBox(15);
        content.setPadding(new Insets(5, 15, 5, 15));

        Usuario usuario = new Usuario();
        ArrayList<Model> lista = usuario.select();

        TableView<Usuario> table = new TableView<>();

        TableColumn<Usuario, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Usuario, String> nomeColumn = new TableColumn<>("Nome");
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Usuario, String> sobrenomeColumn = new TableColumn<>("Sobrenome");
        sobrenomeColumn.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));

        TableColumn<Usuario, String> telefoneColumn = new TableColumn<>("Telefone");
        telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        TableColumn<Usuario, String> emailColumn = new TableColumn<>("E-mail");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Usuario, Void> acaoColumn = new TableColumn<>("Ações");

        acaoColumn.setCellFactory(coluna -> new TableCell<>() {
            private final Button btnEditar = new Button("Editar");
            private final Button btnExcluir = new Button("Excluir");
            private final HBox box = new HBox(5, btnEditar, btnExcluir);

            {
                btnEditar.setOnAction(event -> {
                    Usuario usuario = getTableView().getItems().get(getIndex());

                });

                btnExcluir.setOnAction(event -> {
                    Usuario usuario = getTableView().getItems().get(getIndex());
                    System.out.println("Excluir: " + usuario.getNome());
                    // Aqui você pode remover o item da lista e atualizar a TableView
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);  // limpa a célula
                } else {
                    setGraphic(box);   // adiciona o HBox com os botões
                }
            }
        });

        table.getColumns().addAll(idColumn, nomeColumn, sobrenomeColumn, telefoneColumn, emailColumn, acaoColumn);

        // Convertendo a lista para ObservableList
        ObservableList<Usuario> dados = FXCollections.observableArrayList();
        for (Model item : lista) {
            dados.add((Usuario) item);
        }

        table.setItems(dados);

        content.getChildren().addAll(titulo, table);

        return content;
    }


    public Node getCadastrar() {
        Label titulo = new Label("Cadastro de Usuário");
        titulo.setFont(Font.font("Inter", FontWeight.BOLD, 16));

        VBox content = new VBox(15);
        content.setPadding(new Insets(5, 15, 5, 15));
        content.getChildren().add(titulo);

        FormControl nomeFC = new FormControl("Nome");
        FormControl sobrenomeFC = new FormControl("Sobrenome");
        FormControl telefoneFC = new FormControl("Telefone");
        FormControl emailFC = new FormControl("Email");
        FormControl senhaFC = new FormControl("Senha");

        Label validacao = new Label("");
        validacao.setFont(Font.font("Inter", FontWeight.MEDIUM, 14));
        validacao.setTextFill(Color.RED);

        Button btnSubmit = new Button("Cadastrar");
        btnSubmit.setOnAction(e -> {
            String nome = nomeFC.getValue();
            String sobrenome = sobrenomeFC.getValue();
            String telefone = telefoneFC.getValue();
            String email = emailFC.getValue();
            String senha = senhaFC.getValue();

            if(nome != "" && sobrenome  != "" && telefone != "" && email != "" && senha != "") {
                validacao.setText("");
                try {
                    Usuario usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setSobrenome(sobrenome);
                    usuario.setTelefone(telefone);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);

                    usuario.save();

                    nomeFC.setValue("");
                    sobrenomeFC.setValue("");
                    telefoneFC.setValue("");
                    emailFC.setValue("");
                    senhaFC.setValue("");

                    validacao.setText("Usuário cadastrado com sucesso!");
                    validacao.setTextFill(Color.GREEN);
                } catch (Exception ex) {
                    validacao.setText("Falha ao cadastrar usuário!");
                    validacao.setTextFill(Color.RED);
                }
            } else {
                validacao.setText("Preencha todos os campos!");
                validacao.setTextFill(Color.RED);
            }
        });

        HBox buttons = new HBox(btnSubmit);

        content.getChildren().addAll(nomeFC.getInput(), sobrenomeFC.getInput(), telefoneFC.getInput(), emailFC.getInput(), senhaFC.getPasswordInput(), validacao, buttons);

        return content;
    }

    public Node getAtualizar(String id) {
        Label titulo = new Label("Usuário::Atualizar");
        titulo.setFont(Font.font("Inter", FontWeight.MEDIUM, 20));
        titulo.setAlignment(Pos.TOP_LEFT);

        HBox content = new HBox();
        content.getChildren().add(titulo);

        return content;
    }

    public Node getDeletar(String id){
        Label titulo = new Label("Usuário::Deletar");
        titulo.setFont(Font.font("Inter", FontWeight.MEDIUM, 20));
        titulo.setAlignment(Pos.TOP_LEFT);

        HBox content = new HBox();
        content.getChildren().add(titulo);

        return content;
    }
}
