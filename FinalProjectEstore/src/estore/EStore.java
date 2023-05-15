package estore;

import estore.controller.E_storeController;
import estore.models.Person;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EStore extends Application {

    public static String title = "Store";
    private static Scene scene;
    private static Person person;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(getFxml("sign"));
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setMaximized(false);
        stage.show();
    }

    public static void setRoot(String name) {
        scene.setRoot(getFxml(name));
    }

    private static Parent getFxml(String name) {
        try {
            return FXMLLoader.load(EStore.class.getResource(String.format("/estore/fxml/file/%s.fxml", name)));
        } catch (IOException ex) {
            Logger.getLogger(EStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Person getPerson() {
        return person;
    }

    public static void setPerson(Person aPerson) {
        person = aPerson;
    }
}
