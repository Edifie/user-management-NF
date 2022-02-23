package teste.servicos;

import teste.domain.User;
import teste.domain.UserSession;
import teste.domain.dao.DaoFactory;
import teste.servicepack.security.logic.Permission.Transaction;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class LoginService {

    User user = null;

    @Transaction

    public User checkLogin (String username, String password, UserSession session) throws ServletException, IOException{

        List<User> users = DaoFactory.createUserDao().createCriteria().list();

        for (User value : users){
            if (value.getUsername().equals(username) && value.getPassword().equals(password)){
                System.out.println(session.getCookie());
                user = value;
                session.setUser(value);
                return user;
            }
        }
        return null;
    }

    @Transaction
    public String returnRole(){
        return user.getRoles();
    }
}
