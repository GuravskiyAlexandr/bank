package service;/*
 * Created by Alexsandr        30.05.2018
 */

import entity.ExchangeRates;
import enums.Coin;

import java.util.Date;

public interface ServiceExchangeRatesDAO {
    boolean addExchangeRates(Date date, Coin currency, Integer countUAN, Float exchange);
    ExchangeRates getExchangeLast(Coin coin);
}
