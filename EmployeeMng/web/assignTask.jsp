<%-- 
    Document   : employee
    Created on : Sep 28, 2017, 2:35:30 PM
    Author     : Developer PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="nav.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <title>Emp Manager</title>
        <script>
            function me() {
                document.getElementsByName("navitem").class = "";
                document.getElementById("emp").setAttribute("class", "active");



                getemployeecombo();
            }

            function gettask() {

                var taskdesc = document.getElementById("taskdesc").value;
                var dataObj = {};

                dataObj["taskdesc"] = taskdesc;

                var dataString = JSON.stringify(dataObj);
                console.log(dataString);

                var xmlHttp = getAjaxObject();
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                    {

                        var reply = eval('(' + xmlHttp.responseText + ')');
                        var table = document.getElementById('viewtaskDetials_tbl').getElementsByTagName('tbody')[0];
                        table.innerHTML = "";

                        if (reply.message) {
                            var content = reply.tDetails;

                            console.log(content);

                            for (var key in content) {
                                if (content.hasOwnProperty(key)) {
                                    console.log(key + " -> " + content[key]);
                                    var taskid = content[key].TaskID;
                                    var taskdesc = content[key].Description;
                                    var input = document.createElement('INPUT');
                                    input.type = 'checkbox';
                                    var row = document.createElement("tr");
                                    row.id = taskid;
                                    row.value = taskid;
                                    row.name = taskid;
                                    row.innerHTML = "<td>" + taskid + "</td>\n\
                                <td>" + taskdesc + "</td>";

                                    table.appendChild(row);
                                }

                                var currentRow = table.rows[key];
                                var createClickHandler =
                                        function (row)
                                        {
                                            return function () {
                                                var taskid = row.getElementsByTagName("td")[0].innerHTML;
                                                var taskname = row.getElementsByTagName("td")[1].innerHTML;

                                                document.getElementById("orgtaskid").value = taskid;
                                                document.getElementById("orgtaskdesc").value = taskname;
                                            };
                                        };

                                currentRow.onclick = createClickHandler(currentRow);

                            }
                        }

                    }
                };
                xmlHttp.open("POST", "taskController?actionType=Loadtask&data=" + dataString, true);
                xmlHttp.send();

            }

            function getemployeecombo() {

                var dataObj = {};
                var dataString = JSON.stringify(dataObj);
                var xmlHttp = getAjaxObject();
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                    {

                        var reply = eval('(' + xmlHttp.responseText + ')');
                        if (reply.message) {
                            var content = reply.eDetails;
                            if (reply.message) {
                                $('#emplist')
                                        .find('option')
                                        .remove()
                                        .end()
                                        .val('whatever')
                                        ;

                                var select = document.getElementById("emplist");

                                console.log(reply.eDetails);
                                var opt = document.createElement('option');
                                opt.value = "NONE";
                                opt.innerHTML = "-Select Employee-";
                                opt.disabled = true;
                                select.appendChild(opt);

                                for (var key in content) {

                                    if (content.hasOwnProperty(key)) {

                                        var opt = document.createElement('option');
                                        opt.value = content[key].EmployeeID;
                                        opt.innerHTML = content[key].Name;
                                        select.appendChild(opt);
                                    }

                                }

                            }
                        }
                    }


                };
                xmlHttp.open("POST", "employeeController?actionType=viewemployee&data=" + dataString, true);
                xmlHttp.send();

            }
            
            function assignTask() {
                var orgtaskid = document.getElementById("orgtaskid").value;
                var orgtaskdesc = document.getElementById("orgtaskdesc").value;
                var empid = document.getElementById("emplist").value;
                var dataObj = {};
                dataObj["orgtaskid"] = orgtaskid;
                dataObj["orgtaskdesc"] = orgtaskdesc;
                dataObj["empid"] = empid;

                var dataString = JSON.stringify(dataObj);
                console.log(dataString);

                var xmlHttp = getAjaxObject();
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                    {

                        var reply = eval('(' + xmlHttp.responseText + ')');
                        console.log(reply.message);
                        if (reply.message) {
                            alert("Assign Successfully");
                            //location.reload();
                        }

                    }
                };
                xmlHttp.open("POST", "employeeController?actionType=assignTask&data=" + dataString, true);
                xmlHttp.send();

            }
            
            
        </script>
    </head>
    <body onload="me()">
        <div class="container-fluid" >

            <!-- Page Heading -->
            <div class="panel panel-default">
                <div class="panel-heading" >
                    <h3 class="panel-title">Assign Task</h3>
                </div>
                <div class="row">
                    <label for="rolelist" class="control-label col-xs-5 lbl_name">Task : </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="text" name="taskdesc" class="inputtext" id="taskdesc" placeholder="Task description goes here" required/>

                    <button class="button" name="btnserchtask" id="btnserchtask" onclick="gettask()">Search</button>
                </div>
                <div class="row">
                    <table class="table table-bordered table-hover table-striped" id="viewtaskDetials_tbl" >
                        <thead>
                            <tr>
                                <th bgcolor="#EFEFEF">ID</th>
                                <th bgcolor="#EFEFEF" >Task</th>
                            </tr>
                        </thead>
                        <tbody>  
                        </tbody>
                    </table>
                </div>
            </div>
            <br/><br/><br/><br/>
            <div class="panel panel-default">
                <div class="panel-heading" >
                    <h3 class="panel-title">Task Details</h3>
                </div>
                <form class="">
                    <div class="row">
                        <label for="rolelist" class="control-label col-xs-5 lbl_name">Task : </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="hidden" name="orgtaskid" class="inputtext" id="orgtaskid"/>
                        <input type="text" name="orgtaskdesc" class="inputtext" id="orgtaskdesc" required/>
                    </div>
                    <div class="row">
                        <label for="emplist" class="control-label col-xs-5 lbl_name">Employee : </label>&nbsp;&nbsp;&nbsp;&nbsp;
                        <select class="inputtext" id="emplist" name="emplist">
                        </select>
                        <button class="button" name="btnserchtask" id="btnserchtask" onclick="assignTask()">Assign</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
