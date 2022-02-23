package teste.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import teste.pluginSession.*;

public class SecurityFilter implements Filter {

    @Override
    public void init (FilterConfig filterConfig) throws ServletException{

    }

    @Override
    public void doFilter (ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        openSession (servletRequest,filterChain, response);
    }

    private void openSession(ServletRequest servletRequest, FilterChain filterChain, HttpServletResponse response) throws IOException, ServletException{
        RegisterSession registerSession = null;

        try {
            registerSession = (RegisterSession) PluginFactory.getPlugin(RegisterSession.class);
            registerSession.openSession(servletRequest,filterChain,response);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }

    }


    @Override
    public void destroy() {

    }
}
