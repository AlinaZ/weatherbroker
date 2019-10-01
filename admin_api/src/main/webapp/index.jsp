<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 22.08.2019
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Admin_API</h2>


<form action="admin" method="get">
    Введите название города для загрузки погоды в БД (например, ufa): <input type="text" name = "city" ><br/>
    Введите двухбуквенный код страны или штата (например, ru): <input type="text" name = "region" ><br/>
    <input type="submit" value="Submit">
</form>

</body>
</html>
