package service;/*
 * Created by Alexsandr        30.05.2018
 */

import dao.ExchangeRatesDAO;
import dao.ExchangeRatesDAOImpl;
import dao.TransactionDAO;
import dao.TransactionDAOImpl;
import entity.ExchangeRates;
import enums.Coin;

import javax.persistence.EntityManager;
import java.util.Date;

public class ServiceExchangeRatesDAOImpl implements  ServiceExchangeRatesDAO {
    EntityManager em;

    public ServiceExchangeRatesDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public boolean addExchangeRates(Date date, Coin currency, Integer countUAN, Float exchange) {
        ExchangeRates rates = new ExchangeRates(date, currency, countUAN, exchange);
        ExchangeRatesDAO dao = new ExchangeRatesDAOImpl(em);
        if(dao.add(rates))
            return true;
        return false;
    }

    @Override
    public ExchangeRates getExchangeLast( Coin coin) {
        ExchangeRatesDAO dao = new ExchangeRatesDAOImpl(em);
        ExchangeRates rates =  dao.getExchangeLast(coin);
        return rates;
    }
}
