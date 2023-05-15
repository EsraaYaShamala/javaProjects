package estore.controller;

import estore.EStore;
import estore.dao.PersonDao;
import estore.models.Person;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;

public class SettingController implements Initializable {

    @FXML
    private PasswordField oldpass;
    @FXML
    private PasswordField newpass;
    @FXML
    private PasswordField confirmpass;
    @FXML
    private Button btnsave;
    @FXML
    private Button btnExit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Person person = EStore.getPerson();
        btnsave.setOnAction(e -> {
            person.setOldPassword(person.getpPassword());
            if (person.getOldPassword().equals(oldpass.getText())) {
                if (newpass.getText().equals(confirmpass.getText())) {
                    person.setNewPassword(newpass.getText());
                    person.setpPassword(newpass.getText());
                    person.setOldPassword(oldpass.getText());
                    PersonDao.getInstance().update(person);
                    message("Change successfully", "Your password is Change successfully");
                } else {
                    message("Error", "Check that confirmPassword equals newPassword");
                }
            } else {
                message("Error", "Check that oldPassword is coorect");
            }
        });
        btnExit.setOnAction(e -> {
                EStore.setRoot("e_store");
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

}
