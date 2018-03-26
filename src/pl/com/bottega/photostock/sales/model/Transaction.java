package pl.com.bottega.photostock.sales.model;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class Transaction {

    private Money amount;
    private String description;
    private LocalDateTime date = LocalDateTime.now();


    public Transaction(Money amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public Money getAmount(){
        return amount;
    }


}
