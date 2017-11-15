<%-- 
    Document   : tasks
    Created on : Sep 28, 2017, 2:34:53 PM
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
                document.getElementById("task").setAttribute("class", "active");
                
                gettask();
            }
            
            function addtask() {
                var taskid = document.getElementById("taskid").value;
                var taskdesc = document.getElementById("taskdesc").value;
                var dataObj = {};
                dataObj["taskid"] = taskid;
                dataObj["taskdesc"] = taskdesc;

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
                xmlHttp.open("POST", "taskController?actionType=addtask&data=" + dataString, true);
                xmlHttp.send();

            }

            function gettask() {

                var dataObj = {};
                var dataString = JSON.stringify(dataObj);
                var xmlHttp = getAjaxObject();
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                    {

                        var reply = eval('(' + xmlHttp.responseText + ')');
                        var table = document.getElementById('viewtaskDetials_tbl').getElementsByTagName('tbody')[0];
                        if (reply.message) {
                            var content = reply.tDetails;

                            console.log(content);

                            for (var key in content) {
                                if (content.hasOwnProperty(key)) {
                                    console.log(key + " -> " + content[key]);
                                    var taskid = content[key].TaskID;
                                    var taskdesc = content[key].Description;
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
                                                var taskdesc = row.getElementsByTagName("td")[1].innerHTML;
                                                
                                                document.getElementById("taskid").value = taskid;
                                                document.getElementById("taskdesc").value = taskdesc;
                                            };
                                        };

                                currentRow.onclick = createClickHandler(currentRow);
                            }
                        }

                    }
                };
                xmlHttp.open("POST", "taskController?actionType=viewtask&data=" + dataString, true);
                xmlHttp.send();

            }
        </script>
    </head>
    <body onload="me()">
       <div class="container-fluid" >

            <!-- Page Heading -->
            <div class="panel panel-default">
                <div class="panel-heading" >
                    <h3 class="panel-title">Task details</h3>
                </div>
                <div class="row">
                    <label for="inputGarmentName" class="control-label col-xs-5 lbl_name">Task description : </label>
                    <input type="hidden" name="taskid" class="inputtext" id="taskid" value="-1"/>
                    <input type="text" name="taskdesc" class="inputtext" id="taskdesc" placeholder="Task description goes here" required/>
                    <button class="button" name="btnaddtask" id="btnaddtask" onclick="addtask()">Insert</button>
                </div>
            </div>
            <br/><br/>
            <div class="row">
                <table class="table table-bordered table-hover table-striped" id="viewtaskDetials_tbl" >
                    <thead>
                        <tr>
                            <th bgcolor="#EFEFEF">ID</th>
                            <th bgcolor="#EFEFEF" >Task Description</th>
                        </tr>
                    </thead>
                    <tbody>  
                    </tbody>
                </table>
            </div>

        </div>
    </body>
</html>
