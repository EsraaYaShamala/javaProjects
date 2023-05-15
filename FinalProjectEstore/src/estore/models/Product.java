package estore.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author AL-Qema
 */
public class Product {
    final private IntegerProperty prod_code;
    final private StringProperty prod_name;
    final private StringProperty prod_url;
    final private StringProperty prod_picture;
    final private StringProperty productionDate;
    final private IntegerProperty price;
    final private IntegerProperty currency;
    final private IntegerProperty quantity;
    final private IntegerProperty pID; 
    final private IntegerProperty total;
    public Product() {
        this.pID=new SimpleIntegerProperty();
        this.currency=new SimpleIntegerProperty();
        this.price=new SimpleIntegerProperty();
        this.prod_code=new SimpleIntegerProperty();
        this.quantity=new SimpleIntegerProperty();
        this.total=new SimpleIntegerProperty();
        this.prod_name=new SimpleStringProperty();
        this.prod_picture=new SimpleStringProperty();
        this.prod_url=new SimpleStringProperty();
        this.productionDate=new SimpleStringProperty();
    }

    public Product(Integer prod_code, String prod_name, String prod_url, String prod_picture, String productionDate, Integer price, Integer currency, Integer quantity, Integer pID, Integer total) {
        this.prod_code = new SimpleIntegerProperty(prod_code);
        this.prod_name = new SimpleStringProperty(prod_name);
        this.prod_url = new SimpleStringProperty(prod_url);
        this.prod_picture = new SimpleStringProperty(prod_picture);
        this.productionDate = new SimpleStringProperty(productionDate);
        this.price = new SimpleIntegerProperty(price);
        this.currency = new SimpleIntegerProperty(currency);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.pID = new SimpleIntegerProperty(pID);
        //total=price*quantity
        this.total = new SimpleIntegerProperty(total);
    }
    
    public Integer Prod_codeProperty() {
        return prod_code.get();
    }

    public String Prod_nameProperty() {
        return prod_name.get();
    }

    public String Prod_urlProperty() {
        return prod_url.get();
    }

    public String Prod_pictureProperty() {
        return prod_picture.get();
    }

    public String ProductionDateProperty() {
        return productionDate.get();
    }

    public Integer PriceProperty() {
        return price.get();
    }

    public Integer CurrencyProperty() {
        return currency.get();
    }

    public Integer QuantityProperty() {
        return quantity.get();
    }

    public Integer pIDProperty() {
        return pID.get();
    }

    public Integer TotalProperty() {
        return total.get();
    }

    public void setProd_code(Integer prod_code) {
        this.prod_code.set(prod_code);
    }

    public void setProd_name(String prod_name) {
        this.prod_name.setValue(prod_name);
    }

    public void setProd_url(String prod_url) {
        this.prod_url.setValue(prod_url);
    }

    public void setProd_picture(String prod_picture) {
        this.prod_picture.setValue(prod_picture);
    }

    public void setProductionDate(String productionDate) {
        this.productionDate.set(productionDate);
    }

    public void setPrice(Integer price) {
        this.price.set(price);
    }

    public void setCurrency(Integer currency) {
        this.currency.set(currency);
    }

    public void setQuantity(Integer quantity) {
        this.quantity.set(quantity);
    }

    public void setpID(Integer pID) {
        this.pID.set(pID);
    }

    public void setTotal(Integer total) {
        this.total.set(total);
    }

    public Integer getProd_code() {
        return prod_code.get();
    }

    public String getProd_name() {
        return prod_name.get();
    }

    public String getProd_url() {
        return prod_url.get();
    }

    public String getProd_picture() {
        return prod_picture.get();
    }

    public String getProductionDate() {
        return productionDate.get();
    }

    public Integer getPrice() {
        return price.get();
    }

    public Integer getCurrency() {
        return currency.get();
    }

    public Integer getQuantity() {
        return quantity.get();
    }

    public Integer getpID() {
        return pID.get();
    }

    public Integer getTotal() {
        return total.get();
    }

    @Override
    public String toString() {
        return "Product{\n" + "prod_code=" + prod_code + "\n prod_name=" + prod_name + "\n prod_url=" + prod_url + "\n prod_picture=" + prod_picture + "\n productionDate=" + productionDate + "\n price=" + price + "\n currency=" + currency + "\n quantity=" + quantity + "\n pID=" + pID + "\n total=" + total + "\n}";
    }
    
}
