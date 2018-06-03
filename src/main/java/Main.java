import entity.Client;
import enums.Coin;
import org.junit.After;
import org.junit.Before;
import service.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {
    private static EntityManagerFactory emFactory;
    protected static EntityManager em;

    @Before
    public static void init() {
        emFactory = Persistence.createEntityManagerFactory("JPATest");
        em = emFactory.createEntityManager();
    }

    @After
    public static void finish() {
        em.close();
        emFactory.close();
    }

    public static void main(String[] args) {
        init();
        ServiceClientDAO dao = new ServiceClientDAOImpl(em);

        try {
            ServiceExchangeRatesDAO ratesDAO = new ServiceExchangeRatesDAOImpl(em);
            ratesDAO.addExchangeRates(new Date(), Coin.EUR, 1, 30.20F);
            ratesDAO.addExchangeRates(new Date(), Coin.USD, 1, 26.20F);
            ratesDAO.addExchangeRates(new Date(), Coin.EUR, 1, 30.20F);
            ratesDAO.addExchangeRates(new Date(), Coin.USD, 1, 26.20F);
            ratesDAO.addExchangeRates(new Date(), Coin.EUR, 1, 30.20F);
            ratesDAO.addExchangeRates(new Date(), Coin.USD, 1, 26.20F);
            ratesDAO.addExchangeRates(new Date(), Coin.EUR, 1, 30.20F);
            ratesDAO.addExchangeRates(new Date(), Coin.USD, 1, 26.20F);
            ratesDAO.addExchangeRates(new Date(), Coin.EUR, 1, 30.20F);
            ratesDAO.addExchangeRates(new Date(), Coin.USD, 1, 26.20F);
            ratesDAO.addExchangeRates(new Date(), Coin.EUR, 1, 30.20F);
            ratesDAO.addExchangeRates(new Date(), Coin.USD, 1, 26.20F);




            dao.addClient("Stiv", "Hopcins");
            dao.addClient("Alexandr", "Macedonskiy");

            Client client = dao.getFirstClientByFirstId();
            dao.addBillToClient(111111, 200.10f, Coin.EUR, client);
            dao.addBillToClient(111112, 200.30f, Coin.USD, client);
            dao.addBillToClient(111113, 200.60f, Coin.UAN, client);

            Client client1 = dao.getClientById(14l);
            dao.addBillToClient(111121, 200.10f, Coin.EUR, client1);
            dao.addBillToClient(111122, 200.30f, Coin.USD, client1);
            dao.addBillToClient(111123, 200.60f, Coin.UAN, client1);

            dao.replenishAnAccount(111111, 100.50f, Coin.EUR);

            dao.transferOfFundsFromOneAccountToAnother(111123, 150.3f, 111113, Coin.UAN);


        } finally {
            em.close();
        }

        em = emFactory.createEntityManager();
        try {

            ServiceClientDAO dao1 = new ServiceClientDAOImpl(em);

            dao1.transferOfFundsWithinOneCustomer(111111, 111112, 20.70f);
            Client cl = dao1.getClientById(14l);
            System.out.printf("%.2f", dao1.getTotalAmountOfMoneyFromAllInUAN(cl));
            System.out.println();

        }finally {
            finish();
        }



    }
}
