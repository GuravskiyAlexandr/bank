package dao;/*
 * Created by Alexsandr        30.05.2018
 */

import entity.ExchangeRates;
import enums.Coin;

public interface ExchangeRatesDAO {
    boolean add(ExchangeRates rates);

    ExchangeRates getExchangeLast(Coin coin);
}
