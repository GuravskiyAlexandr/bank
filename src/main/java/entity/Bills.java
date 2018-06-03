package entity;/*
 * Created by Alexsandr        29.05.2018
 */

import enums.Coin;

import javax.persistence.*;

@Entity
public class Bills {
    @Id
    @GeneratedValue
    @Column( nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private Integer numberAccountToBank;
    private Float CountMomey;
    @Enumerated(EnumType.STRING)
    private Coin nameCurrency;

    @ManyToOne()
    @JoinColumn(name = "client_id")
    private Client client;

    public Bills(Integer bankAccount, Float countMomey, Coin currency, Client client) {
        this.numberAccountToBank = bankAccount;
        CountMomey = countMomey;
        this.nameCurrency = currency;
        this.client = client;
    }

    public Bills() {
    }

    public Integer getNumberAccountToBank() {
        return numberAccountToBank;
    }

    public void setNumberAccountToBank(Integer numberAccountToBank) {
        this.numberAccountToBank = numberAccountToBank;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCountMomey() {
        return CountMomey;
    }

    public void setCountMomey(Float countMomey) {
        CountMomey = countMomey;
    }

    public Coin getNameCurrency() {
        return nameCurrency;
    }

    public void setNameCurrency(Coin nameCurrency) {
        this.nameCurrency = nameCurrency;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
