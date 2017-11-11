package libraryManagement;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

public class Main extends Application {
    public static boolean exit = false;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/User/mainWindow.fxml"));
        //add bluer effect to window
        ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);
        root.setEffect(adj);
        primaryStage.setTitle("Library Management");
        Scene scene = new Scene(root, 818, 546);
        primaryStage.setScene(scene);
        //Dont allow to resize window
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
        new MainStage(primaryStage);
        root = FXMLLoader.load(getClass().getResource("fxmlFiles/User/mainWindow.fxml"));
        scene.setRoot(root);
        if (exit) {
            Platform.exit();
        }
    }

    @Override
    public void init() throws Exception {
        super.init();
        DataConnection.getDataConnection();
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        launch(args);
    }
}
