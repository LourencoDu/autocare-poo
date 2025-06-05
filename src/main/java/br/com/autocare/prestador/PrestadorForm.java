package br.com.autocare.prestador;

import br.com.autocare.components.FormControl;
import br.com.autocare.model.Prestador;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PrestadorForm {
    private PrestadorTable table;
    private VBox form;

    private Prestador prestador;

    private final FormControl idFC;
    private final Node idInput;
    private final FormControl nomeOficinaFC;
    private final FormControl cepFC;
    private final FormControl emailFC;
    private final FormControl senhaFC;

    private final Label validacaoLabel;

    private final Button submitBtn;


    public PrestadorForm() {
        this.idFC = new FormControl("ID");
        this.idInput = this.idFC.getInput();
        this.idInput.setVisible(false);
        this.idInput.setManaged(false);
        this.idInput.setDisable(true);

        this.nomeOficinaFC = new FormControl("Nome da Oficina");
        this.cepFC = new FormControl("CEP");
        this.emailFC = new FormControl("E-mail");
        this.senhaFC = new FormControl("Senha");

        this.validacaoLabel = new Label("");
        validacaoLabel.setFont(Font.font("Inter", FontWeight.MEDIUM, 14));
        validacaoLabel.setTextFill(Color.RED);

        this.submitBtn = new Button("Salvar");
    }

    public void setTable(PrestadorTable table) {
        this.table = table;
    }

    private void setValidacaoLabel(String texto, Color cor) {
        validacaoLabel.setText(texto);
        validacaoLabel.setTextFill(cor);
    }

    private void clearFormValues() {
        idFC.setValue("");
        nomeOficinaFC.setValue("");
        cepFC.setValue("");
        emailFC.setValue("");
        senhaFC.setValue("");
        setPrestador(null);
    }

    private void createForm() {
        Label titulo = new Label("Formulário");
        titulo.setFont(Font.font("Inter", FontWeight.BOLD, 16));

        VBox content = new VBox(10);
        content.setMinWidth(256);
        content.setMaxWidth(256);
        content.setPadding(new Insets(5, 15, 15, 15));

        Node nomeOficinaInput = nomeOficinaFC.getInput();
        Node cepInput = cepFC.getInput();
        Node emailInput = emailFC.getInput();
        Node senhaInput = senhaFC.getPasswordInput();

        setValidacaoLabel("", Color.RED);

        this.submitBtn.setOnAction(e -> {
            String nomeOficina = nomeOficinaFC.getValue();
            String cep = cepFC.getValue();
            String email = emailFC.getValue();
            String senha = senhaFC.getValue();

            if(nomeOficina != "" && cep  != "" && email != "" && senha != "") {
                setValidacaoLabel("", Color.RED);
                try {
                    Prestador prestador = this.prestador != null ? this.prestador : new Prestador();
                    prestador.setNomeOficina(nomeOficina);
                    prestador.setCep(cep);
                    prestador.setEmail(email);
                    prestador.setSenha(senha);

                    prestador.save();

                    if(this.prestador != null) {
                        setValidacaoLabel("Prestador atualizado com sucesso!", Color.GREEN);
                    } else {
                        setValidacaoLabel("Prestador cadastrado com sucesso!", Color.GREEN);
                    }

                    clearFormValues();

                    if(this.table != null) {
                        this.table.updateDados();
                    }
                } catch (Exception ex) {
                    if(this.prestador != null) {
                        setValidacaoLabel("Falha ao atualizar Prestador!", Color.RED);
                    } else {
                        setValidacaoLabel("Falha ao cadastrar Prestador!", Color.RED);
                    }
                }
            } else {
                setValidacaoLabel("Preencha todos os campos!", Color.RED);
            }
        });

        HBox buttons = new HBox(submitBtn);

        content.getChildren().add(titulo);

        content.getChildren().addAll(idInput, nomeOficinaInput, cepInput, emailInput, senhaInput, validacaoLabel, buttons);

        this.form = content;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;

        if(this.prestador != null) {
            idFC.setValue(this.prestador.getId());
            idInput.setVisible(true);
            idInput.setManaged(true);

            nomeOficinaFC.setValue(this.prestador.getNomeOficina());
            cepFC.setValue(this.prestador.getCep());
            emailFC.setValue(this.prestador.getEmail());
            senhaFC.setValue(this.prestador.getSenha());
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
