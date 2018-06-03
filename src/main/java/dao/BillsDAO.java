package dao;/*
 * Created by Alexsandr        01.06.2018
 */

import entity.Bills;
import entity.Client;
import enums.Coin;

public interface BillsDAO {

    Bills getBill(Integer bankAccount);
}
