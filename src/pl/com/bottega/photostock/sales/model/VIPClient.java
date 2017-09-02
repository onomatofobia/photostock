package pl.com.bottega.photostock.sales.model;

import java.util.LinkedList;
import java.util.List;

public class VIPClient extends Client{

    private Money creditLimit;
    private List<Transaction> transaction = new LinkedList<>();

    public VIPClient(String name, Address address, ClientStatus status, Money balance, Money creditLimit){
        super(name, address, status, balance);
        this.creditLimit = creditLimit;
        if (balance.gt(Money.ZERO))
            transaction.add(new Transaction(balance, "First charge."));
    }

    public VIPClient(String name, Address address){
        this(name, address, ClientStatus.VIP, Money.ZERO, Money.ZERO );
    }

    public boolean canAfford(Money amount){
        return balance().add(creditLimit).gte(amount);
    }
}
