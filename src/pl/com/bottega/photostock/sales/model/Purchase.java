package pl.com.bottega.photostock.sales.model;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by user on 19.08.2017.
 */
public class Purchase {

    private Collection<Product> items;
    private Client buyer;
    private LocalDateTime purchaseDate = LocalDateTime.now();

    public Purchase(Client buyer, Collection<Product> items){
        this.buyer = buyer;
        this.items = new LinkedList<>(items);
    }
}
