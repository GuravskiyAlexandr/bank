package service;/*
 * Created by Alexsandr        29.05.2018
 */

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import dao.*;
import entity.Bills;
import entity.Client;
import entity.ExchangeRates;
import entity.Transaction;
import enums.Coin;

import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;

public class ServiceClientDAOImpl implements ServiceClientDAO {
    EntityManager em;

    public ServiceClientDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean addClient(String name, String surname) {
        if (name.length() > 2 && surname.length() > 2) {
            ClientDAO dao = new ClientDAOImpl(em);
            Client clients = new Client(name, surname);
            dao.addClient(clients);
            return true;
        }
        return false;
    }

    @Override
    public Client getClientById(Long id) {
        ClientDAO dao = new ClientDAOImpl(em);
        return dao.getClient(id);
    }

    @Override
    public boolean addBillToClient(Integer bankAccount, Float countMomey, Coin currency, Client client) {

        Transaction transaction =
                new Transaction(
                        new Date(), "opening and crediting of money to the account at " +
                        currency + " Client " + client.getName(), countMomey, client);
        Bills bills = new Bills(bankAccount, countMomey, currency, client);
        ClientDAO dao = new ClientDAOImpl(em);
        client.addTransaction(transaction);
        client.addBill(bills);
        if (dao.addBillToClient(bills, transaction))
            return true;
        return false;
    }

    @Override
    public Client getFirstClientByFirstId() {
        ClientDAO dao = new ClientDAOImpl(em);
        return dao.getFirstClientByFirstId();
    }

    @Override
    public boolean replenishAnAccount(Integer bankAccount, Float countMomey, Coin currency) {
        BillsDAO billsDAO = new BillsDAOImpl(em);
        Bills bills = billsDAO.getBill(bankAccount);
        Client client = bills.getClient();
        bills.setCountMomey(bills.getCountMomey() + countMomey);

        Transaction transaction = new Transaction(
                new Date(), "replenish an account at " + currency + " Client " + client.getName(), countMomey, client);

        ClientDAO clientDAO = new ClientDAOImpl(em);
        client.addTransaction(transaction);
        if (clientDAO.replenishAnAccount(bills, transaction))
            return true;
        return false;
    }

    @Override
    public boolean transferOfFundsFromOneAccountToAnother(Integer bankAccountFrom, Float countMomey, Integer bankAccountTo, Coin currency) {
        BillsDAO billsDAO = new BillsDAOImpl(em);
        Bills billFrom = billsDAO.getBill(bankAccountFrom);
        Bills billTo = billsDAO.getBill(bankAccountTo);
        if (billFrom.getCountMomey() > countMomey &&
                billFrom.getNameCurrency().equals(billTo.getNameCurrency()) &&
                billFrom.getNameCurrency().equals(currency)) {
            Transaction transaction = new Transaction(
                    new Date(), "transfer of funds from " + billFrom.getClient().getName() +
                    " account to" + billTo.getClient().getName() + " count " + countMomey, countMomey, billFrom.getClient());
            billFrom.setCountMomey(billFrom.getCountMomey() - countMomey);
            billTo.setCountMomey(billTo.getCountMomey() + countMomey);
            billFrom.getClient().addTransaction(transaction);
            billTo.getClient().addTransaction(transaction);

            ClientDAO clientDAO = new ClientDAOImpl(em);
            if (clientDAO.transferOfFundsFromOneAccountToAnother(billFrom, billTo, transaction))
                return true;
        }
        return false;
    }

    @Override
    public boolean transferOfFundsWithinOneCustomer(Integer bankAccountFrom, Integer bankAccountTo, Float countMomey) {
        BillsDAO billsDAO = new BillsDAOImpl(em);

        Bills billFrom = billsDAO.getBill(bankAccountFrom);
        Coin coinFrom = billFrom.getNameCurrency();
        Bills billTo = billsDAO.getBill(bankAccountTo);
        Coin coinTo = billTo.getNameCurrency();
        ServiceExchangeRatesDAO ratesDAO = new ServiceExchangeRatesDAOImpl(em);
        ExchangeRates ratesFrom = ratesDAO.getExchangeLast(coinFrom);
        ExchangeRates ratesTo = ratesDAO.getExchangeLast(coinTo);


        if (billFrom.getCountMomey() > countMomey) {
            billFrom.setCountMomey(billFrom.getCountMomey() - countMomey);

            BigDecimal dFrom = new BigDecimal(String.valueOf(ratesFrom.getExchange()));
            BigDecimal dTo = new BigDecimal(String.valueOf(ratesTo.getExchange()));

            BigDecimal tmp = dFrom.divide(dTo, 20, RoundingMode.CEILING);
            tmp = tmp.multiply(new BigDecimal(String.valueOf(countMomey)));
            tmp = tmp.divide(new BigDecimal(1), 2, RoundingMode.CEILING);
            Float sum = Float.valueOf(String.valueOf(tmp));
            System.out.println(sum);
            Transaction transaction = new Transaction(
                    new Date(), "currency conversion within a single user account. Account From " + billFrom.getNumberAccountToBank() +
                    " account to " + billTo.getNumberAccountToBank() + " count " + countMomey, countMomey, billFrom.getClient());
            billFrom.getClient().addTransaction(transaction);
            billTo.getClient().addTransaction(transaction);
            billTo.setCountMomey(billTo.getCountMomey() + sum);
            ClientDAO clientDAO = new ClientDAOImpl(em);
            if (clientDAO.currencyConversionWithinASingleUserAccount(billFrom, billTo, transaction))
                return true;
        }
        return false;
    }

    @Override
    public Float getTotalAmountOfMoneyFromAllInUAN(Client client) {
        ServiceExchangeRatesDAO ratesDAO = new ServiceExchangeRatesDAOImpl(em);
        ExchangeRates ratesEUR= ratesDAO.getExchangeLast(Coin.EUR);
        ExchangeRates ratesUSD = ratesDAO.getExchangeLast(Coin.USD);
        Float sum = 0f;
        for (Bills b : client.getBillsList()) {
            Float f = b.getCountMomey();
            if (b.getNameCurrency().equals(Coin.EUR))
                sum += (f * ratesEUR.getExchange());
            if (b.getNameCurrency().equals(Coin.USD))
                sum += (f * ratesUSD.getExchange());
            if (b.getNameCurrency().equals(Coin.UAN))
                sum +=f;
        }
        return sum;
    }
}
