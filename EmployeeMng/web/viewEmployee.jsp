<%-- 
    Document   : profile
    Created on : Oct 6, 2017, 9:47:46 AM
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
            function getemployeeList() {

                var dataObj = {};
                var dataString = JSON.stringify(dataObj);
                var xmlHttp = getAjaxObject();
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                    {

                        var reply = eval('(' + xmlHttp.responseText + ')');
                        var table = document.getElementById('viewEmpListDetials_tbl').getElementsByTagName('tbody')[0];
                        if (reply.message) {
                            var content = reply.eDetails;

                            console.log(content);

                            for (var key in content) {
                                if (content.hasOwnProperty(key)) {
                                    console.log(key + " -> " + content[key]);
                                    var empid = content[key].EmployeeID;
                                    var empname = content[key].Name;
                                    var row = document.createElement("tr");
                                    row.id = empid;
                                    row.value = empid;
                                    row.name = empid;
                                    row.innerHTML = "<td>" + empid + "</td>\n\
                                <td>" + empname + "</td>";



                                    table.appendChild(row);
                                }

                                var currentRow = table.rows[key];
                                var createClickHandler =
                                        function (row)
                                        {
                                            return function () {
                                                var empid = row.getElementsByTagName("td")[0].innerHTML;
                                                var empname = row.getElementsByTagName("td")[1].innerHTML;

                                                document.getElementById("empid").value = empid;
                                                document.getElementById("empname").value = empname;
                                            };
                                        };

                                currentRow.onclick = createClickHandler(currentRow);
                            }
                        }

                    }
                };
                xmlHttp.open("POST", "employeeController?actionType=viewemployee&data=" + dataString, true);
                xmlHttp.send();

            }
            function getemployee() {
                var empid = document.getElementById("empid").value;
                //var empname = document.getElementById("empname").value;

                var dataObj = {};
                dataObj["empid"] = empid;
                //dataObj["empname"] = empname;
                var dataString = JSON.stringify(dataObj);
                var xmlHttp = getAjaxObject();
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                    {

                        var reply = eval('(' + xmlHttp.responseText + ')');
                        var table = document.getElementById('emptasktble').getElementsByTagName('tbody')[0];
                        table.innerHTML = "";
                        if (reply.message) {
                            var content = reply.eDetails;

                            console.log(content);

                            for (var key in content) {
                                if (content.hasOwnProperty(key)) {
                                    console.log(key + " -> " + content[key]);
                                    var empid = content[key].EmployeeID;
                                    var empname = content[key].Name;
                                    var emprole = content[key].Role;
                                    var emproleID = content[key].RoleID;

                                    console.log(content[key].Tasks);
                                    var tasklist = content[key].Tasks;

                                    document.getElementById("vemployeeid").innerHTML = empid;
                                    document.getElementById("vemployeename").innerHTML = empname;
                                    document.getElementById("vemployeerole").innerHTML = emprole;
                                    document.getElementById("vemployeeroleID").innerHTML = emproleID;

                                    for (var key in tasklist) {
                                        var taskid = tasklist[key].TaskID;
                                        var taskdesc = tasklist[key].Description;
                                        var row = document.createElement("tr");
                                        row.id = taskid;
                                        row.value = taskid;
                                        row.name = taskid;
                                        row.innerHTML = "<td>" + taskid + "</td>\n\
                                <td>" + taskdesc + "</td>\n\
                                <td><button id=btnclose value=Remove onclick=remove(" + taskid + ")>Remove</button></td>";

                                        table.appendChild(row);


                                    }

                                }

                            }
                        }

                    }
                };
                xmlHttp.open("POST", "employeeController?actionType=getemployee&data=" + dataString, true);
                xmlHttp.send();

            }

            function remove(id) {

                var taskid = id.toString();

                var dataObj = {};
                dataObj["taskid"] = taskid;

                var dataString = JSON.stringify(dataObj);
                console.log(dataString);

                var xmlHttp = getAjaxObject();
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                    {

                        var reply = eval('(' + xmlHttp.responseText + ')');
                        if (reply.message) {
                            alert("Remove Successfully");
                            getemployee();
                            //location.reload();
                        }

                    }
                };
                xmlHttp.open("POST", "taskController?actionType=removetask&data=" + dataString, true);
                xmlHttp.send();
            }
            
            function removeRole(id) {

                var taskid = id.toString();

                var dataObj = {};
                dataObj["taskid"] = taskid;

                var dataString = JSON.stringify(dataObj);
                console.log(dataString);

                var xmlHttp = getAjaxObject();
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                    {

                        var reply = eval('(' + xmlHttp.responseText + ')');
                        if (reply.message) {
                            alert("Remove Successfully");
                            getemployee();
                            //location.reload();
                        }

                    }
                };
                xmlHttp.open("POST", "taskController?actionType=removetask&data=" + dataString, true);
                xmlHttp.send();
            }
            
            function removeRole(){
                
               var empid = document.getElementById("vemployeeid").innerHTML.toString();
               
                var dataObj = {};
                dataObj["empid"] = empid;

                var dataString = JSON.stringify(dataObj);
                console.log(dataString);

                var xmlHttp = getAjaxObject();
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                    {

                        var reply = eval('(' + xmlHttp.responseText + ')');
                        if (reply.message) {
                            alert("Remove Successfully");
                            getemployee();
                            //location.reload();
                        }

                    }
                };
                xmlHttp.open("POST", "employeeController?actionType=removeRole&data=" + dataString, true);
                xmlHttp.send();
            }
            
        </script>
    </head>
    <body onload="getemployeeList()">
        <div class="container-fluid" >
            <div class="panel panel-default">
                <div class="panel-heading" >
                    <h3 class="panel-title">Profile</h3>
                </div>
                <div class="panel-body" >
                    <div class="row">
                        <label for="empname" class="control-label col-xs-5 lbl_name">Employee ID : </label>
                        <input type="text" name="empid" class="inputtext" id="empid" placeholder="Employee ID goes here" required/>
                        <button class="button" name="btnaddrole" id="btnaddrole" onclick="getemployee()">Search</button>
                    </div>
                    <div class="row" style="padding-top: 5%;max-width: 60%">
                        <table class="table table-bordered table-hover table-striped" id="viewEmpDetials_tbl" >
                            <thead>
                                <tr>
                                </tr>
                            </thead>
                            <tbody>  
                                <tr>
                                    <th bgcolor="#EFEFEF">Employee ID</th>
                                    <td id="vemployeeid">id</td>
                                </tr>
                                <tr>
                                    <th bgcolor="#EFEFEF">Employee Name</th>
                                    <td id="vemployeename">name</td>
                                </tr>
                                <tr>
                                    <th bgcolor="#EFEFEF">Employee Role</th>
                                    <td id="vemployeeroleID" hidden>role</td>
                                    <td id="vemployeerole">role</td>
                                    <td id="rmvRole"><button onclick="removeRole()">Remove</button></td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="table table-bordered table-hover table-striped" id="emptasktble" >
                            <thead>
                            <th>Employee tasks
                            </th>
                            </thead>
                            <tbody>  
                            </tbody>
                        </table>
                    </div>
                    <br/><br/>
                    <div class="panel panel-default">
                        <div class="panel-heading" >
                            <h3 class="panel-title">Employee List</h3>
                        </div>
                        <div class="panel-body" >
                            <div class="row">
                                <table class="table table-bordered table-hover table-striped" id="viewEmpListDetials_tbl" >
                                    <thead>
                                        <tr>
                                            <th bgcolor="#EFEFEF">ID</th>
                                            <th bgcolor="#EFEFEF" >Name</th>
                                        </tr>
                                    </thead>
                                    <tbody>  
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
