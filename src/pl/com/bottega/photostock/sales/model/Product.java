package pl.com.bottega.photostock.sales.model;

/**
 * Created by user on 22.08.2017.
 */
public interface Product {

    Long getNumber();

    boolean isAvailable();

    Money calculatePrice(Client client);

    void reservedPer(Client client);

    void unreservedPer(Client client);

}
