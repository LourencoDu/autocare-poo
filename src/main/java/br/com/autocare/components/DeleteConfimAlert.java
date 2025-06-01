package br.com.autocare.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

import java.util.Optional;
public class DeleteConfimAlert {
    public static boolean deleteConfirm() {
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Confirmação de Exclusão");
        alerta.setHeaderText("Tem certeza que deseja excluir este registro?");
        alerta.setContentText("Essa ação não pode ser desfeita.");

        ButtonType botaoSim = new ButtonType("Sim, excluir registro");
        ButtonType botaoNao = new ButtonType("Não, cancelar exclusão", ButtonData.CANCEL_CLOSE);

        alerta.getButtonTypes().setAll(botaoSim, botaoNao);

        Optional<ButtonType> resultado = alerta.showAndWait();
        return resultado.isPresent() && resultado.get() == botaoSim;
    }
}
