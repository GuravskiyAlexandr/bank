package dao;/*
 * Created by Alexsandr        01.06.2018
 */

import entity.Bills;
import entity.Client;
import enums.Coin;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class BillsDAOImpl implements BillsDAO {
    EntityManager em;
    public BillsDAOImpl(EntityManager em) {
        this.em = em;
    }
    @Override
    public Bills getBill(Integer bankAccount) {
        Query query = em.createQuery("SELECT b FROM Bills b WHERE b.numberAccountToBank = :bankAccount");
        query.setParameter("bankAccount", bankAccount);
        Bills bills = (Bills)query.getSingleResult();
        return bills;
    }
}
