package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.SQLException;

/**
 * :)
 * 1. in edit configurations
 * 2. add new configuration - application
 * 3. main class - select main class
 * 4. modify options - add VM options
 * 5. paste this into VM option text box
 *     windows: --module-path "libraries/openjfx-20.0.1_windows-x64_bin-sdk/javafx-sdk-20.0.1/lib" --add-modules javafx.controls,javafx.fxml
 *     mac/linux: --module-path libraries/openjfx-20.0.1_windows-x64_bin-sdk/javafx-sdk-20.0.1/lib --add-modules javafx.controls,javafx.fxml
 * 6. click ok & run this configuration
 *
 * login with
 * username: meat
 * password:balls
 */

public class Main extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        String css = this.getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setMinHeight(720);
        stage.setMaxHeight(720);
        stage.setMinWidth(1280);
        stage.setMaxWidth(1280);
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[] args) {
        databaseHandler handler = new databaseHandler();

        launch();

        try {
            databaseHandler.getHandler().connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

