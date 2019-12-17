<%--
  Created by IntelliJ IDEA.
  User: I2V
  Date: 16-Dec-19
  Time: 8:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>This is a business logic server, : </h1>
  <table>
    <tr>
      <th>Provided services</th>
      <th>Input</th>
      <th>Output</th>
    </tr>
    <tr>
      <th>addRun</th>
      <th>username, distance, calories, start_time, end_time</th>
      <th>run_id</th>
    </tr>
    <tr>
      <th>getStats</th>
      <th>username, start_time, end_time</th>
      <th>average_ran_meters, average_burnt_calories</th>
    </tr>
  </table>
  </body>
</html>
