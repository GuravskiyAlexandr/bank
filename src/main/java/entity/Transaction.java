package entity;/*
 * Created by Alexsandr        29.05.2018
 */

import javax.persistence.*;
import java.util.Date;
@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    private String nameOperation;
    private Float sum;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Transaction(Date date, String nameOperation, Float sum, Client client) {
        this.date = date;
        this.nameOperation = nameOperation;
        this.sum = sum;
        this.client = client;
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNameOperation() {
        return nameOperation;
    }

    public void setNameOperation(String nameOperation) {
        this.nameOperation = nameOperation;
    }

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", nameOperation='" + nameOperation + '\'' +
                ", sum=" + sum +
                ", client=" + client.getName() +
                '}';
    }
}
