package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.SQLException;

/**
 * eyo
 * log in with:
 * username: meat
 * password: balls
 *
 * VM configuration thing so you don't have to google it:
 * windows: --module-path "\path\to\javafx-sdk-20\lib" --add-modules javafx.controls,javafx.fxml
 * mac/linux: --module-path /path/to/javafx-sdk-20/lib --add-modules javafx.controls,javafx.fxml
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

