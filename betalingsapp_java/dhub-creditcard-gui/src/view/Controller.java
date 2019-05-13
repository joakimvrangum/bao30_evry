package view;

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

    @FXML TextArea output;
    @FXML TextField textfieldPrice, textfieldTransactionText;
    @FXML Button btnPay;
    @FXML GridPane grid;
    @FXML Label labelStatus;

    private final StringBuffer bufferCard = new StringBuffer();
    String[] card;
    private enum LABEL_STATUS {
        OK,
        IN_PROGRESS,
        ERROR
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleBtnBetale() {
        textfieldPrice.setDisable(true);
        textfieldTransactionText.setDisable(true);
        btnPay.setDisable(true);
        bufferCard.delete(0,bufferCard.length());
        updateStatusLabel(LABEL_STATUS.OK, "Klar til betaling - dra bankkortet.");
        Stage stage = (Stage) grid.getScene().getWindow();
        stage.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.ENTER) {
                card = new String[0];
                card = parseCreditCardData(bufferCard.toString(), true);
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
                textfieldPrice.setDisable(false);
                textfieldTransactionText.setDisable(false);
                btnPay.setDisable(false);
                bufferCard.delete(0,bufferCard.length());
            }
            bufferCard.append(key.getText());
        });
    }

    public void updateStatusLabel(LABEL_STATUS status_code, String status_text) {
        switch (status_code) {
            case OK:
                labelStatus.setStyle("-fx-text-fill: greenyellow");
                break;
            case IN_PROGRESS:
                labelStatus.setStyle("-fx-text-fill: coral");
                break;
            case ERROR:
                labelStatus.setStyle("-fx-text-fill: firebrick");
                break;
        }
        labelStatus.setText(status_text);
    }

    public void handleCloseWindowButton() {
        System.out.println("Lukk");
        Stage stage = (Stage) grid.getScene().getWindow();
        stage.close();
    }

}
