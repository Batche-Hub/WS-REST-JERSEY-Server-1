/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.jaxrs.dao;
import fr.doranco.jaxrs.jersey.entity.Employe;
import java.util.List;

/**
 *
 * @author Boule
 */
public interface IEmployeDAO {    
    public Employe getEmployeById(int id) throws Exception;
    public Employe addEmploye(Employe employe) throws Exception;
    public void updateEmploye(Employe employe) throws Exception;
    public void removeEmploye(Integer id) throws Exception;
    public List<Employe> getListeEmploye() throws Exception;
}
