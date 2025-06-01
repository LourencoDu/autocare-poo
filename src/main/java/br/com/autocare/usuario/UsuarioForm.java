package br.com.autocare.usuario;

import br.com.autocare.components.FormControl;
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
    private UsuarioTable table;
    private VBox form;

    private Usuario usuario;

    private final FormControl idFC;
    private final Node idInput;
    private final FormControl nomeFC;
    private final FormControl sobrenomeFC;
    private final FormControl telefoneFC;
    private final FormControl emailFC;
    private final FormControl senhaFC;

    private final Label validacaoLabel;

    private final Button submitBtn;


    public UsuarioForm() {
        this.idFC = new FormControl("ID");
        this.idInput = this.idFC.getInput();
        this.idInput.setVisible(false);
        this.idInput.setManaged(false);

        this.nomeFC = new FormControl("Nome");
        this.sobrenomeFC = new FormControl("Sobrenome");
        this.telefoneFC = new FormControl("Telefone");
        this.emailFC = new FormControl("E-mail");
        this.senhaFC = new FormControl("Senha");

        this.validacaoLabel = new Label("");
        validacaoLabel.setFont(Font.font("Inter", FontWeight.MEDIUM, 14));
        validacaoLabel.setTextFill(Color.RED);

        this.submitBtn = new Button("Salvar");
    }

    public void setTable(UsuarioTable table) {
        this.table = table;
    }

    private void setValidacaoLabel(String texto, Color cor) {
        validacaoLabel.setText(texto);
        validacaoLabel.setTextFill(cor);
    }

    private void clearFormValues() {
        idFC.setValue("");
        nomeFC.setValue("");
        sobrenomeFC.setValue("");
        telefoneFC.setValue("");
        emailFC.setValue("");
        senhaFC.setValue("");
        setUsuario(null);
    }

    private void createForm() {
        Label titulo = new Label("Formulário");
        titulo.setFont(Font.font("Inter", FontWeight.BOLD, 16));

        VBox content = new VBox(10);
        content.setMinWidth(256);
        content.setMaxWidth(256);
        content.setPadding(new Insets(5, 15, 15, 15));

        Node nomeInput = nomeFC.getInput();
        Node sobrenomeInput = sobrenomeFC.getInput();
        Node telefoneInput = telefoneFC.getInput();
        Node emailInput = emailFC.getInput();
        Node senhaInput = senhaFC.getPasswordInput();

        setValidacaoLabel("", Color.RED);

        this.submitBtn.setOnAction(e -> {
            String nome = nomeFC.getValue();
            String sobrenome = sobrenomeFC.getValue();
            String telefone = telefoneFC.getValue();
            String email = emailFC.getValue();
            String senha = senhaFC.getValue();

            if(nome != "" && sobrenome  != "" && telefone != "" && email != "" && senha != "") {
                setValidacaoLabel("", Color.RED);
                try {
                    Usuario usuario = this.usuario != null ? this.usuario : new Usuario();
                    usuario.setNome(nome);
                    usuario.setSobrenome(sobrenome);
                    usuario.setTelefone(telefone);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);

                    usuario.save();

                    if(this.usuario != null) {
                        setValidacaoLabel("Usuário atualizado com sucesso!", Color.GREEN);
                    } else {
                        setValidacaoLabel("Usuário cadastrado com sucesso!", Color.GREEN);
                    }

                    clearFormValues();

                    if(this.table != null) {
                        this.table.updateDados();
                    }
                } catch (Exception ex) {
                    if(this.usuario != null) {
                        setValidacaoLabel("Falha ao atualizar usuário!", Color.RED);
                    } else {
                        setValidacaoLabel("Falha ao cadastrar usuário!", Color.RED);
                    }
                }
            } else {
                setValidacaoLabel("Preencha todos os campos!", Color.RED);
            }
        });

        HBox buttons = new HBox(submitBtn);

        content.getChildren().add(titulo);

        content.getChildren().addAll(idInput, nomeInput, sobrenomeInput, telefoneInput, emailInput, senhaInput, validacaoLabel, buttons);

        this.form = content;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

        if(this.usuario != null) {
            idFC.setValue(this.usuario.getId());
            idInput.setVisible(true);
            idInput.setManaged(true);

            nomeFC.setValue(this.usuario.getNome());
            sobrenomeFC.setValue(this.usuario.getSobrenome());
            telefoneFC.setValue(this.usuario.getTelefone());
            emailFC.setValue(this.usuario.getEmail());
            senhaFC.setValue(this.usuario.getSenha());
        } else {
            idInput.setVisible(false);
            idInput.setManaged(false);
        }
    }

    public Node getForm() {
        if(this.form == null) {
            createForm();
        }

        return this.form;
    }
}
