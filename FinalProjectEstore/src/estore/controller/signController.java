package estore.controller;

import estore.DatabaseManager;
import estore.EStore;
import estore.dao.PersonDao;
import estore.models.Person;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class signController implements Initializable {

    @FXML
    private TextField txtuser;
    @FXML
    private PasswordField txtpass;
    @FXML
    private Button login;
    @FXML
    private Label newAccount;
    @FXML
    private ImageView userLogin;
    @FXML
    private ImageView user;
    @FXML
    private ImageView lock;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseManager.getInstance().getConnection();
        login.setOnAction(e -> {
            this.openEstoreController();
        });
        txtpass.setOnAction(t -> {
            this.openEstoreController();
        });
        newAccount.setOnMouseClicked(e -> {
            EStore.setRoot("signin");
        });
    }

    void openEstoreController() {
        boolean flag = true;
        ObservableList<Person> items = FXCollections.observableArrayList(PersonDao.getInstance().getAll());
        for (Person person : items) {
            if (person.getpName().equals(txtuser.getText()) & person.getpPassword().equals(txtpass.getText())) {
                flag=false;
                EStore.setPerson(person);
                EStore.setRoot("e_store");
            }
        }
        if(flag){
        this.txtpass.setText(null);
        this.txtuser.setText(null);
        Dialog d = new Dialog();
        d.setHeaderText("Error");
        d.setTitle(EStore.title);
        d.setContentText("Verify password");
        ButtonType btnOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(btnOk);
        d.showAndWait();
    }}
}
