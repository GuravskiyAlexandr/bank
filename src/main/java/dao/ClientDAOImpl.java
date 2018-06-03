package dao;/*
 * Created by Alexsandr        29.05.2018
 */

import entity.Bills;
import entity.Client;
import entity.Transaction;
import enums.Coin;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ClientDAOImpl implements ClientDAO {
    EntityManager em;

    public ClientDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean addClient(Client client) {
        em.getTransaction().begin();
        try {
            em.persist(client);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }

    @Override
    public Client getClient(Long id) {
        if (!em.isOpen())
            em.getTransaction().begin();
        Client client = em.find(Client.class, id);
        return client;
    }

    @Override
    public boolean addBillToClient(Bills bills, Transaction transaction) {
        em.getTransaction().begin();
        try {
            em.persist(bills);
            em.persist(transaction);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }

    @Override
    public void upDate(Client client) {
        em.getTransaction().begin();
        try {
            em.merge(client);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public Client getFirstClientByFirstId() {
        Query query = em.createQuery("SELECT c FROM Client c WHERE c.id =(select min(id) from Client c)  ");
        return (Client) query.getSingleResult();
    }

    @Override
    public boolean replenishAnAccount(Bills bills, Transaction transaction) {
        em.getTransaction().begin();
        try {
            em.merge(bills);
            em.persist(transaction);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }

    @Override
    public boolean transferOfFundsFromOneAccountToAnother(Bills billFrom, Bills billTo, Transaction transaction) {
        em.getTransaction().begin();
        try {
            em.merge(billFrom);
            em.merge(billTo);
            em.persist(transaction);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }

    @Override
    public boolean currencyConversionWithinASingleUserAccount(Bills billFrom, Bills billTo, Transaction transaction) {
        em.getTransaction().begin();
        try {
            em.merge(billFrom);
            em.persist(transaction);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }

    @Override
    public Float getTotalAmountOfMoneyFromAllInUAN(Client client) {
//        Long id = client.getId();
//        Query query = em.createQuery("select sum(b.CountMomey) FROM Bills b where b.client.id = :id ");
//        query.setParameter("id", id);
//        query.setParameter("USD", Coin.USD);
//        query.setParameter("EUR", Coin.EUR);
//        System.out.println(query.getSingleResult());
        return null;
    }
}
