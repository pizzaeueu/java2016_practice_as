package com.inquirer.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        String uri = request.getRequestURI();

        if ((session!=null && session.getAttribute("user")!=null)||uri.endsWith("login")||uri.matches(".*[css|jpg|png|gif|js]")){
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    public void destroy() {

    }
}
