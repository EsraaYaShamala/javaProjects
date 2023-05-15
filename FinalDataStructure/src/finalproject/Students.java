package finalproject;

public class Students {
    private int id;
    private String name;
    private String address;
    private double avg;
    private int level;
    private boolean isGraduated;
    private double installments;

    public Students() {
    }

    public Students(int id, String name, String address, double avg, int level, boolean isGraduated, double installments) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.avg = avg;
        this.level = level;
        this.isGraduated = isGraduated;
        this.installments = installments;
    }
    
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getAvg() {
        return avg;
    }

    public int getLevel() {
        return level;
    }

    public boolean isIsGraduated() {
        return isGraduated;
    }

    public double getInstallments() {
        return installments;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setIsGraduated(boolean isGraduated) {
        this.isGraduated = isGraduated;
    }

    public void setInstallments(double installments) {
        this.installments = installments;
    }

    @Override
    public String toString() {
        return "Students{" + "id=" + id + ", name=" + name + ", address=" + address + ", avg=" + avg + ", level=" + level + ", isGraduated=" + isGraduated + ", installments=" + installments + '}';
    }
    
}
