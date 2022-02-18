package teste.domain.dao;

import teste.domain.UserSession;

public class UserSessionDao extends AbstractDao<UserSession>{
    @Override
    public Class obtainDomainClass() {
        return null;
    }

    private UserSessionDao(){

    }

    private static UserSessionDao instance = new UserSessionDao();

    protected static UserSessionDao getInstance(){
        return instance;
    }
}
