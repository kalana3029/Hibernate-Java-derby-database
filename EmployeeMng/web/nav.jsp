<%-- 
    Document   : nav
    Created on : Sep 28, 2017, 1:51:42 PM
    Author     : Developer PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <script src='js/jquery.min.js'></script>
        <script src="js/my.js" type="text/javascript"></script>
        <title>Emp Manager</title>

    </head>
    <body>
        <!--<div id="nav" class="topnav">-->
        <!--<nav class="topnav">-->
        <!--        <ul class="topnav">
                    <li><a id="home" name="navitem" href="index.jsp" onclick="clickme(this)">Home</a>
                        <a id="role" name="navitem" href="role.jsp" onclick="clickme(this)">Roles</a>
                        <a id="task" name="navitem" href="tasks.jsp" onclick="clickme(this)">Tasks</a>
                        <a id="emp"  name="navitem" href="employee.jsp" onclick="clickme(this)">Employees</a></li>
                </ul>-->
        <ul>
            <!--<li><a id="home" name="navitem" href="index.jsp" onclick="clickme(this)">Home</a></li>-->
            <li class="dropdown">
                <a href="javascript:void(0)" class="dropbtn">Roles</a>
                <div class="dropdown-content">
                    <a id="role" name="navitem" href="role.jsp" onclick="clickme(this)">Insert Role</a>
                    <a id="empassrole"  name="navitem" href="assignRole.jsp" onclick="clickme(this)">Assign Role</a>
                </div>
            </li>
            <li class="dropdown">
                <a href="javascript:void(0)" class="dropbtn">Task</a>
                <div class="dropdown-content">
                    <a id="task" name="navitem" href="tasks.jsp" onclick="clickme(this)">Insert Tasks</a>
                    <a id="empassrole"  name="navitem" href="assignTask.jsp" onclick="clickme(this)">Assign Tasks</a>
                </div>
            </li>
            <li class="dropdown">
                <a href="javascript:void(0)" class="dropbtn">Employee</a>
                <div class="dropdown-content">
                    <a id="emp"  name="navitem" href="employee.jsp" onclick="clickme(this)">Insert Employee</a>
                    <a id="viewemp"  name="navitem" href="viewEmployee.jsp" onclick="clickme(this)">View Employee</a>
                </div>
            </li>
        </ul>
    </body>
</html>
