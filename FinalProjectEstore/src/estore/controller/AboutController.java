package estore.controller;

import estore.EStore;
import estore.EStore;
import estore.dao.ProductDao;
import estore.models.Person;
import estore.models.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class AboutController implements Initializable {

    @FXML
    private Label lblWelcome;
    @FXML
    private TextArea txtArea;
    @FXML
    private Button btnExit;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtArea.setWrapText(true);
        btnExit.setOnAction(e -> {
            EStore.setRoot("e_store");
        });
        Person person=EStore.getPerson();
        lblWelcome.setText("Welcome "+person.getpName());
        String report =person.toString();
        ObservableList<Product> items = FXCollections.observableArrayList(ProductDao.getInstance().getAll(person.getpID()));
        for (Product t : items) {
            report += t.toString() + "\n";
        }
        txtArea.setText(report);
    }

}
