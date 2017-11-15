<%-- 
    Document   : role
    Created on : Sep 28, 2017, 1:43:40 PM
    Author     : Developer PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="nav.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <title>Emp Manager</title>
        <script type="text/javascript">
            function me() {
                document.getElementsByName("navitem").class = "";
                document.getElementById("role").setAttribute("class", "active");

                getrole();
            }

            function addrole() {
                var roleid = document.getElementById("roleid").value;
                var roletitle = document.getElementById("roletitle").value;
                var dataObj = {};
                dataObj["roleid"] = roleid;
                dataObj["roletitle"] = roletitle;

                var dataString = JSON.stringify(dataObj);

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
                xmlHttp.open("POST", "roleController?actionType=addrole&data=" + dataString, true);
                xmlHttp.send();

            }

            function getrole() {

                var dataObj = {};
                var dataString = JSON.stringify(dataObj);
                var xmlHttp = getAjaxObject();
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                    {

                        var reply = eval('(' + xmlHttp.responseText + ')');
                        var table = document.getElementById('viewDetials_tbl').getElementsByTagName('tbody')[0];
                        if (reply.message) {
                            var content = reply.rDetails;

                            console.log(content);

                            for (var key in content) {
                                if (content.hasOwnProperty(key)) {
                                    console.log(key + " -> " + content[key]);
                                    var roleid = content[key].Roleid;
                                    var Title = content[key].Title;
                                    var row = document.createElement("tr");
                                    row.id = roleid;
                                    row.value = roleid;
                                    row.name = roleid;
                                    row.innerHTML = "<td>" + roleid + "</td>\n\
                                <td>" + Title + "</td>";



                                    table.appendChild(row);
                                }

                                var currentRow = table.rows[key];
                                var createClickHandler =
                                        function (row)
                                        {
                                            return function () {
                                                var roleid = row.getElementsByTagName("td")[0].innerHTML;
                                                var roletitle = row.getElementsByTagName("td")[1].innerHTML;
                                                
                                                document.getElementById("roleid").value = roleid;
                                                document.getElementById("roletitle").value = roletitle;
                                            };
                                        };

                                currentRow.onclick = createClickHandler(currentRow);
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
                    <h3 class="panel-title">Role details</h3>
                </div>
                <div class="row">
                    <label for="inputGarmentName" class="control-label col-xs-5 lbl_name">Role Title : </label>
                    <input type="hidden" name="roleid" class="inputtext" id="roleid"/>
                    <input type="text" name="roletitle" class="inputtext" id="roletitle" placeholder="role title goes here" required/>
                    <button class="button" name="btnaddrole" id="btnaddrole" onclick="addrole()">Insert</button>
                </div>
            </div>
            <br/><br/>
            <div class="row">
                <table class="table table-bordered table-hover table-striped" id="viewDetials_tbl" >
                    <thead>
                        <tr>
                            <th bgcolor="#EFEFEF">ID</th>
                            <th bgcolor="#EFEFEF" >Role Title</th>
                        </tr>
                    </thead>
                    <tbody>  
                    </tbody>
                </table>
            </div>

        </div>
    </body>
</html>
