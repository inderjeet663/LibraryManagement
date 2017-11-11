package libraryManagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.stage.StageStyle.TRANSPARENT;

public class MainStage {
    public static Stage primaryStage;
    static boolean isExecuted = false;
    public static String admin = null;
    public MainStage(Stage pStage) throws IOException {
        primaryStage = pStage;
        Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/User/mainWindow.fxml"));
        primaryStage.setTitle("Library Management");
        Scene scene = new Scene(root, 818, 546);
        primaryStage.setScene(scene);
        //Dont allow to resize window
        primaryStage.resizableProperty().setValue(false);
        if (!isExecuted) {
            //adding blur effect to main window
            ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);
            root.setEffect(adj);
        }
        primaryStage.show();
        if (!isExecuted) {
            new LoginStage();
        }
        root = FXMLLoader.load(getClass().getResource("fxmlFiles/User/mainWindow.fxml"));
        scene.setRoot(root);
    }
}

