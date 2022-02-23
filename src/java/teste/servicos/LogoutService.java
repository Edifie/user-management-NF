package teste.servicos;

import teste.domain.UserSession;
import teste.servicepack.security.logic.Permission.IsAuthenticated;
import teste.servicepack.security.logic.Permission.Transaction;

public class LogoutService {

    @IsAuthenticated
    @Transaction
    public void logout (UserSession session){
        session.setUser(null);
    }
}
