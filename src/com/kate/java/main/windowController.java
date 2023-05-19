package main;

import java.io.IOException;
import java.sql.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class windowController {

    @FXML
    private PasswordField username;
    @FXML
    private PasswordField password;
    @FXML
    private Text errormessage;

    /**
     * bring up logged in screen if user is registered
     */
    public void login() {
        if (userExists(username.getText(),password.getText())) {
            try {
                FXMLLoader loader =new FXMLLoader(getClass().getResource("/login.fxml"));
                Parent parent = loader.load();
                loginController loginController = loader.getController();
                loginController.setWelcome(username.getText());
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene(parent,100,100));
                stage.setMinHeight(420);
                stage.setMaxHeight(420);
                stage.setMinWidth(420);
                stage.setMaxWidth(420);
                Main.stage.close();
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            errormessage.setStyle("-fx-opacity: 100");

        }

    }

    /**
     * check if user is registered
     * @param username
     * @param password
     */
    private boolean userExists(String username, String password) {

        String stm = "SELECT * FROM USERINFO WHERE USERNAME = '" + username + "'";
        ResultSet resultset = databaseHandler.getHandler().executeQuery(stm);
        try {
            if (resultset.next()) {
                if (resultset.getString("password").equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void exit() {
        System.exit(1);
    }
}
