package br.com.autocare;

import br.com.autocare.model.Usuario;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
        Label titulo = new Label("Usuário::Cadastrar");
        titulo.setFont(Font.font("Inter", FontWeight.MEDIUM, 20));
        titulo.setAlignment(Pos.TOP_LEFT);

        HBox content = new HBox();
        content.getChildren().add(titulo);

//        Usuario u1 = new Usuario();
//        u1.setNome("Eduardo");
//        u1.setSobrenome("Lourenço da Silva");
//        u1.setTelefone("13991538145");
//        u1.setEmail("eduardo.lourenco2310@gmail.com");
//        u1.setSenha("@Teste123");
//
//        u1.save();

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