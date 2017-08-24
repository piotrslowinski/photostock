package pl.com.bottega.photostock.sales.model;

import java.util.Set;

/**
 * Created by user on 23.08.2017.
 */
public class Clip implements Product {


    private Long number;
    private Money price;
    private Boolean active;
    private Client reservedBy, owner;
    private Long length;


    public Clip(Long number, Money price, Long length){
        this(number, price, length, true );

    }

    public Clip(Long number, Money price, Long length,boolean active ){
        this.number = number;
        this.price = price;
        this.length = length;
        this.active = active;

    }

    @Override
    public Long getNumber() {
        return number;
    }

    @Override
    public boolean isAvailable() {
        return active || reservedBy == null;
    }

    @Override
    public Money calculatePrice(Client client) {
        return price.percent(100-client.discountPercent());
    }

    @Override
    public void reservedPer(Client client) {

    }

    @Override
    public void unreservedPer(Client client) {

    }

}
