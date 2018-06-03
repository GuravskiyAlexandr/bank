package dao;/*
 * Created by Alexsandr        30.05.2018
 */

import entity.Transaction;

public interface TransactionDAO {
    boolean add(Transaction transaction);
}
