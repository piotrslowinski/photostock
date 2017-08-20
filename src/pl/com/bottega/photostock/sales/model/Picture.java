package pl.com.bottega.photostock.sales.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 19.08.2017.
 */
public class Picture {

    private Long number;
    private Set<String> tags;
    private Money price;
    private Boolean active;
    private Client reservedBy, owner;


    public Picture(Long number, Set<String> tags, Money price){
        this(number, tags, price, true); //wywolywanie jednego konstruktora z drugiego
    }

    public Picture(Long number, Set<String> tags, Money price, Boolean active){
        this.number = number;
        this.tags = new HashSet<>(tags);
        this.price = price;
        this.active = active;
    }

    public Money calculatePrice(Client client){
        return price;
    }

    public boolean isAvailable(){
        return active && reservedBy == null;
    }

    public void reservedPer(Client client){

        if (!isAvailable())
            throw new IllegalStateException("Product is not available");
        reservedBy = client;

    }

    public void unreservedPer(Client client){
        if (owner != null)
            throw new IllegalStateException("Product is already purchased");
        checkReservation(client);
        if (!reservedBy.equals(client))
            throw new IllegalStateException(String.format("Product is not reserved by %s ", client));
        reservedBy = null;
    }

    private void checkReservation(Client client) {
        if (reservedBy == null || !reservedBy.equals(client))
            throw new IllegalStateException(String.format("Product is not reserved by %s ", client));
    }

    public void soldPer(Client client){
        if (!reservedBy.equals(client))
            throw new IllegalStateException(String.format("Product is not reserved by %s ", client));
        owner = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        return number.equals(picture.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    public Long getNumber() {
        return number;
    }

    public Money getPrice() {
        return price;
    }
}
