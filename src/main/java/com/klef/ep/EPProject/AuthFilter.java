package com.klef.ep.EPProject;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        // Initialization code if necessary
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String contextPath = req.getContextPath();
        String requestURI = req.getRequestURI();

        String loginURI = contextPath + "/login.jsf";
        String adminLoginURI = contextPath + "/adminlogin.jsf";
        String facultyLoginURI = contextPath + "/facultylogin.jsf";
        String studentLoginURI = contextPath + "/studentlogin.jsf";
        String aboutURI = contextPath + "/about.jsf";
        String homeURI = contextPath + "/index.jsf";
        String homeURI1 = contextPath +"/" ;

        boolean loggedIn = (session != null && (session.getAttribute("admin") != null
                || session.getAttribute("student") != null
                || session.getAttribute("faculty") != null));

        boolean loginRequest = requestURI.equals(loginURI)
                || requestURI.equals(adminLoginURI)
                || requestURI.equals(facultyLoginURI)
                || requestURI.equals(studentLoginURI)
                || requestURI.equals(aboutURI)
                || requestURI.equals(homeURI)
                || requestURI.equals(homeURI1);

        // Debugging information
        System.out.println("Request URI: " + requestURI);
        System.out.println("Context Path: " + contextPath);
        System.out.println("Login URI: " + loginURI);
        System.out.println("Admin Login URI: " + adminLoginURI);
        System.out.println("Faculty Login URI: " + facultyLoginURI);
        System.out.println("Student Login URI: " + studentLoginURI);
        System.out.println("About URI: " + aboutURI);
        System.out.println("Home URI: " + homeURI);
        System.out.println("Home URI 1: " + homeURI1);
        System.out.println("Logged In: " + loggedIn);
        System.out.println("Login Request: " + loginRequest);

        if (loggedIn || loginRequest) {
            System.out.println("User is logged in or requesting a login page, continuing filter chain.");
            chain.doFilter(request, response);
        } else {
            System.out.println("User is not logged in, redirecting to unauthorized access page.");
            res.sendRedirect(contextPath + "/unauthorizedaccess.html");
        }
    }

    @Override
    public void destroy() {
        // Cleanup code if necessary
    }
}
