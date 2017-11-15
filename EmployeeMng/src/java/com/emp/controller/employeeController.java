/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emp.controller;

import com.emp.daoImpl.employeeDaoImpl;
import com.emp.daoImpl.roleDaoImpl;
import com.emp.daoImpl.taskDaoImpl;
import com.emp.model.EmpEmployee;
import com.emp.model.EmpRole;
import com.emp.model.EmpTask;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Developer PC
 */
public class employeeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        JSONObject responseObj = new JSONObject();
        responseObj.put("message", false);

        try {
            String actionType = request.getParameter("actionType");
            switch (actionType) {

                case "addemployee":
                    String data = request.getParameter("data");
                    JSONParser jSONParser1 = new JSONParser();
                    JSONObject dataObject = (JSONObject) jSONParser1.parse(data);

                    String empid = (String) dataObject.get("empid");
                    String empname = (String) dataObject.get("empname");

                    System.out.println("taskid>>>> " + empid);

                    if (empid.equals("-1")) {
                        EmpEmployee employee = new EmpEmployee();
                        employee.setName(empname);
                        employee.setRole(0);

                        try {
                            new employeeDaoImpl().addEmployee(employee);

                            responseObj.put("message", true);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    } else {
                        EmpEmployee employee = new employeeDaoImpl().getEmployee(Integer.parseInt(empid));
                        employee.setName(empname);

                        try {
                            new employeeDaoImpl().updateEmployee(employee);

                            responseObj.put("message", true);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    break;
                case "viewemployee":

                    String data1 = request.getParameter("data");
                    JSONParser jSONParser2 = new JSONParser();
                    JSONObject dataObject2 = (JSONObject) jSONParser2.parse(data1);

                    String empid1 = (String) dataObject2.get("empid");
                    String empname1 = (String) dataObject2.get("empname");
                    try {
                        List<EmpEmployee> tempemployees = new employeeDaoImpl().viewEmployee();
                        JSONObject empDetailst = new JSONObject();
                        int a = 0;
                        for (EmpEmployee er : tempemployees) {

                            JSONObject temprDetails = new JSONObject();
                            temprDetails.put("EmployeeID", er.getEmployeeID());
                            temprDetails.put("Name", er.getName());
                            empDetailst.put(a, temprDetails);
                            a++;
                        }

                        responseObj.put("eDetails", empDetailst);
                        responseObj.put("message", true);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;

                case "getemployee":

                    String data2 = request.getParameter("data");
                    JSONParser jSONParser3 = new JSONParser();
                    JSONObject dataObject3 = (JSONObject) jSONParser3.parse(data2);

                    String empid3 = (String) dataObject3.get("empid");
                    //String empname3 = (String) dataObject3.get("empname");
                    try {
                        EmpEmployee tempemplist = new employeeDaoImpl().getEmployee(Integer.parseInt(empid3));
                        JSONObject empDetails = new JSONObject();
                        JSONObject empTaskDetails = new JSONObject();
                        int a = 0;

                        JSONObject temprDetails = new JSONObject();
                        temprDetails.put("EmployeeID", tempemplist.getEmployeeID());
                        temprDetails.put("Name", tempemplist.getName());

                        if (tempemplist.getRole() != -1) {
                            EmpRole empRole = new roleDaoImpl().getRole(tempemplist.getRole());
                            temprDetails.put("RoleID", empRole.getRoleid());
                            temprDetails.put("Role", empRole.getTitle());
                        } else {
                            temprDetails.put("RoleID", "1");
                            temprDetails.put("Role", "NONE");
                        }

                        int x = 0;
                        for (Iterator iter = tempemplist.getTasks().iterator(); iter.hasNext();) {
                            JSONObject tempTask = new JSONObject();
                            EmpTask empTask = (EmpTask) iter.next();

                            tempTask.put("TaskID", empTask.getTaskID());
                            tempTask.put("Description", empTask.getDescription());

                            empTaskDetails.put(x, tempTask);
                            x++;
                        }

                        temprDetails.put("Tasks", empTaskDetails);

                        empDetails.put(a, temprDetails);
                        a++;

                        responseObj.put("eDetails", empDetails);
                        responseObj.put("message", true);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;

                case "assignRole":
                    String data4 = request.getParameter("data");
                    JSONParser jSONParser4 = new JSONParser();
                    JSONObject dataObject4 = (JSONObject) jSONParser4.parse(data4);

                    String empid4 = (String) dataObject4.get("empid");
                    String rolelist = (String) dataObject4.get("rolelist");

                    System.out.println("taskid>>>> " + empid4);

                    EmpEmployee employee = new employeeDaoImpl().getEmployee(Integer.parseInt(empid4));
                    EmpRole empRole = new roleDaoImpl().getRole(Integer.parseInt(rolelist));
                    employee.setRole(empRole.getRoleid());

                    try {
                        new employeeDaoImpl().updateEmployee(employee);

                        responseObj.put("message", true);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    break;

                case "assignTask":
                    String data5 = request.getParameter("data");
                    JSONParser jSONParser5 = new JSONParser();
                    JSONObject dataObject5 = (JSONObject) jSONParser5.parse(data5);

                    String orgtaskid = (String) dataObject5.get("orgtaskid");
                    String orgtaskdesc = (String) dataObject5.get("orgtaskdesc");
                    String empid5 = (String) dataObject5.get("empid");

                    System.out.println("taskid>>>> " + empid5);

                    try {
                        //EmpEmployee employee5 = new employeeDaoImpl().getEmployee(Integer.parseInt(empid5));
                        EmpTask empTask = new taskDaoImpl().viewTask(Integer.parseInt(orgtaskid));
                        empTask.setEmployee(Integer.parseInt(empid5));
                        int saved = new taskDaoImpl().updateTask(empTask);

                        if (saved == 0) {
                            responseObj.put("message", true);
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    break;
                case "removeRole":
                    String data6 = request.getParameter("data");
                    JSONParser jSONParser6 = new JSONParser();
                    JSONObject dataObject6 = (JSONObject) jSONParser6.parse(data6);

                    String empid6 = (String) dataObject6.get("empid");

                    System.out.println("taskid>>>> " + empid6);

                    EmpEmployee employee6 = new employeeDaoImpl().getEmployee(Integer.parseInt(empid6));

                    employee6.setRole(-1);

                    try {
                        new employeeDaoImpl().updateEmployee(employee6);

                        responseObj.put("message", true);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.getWriter().write(responseObj.toString());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
