package br.com.autocare;

import br.com.autocare.components.control.FormControl;
import br.com.autocare.model.Usuario;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UsuarioController {
    public Node getListar() {
        Label titulo = new Label("Usuário::Listar");
        titulo.setFont(Font.font("Inter", FontWeight.MEDIUM, 20));
        titulo.setAlignment(Pos.TOP_LEFT);

        HBox content = new HBox();
        content.getChildren().add(titulo);

        return content;
    }

    public Node getCadastrar() {
        Label titulo = new Label("Cadastro de Usuário");
        titulo.setFont(Font.font("Inter", FontWeight.BOLD, 14));

        VBox content = new VBox(15);
        content.setPadding(new Insets(5, 15, 5, 15));
        content.getChildren().add(titulo);

        FormControl nomeFC = new FormControl("Nome");
        FormControl sobrenomeFC = new FormControl("Sobrenome");
        FormControl telefoneFC = new FormControl("Telefone");
        FormControl emailFC = new FormControl("Email");
        FormControl senhaFC = new FormControl("Senha");

        Button btnSubmit = new Button("Cadastrar");
        btnSubmit.setOnAction(e -> {
            String nome = nomeFC.getValue();
            String sobrenome = sobrenomeFC.getValue();
            String telefone = telefoneFC.getValue();
            String email = emailFC.getValue();
            String senha = senhaFC.getValue();

            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setSobrenome(sobrenome);
            usuario.setTelefone(telefone);
            usuario.setEmail(email);
            usuario.setSenha(senha);

            usuario.save();
        });

        content.getChildren().addAll(nomeFC.getInput(), sobrenomeFC.getInput(), telefoneFC.getInput(), emailFC.getInput(), senhaFC.getInput(), btnSubmit);

        return content;
    }

    public Node getAtualizar() {
        Label titulo = new Label("Usuário::Atualizar");
        titulo.setFont(Font.font("Inter", FontWeight.MEDIUM, 20));
        titulo.setAlignment(Pos.TOP_LEFT);

        HBox content = new HBox();
        content.getChildren().add(titulo);

        return content;
    }

    public Node getDeletar(){
        Label titulo = new Label("Usuário::Deletar");
        titulo.setFont(Font.font("Inter", FontWeight.MEDIUM, 20));
        titulo.setAlignment(Pos.TOP_LEFT);

        HBox content = new HBox();
        content.getChildren().add(titulo);

        return content;
    }
}