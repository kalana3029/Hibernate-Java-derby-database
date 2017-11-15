/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emp.controller;

import com.emp.daoImpl.roleDaoImpl;
import com.emp.model.EmpRole;
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
public class roleController extends HttpServlet {

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

                case "addrole":
                    String data = request.getParameter("data");
                    JSONParser jSONParser1 = new JSONParser();
                    JSONObject dataObject = (JSONObject) jSONParser1.parse(data);

                    String roleid = (String) dataObject.get("roleid");
                    String roletitle = (String) dataObject.get("roletitle");

                    if (roleid == null) {
                        EmpRole role = new EmpRole();
                        role.setTitle(roletitle);

                        try {
                            new roleDaoImpl().addRole(role);

                            responseObj.put("message", true);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        
                    }else{
                        EmpRole role = new EmpRole(Integer.parseInt(roleid));
                        role.setTitle(roletitle);
                        
                        try {
                            new roleDaoImpl().updateRole(role);

                            responseObj.put("message", true);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    break;
                case "viewrole":

                    try {
                        List<EmpRole> temprole = new roleDaoImpl().viewRole();
                        JSONObject rDetails = new JSONObject();
                        int a = 0;
                        for (EmpRole er : temprole) {

                            JSONObject temprDetails = new JSONObject();
                            temprDetails.put("Roleid", er.getRoleid());
                            temprDetails.put("Title", er.getTitle());
                            rDetails.put(a, temprDetails);
                            a++;
                        }

                        responseObj.put("rDetails", rDetails);
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
