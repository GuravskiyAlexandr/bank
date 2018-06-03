package dao;/*
 * Created by Alexsandr        30.05.2018
 */

import entity.Transaction;

import javax.persistence.EntityManager;

public class TransactionDAOImpl implements TransactionDAO {
    EntityManager em;
    public TransactionDAOImpl(EntityManager em) {
        this.em = em;
    }
    @Override
    public boolean add(Transaction transaction) {
        em.getTransaction().begin();
        try {
            em.persist(transaction);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }
}
