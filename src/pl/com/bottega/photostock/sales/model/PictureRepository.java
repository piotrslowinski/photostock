package pl.com.bottega.photostock.sales.model;

import java.util.Optional;

/**
 * Created by user on 20.08.2017.
 */
public interface PictureRepository {


    //pobiera obiekt po identyfikatorze
    Picture get(Long number);

    Optional<Picture> getOptional(Long number);

    //zapis nowego oraz aktualizacja istniejeacego obiektu
    void save(Picture picture);
}
