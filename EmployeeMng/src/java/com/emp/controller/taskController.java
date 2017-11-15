/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emp.controller;

import com.emp.daoImpl.taskDaoImpl;
import com.emp.model.EmpTask;
import java.io.IOException;
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
public class taskController extends HttpServlet {

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

                case "addtask":
                    String data = request.getParameter("data");
                    JSONParser jSONParser1 = new JSONParser();
                    JSONObject dataObject = (JSONObject) jSONParser1.parse(data);

                    String taskid = (String) dataObject.get("taskid");
                    String taskdesc = (String) dataObject.get("taskdesc");

                    System.out.println("taskid>>>> " + taskid);

                    if (taskid.equals("-1")) {
                        EmpTask task = new EmpTask();
                        task.setDescription(taskdesc);
                        task.setEmployee(-1);

                        try {
                            new taskDaoImpl().addTask(task);

                            responseObj.put("message", true);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    } else {
                        EmpTask task = new taskDaoImpl().viewTask(Integer.parseInt(taskid));
                        task.setDescription(taskdesc);

                        try {
                            new taskDaoImpl().updateTask(task);

                            responseObj.put("message", true);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    break;
                case "viewtask":

                    try {
                        List<EmpTask> temptask = new taskDaoImpl().viewTask();
                        JSONObject tDetails = new JSONObject();
                        int a = 0;
                        for (EmpTask er : temptask) {

                            JSONObject temprDetails = new JSONObject();
                            temprDetails.put("TaskID", er.getTaskID());
                            temprDetails.put("Description", er.getDescription());
                            tDetails.put(a, temprDetails);
                            a++;
                        }

                        responseObj.put("tDetails", tDetails);
                        responseObj.put("message", true);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "Loadtask":

                    String data3 = request.getParameter("data");
                    JSONParser jSONParser3 = new JSONParser();
                    JSONObject dataObject3 = (JSONObject) jSONParser3.parse(data3);

                    String taskdesc1 = (String) dataObject3.get("taskdesc");
                    try {
                        List<EmpTask> temptask = new taskDaoImpl().loadTask(taskdesc1);
                        JSONObject tDetails = new JSONObject();
                        int a = 0;
                        for (EmpTask er : temptask) {

                            JSONObject temprDetails = new JSONObject();
                            temprDetails.put("TaskID", er.getTaskID());
                            temprDetails.put("Description", er.getDescription());
                            tDetails.put(a, temprDetails);
                            a++;
                        }

                        responseObj.put("tDetails", tDetails);
                        responseObj.put("message", true);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;

                case "removetask":
                    String data4 = request.getParameter("data");
                    JSONParser jSONParser4 = new JSONParser();
                    JSONObject dataObject4 = (JSONObject) jSONParser4.parse(data4);

                    String taskid4 = (String) dataObject4.get("taskid");

                    System.out.println("taskid>>>> " + taskid4);

                    EmpTask task = new taskDaoImpl().viewTask(Integer.parseInt(taskid4));
                    task.setEmployee(-1);

                    try {
                        new taskDaoImpl().updateTask(task);

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
