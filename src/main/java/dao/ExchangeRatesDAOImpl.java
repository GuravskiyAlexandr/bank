package dao;/*
 * Created by Alexsandr        30.05.2018
 */

import entity.ExchangeRates;
import enums.Coin;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ExchangeRatesDAOImpl implements ExchangeRatesDAO {
    EntityManager em;

    public ExchangeRatesDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean add(ExchangeRates rates) {
        em.getTransaction().begin();
        try {
            em.persist(rates);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }

    @Override
    public ExchangeRates getExchangeLast(Coin coin) {
        Query query = em.createQuery(
                "SELECT e FROM Exchange_Rates e  WHERE e.id = (select max (r.id) from Exchange_Rates r WHERE r.nameCurrency = :coin)  ", ExchangeRates.class);
        query.setParameter("coin", coin);
        return (ExchangeRates) query.getSingleResult();
    }
}
