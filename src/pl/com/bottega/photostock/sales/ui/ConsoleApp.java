package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.infrastructures.InMemoryPictureRepository;
import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.repositories.PictureRepository;

/**
 * Created by user on 06.08.2017.
 */
public class ConsoleApp {
    public static void main(String[] args) {

        PictureRepository repository = new InMemoryPictureRepository();
        Picture p1 = repository.get(1L);
        Picture p2 = repository.get(2L);
        Picture p3 = repository.get(3L);


        Client client = new Client("Jan Nowak", new Address("ul. Północna", "Poland", "Lublin", "20-520"));
        client.recharge(Money.valueOf(100000));

        Reservation reservation = new Reservation(client);

        LightBox l = new LightBox(client, "kotki");
        l.add(p1);
        l.add(p2);
        l.add(p3);

        LightBoxPresenter presenter = new LightBoxPresenter();
        presenter.show(l);

        reservation.add(p1);
        reservation.add(p2);
        presenter.show(l);

        reservation.add(p3);

        System.out.println("W rezzerwacji jest " + reservation.getItemsCount() + " produktów.");
        //alternatywa - String.format
        System.out.println(String.format("W rezerwacji jest %d produktów", reservation.getItemsCount()));

//        reservation.getItemsCount();
        Offer offer = reservation.generateOffer();
        Money cost = offer.getTotalCost();
        if (client.canAfford(offer.getTotalCost())){
            Purchase purchase = new Purchase(client, offer.getItems());
            client.charge(cost, String.format("Zakup %s", purchase));
            System.out.println(String.format("Ilośc zakupionych zdjęć: %d, całkowity koszt: %s, ", offer.getItemsCount(), offer.getTotalCost()));
        }
        else
            System.out.println("Client cannot afford");
    }
}
