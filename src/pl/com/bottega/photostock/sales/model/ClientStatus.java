package pl.com.bottega.photostock.sales.model;

/**
 * Created by user on 19.08.2017.
 */
public enum ClientStatus {
    STANDARD(0), VIP(0), GOLD(5), PLATINUM(10), SILVER(15);

    private int discountPercent;

    ClientStatus(int discount){
        this.discountPercent = discount;
    }

    public int discountPercent(){
        return discountPercent;
    }
}
