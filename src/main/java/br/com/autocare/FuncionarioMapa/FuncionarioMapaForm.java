package br.com.autocare.FuncionarioMapa;

import br.com.autocare.components.FormControl;
import br.com.autocare.model.FuncionarioMapa;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FuncionarioMapaForm {
    private FuncionarioMapaTable table;
    private VBox form;

    private FuncionarioMapa funcionario;

    private final FormControl idFC;
    private final Node idInput;
    private final FormControl nomeFC;
    private final FormControl sobrenomeFC;
    private final FormControl telefoneFC;
    private final FormControl emailFC;
    private final FormControl senhaFC;

    private final Label validacaoLabel;
    private final Button submitBtn;

    public FuncionarioMapaForm() {
        this.idFC = new FormControl("ID");
        this.idInput = this.idFC.getInput();
        this.idInput.setVisible(false);
        this.idInput.setManaged(false);
        this.idInput.setDisable(true);

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

    public void setTable(FuncionarioMapaTable table) {
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
        setFuncionario(null);
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

            if (!nome.isEmpty() && !sobrenome.isEmpty() && !telefone.isEmpty() && !email.isEmpty() && !senha.isEmpty()) {
                setValidacaoLabel("", Color.RED);
                try {
                    FuncionarioMapa funcionario = this.funcionario != null ? this.funcionario : new FuncionarioMapa();
                    funcionario.setNome(nome);
                    funcionario.setTelefone(telefone);
                    funcionario.setEmail(email);
                    funcionario.setSenha(senha);

                    funcionario.save();

                    if (this.funcionario != null) {
                        setValidacaoLabel("Funcionário atualizado com sucesso!", Color.GREEN);
                    } else {
                        setValidacaoLabel("Funcionário cadastrado com sucesso!", Color.GREEN);
                    }

                    clearFormValues();

                    if (this.table != null) {
                        this.table.updateDados();
                    }
                } catch (Exception ex) {
                    if (this.funcionario != null) {
                        setValidacaoLabel("Falha ao atualizar funcionário!", Color.RED);
                    } else {
                        setValidacaoLabel("Falha ao cadastrar funcionário!", Color.RED);
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

    public void setFuncionario(FuncionarioMapa funcionario) {
        this.funcionario = funcionario;

        if (this.funcionario != null) {
            idFC.setValue(this.funcionario.getId());
            idInput.setVisible(true);
            idInput.setManaged(true);

            nomeFC.setValue(this.funcionario.getNome());
            telefoneFC.setValue(this.funcionario.getTelefone());
            emailFC.setValue(this.funcionario.getEmail());
            senhaFC.setValue(this.funcionario.getSenha());
        } else {
            idInput.setVisible(false);
            idInput.setManaged(false);
        }
    }

    public Node getForm() {
        if (this.form == null) {
            createForm();
        }
        return this.form;
    }
}
