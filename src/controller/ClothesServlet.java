package controller;

import DBUtil.Db;
import entity.Clothes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClothesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("gbk");
        List<Clothes> lists = new ArrayList<>();
        String num = req.getParameter("num");
        ResultSet resultSet;
        PreparedStatement preparedStatement;
        try {
            if (num == null) {
                String sql = "select * from stock";
                 preparedStatement = Db.createPreparedStatement(sql);
                if (preparedStatement != null) {
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Clothes clothes = new Clothes();
                        clothes.setId(resultSet.getInt("id"));
                        clothes.setBrand(resultSet.getString("brand"));
                        clothes.setNumber(Integer.valueOf(resultSet.getString("num")));
                        clothes.setPrice(Integer.valueOf(resultSet.getString("price")));
                        lists.add(clothes);
                    }
                }
            } else {
                String sql = "select * from stock where num > ?";
                preparedStatement = Db.createPreparedStatement(sql);
                if (preparedStatement != null) {
                    preparedStatement.setObject(1,num);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Clothes clothes = new Clothes();
                        clothes.setId(resultSet.getInt("id"));
                        clothes.setBrand(resultSet.getString("brand"));
                        clothes.setNumber(Integer.valueOf(resultSet.getString("num")));
                        clothes.setPrice(Integer.valueOf(resultSet.getString("price")));
                        lists.add(clothes);
                    }
                }
            }
            Db.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        session.setAttribute("clothes", lists);
        resp.sendRedirect("/stock.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
