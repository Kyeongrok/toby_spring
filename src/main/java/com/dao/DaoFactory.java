package com.dao;

public class DaoFactory {
    private ConnectionMaker connectionMaker(){
        BinaryConnectionMaker binaryConnectionMaker =  new BinaryConnectionMaker();
        return binaryConnectionMaker;
    }
    public UserDao userDao(){
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }
}
