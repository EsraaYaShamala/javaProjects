package estore;


public class currency {

     private final Integer mCode;
    private final String mName;

    public currency(Integer mCode, String mName) {
        this.mCode=mCode;
        this.mName=mName;
    }

    @Override
    public String toString() {
        return  String.format("%02d-%s", this.mCode,this.mName);
    }

}
