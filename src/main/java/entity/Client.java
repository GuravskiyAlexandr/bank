package entity;/*
 * Created by Alexsandr        29.05.2018
 */

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    @OneToMany(mappedBy = "client")
    private List<Bills> billsList = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Transaction> transaction = new ArrayList<>();

    public void addBill(Bills bill){
        if (!billsList.contains(bill)) {
            billsList.add(bill);
            bill.setClient(this);
        }
    }
    public void addTransaction(Transaction tran){
        if (!billsList.contains(tran)) {
            transaction.add(tran);
            tran.setClient(this);
        }
    }
    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    public List<Bills> getBillsList() {
        return billsList;
    }

    public void setBillsList(List<Bills> billsList) {
        this.billsList = billsList;
    }

    public Client(){
    }
    public Client(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", billsList=" + billsList +
                ", transaction=" + transaction +
                '}';
    }
}
