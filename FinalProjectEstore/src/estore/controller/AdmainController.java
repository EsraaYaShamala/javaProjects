package estore.controller;

import estore.EStore;
import estore.dao.ProductDao;
import estore.dao.admainDao;
import estore.models.Person;
import estore.models.Product;
import estore.models.admain;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AdmainController implements Initializable {

    @FXML
    private Label lblwelcome_user;
    @FXML
    private TextField txtpCode;
    @FXML
    private TextField txtpName;
    @FXML
    private Button btnSer_Name;
    @FXML
    private Button btnSer_code;
    @FXML
    private TableView<admain> tbview;
    @FXML
    private TableColumn<admain, IntegerProperty> colpCode;
    @FXML
    private TableColumn<admain, StringProperty> colpName;
    @FXML
    private TableColumn<admain, IntegerProperty> colpTotal;
    @FXML
    private TableColumn<admain, StringProperty> colPersonName;
    @FXML
    private TableColumn<admain, IntegerProperty> colcurrency;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnSetting;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillTable();
        lblwelcome_user.setText(EStore.getPerson().getpName());
        tbview.setOnMouseClicked(eh->{
            handleMouseAction(eh);
        });
        btnSetting.setOnAction(e->{ // FIX ME
            EStore.setRoot("setting");
        });
        btnSer_code.setOnAction(e -> {
            TextInputDialog td = new TextInputDialog();
            td.setHeaderText("product search");
            td.setTitle(EStore.title);
            td.setContentText("Enter product code");
            td.showAndWait();
            String prod_codeAsString = td.getEditor().getText();
            if (prod_codeAsString.length() > 0) {
                int prod_code = Integer.valueOf(td.getEditor().getText());
                Product product = new Product();
                ProductDao.getInstance().get(prod_code);
                this.txtpCode.setText(product.getProd_code().toString());
                this.txtpName.setText(product.getProd_name());
            }
        });
        btnSer_Name.setOnAction(e -> {
            TextInputDialog td = new TextInputDialog();
            td.setHeaderText("product search");
            td.setTitle(EStore.title);
            td.setContentText("Enter product Name");
            td.showAndWait();
            String prod_name = td.getEditor().getText();
            if (prod_name.length() > 0) {
                Product product = new Product();
                ProductDao.getInstance().get(prod_name);
                this.txtpCode.setText(product.getProd_code().toString());
                this.txtpName.setText(product.getProd_name());
            }
        });
        btnExit.setOnAction((var e) -> {
            exit();
        });
    }    
    private void fillTable() {
        admain admain = new admain();
        colpCode.setCellValueFactory(new PropertyValueFactory("pCode"));
        colpName.setCellValueFactory(new PropertyValueFactory("pName"));
        colcurrency.setCellValueFactory(new PropertyValueFactory("currency"));
        colpTotal.setCellValueFactory(new PropertyValueFactory("total"));
        colPersonName.setCellValueFactory(new PropertyValueFactory("personName"));
        admainDao.getInstance().save(admain);
        List<admain> admains = admainDao.getInstance().getAll();
        tbview.getItems().addAll(admains);
    }
    public void handleMouseAction(MouseEvent event) {
        admain admain = this.tbview.getSelectionModel().getSelectedItem();
        this.txtpCode.setText(admain.getpCode().toString());
        this.txtpName.setText(admain.getpName());
    }
    public static void exit() {
        Dialog d = new Dialog();
        d.setHeaderText("Exit the app...");
        d.setTitle(EStore.title);
        d.setContentText("Are you sure you want to exit the application?");
        ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType btnNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        d.getDialogPane().getButtonTypes().addAll(btnYes, btnNo);
        if (d.showAndWait().get() == btnYes) {
            Platform.exit();
        }
    }
    private void message(String header, String content) {
        Dialog d = new Dialog();
        d.setHeaderText(header);
        d.setTitle(EStore.title);
        d.setContentText(content);
        ButtonType btnOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(btnOk);
        d.showAndWait();
    }
}
