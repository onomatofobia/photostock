package pl.com.bottega.photostock.sales.model;

import java.awt.datatransfer.DataFlavor;
import java.util.*;

public abstract class Client {

    private String name;
    private Address address;
    private ClientStatus status;
    //private Money balance;
    private List<Transaction> transaction = new LinkedList<>();
    private String clientNumber;


    public Client(String name, Address address, ClientStatus status, Money balance){
        this.clientNumber = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.status = status;
        if (balance.gt(Money.ZERO))
            transaction.add(new Transaction(balance, "First charge."));
    }

    public Client(String name, Address address){
        this(name, address, ClientStatus.STANDARD, Money.ZERO);
    }

    public abstract boolean canAfford(Money amount);

    public Money balance(){
        Money sumOfTransactions = Money.ZERO;

        for (Transaction t : transaction){
            sumOfTransactions = sumOfTransactions.add(t.getAmount());
        }
        return sumOfTransactions;
    }

    public void charge(Money amount, String reason){
        if (!canAfford(amount))
            throw new IllegalStateException("Not enough balamce.");
        transaction.add(new Transaction(amount.neg(), reason));
    }

    public void recharge(Money amount){
        transaction.add(new Transaction(amount, "Recharge account"));

    }

    @Override
    public String toString() {

        return "Client{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", status=" + status +
                ", transaction=" + transaction +
                '}';
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public int discountPercent() {
        return status.discountPercent();
    }

    public String getName() {
        return name;
    }

    public boolean hasLogin(String login){

        return name.equals(login);
    }

}
