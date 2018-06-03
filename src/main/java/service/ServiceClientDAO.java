package service;/*
 * Created by Alexsandr        29.05.2018
 */

import entity.Client;
import enums.Coin;

public interface ServiceClientDAO {
    boolean addClient(String name, String surname);

    Client getClientById(Long id);

    boolean addBillToClient(Integer bankAccount, Float countMomey, Coin currency, Client client);

    Client getFirstClientByFirstId();

    boolean replenishAnAccount(Integer bankAccount, Float countMomey, Coin currency);

    boolean transferOfFundsFromOneAccountToAnother(Integer bankAccountFrom, Float countMomey, Integer bankAccountTo, Coin currency);

    boolean transferOfFundsWithinOneCustomer(Integer bankAccountFrom, Integer bankAccountTo, Float countMomey);

    Float getTotalAmountOfMoneyFromAllInUAN(Client client);
}
