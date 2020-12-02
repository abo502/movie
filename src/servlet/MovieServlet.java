package servlet;

import DBUtil.Db;
import bean.Movie;

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

public class MovieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("gbk");
        List<Movie> lists = new ArrayList<>();
        String btime = req.getParameter("btime");
        ResultSet resultSet;
        PreparedStatement preparedStatement;
        try {
            if (btime == null) {
                String sql = "select * from movie";
                 preparedStatement = Db.createPreparedStatement(sql);
                if (preparedStatement != null) {
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Movie movie = new Movie();
                        movie.setId(resultSet.getInt("id"));
                        movie.setName(resultSet.getString("name"));
                        movie.setBeginTime(Integer.valueOf(resultSet.getString("begin_time")));
                        movie.setPrice(Integer.valueOf(resultSet.getString("price")));
                        lists.add(movie);
                    }
                }
            } else {
                String sql = "select * from movie where begin_time > ?";
                preparedStatement = Db.createPreparedStatement(sql);
                if (preparedStatement != null) {
                    preparedStatement.setObject(1,btime);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Movie movie = new Movie();
                        movie.setId(resultSet.getInt("id"));
                        movie.setName(resultSet.getString("name"));
                        movie.setBeginTime(Integer.valueOf(resultSet.getString("begin_time")));
                        movie.setPrice(Integer.valueOf(resultSet.getString("price")));
                        lists.add(movie);
                    }
                }
            }
            Db.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        session.setAttribute("movies", lists);
        resp.sendRedirect("/movie.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
