package teste.web;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import teste.domain.User;
import teste.servicos.LoginService;
import teste.utils.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends AbstractServlet
{
    private static final Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LoginService loginService = new LoginService();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        logger.info("Username: " + username);
        logger.debug("Password" + password);

        User user = loginService.checkLogin(username,password,null);

        if (user!= null){
            String roles = loginService.returnRole();
            logger.info("Username: " + username);

            if (roles !=null){
                HttpSession session = req.getSession();

                req.setAttribute("userLoggedIn", user);
                session.setAttribute("username", username);
                session.setAttribute("roles",roles);

                String encodedURL = resp.encodeRedirectUrl("home.jsp");
                resp.sendRedirect(encodedURL);
            }else {
                HttpSession session = req.getSession();

                req.setAttribute("userLoggedIn", user);
                session.setAttribute("username", username);
                session.setAttribute("roles", "normal");

                String encodedURL = resp.encodeRedirectUrl("home.jsp");
                resp.sendRedirect(encodedURL);
            }
        }else {
            String encodedURL = resp.encodeRedirectUrl("http://localhost:8080/TrabalhoES/login.jsp?wrong_password");
            resp.sendRedirect(encodedURL);
        }
    }

    @Override
    protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}