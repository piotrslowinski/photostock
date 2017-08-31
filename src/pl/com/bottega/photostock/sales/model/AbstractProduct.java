package pl.com.bottega.photostock.sales.model;

/**
 * Created by user on 31.08.2017.
 */
public class AbstractProduct implements Product {

    private Long number;
    private Money price;
    private Boolean active;
    private Client reservedBy, owner;

    public AbstractProduct(Long number, Money price){
        this(number, price, true);
    }

    public AbstractProduct(Long number, Money price, Boolean active){
        this.number = number;
        this.price = price;
        this.active = active;
    }

    @Override
    public Long getNumber() {
        return number;
    }

    @Override
    public boolean isAvailable() {
        return (active && reservedBy == null);
    }

    @Override
    public Money calculatePrice(Client client) {
        return price.percent(100 - client.discountPercent());
    }

    @Override
    public void reservedPer(Client client) {
        if (!isAvailable())
            throw new IllegalStateException("Product is not available");
        reservedBy = client;

    }

    @Override
    public void unreservedPer(Client client) {
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
}
