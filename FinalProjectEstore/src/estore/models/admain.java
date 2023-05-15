package estore.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class admain {
    final private IntegerProperty pCode;
    final private StringProperty pName;
    final private IntegerProperty total;
    final private IntegerProperty currency;
    final private StringProperty personName;
    public admain() {
        this.pCode=new SimpleIntegerProperty();
        this.total=new SimpleIntegerProperty();
        this.currency=new SimpleIntegerProperty();
        this.pName= new SimpleStringProperty();
        this.personName= new SimpleStringProperty();
    
    }

    public admain(Integer pCode, String pName, Integer total, Integer currency, String personName) {
        this.pCode=new SimpleIntegerProperty(pCode);
        this.total=new SimpleIntegerProperty(total);
        this.currency=new SimpleIntegerProperty(currency);
        this.pName= new SimpleStringProperty(pName);
        this.personName= new SimpleStringProperty(personName);
    }

    public Integer getpCode() {
        return pCode.get();
    }

    public String getpName() {
        return pName.get();
    }

    public Integer getTotal() {
        return total.get();
    }

    public Integer getCurrency() {
        return currency.get();
    }

    public String getPersonName() {
        return personName.get();
    }
    public void setProd_code(Integer pCode) {
        this.pCode.set(pCode);
    }

    public void setProd_name(String pName) {
        this.pName.setValue(pName);
    }
    
    public void setpersonName(String personName) {
        this.personName.setValue(personName);
    }

    public void setCurrency(Integer currency) {
        this.currency.set(currency);
    }

    public void setTotal(Integer total) {
        this.total.set(total);
    }
    
     public Integer TotalProperty() {
        return total.get();
    }
     public Integer CurrencyProperty() {
        return currency.get();
    }
     
     public String Prod_nameProperty() {
        return pName.get();
    }
     public String personNameProperty() {
        return personName.get();
    }
     public Integer Prod_codeProperty() {
        return pCode.get();
    }
}
