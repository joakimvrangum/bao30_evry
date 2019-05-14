package view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static view.handlers.MagneticCardHandler.parseCreditCardData;
import static view.handlers.dHubHttpHandler.dHubTransactionPOST;

public class Controller implements Initializable {

    @FXML TextField textfieldPrice, textfieldTransactionText;
    @FXML Button btnPay, btnX;
    @FXML GridPane grid;
    @FXML Label labelStatus;

    private enum LABEL_STATUS {
        OK,
        IN_PROGRESS,
        ERROR
    }

    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void handleBtnBetale(ActionEvent event) {
        StringBuffer bufferCard = new StringBuffer();
        disableUI();
        updateStatusLabel(LABEL_STATUS.IN_PROGRESS, "Klar til betaling - dra bankkortet.");
        stage = (Stage) grid.getScene().getWindow();

        EventHandler eventCardRead = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if(ke.getCode()== KeyCode.ENTER) {
                    System.out.println(bufferCard.toString());
                    String[] card = parseCreditCardData(bufferCard.toString(), false);
                    updateStatusLabel(LABEL_STATUS.IN_PROGRESS, "Kort lest - sender til server (dHub)");
                    try {
                        String response = dHubTransactionPOST(card[1], Double.parseDouble(textfieldPrice.getText()), textfieldTransactionText.getText());
                        if (response.startsWith("Avvist!")) {
                            updateStatusLabel(LABEL_STATUS.ERROR, response);
                        } else {
                            updateStatusLabel(LABEL_STATUS.OK, response);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        updateStatusLabel(LABEL_STATUS.ERROR, e.getMessage());
                    }
                    enableUI();
                    stage.removeEventHandler(KeyEvent.KEY_PRESSED, this);
                } else
                    bufferCard.append(ke.getText());
            };
        };

        stage.addEventHandler(KeyEvent.KEY_PRESSED, eventCardRead);
    }

    public void updateStatusLabel(LABEL_STATUS status_code, String status_text) {
        switch (status_code) {
            case OK:
                labelStatus.setStyle("-fx-text-fill: greenyellow");
                break;
            case IN_PROGRESS:
                labelStatus.setStyle("-fx-text-fill: yellow");
                break;
            case ERROR:
                labelStatus.setStyle("-fx-text-fill: firebrick");
                break;
        }
        labelStatus.setText(status_text);
    }

    @FXML
    public void handleCloseWindowButton(ActionEvent event) {
        stage = (Stage) grid.getScene().getWindow();
        stage.close();
    }

    public void disableUI() {
        textfieldPrice.setDisable(true);
        textfieldTransactionText.setDisable(true);
        btnPay.setDisable(true);
        btnX.setDisable(true);
    }

    public void enableUI() {
        textfieldPrice.setDisable(false);
        textfieldTransactionText.setDisable(false);
        btnPay.setDisable(false);
        btnX.setDisable(false);
    }

}
