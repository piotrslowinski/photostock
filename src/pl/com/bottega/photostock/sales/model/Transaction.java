package pl.com.bottega.photostock.sales.model;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 20.08.2017.
 */
public class Transaction {

    private Money amount;
    private String description;
    private LocalDateTime date = LocalDateTime.now();
    private List<Transaction> transactions = new LinkedList<>();

    public Transaction(Money amount, String description) {
        this.amount = amount;
        this.description = description;
    }
}
