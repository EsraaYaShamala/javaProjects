package estore.controller;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class SigninController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private RadioButton admain;
    @FXML
    private ToggleGroup classification;
    @FXML
    private RadioButton normal;
    @FXML
    private PasswordField newPass;
    @FXML
    private PasswordField confPass;
    @FXML
    private Button btnSignup;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnExit;

    boolean flagName = true;
    boolean flagPass = true;
    ObservableList<Person> items = FXCollections.observableArrayList(PersonDao.getInstance().getAll());
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        normal.setSelected(true);
        btnSignup.setOnAction(e -> {
            Person person=validation();
                if (flagName & flagPass) {
                    person.setpID(person.getpID());
                    person.setpName(txtName.getText());
                    person.setpClassification(admain.isSelected() ? "Admain" : "normal");
                    person.setNewPassword(newPass.getText());
                    person.setOldPassword(null);
                    person.setpPassword(newPass.getText());
                    PersonDao.getInstance().save(person);
                    message("Save Person", "The signUp process has been completed successfully");
                }
            
        });
        btnNew.setOnAction(e->{
            this.newSignup();
        });
        btnExit.setOnAction(e->{
            EStore.setRoot("Sign");
        });
    }

    void message(String header, String content) {
        Dialog d = new Dialog();
        d.setHeaderText(header);
        d.setTitle(EStore.title);
        d.setContentText(content);
        ButtonType btnOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(btnOk);
        d.showAndWait();
    }

    Person validation() {
        Person p = new Person();
        for (Person person : items) {
            if (person.getpName().equals(txtName.getText())) {
                flagName = false;
                newSignup();
                message("Error", "This Name is Used Before");
            }
            if (!(newPass.getText().equals(confPass.getText()))) {
                flagPass = false;
                newSignup();
                message("Error", "Verify Password");
            }
            p.setpID(person.getpID()+1);
        }
        return p;
    }
    void newSignup(){
        txtName.setText(null);
        this.confPass.setText(null);
        this.newPass.setText(null);
        items=FXCollections.observableArrayList(PersonDao.getInstance().getAll());
    }
}
