/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.jaxrs.jersey.main;

import fr.doranco.jaxrs.dao.EmployeDAO;
import fr.doranco.jaxrs.jersey.entity.Employe;
import fr.doranco.jaxrs.jersey.server.EmployeWebResource;

/**
 *
 * @author Boule
 */
public class main {
    public static void main(String[] args) throws Exception {
        EmployeWebResource emplWeb = new EmployeWebResource();
//        EmployeDAO emplDAO = new EmployeDAO();
        Employe employe = new Employe("BADAD", "Chérif", "OUHAOU");

//
//        SyemplWeb.addEmploye(employe);
        System.err.println(emplWeb.addEmploye(employe));
        System.err.println(emplWeb.getEmployeAuFormatJsonString(employe.getId()));
        emplWeb.removeEmploye(10);
    }
}
