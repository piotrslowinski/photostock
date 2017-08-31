package pl.com.bottega.photostock.sales.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 19.08.2017.
 */
public class Picture extends AbstractProduct {


    private Set<String> tags;


    public Picture(Long number, Set<String> tags, Money price){
        this(number, tags, price, true); //wywolywanie jednego konstruktora z drugiego
        this.tags = new HashSet<>(tags);
    }

    public Picture(Long number, Set<String> tags, Money price, Boolean active){
        super(number, price, active);
        this.tags = new HashSet<>(tags);

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        return getNumber().equals(picture.getNumber());
    }

    @Override
    public int hashCode() {
        return getNumber().hashCode();
    }



}
