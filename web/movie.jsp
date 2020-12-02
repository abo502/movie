<%@ page import="java.util.List" %>
<%@ page import="bean.Movie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>电影列表</title>
</head>
<body>
<div style="height:700px; width:700px; margin:0 auto;text-align: center;border: 2px solid; ">
    <h1 align="center">电影列表:</h1><br>
    <form action="/movie" method="get">
        搜索还未开始的电影：
        <input type="number" name="btime">&nbsp;<input type="submit" value="搜索">
        <%List<Movie> movies = (List<Movie>) session.getAttribute("movies");%>
        <table border="1px" style="margin-top: 80px;margin-left: 130px; width: 400px;height: 400px">
            <th>电影名称：</th>
            <th>电影开始时间：</th>
            <th>电影票价格：</th>
            <%
                for (Movie movie : movies) {
            %>
            <tr>
                <td><%=movie.getName()%>
                </td>
                <td><%=movie.getBeginTime()%>h
                </td>
                <td>￥<%=movie.getPrice()%>.00
                </td>
                <%}%>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
