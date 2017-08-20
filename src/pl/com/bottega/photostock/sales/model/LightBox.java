package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 19.08.2017.
 */
public class LightBox {

    private String name;
    private List<Picture> items = new LinkedList<>();
    private Client owner;
    private Client client;

    public LightBox(Client owner, String name){
        this.owner = owner;
        this.name = name;
    }

    public void add(Picture picture){
        if (!picture.isAvailable())
            throw new IllegalArgumentException("Picture is not available");
        if (items.contains(picture))
            throw new IllegalArgumentException("Picture is already added");
        items.add(picture);
    }

    public void remove(Picture picture){
        if (!items.contains(picture))
            throw  new IllegalArgumentException("Picture is not available in LightBox");
        else if (items.contains(picture))
            items.remove(picture);
    }

    public String getName() {
        return name;
    }

    public Client getOwner() {
        return client;
    }

    public List<Picture> getItems() {
        return Collections.unmodifiableList(items);
    }
}



