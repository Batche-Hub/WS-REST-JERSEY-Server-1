/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.jaxrs.jersey.server;

import fr.doranco.jaxrs.jersey.entity.Employe;
import java.net.URISyntaxException;
import java.util.List;
import javax.ws.rs.core.Response;

/**
 *
 * @author Boule
 */
public interface IEmployeWebResource {
    
    public String getInfo();
    
    public String getEmployeAuFormatXmlString(Integer id);
    
    public String getEmployeAuFormatJsonString (Integer id);
    
    public Response getEmployeJSONtoResponse(Integer id);
    
    public List<Employe> getEmployesByList();
    
    public Response addEmploye(Employe employe) throws URISyntaxException ;
    
    public Response updateEmploye(Employe employe) throws URISyntaxException ;
    
    public Response removeEmploye(Integer id) throws URISyntaxException ;
    
    
}
