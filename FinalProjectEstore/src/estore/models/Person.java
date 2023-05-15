package estore.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    final private IntegerProperty pID;
    final private StringProperty pName;
    final private StringProperty pPassword;
    final private StringProperty pClassification;
    final private StringProperty newPassword;
    final private StringProperty oldPassword;

    public Person() {
        this.pID=new SimpleIntegerProperty();
        this.pName= new SimpleStringProperty();
        this.pPassword= new SimpleStringProperty();
        this.pClassification= new SimpleStringProperty();
        this.newPassword= new SimpleStringProperty();
        this.oldPassword= new SimpleStringProperty();
    }
    public Person(Person person){
        this.pID = new SimpleIntegerProperty(person.getpID());
        this.pName = new SimpleStringProperty(person.getpName());
        this.pPassword = new SimpleStringProperty(person.getpPassword());
        this.pClassification =new SimpleStringProperty(person.getpClassification());
        this.newPassword = new SimpleStringProperty(person.getNewPassword());
        this.oldPassword = new SimpleStringProperty(person.getOldPassword());
    }
    
    public Person(Integer pID, String pName, String pPassword, String pClassification, String newPassword, String oldPassword) {
        this.pID =new SimpleIntegerProperty(pID);
        this.pName =  new SimpleStringProperty(pName);
        this.pPassword =  new SimpleStringProperty(pPassword);
        this.pClassification =  new SimpleStringProperty(pClassification);
        this.newPassword =  new SimpleStringProperty(newPassword);
        this.oldPassword =  new SimpleStringProperty(oldPassword);
    }

    public Integer getpID() {
        return this.pID.get();
    }

    public String getpName() {
         return pName.get();
    }

    public String getpClassification() {
        return pClassification.get();
    }

    public String getNewPassword() {
        return newPassword.get();
    }

    public String getOldPassword() {
        return oldPassword.get();
    }

    public String getpPassword() {
        return pPassword.get();
    }
    public IntegerProperty pIDProperty() {
        return pID;
    }
    public StringProperty pPasswordProperty() {
        return pPassword;
    }

    public StringProperty pNameProperty() {
        return pName;
    }

    public StringProperty pClassificationProperty() {
       return pClassification;
    }

    public StringProperty NewPasswordProperty() {
        return newPassword;
    }

    public StringProperty OldPasswordProperty() {
        return oldPassword;
    }
    public void setpID(Integer pID) {
        this.pID.set(pID);
    }
    public void setpPassword(String pPassword) {
        this.pPassword.set(pPassword);
    }

    public void setpName(String pName) {
        this.pName.set(pName);
    }

    public void setpClassification(String pClassification) {
        this.pClassification .set(pClassification);
    }

    public void setNewPassword(String newPassword) {
        this.newPassword .set(newPassword);
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword .set(oldPassword);
    }
    @Override
    public String toString() {
        return "Person{\n" + "pID=" + pID + "\n pName=" + pName + "\n pPassword=" + pPassword + "\n pClassification=" + pClassification + "\n newPassword=" + newPassword + "\n oldPassword=" + oldPassword + "\n}";
    }
    
}
