package pl.com.bottega.photostock.sales.model;

/**
 * Created by user on 22.08.2017.
 */
public interface Product {

    Long getNumber();

    boolean isAvailable();

    Money calculatePrice();

    void reservedBy();

    void unreservedPer();

}
