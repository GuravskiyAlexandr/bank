package entity;/*
 * Created by Alexsandr        29.05.2018
 */

import enums.Coin;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Exchange_Rates")
public class ExchangeRates {
    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    @Enumerated(EnumType.STRING)
    private Coin nameCurrency;
    @Column(name = "count_UAN")
    private Integer countUAN;
    private Float exchange;

    public ExchangeRates(Date date, Coin currency, Integer countUAN, Float exchange) {
        this.date = date;
        this.nameCurrency = currency;
        this.countUAN = countUAN;
        this.exchange = exchange;
    }

    public ExchangeRates() {
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

    public Coin getNameCurrency() {
        return nameCurrency;
    }

    public void setNameCurrency(Coin currency) {
        this.nameCurrency = currency;
    }

    public Integer getCount() {
        return countUAN;
    }

    public void setCount(Integer count) {
        this.countUAN = count;
    }

    public Float getExchange() {
        return exchange;
    }

    public void setExchange(Float exchange) {
        this.exchange = exchange;
    }

    @Override
    public String toString() {
        return "ExchangeRates{" +
                "id=" + id +
                ", date=" + date +
                ", nameCurrency=" + nameCurrency +
                ", countUAN=" + countUAN +
                ", exchange=" + exchange +
                '}';
    }
}
