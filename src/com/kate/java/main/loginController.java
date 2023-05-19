package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;


public class loginController {
    public Text username;
    @FXML
    public Button register;

    /**
     * set welcome message to include username
     * @param username username
     */
    public void setWelcome(String username) {
        this.username.setText(username);
    }


    /**
     * bring up registration pop-up window
     */
    public void adduser() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/register.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
