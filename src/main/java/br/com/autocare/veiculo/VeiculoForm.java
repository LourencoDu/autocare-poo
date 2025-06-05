package br.com.autocare.veiculo;

import br.com.autocare.components.FormControl;
import br.com.autocare.model.Veiculo;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VeiculoForm {
    private VeiculoTable table;
    private VBox form;

    private Veiculo veiculo;

    private final FormControl idFC;
    private final Node idInput;
    private final FormControl modeloFC;
    private final FormControl placaFC;
    private final FormControl corFC;;

    private final Label validacaoLabel;

    private final Button submitBtn;


    public VeiculoForm() {
        this.idFC = new FormControl("ID");
        this.idInput = this.idFC.getInput();
        this.idInput.setVisible(false);
        this.idInput.setManaged(false);
        this.idInput.setDisable(true);

        this.modeloFC = new FormControl("Modelo");
        this.placaFC = new FormControl("Placa");
        this.corFC = new FormControl("Cor");

        this.validacaoLabel = new Label("");
        validacaoLabel.setFont(Font.font("Inter", FontWeight.MEDIUM, 14));
        validacaoLabel.setTextFill(Color.RED);

        this.submitBtn = new Button("Salvar");
    }

    public void setTable(VeiculoTable table) {
        this.table = table;
    }

    private void setValidacaoLabel(String texto, Color cor) {
        validacaoLabel.setText(texto);
        validacaoLabel.setTextFill(cor);
    }

    private void clearFormValues() {
        idFC.setValue("");
        modeloFC.setValue("");
        placaFC.setValue("");
        corFC.setValue("");
        setVeiculo(null);
    }

    private void createForm() {
        Label titulo = new Label("Formulário");
        titulo.setFont(Font.font("Inter", FontWeight.BOLD, 16));

        VBox content = new VBox(10);
        content.setMinWidth(256);
        content.setMaxWidth(256);
        content.setPadding(new Insets(5, 15, 15, 15));

        Node modeloInput = modeloFC.getInput();
        Node placaInput = placaFC.getInput();
        Node corInput = corFC.getInput();;

        setValidacaoLabel("", Color.RED);

        this.submitBtn.setOnAction(e -> {
            String modelo = modeloFC.getValue();
            String placa = placaFC.getValue();
            String cor = corFC.getValue();

            if(modelo != "" && placa != "" && cor != "") {
                setValidacaoLabel("", Color.RED);
                try {
                    Veiculo veiculo = this.veiculo != null ? this.veiculo : new Veiculo();
                    veiculo.setModelo(modelo);
                    veiculo.setPlaca(placa);
                    veiculo.setCor(cor);

                    veiculo.save();

                    if(this.veiculo != null) {
                        setValidacaoLabel("Veiculo atualizado com sucesso!", Color.GREEN);
                    } else {
                        setValidacaoLabel("Veiculo cadastrado com sucesso!", Color.GREEN);
                    }

                    clearFormValues();

                    if(this.table != null) {
                        this.table.updateDados();
                    }
                } catch (Exception ex) {
                    if(this.veiculo != null) {
                        setValidacaoLabel("Falha ao atualizar Veiculo!", Color.RED);
                    } else {
                        setValidacaoLabel("Falha ao cadastrar Veiculo!", Color.RED);
                    }
                }
            } else {
                setValidacaoLabel("Preencha todos os campos!", Color.RED);
            }
        });

        HBox buttons = new HBox(submitBtn);

        content.getChildren().add(titulo);

        content.getChildren().addAll(idInput, modeloInput, placaInput, corInput, validacaoLabel, buttons);

        this.form = content;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;

        if(this.veiculo != null) {
            idFC.setValue(this.veiculo.getId());
            idInput.setVisible(true);
            idInput.setManaged(true);

            modeloFC.setValue(this.veiculo.getModelo());
            placaFC.setValue(this.veiculo.getPlaca());
            corFC.setValue(this.veiculo.getCor());
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
