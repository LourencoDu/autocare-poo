package br.com.autocare.usuario;

import br.com.autocare.components.control.FormControl;
import br.com.autocare.model.Usuario;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UsuarioForm {
    public Node getForm() {
        Label titulo = new Label("Formulário");
        titulo.setFont(Font.font("Inter", FontWeight.BOLD, 16));

        VBox content = new VBox(10);
        content.setMinWidth(256);
        content.setMaxWidth(256);
        content.setPadding(new Insets(5, 15, 15, 15));

        FormControl nomeFC = new FormControl("Nome");
        Node nomeInput = nomeFC.getInput();

        FormControl sobrenomeFC = new FormControl("Sobrenome");
        Node sobrenomeInput = sobrenomeFC.getInput();

        FormControl telefoneFC = new FormControl("Telefone");
        Node telefoneInput = telefoneFC.getInput();

        FormControl emailFC = new FormControl("E-mail");
        Node emailInput = emailFC.getInput();

        FormControl senhaFC = new FormControl("Senha");
        Node senhaInput = senhaFC.getPasswordInput();

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

        content.getChildren().addAll(titulo, nomeInput, sobrenomeInput, telefoneInput, emailInput, senhaInput, validacao, buttons);
        return content;
    }
}
