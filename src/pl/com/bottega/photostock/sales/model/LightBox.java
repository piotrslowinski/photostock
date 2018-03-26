package pl.com.bottega.photostock.sales.model;

import java.util.*;


public class LightBox {

    private String name;
    private List<Picture> items = new LinkedList<>();
    private Client owner;
    private String number;


    public LightBox(Client owner, String name, String number){
        this.owner = owner;
        this.name = name;
        this.number = UUID.randomUUID().toString();
    }

    public void add(Picture picture){
        if (items.contains(picture))
            throw new IllegalArgumentException("Picture is already added");
        picture.ensureAvailable();
        items.add(picture);
    }

    public void remove(Picture picture){
        if (!items.contains(picture))
            throw  new IllegalArgumentException("Picture is not available in LightBox");
    }

    public String getName() {
        return name;
    }

    public Client getOwner() {
        return owner;
    }

    public List<Picture> getItems() {
        return Collections.unmodifiableList(items);
    }

    public List<Picture> getPictures(Set<Long> pictureNumbers) {
        List<Picture> results = new LinkedList<>();
        for(Picture pic : items)
            if(pictureNumbers.contains(pic.getNumber()))
                results.add(pic);
        return results;
    }
}



