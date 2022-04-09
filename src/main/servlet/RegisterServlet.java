package main.servlet;

import main.CURD.JDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("userName");
        System.out.println("================" + userName);
        String password = req.getParameter("password");
        System.out.println("================" + password);
        String email = req.getParameter("email");
        System.out.println("================" + email);

        if(JDBC.Retrieve_user(email)){
            System.out.println("注册失败");
            req.setAttribute("Msg", "注册失败，该邮箱已被注册！");
            super.processTemplate("index",req,resp);
        }else {
            JDBC.create_user(userName, password, email);
            System.out.println("注册成功");
            req.setAttribute("Msg", "注册成功！");
            super.processTemplate("index",req,resp);
        }
    }
}
