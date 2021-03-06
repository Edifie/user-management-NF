package teste.domain.dao;

import teste.domain.User;

public class UserDao extends AbstractDao<User>{
    @Override
    public Class obtainDomainClass() {
        return User.class;
    }

    private UserDao(){

    }

    private static UserDao instance = new UserDao();

    protected static UserDao getInstance(){
        return instance;
    }

}
