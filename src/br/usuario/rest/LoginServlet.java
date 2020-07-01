package br.usuario.rest;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            HttpSession session = ((HttpServletRequest) request).getSession();
            String usuario = null;
            if (session != null ){
                usuario = (String) session.getAttribute("login");
            }
                PasswordUtils pu = new PasswordUtils();
                String senha = pu.convertPassword(request.getParameter("senha"));
                System.out.println(senha);

                Conexao conec = new Conexao();
                Connection conexao = conec.abrirConexao();

                ValidaUsuario vd = new ValidaUsuario(conexao);
                boolean validado = vd.validaUsuario(request.getParameter("usuario"), senha);

            if (validado) {
                HttpSession sessao = request.getSession();
                sessao.setAttribute("login", request.getParameter("usuario"));
                response.sendRedirect("/Session/principal/principal.html");
            } else {
                response.sendRedirect("index.html");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}

