package estore.controller;

import estore.EStore;
import estore.currency;
import estore.dao.ProductDao;
import estore.models.Product;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class E_storeController implements Initializable {

    @FXML
    private Label lblwelcome_user;
    @FXML
    private ImageView imgAbout;
    @FXML
    private TextField txtprod_code;
    @FXML
    private TextField txtprod_Name;
    @FXML
    private TextField txtprod_url;
    @FXML
    private TextField txtprod_price;
    @FXML
    private TextField txtprod_quantity;
    @FXML
    private DatePicker DOP_picker;
    @FXML
    private ComboBox<currency> cboCurrency;
    @FXML
    private Button btnSer_Name;
    @FXML
    private Button btnSer_code;
    @FXML
    private ImageView imgProd_pic;
    @FXML
    private Button btnload;
    @FXML
    private TableView<Product> tbview;
    @FXML
    private TableColumn<Product, IntegerProperty> colProd_code;
    @FXML
    private TableColumn<Product, StringProperty> colProd_Name;
    @FXML
    private TableColumn<Product, IntegerProperty> colProd_price;
    @FXML
    private TableColumn<Product, IntegerProperty> colProd_quantity;
    @FXML
    private TableColumn<Product, IntegerProperty> colProd_currency;
    @FXML
    private TableColumn<Product, IntegerProperty> colProd_total;
    @FXML
    private TableColumn<Product, StringProperty> colProd_dop;
    @FXML
    private TableColumn<Product, StringProperty> colProd_url;
    @FXML
    private Button btnExit;
    @FXML
    private Label lbltotal;
    @FXML
    private Button btntotal;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnSetting;
    String imagePath = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblwelcome_user.setText(EStore.getPerson().getpName());
        this.fillCurrency();
        this.fillTable();
        tbview.setOnMouseClicked(eh -> {
            handleMouseAction(eh);
        });
        btnSave.setOnAction(e -> {
            if (isValidation()) {
                Product product = new Product();
                product.setProd_code(Integer.valueOf(this.txtprod_code.getText().trim()));
                product.setPrice(Integer.valueOf(this.txtprod_price.getText().trim()));
                product.setQuantity(Integer.valueOf(this.txtprod_quantity.getText().trim()));
                product.setTotal(product.getPrice() * product.getQuantity());
                product.setpID(EStore.getPerson().getpID());
                product.setProd_name(this.txtprod_Name.getText());
                product.setProd_url(this.txtprod_url.getText());
                product.setProd_picture(imagePath);
                product.setProductionDate(this.DOP_picker.getValue().toString());
                product.setCurrency(Integer.valueOf(this.cboCurrency.getValue().toString().substring(0, 2)));
                if (btnSave.getText().equals("Save")) {
                    if (addRecord(product) == 1) {
                        tbview.getItems().add(product);
                        message("save record", "Saved successfully");
                        this.btnSave.setText("Edit ");
                        this.btnDelete.setDisable(false);
                    }
                } else {
                    if (updateRecord(product) == 1) {
                        tbview.getItems().clear();
                        fillTable();
                        message("The record has been modified", "Saved successfully");
                        this.btnDelete.setDisable(false);
                    }
                }
            } else {
                this.message("Save error", "There are empty fields that require data entry to complete saving");
            }
        });

        btnNew.setOnAction(e -> {
            newRecord();
        });

        btnload.setOnAction((ActionEvent e) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle(EStore.title);
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                imagePath = selectedFile.toURI().toString();
                imgProd_pic.setImage(new Image(imagePath));
            }
        });

        btnDelete.setOnAction(e -> {
            if (this.txtprod_code.getText().trim().length() > 0) {
                int prod_code = Integer.valueOf(this.txtprod_code.getText().trim());
                if (prod_code > 0) {
                    Optional<Product> product = ProductDao.getInstance().get(prod_code);
                    deleteRecord(product.get());
                    newRecord();
                }
            }

        });

        btnExit.setOnAction((var e) -> {
            exit();
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
                Optional<Product> product = ProductDao.getInstance().get(prod_code);
                if (prod_code==product.get().getProd_code()) {
                    this.txtprod_code.setText(product.get().getProd_code().toString());
                    this.txtprod_Name.setText(product.get().getProd_name());
                    this.txtprod_price.setText(product.get().getPrice().toString());
                    this.txtprod_quantity.setText(product.get().getQuantity().toString());
                    this.btnDelete.setDisable(false);
                    this.btnSave.setText("Edit");
                }
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
                Optional<Product> product = ProductDao.getInstance().get(prod_name);
                if (product.get().getProd_name().equals(prod_name)) {
                    this.txtprod_code.setText(product.get().getProd_code().toString());
                    this.txtprod_Name.setText(product.get().getProd_name());
                    this.txtprod_price.setText(product.get().getPrice().toString());
                    this.txtprod_quantity.setText(product.get().getQuantity().toString());
                    this.btnDelete.setDisable(false);
                    this.btnSave.setText("Edit");
                }
            }
        });

        imgAbout.setOnMouseClicked(eh -> {
            EStore.setRoot("About");
        });
        btnSetting.setOnAction(e -> { 
            this.message("ChangePassword", "Do you want to change the password");
            EStore.setRoot("setting");
        });

    }

    public void handleMouseAction(MouseEvent event) {
        newRecord();
        Product product = this.tbview.getSelectionModel().getSelectedItem();
        this.txtprod_code.setText(product.getProd_code().toString());
        this.txtprod_Name.setText(product.getProd_name());
        this.txtprod_url.setText(product.getProd_url());
        this.txtprod_price.setText(product.getPrice().toString());
        this.txtprod_quantity.setText(product.getQuantity().toString());
        this.lbltotal.setText(product.getTotal().toString());
        this.btnSave.setText("Edit");
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

    private void deleteRecord(Product product) {
        Dialog d = new Dialog();
        d.setHeaderText("Delete records...");
        d.setTitle(EStore.title);
        d.setContentText("Are you sure ?");
        ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType btnNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        d.getDialogPane().getButtonTypes().addAll(btnYes, btnNo);
        if (d.showAndWait().get() == btnYes) {
            ProductDao.getInstance().delete(product);
            tbview.getItems().clear();
            fillTable();
            message("Delete record", "The deletion process has been successful");
        }
    }

    private void newRecord() {
        this.txtprod_code.requestFocus();
        this.btnSave.setText("Save");
        this.txtprod_code.setText(null);
        this.txtprod_Name.setText(null);
        this.txtprod_price.setText(null);
        this.txtprod_quantity.setText(null);
        this.txtprod_url.setText(null);
        this.imgProd_pic.setImage(null);
        this.DOP_picker.setValue(null);
        this.cboCurrency.setValue(null);
        this.lbltotal.setText("0");
    }

    private int updateRecord(Product product) {
        ProductDao.getInstance().update(product);
        return 1;
    }

    private int addRecord(Product product) {
        ProductDao.getInstance().save(product);
        return 1;
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

    private boolean isValidation() {
        boolean temp = true;
        if (this.txtprod_code.getText().trim().length() == 0) {
            temp = false;
        }
        if (this.txtprod_Name.getText().trim().length() == 0) {
            temp = false;
        }
        if (this.txtprod_price.getText().trim().length() == 0) {
            temp = false;
        }
        if (this.txtprod_quantity.getText().trim().length() == 0) {
            temp = false;
        }
        if (this.cboCurrency.getValue() == null) {
            temp = false;
        }
        if (this.DOP_picker.getValue() == null) {
            temp = false;
        }
        return temp;
    }

    private void fillTable() {
        tbview.sort();
        colProd_code.setCellValueFactory(new PropertyValueFactory("prod_code"));
        colProd_Name.setCellValueFactory(new PropertyValueFactory("prod_name"));
        colProd_price.setCellValueFactory(new PropertyValueFactory("price"));
        colProd_quantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        colProd_currency.setCellValueFactory(new PropertyValueFactory("currency"));
        colProd_dop.setCellValueFactory(new PropertyValueFactory("productionDate"));
        colProd_url.setCellValueFactory(new PropertyValueFactory("prod_url"));
        colProd_total.setCellValueFactory(new PropertyValueFactory("total"));
        lbltotal.setText(colProd_total.getText()); // FIX ME
        List<Product> products = ProductDao.getInstance().getAll(EStore.getPerson().getpID());
        tbview.getItems().addAll(products);
//        tbview.sort();
    }

    private void fillCurrency() {
        ObservableList<currency> items = FXCollections.observableArrayList(new currency(1, "Shekel"),
                new currency(2, "Dinar"), new currency(3, "Dollar"), new currency(4, "Euro"));
        this.cboCurrency.setItems(items);
    }
}
