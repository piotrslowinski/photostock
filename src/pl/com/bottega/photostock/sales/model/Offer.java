package pl.com.bottega.photostock.sales.model;

import java.util.*;

/**
 * Created by user on 06.08.2017.
 */
public class Offer {

    private Client owner;
    private List<Picture> items;

    public Offer(Client owner, Collection<Picture> items){
        this.owner = owner;
        this.items = new LinkedList<>(items); //tworze nowa kolekcje aby nikt nie modyfikowal mi jej z zewnatrz
        this.items.sort(new Comparator<Picture>(){

            public int compare(Picture o1, Picture o2){
                return o2.calculatePrice(owner).compareTo(o1.calculatePrice(owner));  //malejace sortowanie
            }
        });
    }

    public boolean sameAs(Offer offer, Money tolerance){
        return false;
    }

    public int getItemsCount(){
        return items.size();
    }

    public Money getTotalCost(){
        Money total = Money.ZERO;
        for (Picture item : items)
            total = total.add(item.calculatePrice(owner));
        return total;
    }

    public Collection<Picture> getItems() {
        return Collections.unmodifiableCollection(items);
    }
}
