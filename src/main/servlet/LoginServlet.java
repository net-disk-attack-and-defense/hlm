package main.servlet;

import main.CURD.JDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends ViewBaseServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String password = req.getParameter("password");
        System.out.println("================" + password);
        String email = req.getParameter("email");
        System.out.println("================" + email);
        String name = JDBC.Retrieve_user(email, password);
        if(name!=null){
            System.out.println("登录成功");
            req.setAttribute("Msg", name + "!");
            super.processTemplate("welcome",req,resp);
        }else {
            System.out.println("登录失败");
            req.setAttribute("Msg", "登录失败，请检查邮箱及密码是否正确！");
            super.processTemplate("index",req,resp);
        }
    }
}
