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

                getemployee();
            }
            
            function addemployee() {
                var empid = document.getElementById("empid").value;
                var empname = document.getElementById("empname").value;
                var dataObj = {};
                dataObj["empid"] = empid;
                dataObj["empname"] = empname;

                var dataString = JSON.stringify(dataObj);
                console.log(dataString);

                var xmlHttp = getAjaxObject();
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                    {

                        var reply = eval('(' + xmlHttp.responseText + ')');
                        if (reply.message) {
                            location.reload();
                        }

                    }
                };
                xmlHttp.open("POST", "employeeController?actionType=addemployee&data=" + dataString, true);
                xmlHttp.send();

            }

            function getemployee() {

                var dataObj = {};
                var dataString = JSON.stringify(dataObj);
                var xmlHttp = getAjaxObject();
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                    {

                        var reply = eval('(' + xmlHttp.responseText + ')');
                        var table = document.getElementById('viewEmpDetials_tbl').getElementsByTagName('tbody')[0];
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
                            var content = reply.rDetails;
                            if (reply.message) {
                                $('#rolelist')
                                        .find('option')
                                        .remove()
                                        .end()
                                        .val('whatever')
                                        ;

                                var select = document.getElementById("rolelist");

                                console.log(reply.rDetails);
                                var opt = document.createElement('option');
                                opt.value = "NONE";
                                opt.innerHTML = "-Select Role-";
                                opt.disabled = true;
                                select.appendChild(opt);

                                for (var key in content) {

                                    if (content.hasOwnProperty(key)) {

                                        var opt = document.createElement('option');
                                        opt.value = content[key].Roleid;
                                        opt.innerHTML = content[key].Title;
                                        select.appendChild(opt);
                                    }

                                }

                            }
                        }
                    }


                };
                xmlHttp.open("POST", "roleController?actionType=viewrole&data=" + dataString, true);
                xmlHttp.send();

            }

        </script>
    </head>
    <body onload="me()">
        <div class="container-fluid" >

            <!-- Page Heading -->
            <div class="panel panel-default">
                <div class="panel-heading" >
                    <h3 class="panel-title">Employee details</h3>
                </div>
                <div class="row">
                    <label for="empname" class="control-label col-xs-5 lbl_name">Name : </label>
                    <input type="hidden" name="empid" class="inputtext" id="empid" value="-1"/>
                    <input type="text" name="empname" class="inputtext" id="empname" placeholder="name goes here" required/>
                    
                    <button class="button" name="btnaddrole" id="btnaddrole" onclick="addemployee()">Insert</button>
                </div>
            </div>
            <br/><br/>
            <div class="row">
                <table class="table table-bordered table-hover table-striped" id="viewEmpDetials_tbl" >
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
    </body>
</html>
