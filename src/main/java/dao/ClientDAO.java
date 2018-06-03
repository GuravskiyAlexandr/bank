package dao;/*
 * Created by Alexsandr        29.05.2018
 */

import entity.Bills;
import entity.Client;
import entity.Transaction;

public interface ClientDAO {
    boolean addClient(Client client);

    Client getClient(Long id);

    boolean addBillToClient(Bills bills, Transaction transaction);

    void upDate(Client client);

    Client getFirstClientByFirstId();

    boolean replenishAnAccount(Bills bills, Transaction transaction);

    boolean transferOfFundsFromOneAccountToAnother(Bills billFrom, Bills billTo, Transaction transaction);

    boolean currencyConversionWithinASingleUserAccount(Bills billFrom, Bills billTo, Transaction transaction);

    Float getTotalAmountOfMoneyFromAllInUAN(Client client);
}
