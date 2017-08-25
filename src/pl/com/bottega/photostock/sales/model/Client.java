package pl.com.bottega.photostock.sales.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 19.08.2017.
 */
public class Client {

    private String name;
    private Address address;
    private ClientStatus status;
   // private Money balance;
    private Money creditLimit;
    private List<Transaction> transactions = new LinkedList<>();


    public Client(String name, Address address, ClientStatus status, Money creditLimit) {
        this.name = name;
        this.address = address;
        this.status = status;
        this.creditLimit = creditLimit;
            transactions.add(new Transaction(Money.ZERO, "First charge"));
    }

    public Client(String name, Address address){
        this(name, address, ClientStatus.STANDARD, Money.ZERO);
    }

    public boolean canAfford(Money amount) {
        return (balance().add(creditLimit).gte(amount));
    }

    public void charge(Money amount, String reason) {
        if (!canAfford(amount))
            throw new IllegalStateException("Not enough balance");
        transactions.add(new Transaction(amount.neg(), reason));

    }

    public void recharge(Money amount){
        transactions.add(new Transaction(amount, "Recharge account"));

    }

    public ClientStatus getStatus() {
        return status;
    }

    public int discountPercent() {
        return status.discountPercent();
    }

    private Money balance(){
        Money saldo = creditLimit;
        for (Transaction item: transactions)
            saldo = saldo.add(item.getAmount());
        return saldo;
    }
}
