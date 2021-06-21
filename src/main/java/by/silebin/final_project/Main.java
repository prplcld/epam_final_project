package by.silebin.final_project;

import by.silebin.final_project.model.dao.UserDao;
import by.silebin.final_project.model.dao.impl.UserDaoImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = UserDaoImpl.getInstance();
        System.out.println(userDao.register("test", "test"));
    }

}
