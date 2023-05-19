package main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

public class registerController {
    @FXML
    private PasswordField username;
    @FXML
    private PasswordField password;
    @FXML
    private Text error;

    /**
     * insert user into database if username does not already exist
     */
    public void register() {
        if (userRegistered(username.getText())) {
            error.setStyle("-fx-opacity: 100");
            return;
        }
        String stm = "INSERT INTO USERINFO VALUES ('" +
                username.getText() + "','" + password.getText() + "')";
        databaseHandler.getHandler().executeAction(stm);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("User registered");
        alert.showAndWait();
    }

    /**
     * check if username is already registered
     * @param username
     */
    private boolean userRegistered(String username) {
        ResultSet resultset = databaseHandler.getHandler().executeQuery("SELECT * FROM USERINFO WHERE USERNAME = '" + username + "'");
        try {
            if (resultset.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
